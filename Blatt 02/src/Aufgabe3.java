public class Aufgabe3 {
    public static void main(String[] args) {
        int tageskomponente = SimpleIO.getInt("Bitte geben Sie die Tageskomponente des Startdatums ein.");
        int monatskomponente = SimpleIO.getInt("Bitte geben Sie die Monatskomponente des Startdatums ein.");
        int jahreskomponente = SimpleIO.getInt("Bitte geben Sie die Jahreskomponente des Startdatums ein.");
        int anzahlTage = SimpleIO.getInt("Bitte geben Sie die Anzahl an Tagen ein.");
        int monatstage = 31;
        for (int i = 1; i <= anzahlTage; i++) {
            tageskomponente ++;
            switch (monatskomponente) {
                case 1,3,5,7,8,10,12 -> monatstage = 31;
                case 4,6,9,11 -> monatstage = 30;
                case 2 -> monatstage = 28;
            }
            if (tageskomponente > monatstage) {
                monatskomponente += 1;
                tageskomponente = 1;
            }
            if (monatskomponente > 12 && tageskomponente == 1) {
                jahreskomponente += 1;
                monatskomponente = 1;
            }
        }
        SimpleIO.output("Das Datum " + tageskomponente + "." + monatskomponente + "." + jahreskomponente + " befindet sich " + anzahlTage + " Tage nach dem Startdatum.");
    }
}
