package thread.runnable;

import util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RunnableDemo {

    public static void main(String[] args) {
        testCallable();
    }

    private static void testCallable() {
        ExecutorService executor = Executors.newFixedThreadPool(3, ThreadUtil.newThreadFactory(RunnableDemo.class));

        List<Future<Integer>> lst = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            // submit task
            Future<?> future = executor.submit(() -> {
                    int result = 0;
                    for (int j = 101; j >= 0; j /= 3) {
                        result = result / j;
                    }
            });

            lst.add((Future<Integer>) future);
        }

        /**
         * 使用线程池执行任务，即使是Runnable，也必须调用future.get()，防止异常被吃掉。
         * */
        lst.forEach(future -> {
            try {
                int result = future.get(10, TimeUnit.SECONDS);
                System.out.println(result);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        });

        System.out.println("shutdown!");
        executor.shutdown();
    }

}
