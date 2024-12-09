import java.util.HashSet;
import java.util.Set;

public abstract class AbstractMap<K, V> {

    //abstract methods
    public abstract V getOrThrow(K key) throws UnknownKeyException;
    public abstract void put(K key, V value);
    public abstract Set<K> keysAsSet();

    //implemented methods
    public Set<V> getValuesAsSetOrThrow(Set<K> keys) throws UnknownKeyException {
        HashSet<V> vSet = new HashSet<V>();//Erstellen eines HashSets zum Speichern der Ergebnis-Werte (mit Typparameter V)
        for (K key : keys) {//geht durch alle Keys des uebergebenen keySet
            vSet.add(this.getOrThrow(key));//fuegt den Wert zum Key zum vSet hinzu, oder wirft eine Exception
        }
        return vSet;//gibt das fertige Value Set zurueck, wenn vorher keine Exception aufgetreten ist (dann Methodenabbruch)
    }

}
