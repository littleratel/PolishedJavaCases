package socket.nio.demo2;

import java.util.Scanner;

public class Test {
    // 测试主方法
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
//        // 运行服务器
//        Server.start();
//        // 避免客户端先于服务器启动前执行代码
//        Thread.sleep(2000);

        // 运行客户端
        Client.start();
		Thread.sleep(1000);
        Client2BK.start();

        for (int i = 5; i < 20; i += 5) {
            int j = i + 5;
			Thread.sleep(500);
            Client.sendMsg(i + "+" + j);
            Thread.sleep(500);
            Client2BK.sendMsg(i + "*" + j);
            Thread.sleep(500);
        }

        Client.stop();
        Client2BK.stop();
    }
}
