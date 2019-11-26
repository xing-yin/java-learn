package effectivejava.chapter3.item20;

/**
 * 歌手接口
 *
 * @author yinxing
 * @date 2019/8/26
 */

public interface Singer {

    void sing();

}

/**
 * 总之，一个接口通常是定义允许多个实现的类型的最佳方式。
 * 如果你导出一个重要的接口，应该好好考虑提供一个骨架的实现类。
 * 在可能的情况下，应该通过接口上的默认方法提供骨架实现，以便接口的所有实现者都可以使用它。也就是说，对接口的限制通常要求骨架实现类采用抽象类的形式。
 */
