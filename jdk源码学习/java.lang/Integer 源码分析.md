Integer 源码解析
====

## 简介

Integer

## 继承体系

<div align="center"> <img src="pics/.png" width="500" style="zoom:90%"/> </div><br>

## 源码解析

## 属性

```java
//MIN_VALUE静态变量表示int能取的最小值，为-2的31次方，被final修饰说明不可变。
public static final int   MIN_VALUE = 0x80000000;
//类似的还有MAX_VALUE，表示int最大值为2的31次方减1。
public static final int   MAX_VALUE = 0x7fffffff;
```
- Integer的界限范围是 0x80000000~0x7fffffff， 这与int类型的界限范围是一样的。

> 由于补码表示负数，所以正数比负数多一个数字。

```java
@Native public static final int SIZE = 32;
```

- SIZE用来表示二进制补码形式的int值的比特数，值为32，因为是静态变量所以值不可变。

```java
public static final Class<Integer>  TYPE = (Class<Integer>) Class.getPrimitiveClass("int");
```
- 这个是类型，类型是int。


```java
final static int [] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
                                      99999999, 999999999, Integer.MAX_VALUE };
```

- stringSize 是为了判断一个int型数字对应字符串的长度。sizeTable这个数组存储了该位数的最大值。

```java
    final static char[] digits = {
        '0' , '1' , '2' , '3' , '4' , '5' ,
        '6' , '7' , '8' , '9' , 'a' , 'b' ,
        'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
        'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
        'o' , 'p' , 'q' , 'r' , 's' , 't' ,
        'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };
```

- digits数组里面存的是数字从二进制到36进制所表示的字符，所以需要有36个字符才能表示所有不用进制的数字。

```java
final static char [] DigitTens = {
        '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
        '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
        '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
        '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
        '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
        '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
        '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
        '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
        '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
        } ;

    final static char [] DigitOnes = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        } ;
```

- DigitTens和DigitOnes两个数组，DigitTens数组是为了获取0到99之间某个数的十位,DigitOnes是为了获取0到99之间某个数的个位。


## 构造方法

只有两个构造方法:

- public Integer(int value)
- public Integer(String s) 

## 静态内部类 IntegerCache

关键代码如下：

```java
private static class IntegerCache {
        static final int low = -128;
        static final int high;
        static final Integer cache[];
        static {
            // high value may be configured by property
            int h = 127;
            String integerCacheHighPropValue =                VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
            ...
            // range [-128, 127] must be interned (JLS7 5.1.7)
            assert IntegerCache.high >= 127;
        }
        ...
  }
```

#### 为什么设计这个静态内部类？

简而言之，是利用缓存提高程序的性能。

> IntegerCache 包含了 int 可能需要的 Integer 数组，它存储了(high -low)个静态 Integer对象，并在静态代码块中初始化。
默认范围是 [-128,127]，所以这里默认只实例化了 256 个Integer对象，当Integer的值范围在 [-128,127] 时则直接从缓存中获取对应的Integer对象，不必重新实例化。

这些缓存值都是静态且 final 的，避免重复的实例化和回收。

如果不去配置虚拟机参数，这个值不会变。配合 valueOf(int) 方法，可以节省创建对象造成的资源消耗。

另外如果想改变这些值缓存的范围，再启动JVM时可以通过 `-Djava.lang.Integer.IntegerCache.high=xxx` 就可以改变缓存值的最大值，比如 `-Djava.lang.Integer.IntegerCache.high=888` 则会缓存 [-127,-888]。 


## 普通方法

## 要点剖析

* 1.IntegerCache 已经在上面分析过，不再赘述。

* 2.我们在分析字符串的设计实现时，提到过字符串是不可变的，保证了基本的信息安全和并发编程中的线程安全。

    如果你去看包装类里存储数值的成员变量“value”，你会发现，不管是 Integer 还是 Boolean 等，都被声明为“private final”，所以，它们同样是不可变类型。

    > 这种设计可以这样理解：想象一下这个应用场景，比如Integer提供了getInteger()方法，用于方便地读取系统属性，
    我们可以用属性来设置服务器某个服务的端口，如果我可以轻易地把获取到的Integer对象改变为其他数值，这会带来产品可靠性方面的严重问题。

* 3.Integer等包装类，定义了类似 SIZE 或者 BYTES 这样的常量，这是什么样的设计考虑呢?

    > 如果你使用过其他语言，比如C、C++，类似整数的位数，是不确定的，可能在不同的平台，比如32位或者64位平台，存在非常大的不同。那么，在32位JDK或者64位JDK里，数据位数会有不同吗?

    这种移植对于Java来说相对要简单些，因为原始数据类型是不存在差异的，这些明确定义在Java语言规范里面，不管是32位还是64位环境，开发者无需担心数据的位数差异。

## 小结
