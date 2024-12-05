import java.util.Set;
import java.util.HashSet;

public abstract class AbstractMap<K, V> {

    public abstract V getOrThrow(K key) throws UnknownKeyException;
    public abstract void put(K key, V value);
    public abstract Set<K> keysAsSet();

    public Set<V> getValuesAsSetOrThrow(Set<K> keys) throws UnknownKeyException {
        HashSet<V> hashSet = new HashSet<V>();
        for (K key : keys) {
            hashSet.add(getOrThrow(key));
        }
        return hashSet;
    }

}
