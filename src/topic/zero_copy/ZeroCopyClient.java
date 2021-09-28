package topic.zero_copy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 演示：disk-nic 零拷贝
 * 通过网络把一个文件从client传到server
 * <p>
 * NIO中的 FileChannel 拥有transferTo和transferFrom两个方法，可直接把FileChannel中的数据拷贝到另外一个Channel，
 * 或直接把另外一个Channel中的数据拷贝到 FileChannel。该接口常被用于高效的网络/文件的数据传输和大文件拷贝。
 * <p>
 * 在操作系统支持的情况下，通过该方法传输数据并不需要将源数据从内核态拷贝到用户态，再从用户态拷贝到目标通道的内核态，
 * 同时也减少了两次用户态和内核态间的上下文切换，也即使用了“零拷贝”，所以其性能一般高于Java IO中提供的方法。
 *
 * @author Alan Yin
 * @date 2021/9/28
 */

public class ZeroCopyClient {

    public void testSendFile() throws IOException {
        String host = "localhost";
        int port = 9000;
        SocketAddress sa = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.connect(sa);
        sc.configureBlocking(true);

        String fileName = "src/topic/zero_copy/testFile.data";
        FileChannel fc = new FileInputStream(fileName).getChannel();
        long start = System.nanoTime();
        long curnset = 0;
        curnset = fc.transferTo(0, fc.size(), sc);
        System.out.println("发送的总字节数：" + curnset + ";耗时(ms)：" + (System.nanoTime() - start));

        try {
            sc.close();
            fc.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {
        ZeroCopyClient zc = new ZeroCopyClient();
        zc.testSendFile();
    }

}
