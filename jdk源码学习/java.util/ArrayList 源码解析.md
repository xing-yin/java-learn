ArrayList 源码解析
====

## 简介

ArrayList 是一种用数组实现的 List,与数组相比，它具有动态扩展的能力，所以也称为"动态数组"。

## 继承体系

<div align="center"> <img src="pics/.png" width="500" style="zoom:90%"/> </div><br>

ArrayList 实现了 List, RandomAccess, Cloneable, java.io.Serializable 接口。

* 实现了 List 接口，提供了基本的添加、删除、遍历、判空等操作
* 实现了 RandomAccess 接口，具备了随机访问的能力
* 实现了 Cloneable 接口，具备了克隆的能力
* 实现了 java.io.Serializable 接口，可以被序列化和反序列化

## 源码解析

## 属性

```java
    
    /**
     * Default initial capacity.
     * 默认初始化大小为 10
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Shared empty array instance used for empty instances.
     * 共享的空数组用于实例化一个空的实例
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 空数组，当没有指定容量时使用，在添加第一个元素的时候会重新初始为默认容量大小(10)
     */ 
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 真正存储元素的数组【数组的实现核心】
     */
    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * 集合的元素个数
     *
     * @serial
     */
    private int size;

```

* (1) `DEFAULT_CAPACITY`

    默认的容器大小为 10，我们平时用 new ArrayList() 创建的就是这个容量。

* (2) `EMPTY_ELEMENTDATA`

    一个空的数组，通过 new ArrayList(0) 创建容器就用这个数组


## 构造方法


## 普通方法



## 小结