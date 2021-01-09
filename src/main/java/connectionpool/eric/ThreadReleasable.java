package connectionpool.eric;

public interface ThreadReleasable {
    /**
     * Release any object(s) reserved for a given thread.
     *
     * @param thread the given thread that should be released
     */
    void releaseForThread(Thread thread);
}
