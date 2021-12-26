package connectionpool.eric;

import java.util.Optional;
import java.util.function.Consumer;

public interface LightweightConnectionPool<T> extends ObjectPool<T>, ThreadReleasable, Clearable {

    /**
     * Returns reserved object, but will not reserve any object if not already reserved.
     */
    Optional<T> getTaken(Thread thread);

    /**
     * Returns reserved object for current thread, but will not reserve any object if not already
     * reserved.
     */
    Optional<T> getTaken();

    /**
     * Invalidates a reserved object if that is already reserved.
     * A provided consumer defines the action that shall be executed on the reserved object, causing
     * the object being invalided.
     * <p>
     * If there is no object already reserved this call is ignored.
     * </p>
     *
     * @param consumer the consumer which shall act on the object, e.g. do disconnect.
     */
    void invalidate(Thread thread, Consumer<T> consumer);

    /**
     * Invalidate taken object, if any, for a given thread.
     *
     * @param thread the thread
     */
    void invalidate(Thread thread);
}
