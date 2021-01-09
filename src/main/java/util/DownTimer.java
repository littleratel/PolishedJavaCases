package util;

import java.util.concurrent.TimeUnit;

public class DownTimer {

    private long startInNanos;
    private long timeoutInNanos;

    private DownTimer(long startInNanos, long timeoutInNanos) {
        this.startInNanos = startInNanos;
        this.timeoutInNanos = timeoutInNanos;
    }

    public static DownTimer start(long timeout, TimeUnit unit) {
        return new DownTimer(System.nanoTime(), TimeUnit.NANOSECONDS.convert(timeout, unit));
    }

    /**
     * @return True if we are in a timed out state
     */
    public boolean isTimedOut() {
        return (System.nanoTime() - startInNanos) > timeoutInNanos;
    }

    public long timeRemaining(TimeUnit unit) {
        long remaining = Math.max(timeoutInNanos - (System.nanoTime() - startInNanos), 0);
        return unit.convert(remaining, TimeUnit.NANOSECONDS);
    }

    public long timeElapsed(TimeUnit unit) {
        return unit.convert(System.nanoTime() - startInNanos, TimeUnit.NANOSECONDS);
    }

    public long getTimeout(TimeUnit unit) {
        return unit.convert(timeoutInNanos, TimeUnit.NANOSECONDS);
    }

    public void resetTimer(long timeout, TimeUnit unit) {
        this.startInNanos = System.nanoTime();
        this.timeoutInNanos = TimeUnit.NANOSECONDS.convert(timeout, unit);
    }

    public void resetTimer() {
        this.startInNanos = System.nanoTime();
    }
}
