package socket.nio.demo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import socket.Calculator;

public class ServerHandle implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile boolean started;
    private final int DEFAULT_PORT;

    public ServerHandle(int port) {
        DEFAULT_PORT = port;
        init();
    }

    private void init() {
        try {
            // 打开监听通道
            serverChannel = ServerSocketChannel.open();
            /**
             * 与 Selector一起使用时, Channel必须处于非阻塞模式下. 这意味着FileChannel不能与 Selector
             * 一起使用，因为FileChannel 不能切换到非阻塞模式。而套接字通道都可以。
             */
            serverChannel.configureBlocking(false);// 开启非阻塞模式
            // 绑定端口 backlog设为1024
            serverChannel.socket().bind(new InetSocketAddress(DEFAULT_PORT), 1024);

            // 创建选择器
            selector = Selector.open();
            // 监听客户端连接请求
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 标记服务器已开启
            started = true;
            System.out.println("服务器已启动，端口号：" + DEFAULT_PORT);
            System.out.println("ServerSocketChannel：" + serverChannel.toString() + " and " + serverChannel.hashCode());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        started = false;
    }

    @Override
    public void run() {

        int ready = 0;

        // 循环遍历selector
        while (started) {
            try {
                // 无论是否有读写事件发生，selector每隔1s被唤醒一次
//                ready = selector.select(1000);
                // 当有注册的事件到达时，方法select()返回，否则阻塞。
                ready = selector.select();
                if (ready == 0) {
                    continue;
                } else if (ready < 0) {
                    break;
                }

                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                SelectionKey key;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();//删除已选的key，防止重复处理
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        // selector关闭后会自动释放里面管理的资源
        if (selector != null) {
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isValid()) {
            // 处理新接入的请求消息
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();

                System.out.println("=====> ssc：" + ssc.toString() + " and " + ssc.hashCode());

                // 通过ServerSocketChannel.accept创建SocketChannel实例,接收新连接
                // 完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
                SocketChannel sc = ssc.accept();
                // 设置为非阻塞的
                sc.configureBlocking(false);
                // 注册为读
                sc.register(selector, SelectionKey.OP_READ);
            }
            // 读消息
            else if (selectionKey.isReadable()) {
                SocketChannel sc = (SocketChannel) selectionKey.channel();

                System.out.println("-----> sc：" + sc.toString() + " and " + sc.hashCode());

                // 创建ByteBuffer，并开辟一个1M的缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                // 读取请求码流，返回读取到的字节数
                int readBytes = sc.read(buffer);
                // 读取到字节，对字节进行编解码
                if (readBytes > 0) {
                    // 将缓冲区当前的limit=position, set position=0，用于后续对缓冲区的读取操作
                    buffer.flip();
                    // 根据缓冲区可读字节数创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    // 将缓冲区可读字节数组复制到新建的数组中
                    buffer.get(bytes);
                    String expression = new String(bytes, "UTF-8");
                    System.out.println("[Server][" + Thread.currentThread().getName() + "]Get formula: " + expression);
                    // 处理数据
                    String result = null;
                    try {
                        result = Calculator.cal(expression).toString();
                    } catch (Exception e) {
                        result = "计算错误：" + e.getMessage();
                    }
                    // 发送应答消息
                    System.out.println("[Server][" + Thread.currentThread().getName() + "]Send result: " + result);
                    doWrite(sc, result);
                }
                // 没有读取到字节 忽略
                // else if(readBytes==0);
                // 链路已经关闭，释放资源
                else if (readBytes < 0) {
                    selectionKey.cancel();
                    sc.close();
                }
            }
        }
    }

    // 异步发送应答消息
    private void doWrite(SocketChannel channel, String response) throws IOException {
        // 将消息编码为字节数组
        byte[] bytes = response.getBytes();
        // 根据数组容量,创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        // 将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        // flip操作
        writeBuffer.flip();
        // 发送缓冲区的字节数组
        channel.write(writeBuffer);
        // ****此处不含处理“写半包”的代码
    }
}
