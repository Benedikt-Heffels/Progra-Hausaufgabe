import java.util.HashSet;
import java.util.Set;

public class Launcher {
    public static void main(String[] args) {
        AbstractMap<String, Integer> map = new ArrayMap<>();
        putEntries(map);
        printMap(map);

        //Testen der Methode getValuesAsSetOrThrow
        Set<String> stringSet = new HashSet<>(); //Erstellen einer Set-Menge
        stringSet.add("unknown");
        try{
            //try-catch, da Methode Exception wirft, falls keine Zupordnung zum KEy vorhanden.
            map.getValuesAsSetOrThrow(stringSet);
        }
        catch(UnknownKeyException _){
            System.out.println("unknown key");
        }

    }

    //Helfer-Methoden aus der Aufgabenstellung. Da sie der Aufgabenstellung zufolge nur in dieser Klasse verwendet werden, sind sie hier private.
    private static void putEntries(AbstractMap<String, Integer> abstractMap){//fuegt Elemente hinzu
        //Methode soll gemaess Aufgabenstellung folgende Zuordnungen erstellen:
        abstractMap.put("sizeInMB", 42);
        abstractMap.put("version", 4);
        abstractMap.put("yearOfRelease", 2015);
    }

    private static <K,V> void printMap(AbstractMap<K, V> abstractMap){//gibt uebergebene Map aus
        Set<K> kSet = abstractMap.keysAsSet();
        for(K key : kSet){
            try{
                //Soll alle Schluessel-Wert-Zuordnungen ausgeben, wobei der Wert ein Integer ist.
                //try-catch, da Methode getOrThrow Exception ausgibt, falls kein Key vorhanden.
                Integer value = (Integer) abstractMap.getOrThrow(key);
                System.out.println(key + ": " + value);
            }
            catch(UnknownKeyException _){
                System.out.println("Es ist ein Fehler aufgetreten, weil ein unbekannter Schluessel verwendet wurde, der nicht in der abstractMap enthalten ist, aber dennoch uebergeben wurde!");
            }
        }
    }

}
