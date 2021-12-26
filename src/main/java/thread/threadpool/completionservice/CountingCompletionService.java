package thread.threadpool.completionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DownTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class CountingCompletionService<V> extends ExecutorCompletionService<V> {

//    final Logger logger = LoggerFactory.getLogger(CountingCompletionService.class);

    private final AtomicLong submittedTasks = new AtomicLong();
    private final AtomicLong completedTasks = new AtomicLong();

    @SafeVarargs
    public CountingCompletionService(Executor executor, Callable<V>... callables) {
        super(executor);
        for (Callable<V> item : callables) {
            this.submit(item);
        }
    }

    @Override
    public Future<V> submit(Callable<V> task) {
        Future<V> future = super.submit(task);
        submittedTasks.incrementAndGet();
        return future;
    }

    @Override
    public Future<V> submit(Runnable task, V result) {
        Future<V> future = super.submit(task, result);
        submittedTasks.incrementAndGet();
        return future;
    }

    @Override
    public Future<V> take() throws InterruptedException {
        Future<V> future = super.take();
        completedTasks.incrementAndGet();
        return future;
    }

    @Override
    public Future<V> poll() {
        Future<V> future = super.poll();
        if (future != null) {
            completedTasks.incrementAndGet();
        }
        return future;
    }

    @Override
    public Future<V> poll(long timeout, TimeUnit unit)
            throws InterruptedException {
        Future<V> future = super.poll(timeout, unit);
        if (future != null && future.isDone()) {
            completedTasks.incrementAndGet();
        }
        return future;
    }

    /**
     * @return Number of tasks that have completed
     */
    public long getNumberOfCompletedTasks() {
        return completedTasks.get();
    }

    /**
     * @return Number of tasks that have been submitted
     */
    public long getNumberOfSubmittedTasks() {
        return submittedTasks.get();
    }

    /**
     * @return Number of tasks that have been submitted but are not complete
     */
    public long getNumberOfUncompletedTasks() {
        return submittedTasks.get() - completedTasks.get();
    }

    /**
     * @return True if there exists any incomplete tasks
     */
    public boolean hasUncompletedTasks() {
        return completedTasks.get() < submittedTasks.get();
    }

    /**
     * Wait until all callables submitted to the service are complete and return
     * their result or throw an exception
     *
     * @param timeoutInSeconds
     * @param taskName
     * @return True if all tasks have completed
     * @throws CompletionException
     *         Exception thrown if one of the callables throws an exception or there's
     *         a timeout.
     */
    public List<V> waitServiceCompleted(long timeoutInSeconds, String taskName) throws CompletionException {
        List<V> results = new ArrayList<>();

        DownTimer timer = DownTimer.start(timeoutInSeconds, TimeUnit.SECONDS);
        while (hasUncompletedTasks() && !timer.isTimedOut()) {
            try {
                Future<V> future = poll(3, TimeUnit.SECONDS);
                if (future != null) {
                    results.add(future.get());
//                    logger.info("{} has {} task(s) remaining,", taskName, getNumberOfUncompletedTasks());
                }
            } catch (InterruptedException | CancellationException | ExecutionException e) {
                throw new CompletionException(taskName + " failed", e);
            }
        }

        if (hasUncompletedTasks() || timer.isTimedOut()) {
            throw new CompletionException(taskName + " timed out");
        }

        return results;
    }

    /**
     * Exception thrown when the execution of a task submitted to the CompletionService fails
     */
    public static class CompletionException extends Exception {

        /**
         * Constructs an ExecutionFailedException in the specified message
         *
         * @param message
         */
        public CompletionException(String message) {
            super(message);
        }

        /**
         * Constructs a new exception with the specified detail message and cause.
         *
         * @param message
         * @param cause
         */
        public CompletionException(String message, Throwable cause) {
            super(message, cause);
        }

    }
}
