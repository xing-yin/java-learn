# Java 基础
# 第1讲|谈谈你对Java平台的理解？

### 问题

谈谈你对Java平台的理解? “Java是解释执行”，这句话正确吗?

## 典型回答

我们日常会接触到 `JRE(Java Runtime Environment)` 或者 `JDK(Java Development Kit) `。 

- JRE，也就是**Java运行环境**，包含了JVM和Java类库，以及一些模块等。 
- JDK可以看作是JRE的一个超集，提供了更多工具，比如`编译器、各种诊断工具`等。

对于“Java是解释执行”这句话，这个说法不太准确。我们开发的Java的源代码，首先通过**Javac编译成为字节码**(bytecode)，然后，在运行时，通过 Java虚拟机(JVM)内嵌的**解释器将字节码转换成为最终的机器码**。但是常见的JVM，比如大多数情况使用的Oracle JDK提供的Hotspot JVM，都提供了**JIT(Just-In-Time)编译器**，也就是通常所说的 **及时编译器**，JIT能够在运行时**将热点代码编译成机器码**，这种情况下部分热点代码就属于编译执行，而不是解释执行了。

## 知识扩展

对于Java平台的理解，可以从很多方面简明扼要地谈一下，例如:

- Java语言特性，包括泛型、Lambda等语言特性

- 基础类库，包括**集合、IO/NIO、网络、并发、安全**等基础类库。对于我们**日常工作应用较多的类库，面试前可以系统化总结一下**，有助于临场发挥。

或者谈谈JVM的一些基础概念和机制，例如:

- Java的类加载机制，常用版本JDK(如JDK 8)内嵌的Class-Loader，例如Bootstrap、Extension 和 Application Class-loader
- 类加载大致过程:加载、验证、链接、初始化
- 自定义 Class-Loader
- 垃圾收集的基本原理，最常见的垃圾收集器，如SerialGC、Parallel GC、 CMS、 G1等，对于适用于什么样的工作负载最好也心里有数,这些都是可以扩展开的领域

当然还有JDK包含哪些工具或者Java领域内其他工具等，例如:

- 编译器、运行时环境、安全工具、诊断和监控工具等。这些基本工具是日常工作效率的保证，对于我们工作在其他语言平台上，同样有所帮助，很多都是触类旁通的。

下图是我总结的一个相对宽泛的蓝图供你参考。

<div align="center"> <img src="pics/1-1.png" width="500" style="zoom:100%"/> </div><br>
    
回到前面问到的**解释执行和编译执行**的问题。有些面试官喜欢在特定问题上“刨根问底儿”，因为这是进一步了解面试者对知识掌握程度的有效方法，我稍微深入探讨一下。

通常把Java分为**编译期和运行时**。这里说的Java的编译和C/C++是有着不同的意义的，Javac的编译，编译Java源码生成“.class”文件里面实际是字节码，而不是可以直接执行的机器码。Java通过**字节码和Java虚拟机(JVM)这种跨平台的抽象**，屏蔽了操作系统和硬件的细节，这也是实现“一次编译，到处执行”的基础。

在运行时，JVM会通过**类加载器(Class-Loader)加载字节码，解释或者编译执行**。就像我前面提到的，主流Java版本中，如JDK 8实际是解释和编译混合的一种模式，即所谓的**混合模式**(-Xmixed)。

Oracle Hotspot JVM内置了两个不同的JIT compiler：

- C1对应前面说的client模式，适用于对于启动速度敏感的应用，比如普通Java桌面应用;
- C2对应server模式，它的优化是为长时间运行的服务器端应用设计的。 

> 默认是采用所谓的分层编译(TieredCompilation)。

Java虚拟机启动时，可以**指定不同的参数对运行模式进行选择**。 比如:

- 指定“-Xint”，就是告诉JVM只进行解释执行，不对代码进行编译，这种模式抛弃了JIT可能带来的性能优势。 毕竟解释器(interpreter)是逐条读入，逐条解释运行的。

- 与其相对应的，还有一个“-Xcomp”参数，这是告诉JVM关闭解释器，不要进行解释执行，或者叫作**最大优化级别**。那你可能会问这种模式是不是最高效啊?简单说，还真未必。“-Xcomp”会导致JVM启动变慢非常多，同时有些JIT编译器优化方式，比如分支预测，如果不进行 profiling，往往并不能进行有效优化。

除了我们日常最常见的Java使用模式，其实还有一种**新的编译方式，即所谓的AOT**(Ahead-of-Time Compilation)，**直接将字节码编译成机器代码**，这样就避免了JIT预热等各方面的开销，比如Oracle JDK 9就引入了实验性的AOT特性，并且增加了新的jaotc工具。利用下面的命令把某个类或者某个模块编译成为AOT库。

	jaotc --output libHelloWorld.so HelloWorld.class 
	jaotc --output libjava.base.so --module java.base

然后，在启动时直接指定就可以了。

	java -XX:AOTLibrary=./libHelloWorld.so,./libjava.base.so HelloWorld
	
而且，Oracle JDK支持分层编译和AOT协作使用，这两者并不是二选一的关系。如果你有兴趣，可以[参考相关文档](http://openjdk.java.net/jeps/295)。AOT也不仅仅是只有这 一种方式，业界早就有第三方工具(如GCJ、Excelsior JET)提供相关功能。

另外，JVM作为一个强大的平台，不仅仅只有Java语言可以运行在JVM上，本质上**合规的字节码都可以运行**，Java语言自身也为此提供了便利，我们可以看到类似Clojure、Scala、Groovy、JRuby、Jpython等大量JVM语言，活跃在不同的场景。

