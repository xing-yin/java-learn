package jdk.demo.java.util.concurrent;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Alan Yin
 * @date 2021/6/22
 */

public class BlockingQueueDemo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter base directory(e.g. /usr/local)");
        String directory = in.nextLine();
        System.out.println("Enter keyword(e.g. test)");
        String keyword = in.nextLine();

        // 阻塞队列大小
        final int FILE_QUEUE_SIZE = 10;
        // 关键字搜索线程个数
        final int SEARCH_THREADS = 100;

        BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

        // 只启动一个线程来搜索目录
        FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
        new Thread(enumerator).start();

        // 启动100个线程用来在文件中搜索指定的关键字
        for (int i = 0; i <= SEARCH_THREADS; i++) {
            new Thread(new SearchTask(queue, keyword)).start();
        }
    }

}
