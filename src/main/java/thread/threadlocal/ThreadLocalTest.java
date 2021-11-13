package thread.threadlocal;

import util.SleepUtil;

public class ThreadLocalTest {

    public final static ThreadLocal<String> tl = ThreadLocal.withInitial(() -> "TL_initial_");

    /**
     * Output:
     * Thread-0 Set 0
     * Thread-5 Set 5
     * Thread-3 Set 3
     * Thread-1 Set 1
     * Thread-4 Set 4
     * Thread-2 Set 2
     * */
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            int index = i;
            Thread t = new Thread(() -> {
                try {
                    String tName = Thread.currentThread().getName();
                    tl.set(tName + " Set " + index);
                    SleepUtil.sleepInMillis((int) (Math.random() * 1000));
                    System.out.println(tl.get());
                } finally {
                    tl.remove();
                }
            });

            t.start();
        }

        while (Thread.activeCount() > 2) ;

        System.out.println("End Test.");
    }
}
