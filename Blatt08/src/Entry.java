public final class Entry<K,V> {//K: generischer Typparameter für den Schlüssel; V: generischer Typparameter für den Wert
    //@TODO Added Comments
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
