import java.util.Set;
import java.util.HashSet;

public class Launcher {

    public static void main(String[] args) {
        AbstractMap<String, Integer> map = new ArrayMap<String, Integer>();
        putEntries(map);
        printMap(map);
        Set<String> unknownKeySet = new HashSet<String>();
        unknownKeySet.add("unknown");
        try{
            map.getValuesAsSetOrThrow(unknownKeySet);
        }
        catch(UnknownKeyException _){
            System.out.println("unknown key");
        }

    }

    private static void putEntries(AbstractMap<String, Integer> map){
        map.put("sizeInMB", 42);
        map.put("version", 4);
        map.put("yearOfRelease", 2015);
    }

    private static <K,V> void printMap(AbstractMap<K, V> map){
        Set<K> keys = map.keysAsSet();
        for(K key : keys){
            try{
                System.out.println(key + ": " + map.getOrThrow(key));
            }
            catch(UnknownKeyException _){
                System.out.println("Es ist ein Fehler aufgetreten, weil ein unbekannter Schlüssel verwendet wurde!");
            }
        }
    }

}
