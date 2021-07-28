package jdk.demo.java.util.concurrent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * @author Alan Yin
 * @date 2021/6/22
 */

public class SearchTask implements Runnable {

    private BlockingQueue<File> queue;
    private String keyword;

    public SearchTask(BlockingQueue<File> queue, String keyword) {
        this.queue = queue;
        this.keyword = keyword;
    }

    @Override
    public void run() {
        try {
            boolean finish = false;
            while (!finish) {
                // 取出队首元素，如果队列为空，则阻塞
                File file = queue.take();
                if (file == FileEnumerationTask.DUMMY) {
                    // 取出来后再重新放入，好让其他线程读到它时也很快结束
                    queue.put(file);
                    finish = true;
                } else {
                    search(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void search(File file) throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream(file));
        int lineNumber = 0;
        try {
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
                }
            }
        } finally {
            in.close();
        }
    }
}
