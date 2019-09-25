## 不要忽略异常

### 空的 catch 块会使异常达不到应有的目的
 
或许你会侥幸逃过劫难，或许结果将是灾难性的。每当见到空的 catch 块时，应该让警钟长鸣。

###  如果选择忽略异常， catch 块中应该包含一条注释，说明为什么可以这么做，并且变量应该命名为 ignored:

``` java
Future<Integer> f = exec.submit(planarMap::chromaticNumber);
int numColors = 4; // Default: guaranteed sufficient for any map
try {
    numColors = f.get( 1L, TimeUnit.SECONDS );
} catch ( TimeoutException | ExecutionException ignored ) {
    // Use default: minimal coloring is desirable, not required
}
```

## 小结

不管异常代表了可预见的异常条件，还是编程错误，用空的 catch 块忽略它，都将导致程序在遇到错误的情况下悄然地执行下去。

然后，有可能在将来的某个点上，当程序不能再容忍与错误源明显相关的问题时，它就会失败。

正确地处理异常能够彻底避免失败。只要将异常传播给外界，至少会导致程序迅速失败，从而保留了有助于调试该失败条件的信息。