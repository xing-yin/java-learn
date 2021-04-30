package topic;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Alan Yin
 * @date 2021/4/28
 */

public class LinkedHashMapDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i, "test" + i);
        }

        executorService.execute(() -> {
            for (int i = 0; i < 5; i++) {
                Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, String> retryJob = iterator.next();
                    System.out.println(retryJob);
                    if (retryJob.getKey() / 2 == 0) {
                        iterator.remove();
                        // java.util.ConcurrentModificationException 不能直接使用 map 的 remove 方法
//                        map.remove(retryJob.getKey());
                    } else {
                        map.put(retryJob.getKey(), map.get(retryJob.getKey()) + 1);
                    }
                }
            }
        });

    }


}
