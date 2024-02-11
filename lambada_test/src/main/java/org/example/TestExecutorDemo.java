package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2024/1/18
 **/
public class TestExecutorDemo {

  public static void main(String[] args) {
    long begin = System.currentTimeMillis();
    System.out.println("begin");
    /** 异步提交三方系统请求的线程池 */
    ExecutorService submitterExecutorService = Executors.newFixedThreadPool(5);
    List<Future<?>> futures = new ArrayList<>();  // 用于保存提交的 Future 对象
    for (int i = 0; i < 10; i++) {
      int finalI = i;
      Future<?> submit = submitterExecutorService.submit(
          () -> {
            try {
              doSomething(finalI);
            } catch (Exception e) {
              System.out.println("Exception happen");
              throw e;
            }
          });
      futures.add(submit);  // 将 Future 对象保存到列表中
    }


    // 检查所有任务的执行结果
    for (Future<?> future : futures) {
      try {
        future.get();  // 调用 get() 方法时会抛出异常，可以在这里捕获并进行处理
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      } catch (ExecutionException e) {
        Throwable cause = e.getCause();  // 获取原始异常
        System.out.println("Caught exception in main thread: " + cause.getMessage());
      }
    }
    long cost = System.currentTimeMillis() - begin;
    System.out.println("cost:" + cost);

  }

  private static void doSomething(int num) {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
    }
    System.out.println("doSomething:" + num);
    if (num == 1) {
      throw new IllegalArgumentException("num:" + num);
    }
  }
}
