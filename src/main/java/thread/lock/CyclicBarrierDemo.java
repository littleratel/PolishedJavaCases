package thread.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

    private final static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);
    private final static CyclicBarrier BARRIER = new CyclicBarrier(5);

    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            final String name = "Player-" + i;
            EXECUTOR_SERVICE.execute(() -> {
                try {
                    System.out.println(name + "已准备,等待其他玩家准备...");
                    BARRIER.await();
                    System.out.println(name + "已加入游戏");
                } catch (InterruptedException | BrokenBarrierException e) {
                    System.out.println(name + "离开游戏");
                }
            });
        }
        EXECUTOR_SERVICE.shutdown();
    }

}
