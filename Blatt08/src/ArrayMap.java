import java.util.HashSet;
import java.util.Set;

public final class ArrayMap<K, V> extends AbstractMap<K, V> {
    //Attribute
    private Entry<K, V>[] entries;

    //Konstruktoren
    public ArrayMap() {
        this.entries = GenericArrayHelper.newEntryArrayOfSize(10); //Erstellt neue leere ArrayMap der Laenge 10
    }
    public ArrayMap(Entry<K, V>[] entries) {
        this.entries = GenericArrayHelper.copyArray(entries); //Uebergebene Werte sollen in die ArrayMap kopiert werden (d.h. Werte werden im Konstruktor einmal kopiert)
    }

    //Implementierung der abstrakten Methoden aus AbstractMap
    @Override
    public V getOrThrow(K key) throws UnknownKeyException {
        for (Entry<K, V> entry : this.entries) {
            if (entry != null && entry.getKey().equals(key)) { //Wenn der key des Eintrags gleich dem uebergebenen key ist, soll der Wert zurueck gegeben werden. Dafuer muss zunaechst ueberprueft werden, dass der Eintrag nicht gleich null ist, weil sonst eine unchecked Exception auftritt (NullPointer).
                return entry.getValue();
            }
        }
        throw new UnknownKeyException(); //Wenn kein passender Arrayeintrag gefunden wird: Werfe Exception
    }
    @Override
    public void put(K key, V value) {
        for (int i = 0; i < this.entries.length; i++) {
            if(this.entries[i] == null || key.equals(this.entries[i].getKey())){ //An der ersten Stelle, wo entweder die Map leer ist (entry == null) oder der key gleich dem uebergebenen ist, soll der neue Wert gesetzt werden
                this.entries[i] = new Entry<K,V>(key, value);
                return;
            }
        }
        int nextNullIndex = this.entries.length;
        this.entries = GenericArrayHelper.copyArrayWithIncreasedSize(this.entries, this.entries.length * 2); //Falls es keine leere Position gibt und der key nicht passt, wird die Map kopiert und dabei von der groesse her verdoppelt
        this.entries[nextNullIndex] = new Entry<K,V>(key, value); //nextNullIndes ist die alte laenge der Map. Da diese jetzt vergroessert wurde, ist das die erste Position, an der die Map den Wert null hat (dabei zu beachten: Array-Positionen werden ab 0 benannt, gezaehlt jedoch ab 1)
    }
    @Override
    public Set<K> keysAsSet(){ //Es soll ein HashSet mit den keys erstellt werden
        HashSet<K> kSet = new HashSet<>();
        for(Entry<K, V> entry : this.entries){
            if(entry != null) {
                kSet.add(entry.getKey());
            }
        }
        return kSet;
    }

}
