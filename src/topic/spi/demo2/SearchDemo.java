package topic.spi.demo2;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Alan Yin
 * @date 2021/10/29
 */

public class SearchDemo {

    public static void main(String[] args) {
        ServiceLoader<Search> serviceLoader = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Search search = iterator.next();
            search.searchDoc("hello spi");
        }
    }
}
