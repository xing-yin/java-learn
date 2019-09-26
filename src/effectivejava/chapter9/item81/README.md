# 并发工具优于 wait 和 notify

## 小结

总之，直接使用 wait 方法和 notify 方法就像用“并发汇编语言”进行编程一样，
java.util.concurrent 则提供了更高级的语言。 

没有理由在新代码中使用 wait 方法和 notify 方法，即使有，也是极少的。
 
如果你在维护使用 wait 方法和 notify 方法的代码，务必确保始终是利用标准的模式从 while 循环内部调用 wait 方法。

一般情况下，应该优先使用 notifyAll 方法，而不是使用 notify 方法。如果使用 notify 方法，请一定要小心，以确保程序的活性。