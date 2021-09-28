package topic.zero_copy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 演示：disk-nic 零拷贝
 * 通过网络把一个文件从client传到server
 *
 * @author Alan Yin
 * @date 2021/9/28
 */

public class ZeroCopyServer {

    ServerSocketChannel listener = null;

    /**
     * 服务端设置
     */
    protected void mySetup() {
        InetSocketAddress listenAddr = new InetSocketAddress(9000);
        try {
            listener = ServerSocketChannel.open();
            ServerSocket ss = listener.socket();
            ss.setReuseAddress(true);
            ss.bind(listenAddr);
            System.out.println("监听的端口:" + listenAddr.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("端口绑定失败:" + listenAddr.toString() + ",端口可能被占用，原因为：" + e.getMessage());
        }
    }

    /**
     * 读取数据
     */
    private void readData() {
        ByteBuffer dst = ByteBuffer.allocate(4096);
        try {
            while (true) {
                SocketChannel socketChannel = listener.accept();
                System.out.println("创建的连接：" + socketChannel);
                socketChannel.configureBlocking(true);
                int nread = 0;
                while (nread != -1) {
                    try {
                        // 从 channel 中读取数据写入到 socketChannel 中
                        nread = socketChannel.read(dst);
                    } catch (IOException e) {
                        e.printStackTrace();
                        nread = -1;
                    }
                    // 倒带
                    dst.rewind();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ZeroCopyServer server = new ZeroCopyServer();
        server.mySetup();
        server.readData();
    }

}
