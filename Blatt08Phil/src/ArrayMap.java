import java.util.HashSet;
import java.util.Set;

public final class ArrayMap<K, V> extends AbstractMap<K, V> {

    //Attribute
    private Entry<K, V>[] entries;

    //Konstruktoren
    public ArrayMap() {
        this.entries = GenericArrayHelper.newEntryArrayOfSize(10);//erstellt neue leere ArrayMap der Laenge 10
    }
    public ArrayMap(Entry<K, V>[] entries) {
        this.entries = GenericArrayHelper.copyArray(entries);//erstellt eine neue ArrayMap mit den Elementen die in dem entries array uebergeben werden
    }

    //Implementierung der abstrakten Methoden aus AbstractMap
    @Override
    public V getOrThrow(K key) throws UnknownKeyException {
        for (Entry<K, V> entry : this.entries) {
            if(entry == null) continue;
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        throw new UnknownKeyException();
    }
    @Override
    public void put(K key, V value) {
        for (int i = 0; i < this.entries.length; i++) {
            if(this.entries[i] == null || key.equals(this.entries[i].getKey())){
                this.entries[i] = new Entry<K,V>(key, value);
                return;
            }
        }
        int nextNullIndex = this.entries.length;
        this.entries = GenericArrayHelper.copyArrayWithIncreasedSize(this.entries, this.entries.length * 2);
        this.entries[nextNullIndex] = new Entry<K,V>(key, value);
    }
    @Override
    public Set<K> keysAsSet(){
        HashSet<K> hashSet = new HashSet<K>();
        for(Entry<K, V> entry : this.entries){
            if(entry == null) continue;
            hashSet.add(entry.getKey());
        }
        return hashSet;
    }

}
