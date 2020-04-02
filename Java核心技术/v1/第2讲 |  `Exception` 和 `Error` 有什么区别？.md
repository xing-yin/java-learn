# 第2讲 |  `Exception` 和 `Error` 有什么区别？

### 问题
请对比 `Exception` 和 `Error` ，另外，运行时异常与一般异常有什么区别?

## 典型回答

`Exception` 和 `Error` 都是继承了 `Throwable` 类，在Java中只有 `Throwable` 类型的实例才可以被抛出(throw)或者捕获(catch)，它是异常处理机制的基本组成类型。 

- `Exception` 和 `Error` 体现了Java平台设计者对不同异常情况的分类。 `Exception` 是**程序正常运行中，可以预料的意外情况，可能并且应该被捕获，进行相应处理。**

- `Error` 是指在正常情况下，不大可能出现的情况，绝大部分的 `Error` 都会导致程序(比如JVM自身)处于**非正常的、不可恢复状态**。既然是非正常情况，所以不便于也不需要捕获，常 见的比如 `OutOfMemoryError` 之类，都是 `Error` 的子类。

`Exception` 又分为**可检查**(checked)异常和**不检查**(unchecked)异常。可检查异常在源代码里必须显式地进行捕获处理，这是编译期检查的一部分。

**不检查异常就是所谓的运行时异常**，类似  `NullPointerException` 、 `ArrayIndexOutOfBoundsException` 之类，通常是可以编码避免的逻辑错误，具体根据需要来判断是否需要捕获，并不会在编译期强制要求。

## 考点分析

### 如何处理好异常是比较考验功底的，我觉得需要掌握两个方面：

* 第一，**理解 `Throwable` 、 `Exception` 、 `Error` 的设计和分类**。比如，掌握那些应用最为广泛的子类，以及如何自定义异常等。

很多面试官会进一步追问一些细节，比如，你了解哪些 `Error` 、 `Exception` 或者Runtime `Exception` ? 我画了一个简单的类图，并列出来典型例子，可以给你作为参考。

<div align="center"> <img src="pics/2-1.png" width="500" style="zoom:100%"/> </div><br>
     
其中有些子类型，最好重点理解一下，**比如 `NoClassDefFoundError` 和 `ClassNotFoundException` 有什么区别？**，这也是个经典的入门题目。

首先从名字上可以看出一类是异常，一类属于错误。异常可以通过异常处理机制使程序继续运行，但出现错误必然找出程序崩溃。 

这个问题是和java 类的加载相关的。类加载的时候先要把编译好的类文件（.class ，jar包等）加载**JVM管理的方法区**当中，这个过程叫做加载，如果这个过程中没找类文件就会出现 `ClassNotFoundException`。如果加载成功之后，会有一个该类的类对象（class对象）。运行时，方法调用或new对象时，在内存当中没有找到这个类对象，就会出现 NoClassDefFoundError。

#### 1.ClassNotFoundException：

这类异常出现在对类进行加载时，该路径下找不到对应的class文件。有以下情况：

   1. 通过Class.forName()加载类。

   2. 通过类加载器ClassLoader加载loadClass()，或者findSystemClass()。

如下代码：

	package abs;
	public class Main {
	 
		public static void main(String arg[]) throws Exception {
			Class.forName("Main");
		}
	}
	
就会出现：

	Exception in thread "main" java.lang.ClassNotFoundException: Main
	at java.net.URLClassLoader$1.run(Unknown Source)
	at java.net.URLClassLoader$1.run(Unknown Source)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(Unknown Source)
	at java.lang.ClassLoader.loadClass(Unknown Source)
	at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source)
	at java.lang.ClassLoader.loadClass(Unknown Source)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Unknown Source)
	at abs.Main.main(Main.java:18)

这是因为在加载类的时候找不到 Mian.class 文件(在使用forName()加载类时需要加上包名)。将加载类的代码改成Class.forName("abs.Main")就不会发生异常了。

#### 2.NotClassDefFoundError:

这类错误发生在运行时，通常是编译通过，但当运行时**使用new参数该类对象时，找不到类的定义造成**，此异常通常是由于一个类中引用了另外的一个类，而**被引用的类没有被classLoader找到**。如在编译好Person类后删除person.class时再运行。

	public class Main {
		public static void main(String arg[]) throws Exception {
			Person p=new Person("xjp",20);
			System.out.println(p.getName());
		}
	}
	
就会出现

	Exception in thread "main" java.lang.NoClassDefFoundError: abs/Person

## 知识扩展

### 再来看看第二段代码

``` java 
try {
   // 业务代码
   // …
} catch (IOException e) {
    e.printStackTrace();
}
```

这段代码作为一段实验代码，它是没有任何问题的，但是在产品代码中，通常都不允许这样处理。你先思考一下这是为什么呢? 

我们先来看看printStackTrace()的文档，开头就是“Prints this  `Throwable`  and its backtrace to the **standard  Error stream**”。问题就在这里，在稍微复杂一点的生产系统中，
**标准出错(STERR)不是个合适的输出选项，因为你很难判断出到底输出到哪里去了**。

尤其是对于分布式系统，如果发生异常，但是无法找到**堆栈轨迹(stacktrace)**，这纯属是为诊断设置障碍。所以，**最好使用产品日志，详细地输出到日志系统里。**
   
### 看下面的代码段，体会一下Throw early, catch late原则

``` java
public void readPreferences(String fileName){
	 //...perform operations... 
	InputStream in = new FileInputStream(fileName);
	 //...read the preferences file...
}
```

如果fleName是null，那么程序就会抛出NullPointer `Exception` ，但是由于没有第一时间暴露出问题，堆栈信息可能非常令人费解，往往需要相对复杂的定位。这个NPE只是作为例子，实际产品代码中，可能是各种情况，比如获取配置失败之类的。**在发现问题的时候，第一时间抛出，能够更加清晰地反映问题。**

我们可以修改一下，**让问题“throw early”，对应的异常信息就非常直观了**。

``` java
public void readPreferences(String filename) {
	// throw early
	Objects. requireNonNull(filename);
	//...perform other operations... 
	InputStream in = new FileInputStream(filename);
	 //...read the preferences file...
}
```

至于“catch late”，其实是我们经常苦恼的问题，捕获异常后，需要怎么处理呢?最差的处理方式，就是我前面提到的“生吞异常”，本质上其实是掩盖问题。如果实在不知道如何处理，可以**选择保留原有异常的cause信息，直接再抛出或者构建新的异常抛出去。在更高层面，因为有了清晰的(业务)逻辑，往往会更清楚合适的处理方式是什么。**

有的时候，我们会根据需要自定义异常，这个时候除了保证提供足够的信息，还有两点需要考虑:

- 是否需要定义成 `Checked Exception` ，因为这种类型设计的初衷更是为了从异常情况恢复，作为异常设计者，我们往往有充足信息进行分类。

- **在保证诊断信息足够的同时，也要考虑避免包含敏感信息，**因为那样可能导致潜在的安全问题。

> 如果我们看Java的标准类库，你可能注意到类似 `java.net.ConnectException` ， 出错信息是类似“ Connection refused (Connection refused)”，而不包含具体的机器名、IP、端口等，一个重要考量就是信息安全。类似的情况在日志中也有，比如，用户数据一般是不可以输出到日志里面的。

业界有一种争论(甚至可以算是某种程度的共识)，Java语言的`Checked  Exception` 也许是个设计错误，反对者列举了几点:

- `Checked  Exception` 的假设是我们捕获了异常，然后恢复程序。但是，其实我们大多数情况下，根本就不可能恢复。`Checked  Exception` 的使用，已经大大偏离了最初的设计目的。

- `Checked  Exception` 不兼容functional编程，如果你写过Lambda/Stream代码，相信深有体会。

> 很多开源项目，已经采纳了这种实践，比如Spring、Hibernate等，甚至反映在新的编程语言设计中，比如Scala等。 如果有兴趣，你可以[参考](http://literatejava.com/exceptions/checked-exceptions-javas-biggest-mistake)

> 当然，很多人也觉得没有必要矫枉过正，因为确实有一些异常，比如和环境相关的IO、网络等，其实是存在可恢复性的，而且Java已经通过业界的海量实践，证明了其构建高质量软 件的能力。我就不再进一步解读了，感兴趣的同学可以[点击链接](https://v.qq.com/x/page/d0635rf5x0o.html)，观看Bruce Eckel在2018年全球软件开发大会QCon的分享Failing at Failing: How and Why We've Been Nonchalantly Moving Away From Exception Handling。

- 我们从性能角度来审视一下Java的异常处理机制，这里有两个可能会相对昂贵的地方: **try-catch代码段会产生额外的性能开销**，或者换个角度说，它往往会影响JVM对代码进行优化，所以**建议仅捕获有必要的代码段，尽量不要一个大的try包住整段的代码**;与此同
时，利用异常控制代码流程，也不是一个好主意，远比我们通常意义上的条件语句(if/else、switch)要低效。

- **Java每实例化一个 `Exception` ，都会对当时的栈进行`快照`，这是一个相对比较重的操作**。如果发生的非常频繁，这个开销可就不能被忽略了。

当我们的服务出现反应变慢、吞吐量下降的时候，**检查发生最频繁的 `Exception` 也是一种思路**。
