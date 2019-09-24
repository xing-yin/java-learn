## 优先使用标准的异常


异常|使用场合
:----:|:----:
IllegalArgumentException|非 null 的参数值不正确
IllegalStateException|不适合方法调用的对象状态(如类还没有初始化就调用)
NullPointerException|禁止使用 null 的情况下参数值为 null
IndexOutOfBoundsException|下标参数值越界
ConcurrentModificationException|在禁止并发修改的情况下，检测到对象的并发修改
UnsupportedOperationException|对象不支持请求的方法
