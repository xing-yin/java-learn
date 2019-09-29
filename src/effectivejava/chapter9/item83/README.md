# 理性谨慎地使用延迟初始化

## 建议原则

延迟初始化的最佳建议是「除非需要，否则不要这样做」。**在大多数情况下，常规初始化优于延迟初始化。**

> 它降低了初始化类或创建实例的成本，代价是增加了访问延迟初始化字段的成本,实际上可能会降低性能。

## 实例

- 如果您使用延迟初始化来取代初始化的循环（circularity），请使用同步访问器(最简单、清晰，但性能不是最好)

```
// Lazy initialization of instance field - synchronized accessor
private FieldType field;
private synchronized FieldType getField() {
    if (field == null)
        field = computeFieldValue();
    return field;
}
```

- 如果需要在静态字段上使用延迟初始化来提高性能，使用 lazy initialization holder class 模式。

```
// Lazy initialization holder class idiom for static fields
private static class FieldHolder {
    static final FieldType field = computeFieldValue();
}
private static FieldType getField() { return FieldHolder.field; }
```

- 双重锁机制延迟初始化

```
// Double-check idiom for lazy initialization of instance fields
private volatile FieldType field;
private FieldType getField() {
    FieldType result = field;
    if (result == null) { // First check (no locking)
        synchronized(this) {
            if (field == null) // Second check (with locking)
                field = result = computeFieldValue();
        }
    }
    return result;
}
```

## 小结

总之，应该正常初始化大多数字段，而不是延迟初始化。

如果必须延迟初始化字段以提高性能 或 为了避免有害的初始化循环，则使用适当的延迟初始化技术。

对于字段，使用双重检查模式；对于静态字段，则应该使用the lazy initialization holder class idiom。特别的，如果可以容忍重复初始化实例字段，还可以考虑单检查模式。
