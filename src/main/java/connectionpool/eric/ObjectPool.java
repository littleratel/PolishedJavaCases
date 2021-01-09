package connectionpool.eric;

import connectionpool.eric.exception.AcquireObjectFailedException;

public interface ObjectPool<T> {
    T take() throws AcquireObjectFailedException;

    void put(T object);
}
