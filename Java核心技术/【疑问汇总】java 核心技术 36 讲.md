【疑问汇总】java 核心技术 36 讲
=======

## 第2讲

- 检查和不可检查的异常区别是什么？分别怎么处理

- ClassNotFoundException和NoClassDefFoundError的区别是什么？

## 第3讲

- final 减少并发中的同步开销的原理是什么？(查看 java 虚拟机的说明)

- try with resources 如何使用？

> 平时的开发中 看 什么地方适合使用 final 关键字

- 如何实现深度拷贝（如构造对象时的成员变量的赋值）

- 并发编程中的 copy-on-write 原则是什么？

> Cleaner的实现利用了**幻象引用**(PhantomReference)，这是一种常见的所谓post-mortem清理机制

- 什么是 post-mortem清理机制？

## 第 4 讲

- 4 种引用写 java 实例来演示？

- JVM 可达性分析温习

## 第 5 讲

- 阅读 StringBuffer 和 StringBuilder 源码实现

- 查看 StringBuffer 如何实现线程安全，为什么性能开销比较大？


## 第 7 讲

- 研究一下 Integer 的静态工厂方法 valueOf,如何实现缓存？

- 阅读  Integer 源码，看包装类型的典型实现

- 为什么数组可以利用 CPU 的缓存，分散的对象引用/链表等不能？


## 第 8 讲

- hashSet 根据 hashMap 实现，treeSet 根据 treeMap 实现,具体实现是什么样的？

## 第 10 讲

- 什么是 CAS ? 如何保证并发的同步？

## 第11讲 | Java提供了哪些IO方式？ NIO如何实现多路复用？

- 学习一下 IO 相关的章节，特别是 NIO 的操作机制

- 什么是基于 流模型实现的 io

- 异步 IO 基于事件和回调机制，具体是怎么实现的？

- 同步是不是大部分是阻塞的，异步是不是大部分是非阻塞的

- 高并发下采用线程池的方式，线程的上下文切换开销很明显，线程为什么不是阻塞式的？是如何进行上下文切换的？

##

# 第16讲 | synchronized底层如何实现?什么是锁的升级、降级?

- 什么是安全点？

- 什么是对象头的 mark word? 

- 什么是锁膨胀？

- 什么是停顿(STW)

- 研究一下synchronized内部实现，结合 深入理解 JVM 总结各种锁和实现原理


# 第17讲

- 从线程 API使用部分一直到本节结束，不明白讲述的内容，需要查找额外资料和阅读源码理解

- 什么是守护线程(Daemon Thread)，实际在哪里应用？如何使用



## 第 讲
