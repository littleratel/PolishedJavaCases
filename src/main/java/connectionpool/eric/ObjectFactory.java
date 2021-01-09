package connectionpool.eric;

public interface ObjectFactory<T> {
    /**
     * Creates a new instance.
     *
     * @return the new instance, never null.
     */
    T create();
}
