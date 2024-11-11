public class Tutoraufgabe2 {
    public static void main(String[] args) {
        int ergebnis, neueZahl;
        ergebnis = SimpleIO.getInt("Bitte geben Sie eine Zahl ein");
        boolean running = true;
        String eingabe;
        while (running) { //Alternativ: Abfragen, ob eingabe Stop ist (als Schleifenbedingung verwenden
            eingabe = SimpleIO.getString("Bitte geben Sie eine Rechenoperation (ADD oder SUB) oder STOP ein:");
            if (eingabe.equals("ADD") || eingabe.equals("SUB")){
                neueZahl = SimpleIO.getInt("Bitte geben Sie eine Zahl ein:");
                if (eingabe.equals("ADD")){
                    ergebnis = ergebnis + neueZahl;
                }
                else {
                    ergebnis = ergebnis - neueZahl;
                }
                SimpleIO.output("Aktuelles Ergebnis: " +  ergebnis);
            }
            else {
                running = false;
            }
        }
        SimpleIO.output("Endergebnis: " + ergebnis);

    }
}
