public class AdaptiveListTester {
    public static void main(String[] args) {
        AdaptiveList adaptive = new AdaptiveList(5,null);
        adaptive.setNext(new AdaptiveList(7,null));
        adaptive.prepend(2); //Prepend funktioniert, wenn aufrufendes Element die gesamte Liste ist (Anwendungsfall)
        adaptive.append(10); //Append funktioniert: Liste wird ganz hinten um 10 erweitert.
        System.out.println(adaptive);
//        System.out.println("Prepend und Append: " + adaptive);
//        System.out.println("Contains: " + adaptive.contains(5)); //Contains funktioniert
//        System.out.println("ContainsAdaptive" + adaptive.containsAdaptive(5));
//        System.out.println("ContainsAdaptive: " + adaptive); //ContainsAdaptive funktioniert für alle getesteten Fälle
        System.out.println("ContainsTopPriority: " + adaptive.containsTopPriority(10));
        System.out.println("ContainsTopPriority: " + adaptive);
    }
}
