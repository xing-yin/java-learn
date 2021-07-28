第24讲 | 有哪些方法可以在运行时动态生成一个Java类？
=====

本节将在源码层面介绍动态代理的实现技术，结合类加载的学习基础后，是时候该进行深入分析了。

### 问题
有哪些方法可以在运行时动态生成一个Java类?

## 典型回答
> 可以从常见的Java类来源分析，通常的开发过程是: 开发者编写Java代码，调用javac编译成class文件，然后通过类加载机制载入JVM，就成为应用运行时可以使用的Java类了。

从上面过程得到启发，其中一个直接的方式是从源码入手，**利用Java程序生成一段源码，然后保存到文件等**，下面就只需要解决编译问题了。

有一种笨办法，直接用ProcessBuilder之类启动javac进程，并指定上面生成的文件作为输入，进行编译。最后，再利用类加载器，在运行时加载即可。

前面的方法，本质上还是在当前程序进程之外编译的，那么还有没有不这么low的办法呢?

可以考虑使用Java Compiler API，这是JDK提供的标准API，里面提供了与javac对等的编译器功能，具体请参考[java.compiler](https://docs.oracle.com/javase/9/docs/api/javax/tools/package-summary.html)相关文档。

> 进一步思考，我们一直围绕Java源码编译成为JVM可以理解的字节码，换句话说，只要是符合JVM规范的字节码，不管它是如何生成的，是不是都可以被JVM加载呢?我们能不能直 接生成相应的字节码，然后交给类加载器去加载呢?

当然可以，不过直接去写字节码难度太大，通常我们可以利**用Java字节码操纵工具和类库来实现**，比如 ASM、Javassist、cglib等。

## 考点分析
虽然曾被视为黑魔法，但在当前复杂多变的开发环境中，在运行时动态生成逻辑并不是什么罕见的场景。重新审视我们谈到的动态代理，本质就是在特定的时机，去修改已有类型实现，或者创建新的类型。

明白了基本思路后，围绕类加载机制进行展开，面试过程中可能从技术原理或实践的角度考察:

- 字节码和类加载到底是怎么无缝进行转换的?发生在整个类加载过程的哪一步?

- 如何利用字节码操纵技术，实现基本的动态代理逻辑?

- 除了动态代理，字节码操纵技术还有那些应用场景?

## 知识扩展
#### 首先,理解一下，类从字节码到 ======> Class对象的转换
在类加载过程中，这一步是通过下面的方法提供的功能，或者defneClass的其他本地对等实现。

```
protected final Class<?> defineClass(String name, byte[] b, int off, int len,
                                 	ProtectionDomain protectionDomain)
protected final Class<?> defineClass(String name, java.nio.ByteBuffer b,
                                 	ProtectionDomain protectionDomain)
```

> 这里只选取了最基础的两个典型的defneClass实现，Java重载了几个不同的方法。 

可以看出，**只要能够生成出规范的字节码，不管是作为byte数组的形式，还是放到ByteBufer里，都可以完成字节码到Java对象的转换过程**。 

JDK提供的defneClass方法，最终都是本地代码实现的。

```
static native Class<?> defineClass1(ClassLoader loader, String name, byte[] b, int off, int len,
                                	ProtectionDomain pd, String source);

static native Class<?> defineClass2(ClassLoader loader, String name, java.nio.ByteBuffer b,
                                	int off, int len, ProtectionDomain pd,
                                	String source);
```

更进一步，我们来看看JDK dynamic proxy的实现代码。你会发现，对应逻辑是实现在ProxyBuilder这个静态内部类中，ProxyGenerator生成字节码，并以byte数组的形式保 存，然后通过调用Unsafe提供的defneClass入口。

```
byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
    	proxyName, interfaces.toArray(EMPTY_CLASS_ARRAY), accessFlags);
try {
	Class<?> pc = UNSAFE.defineClass(proxyName, proxyClassFile,
                                 	0, proxyClassFile.length,
      	                           loader, null);
	reverseProxyCache.sub(pc).putIfAbsent(loader, Boolean.TRUE);
	return pc;
} catch (ClassFormatError e) {
// 如果出现 ClassFormatError，很可能是输入参数有问题，比如，ProxyGenerator 有 bug
}
```


#### 接下来一起来看看相关的字节码操纵逻辑。
JDK内部动态代理的逻辑，可以参考`java.lang.refect.ProxyGenerator`的内部实现。

这可以认为是种另类的字节码操纵技术，其利用了DataOutputStrem提供的能力，配 合hard-coded的各种JVM指令实现方法，生成所需的字节码数组。你可以参考下面的示例代码。

```
private void codeLocalLoadStore(int lvar, int opcode, int opcode_0,
                            	DataOutputStream out)
	throws IOException
{
	assert lvar >= 0 && lvar <= 0xFFFF;
	// 根据变量数值，以不同格式，dump 操作码
    if (lvar <= 3) {
    	out.writeByte(opcode_0 + lvar);
	} else if (lvar <= 0xFF) {
    	out.writeByte(opcode);
    	out.writeByte(lvar & 0xFF);
	} else {
    	// 使用宽指令修饰符，如果变量索引不能用无符号 byte
    	out.writeByte(opc_wide);
    	out.writeByte(opcode);
    	out.writeShort(lvar & 0xFFFF);
	}
}
```

这种实现方式的好处是**没有太多依赖关系，简单实用，但是前提是你需要懂各种JVM指令，知道怎么处理那些偏移地址等，实际门槛非常高**，所以并不适合大多数的普通开发场景。

幸好，Java社区专家提供了各种从底层到更高抽象水平的字节码操作类库，不需要什么都自己从头做。JDK内部就集成了ASM类库，虽然并未作为公共API暴露出来，但是它广泛应用在，如java.lang.instrumentation API底层实现，或者Lambda Call Site生成的内部逻辑中，这如果有需要，可以参考类似LamdaForm的字节码生成逻辑:[java.lang.invoke.InvokerBytecodeGenerator](http://hg.openjdk.java.net/jdk/jdk/file/29169633327c/src/java.base/share/classes/java/lang/invoke/InvokerBytecodeGenerator.java)。

> 从实用的角度思考一下，实现一个简单的动态代理，都要做什么? 如何使用字节码操纵技术，走通这个过程呢? 

对于一个普通的Java动态代理，其实现过程可以简化成为:

- 提供一个**基础接口**，作为被调用类型(com.mycorp.HelloImpl)和代理类之间的统一入口，如com.mycorp.Hello。 

- **实现InvocationHandler**，对代理对象方法的调用，会被**分派到其invoke方法来真正实现动作**。 

- 通过Proxy类，调用其newProxyInstance方法，生成一个实现了相应基础接口的代理类实例，可以看下面的方法签名。

          public static Object newProxyInsance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h)
          
> 分析一下，动态代码生成是具体发生在什么阶段呢? 

没错！就是在newProxyInstance生成代理类实例的时候。选取了JDK自己采用的ASM作为示例，一起来看看用ASM实现的简要过程，请参考下面的示例代码片段。 

第一步，**生成对应的类**，和写Java代码很类似，只不过改为用ASM方法和指定参数，代替了我们书写的源码。

```
ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

cw.visit(V1_8,                      // 指定 Java 版本
    	ACC_PUBLIC,             	// 说明是 public 类型
        "com/mycorp/HelloProxy",	// 指定包和类的名称
    	null,                   	// 签名，null 表示不是泛型
    	"java/lang/Object",             	// 指定父类
    	new String[]{ "com/mycorp/Hello" }); // 指定需要实现的接口
```

第二步，可以**按照需要为代理对象实例，生成需要的方法和逻辑**。

```
MethodVisitor mv = cw.visitMethod(
    	ACC_PUBLIC,         	    // 声明公共方法
    	"sayHello",             	// 方法名称
    	"()Ljava/lang/Object;", 	// 描述符
    	null,                   	// 签名，null 表示不是泛型
    	null);                      // 可能抛出的异常，如果有，则指定字符串数组

mv.visitCode();
// 省略代码逻辑实现细节
cw.visitEnd();                      // 结束类字节码生成
```

上面的代码虽然有些晦涩，但总体还是能理解其用意，**不同的visitX方法提供了创建类型，创建各种方法等逻辑**。

> ASM API，广泛的使用了[Visitor](https://en.wikipedia.org/wiki/Visitor_pattern)模式，如果你熟悉这个模式， 就会知道它所针对的场景是将算法和对象结构解耦，非常适合字节码操纵的场合，因为大部分情况都是依赖于特定结构修改或者添加新的方法、变量或者类型等。

按照前面的分析，**字节码操作最后大都是生成byte数组**，ClassWriter提供了一个简便的方法。

     cw.toByteArray();
     
**然后，就可以进入我们熟知的类加载过程**，如果你对ASM的具体用法感兴趣，可以[参考这个教程](https://www.baeldung.com/java-asm)。 

#### 最后一个问题，字节码操纵技术，除了动态代理，还可以应用在什么地方? 

很多你现在正在使用的框架、工具就应用该技术，下面是几个常见领域。

* 各种Mock框架
* ORM框架
* IOC容器，部分Profler工具，或者运行时诊断工具等 
* 生成形式化代码的工具

甚至可以认为，字节码操纵技术是工具和基础框架必不可少的部分，大大减少了开发者的负担。

本节更加深入的类加载和字节码操作方面技术。为了理解底层的原理，我选取的例子是比较偏底层的、能力全面的类库，如果实际项目中需要进行基础的字节码操作，可以考虑使用更加高层次视角的类库，例如Byte Buddy等。

## 一课一练
假如我们有这样一个需求，需要添加某个功能，例如对某类型资源如网络通信的消耗进行统计，重点要求是，不开启时必须 是**零开销，而不是低开销**，可以利用我们今天谈到的或者相关的技术实现吗?

```
参考方案1:(Java 探针)【推荐】

可以考虑用javaagent+字节码处理拦截方法进行统计：对httpclient中的方法进行拦截，增加header或者转发等进行统计。开启和关闭只要增加一个javaagent启动参数就行

参考方案2:

将资源消耗的这个实例，用动态代理的方式创建这个实例动态代理对象，在动态代理的invoke中添加新的需求。开始使用代理对象，不开启则使用原来的方法，因为动态代理是在运行时创建。所以是零消耗。
```

===》抽空学习 java 探针使用
