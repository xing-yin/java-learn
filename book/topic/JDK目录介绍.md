JDK目录介绍（bin，db，jre，include，lib，src）以及javac，java命令
===

目录|作用
:----:|:----:
bin目录|用于存放一些可执行程序，如：java.exe(Java编译器)，java.exe(Java运行工具)，jar.exe(打包工具)和javadoc.exe(文档生成工具)
db目录|db目录是一个小型的数据库。从JDK6.0引入。是一个纯java实现、开源的数据库管理系统。在学习JDBC时，不需要额外的安装一个数据库软件，选择直接使用的javaDB即可。
jre目录|“jre”是“Java Runtime Environment”的缩写，意为“Java运行时环境”。此目录是Java运行时环境的根目录，它包含Java虚拟机，运行时的类包，Java运行启动器以及一个bin目录，但不包含开发环境中的开发工具。
include目录|由于JDK是通过C和C++实现的，因此在启动时需要引入一些C语言的头文件，该目录就是用于存放这些头文件的。
lib目录|是“library”的缩写，意为Java类库和库文件，是开发工具使用的归档包文件。
src.zip|是src的压缩文件，src中放置的是JDK核心类的源代码，通过该文件可以查看Java基础类的源代码。

### 补充

在JDK的bin目录下存放着很多可执行程序，其中最为重要的就是Java.exe和javac.exe。

* javac.exe：是Java编译器工具，它可以将编写好的Java文件编译成Java字节码文件（可执行的Java程序）。java源文件的扩展名为".java",如“hello.java”。编译后生成对应的字节码文件，文件的扩展名为.class。（*.class是二进制文件，中间文件，给IVM来阅读）

* Java.exe：是java运行工具，他会启动一个java虚拟机（JVM）进程，Java虚拟机相当于一个虚拟的操作系统，他们专门负责运行由Java编译器生成的字节码文件（.class文件）。JVM将*.class 文件翻译为底层操作系统字节码，可运行。

java是一门半编译半解释型语言,且具有移植性。
