package thread.exception;

import org.junit.Test;
import util.SleepUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExceptionHandlerTest {

    @Test
    public void testUncaughtExceptionHandler() {
        Runnable r = () -> {
            Thread.currentThread().setUncaughtExceptionHandler(new MyExceptionHandler());
            int a = 5 / 0;
            System.out.println("程序正常结束, 结果是: " + a);
        };

        Thread t = new Thread(r);
        t.start();

        SleepUtil.sleep(3);
        System.out.println(t.getName() + " is alive: " + t.isAlive());
    }

    @Test
    public void testCatchExceptionUsingThreadPool() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<?> future = executor.submit(() -> {
            SleepUtil.sleep(1);
            throw new NullPointerException("Test Exception");
        });

        SleepUtil.sleep(3);
        System.out.println("Task is done: " + future.isDone()); // True, but it will not print exception trace.

        try {
            System.out.println("Get result: " + future.get()); // It's here will print the exception trace
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Main Thread End!");
    }

    class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            // 当线程抛出异常时，后续的处理
            // 当然，这也可以放在run()方法的try/catch里做
            System.out.println(t.getName() + " throw the exception: " + e);
            System.out.println("Need more operations when the thread throw the exception.");
        }
    }
}
