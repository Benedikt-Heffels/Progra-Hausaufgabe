import java.util.HashSet;
import java.util.Set;

public final class ArrayMap<K, V> extends AbstractMap<K, V> {
    private Entry<K, V>[] entries;
    public ArrayMap() {
        entries = GenericArrayHelper.newEntryArrayOfSize(10);
    }
    public ArrayMap(Entry<K, V>[] entries) {
        this.entries = GenericArrayHelper.copyArray(entries);
    }
    @Override
    public V getOrThrow(K key) throws UnknownKeyException {
        for (Entry<K, V> entry : entries) {
            if(entry == null) continue;
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        throw new UnknownKeyException();
    }
    @Override
    public void put(K key, V value) {
        for (int i = 0; i < entries.length; i++) {
            if(entries[i] == null || key.equals(entries[i].getKey())){
                entries[i] = new Entry<K,V>(key, value);
                return;
            }
        }
        int nextNullIndex = entries.length;
        entries = GenericArrayHelper.copyArrayWithIncreasedSize(entries, entries.length * 2);
        entries[nextNullIndex] = new Entry<K,V>(key, value);
    }
    @Override
    public Set<K> keysAsSet(){
        HashSet<K> hashSet = new HashSet<K>();
        for(Entry<K, V> entry : entries){
            if(entry == null) continue;
            hashSet.add(entry.getKey());
        }
        return hashSet;
    }

}
