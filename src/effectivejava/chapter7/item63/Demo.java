package effectivejava.chapter7.item63;

/**
 * @author yinxing
 * @date 2019/9/17
 */

public class Demo {

    /**
     * 不要使用字符串连接操作符合并多个字符串，除非性能无关紧要。
     * 否则使用 StringBuilder 的 append 方法。
     * 或者，使用字符数组，再或者一次只处理一个字符串，而不是组合它们。
     */

    /**
     * 小建议:
     * 如果事先能够判断大小，可以预先分配了一个足够大的 StringBuilder 来保存整个结果，从而消除了自动增长的需要。
     * eg:StringBuilder sb = new StringBuilder(1000);
     */
}
