public final class Entry<K,V> {

    //Attribute
    private K key;
    private V value;

    //Konstruktor
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    //Getter
    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }

}
