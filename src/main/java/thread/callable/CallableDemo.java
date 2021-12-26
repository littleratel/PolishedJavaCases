package thread.callable;

import util.ThreadUtil;
import util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableDemo {

    public static void main(String[] args) {
        testCallable();
    }

    private static void testCallable() {
        ExecutorService executor = Executors.newFixedThreadPool(3, ThreadUtil.newThreadFactory(CallableDemo.class));

        List<Future<Integer>> lst = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            // submit task
            Future<Integer> future = executor.submit(() -> {
                TimeUtil.sleepInSeconds(10);
                int result = 0;
                for (int j = 101; j >= 0; j /= 3) {
                    result = result / j;
                }
                return result;
            });

            lst.add(future);
        }

        //
        lst.forEach(future -> {
            try {
                int result = future.get(3, TimeUnit.SECONDS);
                System.out.println(result);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        });

        System.out.println("shutdown!");

        executor.shutdown();
    }

}
