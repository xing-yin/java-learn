package jdk.demo.java.util.concurrent;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * @author Alan Yin
 * @date 2021/6/22
 */

public class FileEnumerationTask implements Runnable {

    // 哑元文件对象，放在阻塞队列最后，用于标识文件被遍历完成
    public static File DUMMY = new File("");

    private BlockingQueue<File> queue;

    private File startingDirectory;

    public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
        this.queue = queue;
        this.startingDirectory = startingDirectory;
    }

    @Override
    public void run() {
        try {
            enumerate(startingDirectory);
            queue.put(DUMMY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 将指定目录下的所有文件以File对象的形式放入阻塞队列中
    private void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                enumerate(file);
            } else {
                // 将元素放入队尾，如果队列满，则阻塞
                queue.put(file);
            }
        }
    }

}
