## 避免不必要的使用受检查异常

## 抛出未受检异常

``` java
} catch ( TheCheckedException e ) {
    throw new AssertionError(); /* Can't happen! */
}
```

``` java
} catch ( TheCheckedException e ) {
    e.printStackTrace(); /* Oh well, we lose. */
    System.exit( 1 );
}
```

如果使用 API 的程序员无法做得比上面处理的更好，那么未受检的异常可能更为合适。

## 小结

总之，在谨慎使用的前提之下，受检异常可以提升程序的可读性；如果过度使用，将会使 API 使用起来非常痛苦。

- 如果调用者无法恢复失败，就应该抛出未受检异常。

- 如果可以恢复，并且想要迫使调用者处理异常的条件，首选应该返回一个 optional 值。当且仅当万一失败时，这些无法提供足够的信息，才应该抛出受检异常。