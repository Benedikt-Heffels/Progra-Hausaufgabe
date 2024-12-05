import java.util.Set;
import java.util.HashSet;

public abstract class AbstractMap<K, V> {

    //abstract methods
    public abstract V getOrThrow(K key) throws UnknownKeyException;
    public abstract void put(K key, V value);
    public abstract Set<K> keysAsSet();

    //implemented methods
    public Set<V> getValuesAsSetOrThrow(Set<K> keys) throws UnknownKeyException {
        HashSet<V> hashSet = new HashSet<V>();//Erstellen eines Sets zum Speichern der Ergebnis-Werte
        for (K key : keys) {//geht durch alle Keys des uebergebenen keys Set
            hashSet.add(this.getOrThrow(key));//fuegt den Wert zum Key zum Werte-Set hinzu, welches ausgegeben werden soll oder wirft eine Exception
        }
        return hashSet;//gibt das fertige Value Set zurueck, wenn vorher keine Exception aufgetreten ist
    }

}
