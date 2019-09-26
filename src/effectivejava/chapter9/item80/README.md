# executor、task 和 stream 优先于线程 

## ExecutorService

可以利用 ExecutorService 完成很多的工作:

- 可以等待完成一项特殊的任务，可以等待一个任务集合中的任何任务或者所有任务完成（利用 invokeAny 或者 invokeAll 方法）
- 可以等待 executor service 优雅地完成终止（利用 awaitTermination 方法）
- 可以在任务完成时逐个地获取这些任务的结果（利用 ExecutorCompletionService）
- 可以调度在某个特殊的时间段定时运行或者阶段性地运行的任务（利用 ScheduledThreadPoolExecutor）

## 线程池（thread pool ）

- java.util.concurrent.Executors 类包含了静态工厂，能为你提供所需的大多数 executor 。

- 如果想特别指定线程，可以直接使用 ThreadPoolExecutor 类。

## 为特殊的应用程序选择 executor service 技巧

- 轻量负载: Executors.newCachedThreadPool 通常是个不错的选择

- 大负载: 最好使用 Executors.newFixedThreadPool ，它为你提供了一个包含固定线程数目的线程池，或者为了最大限度地控制，就直接使用 ThreadPoolExecutor 类。

## 不仅应该尽量不要编写自己的工作队列，而且应该尽量不直接使用线程。

- 当直接使用线程时， Thread 是既充当工作单元，又是执行机制。

- 在 Executor Framework 中，工作单元和执行机制是分开的。

> 现在关键的抽象是工作单元，称作任务（task） 。
>
> 任务有两种：Runnable 及其近亲 Callable （它与 Runnable 类似，但它会返回值，并且能够抛出任意的异常）

## fork-join 

在 Java 7 中， Executor 框架被扩展为支持 fork-join 任务，这些任务是通过一种称作 fork-join 池的特殊 executor 服务运行的。

fork-join 任务用 ForkJoinTask 实例表示，可以被分成更小的子任务。

包含 ForkJoinPool 的线程不仅要处理这些任务，还要从另一个线程中“偷”任务，以确保所有的线程保持忙碌，从而提高 CPU 使用率、提高吞吐量，并降低延迟。