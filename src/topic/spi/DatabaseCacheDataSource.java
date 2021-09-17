package topic.spi;

/**
 * 步骤2：接口实现
 * @author Alan Yin
 * @date 2020/9/3
 */

public class DatabaseCacheDataSource implements CacheDataSource {

    @Override
    public String getDataSource() {
        System.out.println(this.getClass().getClassLoader());
        System.out.println("DatabaseCacheDataSource");
        return null;
    }
}
