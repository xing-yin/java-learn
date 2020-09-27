package topic.spi;

/**
 * @author Alan Yin
 * @date 2020/9/3
 */

public class MemoryCacheDataSource implements CacheDataSource {

    @Override
    public String getDataSource() {
        System.out.println(this.getClass().getClassLoader());
        System.out.println("MemoryCacheDataSource");
        return null;
    }

}
