## 抛出与抽象对应的异常


## 首选方式

异常转译与不加选择地从低层传递异常的做法相比有所改进，但不能滥用。 

如有可能，最好做法是，在调用低层方法之前确保它们会成功执行，从而避免它们抛出异常。
有时候，可以在给低层传递参数之前，检查更高层方法的参数的有效性，从而避免低层方法抛出异常。

### 形式1:异常转译

``` java
 try {
     //Use lower-level abstraction to do our bidding
 }catch (LowerLevelException e){
     throw new HigherLevelException("message");
 }
```

### 形式2:异常链

如果低层的异常对于调试导致高层异常的问题非常有帮助，使用异常链就很合适。

低层的异常（原因）被传到高层的异常，高层的异常提供访问方法 (Throwable 的 getCause 方法）来获得低层的异常：

``` java
 // Exception Chaining
 try {
     // Use lower-level abstraction to do our bidding
 } catch (LowerLevelException cause) {
     throw new HigherLevelException(cause);
 }
```

``` java
/* Exception with chaining-aware constructor */
class HigherLevelException extends Exception {
    HigherLevelException( Throwable cause ) {
        super(cause);
    }
}
```


## 其次方式

让更高层处理这些异常，将高层方法的调用者与低层的问题隔离开来。

在这种情况下，可以用某种适当的记录机制（如 java.util.logging）将异常记录下来。

这样有助于排查问题，又将客户端代码和最终用户与问题隔离开来。

## 小结

总之，如果不能阻止或者处理来自更低层的异常，一般的做法是使用异常转译，
只有在低层方法的规范碰巧可以保证“它所抛出的所有异常对于更高层也是合适的”情况下，才可以将异常从低层传播到高层。
异常链对高层和低层异常都提供了最佳的功能：它允许抛出适当的高层异常，同时又能捕获低层的原因进行失败分析（详见第 75 条）