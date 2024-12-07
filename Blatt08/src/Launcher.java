import java.util.HashSet;
import java.util.Set;

public class Launcher {
    //@TODO Added more comments
    public static void main(String[] args) {
        //@TODO: Changed variable name
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
            System.out.println("unknown key"); //@TODO: Ausgabe auf Kommandozeile erfragt - so richtig? Oder besser über System.exit()?
        }

    }

    //Helfer-Methoden @TODO (IMPORTANT): In der Aufgabenstellung nicht so deklariert -> vielleicht doch public?
    private static void putEntries(AbstractMap<String, Integer> abstractMap){//fuegt Elemente hinzu
        //@TODO Changed variable name
        //Methode soll gemaess Aufgabenstellung folgende Zuordnungen erstellen:
        abstractMap.put("sizeInMB", 42);
        abstractMap.put("version", 4);
        abstractMap.put("yearOfRelease", 2015);
    }

    private static <K,V> void printMap(AbstractMap<K, V> abstractMap){//gibt uebergebene Map aus
        //@TODO: Changed variable names, changed Output a little bit
        Set<K> kSet = abstractMap.keysAsSet();
        for(K key : kSet){
            try{
                //Soll alle Schlüssel-Wert-Zuordnungen ausgeben, wobei der Wert ein Integer ist.
                //try-catch, da Methode getOrThrow Exception ausgibt, falls kein Key vorhanden.
                //@TODO: Andere Implementation -> Passt die besser zur Aufgabenstellung (gefragt ist nach einem Integer value)?
                //System.out.println(key + ": " + abstractMap.getOrThrow(key));
                Integer value = (Integer) abstractMap.getOrThrow(key);
                System.out.println(key + ": " + value);
            }
            catch(UnknownKeyException _){
                System.out.println("Es ist ein Fehler aufgetreten, weil ein unbekannter Schlüssel verwendet wurde, der nicht in der abstractMap enthalten ist, aber dennoch uebergeben wurde!");
            }
        }
    }

}
