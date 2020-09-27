package topic.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Alan Yin
 * @date 2020/9/3
 */

public class Demo {

    public static void main(String[] args) {
        ServiceLoader<CacheDataSource> loader = ServiceLoader.load(CacheDataSource.class);
        Iterator<CacheDataSource> iterator = loader.iterator();
        while (iterator.hasNext()){
            CacheDataSource cacheDataSource= iterator.next();
            cacheDataSource.getDataSource();
        }
        System.out.println("当前线程上线文类加载器 : " + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器 : " + ServiceLoader.class.getClassLoader());
    }
}
