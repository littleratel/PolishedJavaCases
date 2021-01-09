package thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<Future<String>>();

        /**
         * 不管提交的是Runnable还是Callable的任务，如果不调用Future.get()查看结果，异常都会被吃掉
         *
         * */
        for (int i = 0; i < 10; i++) {
            Future<String> future = executorService.submit(new TaskWithResult(i));
            resultList.add(future);
        }
        executorService.shutdown();

        for (Future<String> fs : resultList) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                executorService.shutdownNow();
                e.printStackTrace();
                return;
            }
        }
    }
}


class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        System.out.println("call()方法被自动调用. " + Thread.currentThread().getName());

        if (new Random().nextBoolean())
            throw new TaskException("Meet error in task." + Thread.currentThread().getName());

        for (int i = 99999999; i > 0; i--)
            ;

        return "call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();
    }
}


class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}