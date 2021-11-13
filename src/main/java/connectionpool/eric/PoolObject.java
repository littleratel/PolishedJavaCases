package connectionpool.eric;

import lombok.Getter;

import java.util.Objects;

@Getter
public class PoolObject<T> {
    private final String id;
    private final T object;

    PoolObject(String id, T object) {
        this.id = id;
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PoolObject))
            return false;
        PoolObject<?> that = (PoolObject<?>) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
