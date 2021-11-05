package topic.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 查看路径：META-INF/services
 *
 * @author Alan Yin
 * @date 2020/9/3
 */

public class Demo {

    public static void main(String[] args) {
        // ServiceLoader.load(XXX.class) 在加载某接口时，会去META-INF/services下找接口的全限定名文件，再根据里面的内容加载相应的实现类。
        ServiceLoader<CacheDataSource> loader = ServiceLoader.load(CacheDataSource.class);
        Iterator<CacheDataSource> iterator = loader.iterator();
        while (iterator.hasNext()) {
            CacheDataSource cacheDataSource = iterator.next();
            cacheDataSource.getDataSource();
        }
        System.out.println("当前线程上线文类加载器 : " + Thread.currentThread().getContextClassLoader());
        // null: 对应的是 BootstrapClassLoader
        System.out.println("ServiceLoader的类加载器 : " + ServiceLoader.class.getClassLoader());
    }
}
