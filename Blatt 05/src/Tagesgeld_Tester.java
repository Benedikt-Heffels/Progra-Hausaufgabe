public class Tagesgeld_Tester {
    public static void main(String[] args) {

        System.out.println("--------- Test der oprimalen Verzinsung ------------");
        Tagesgeld ts0 = new Tagesgeld(5000,1,5.5,1.5);
        System.out.println(ts0.optimaleVerzinsung(10000, 1));
        System.out.println("10057.187381927695 ist der Zielwert");
        System.out.println("\n\n--------Test der Verkuerze-Methode--------");
        Tagesgeld ts1 = new Tagesgeld(5000, 1, 10, 0);
        Tagesgeld ts2 = new Tagesgeld(5000, 0, 10, 0);
        Tagesgeld ts3 = new Tagesgeld(5000, 1, 10, 0);
        System.out.println("Integer.MAX_VALUE() " + Integer.MAX_VALUE);
        int returnValue = Tagesgeld.verkuerzeUmKuerzesteLaufzeit(false, ts1,ts2,ts3);
        System.out.println("returnValue: " + returnValue);
        System.out.println("ts1 Angebotsmonate: " + ts1.getAngebotsmonate());
        System.out.println("ts2 Angebotsmonate: " + ts2.getAngebotsmonate());
        System.out.println("ts3 Angebotsmonate: " + ts3.getAngebotsmonate());

    }
}
