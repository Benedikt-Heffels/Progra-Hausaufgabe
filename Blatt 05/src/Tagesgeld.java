public class Tagesgeld {
  
    private int maxBetrag;
    private int angebotsmonate;
    private double angebotszinsen;
    private double normalzinsen;

    public Tagesgeld(int maxBetrag, int angebotsmonate, double angebotszinsen,
                     double normalzinsen) {
        this.maxBetrag = maxBetrag;
        this.angebotsmonate = angebotsmonate;
        this.angebotszinsen = angebotszinsen;
        this.normalzinsen = normalzinsen;
    }
    
    public int getMaxBetrag() {
        return maxBetrag;
    }
    
    public void setMaxBetrag(int maxBetrag) {
        this.maxBetrag = Math.max(0,maxBetrag);
    }

    public int getAngebotsmonate() {
        return angebotsmonate;
    }
    
    public void setAngebotsmonate(int angebotsmonate) {
        this.angebotsmonate = Math.max(0,angebotsmonate);
    }

    public double getAngebotszinsen() {
        return angebotszinsen;
    }
    
    public void setAngebotszinsen(double angebotszinsen) {
        this.angebotszinsen = angebotszinsen;
    } 

    public double getNormalzinsen() {
        return normalzinsen;
    }
    
    public void setNormalzinsen(double normalzinsen) {
        this.normalzinsen = normalzinsen;
    }
	
    //Diese Methode ist public, da die Berechnung auch in anderen Klassen gebraucht werden koennte
    //  Man kann aber auch argumentieren, dass sie als Hilfsmethode fuer die Klasse Tagesgeld private sein sollte
    //Sie ist ausserdem statisch, da sie nichts mit einem konkreten Tagesgeld-Objekt zu tun hat
	public static double jahresZuMonatszins(double jahreszins) {
	    return (Math.pow(1+jahreszins/100,1.0/12) - 1.0) * 100;
	}
    
    //Diese Methode ist private, da die Signatur nach aussen hin nicht sichtbar sein soll.
    //  Die Klasse soll ja gerade die Berechnung uebernehmen, wann welcher Zinssatz gilt,
    //  und dies nicht dem Nutzer ueberlassen.
    //Die Methode ist nicht statisch, denn sie hat mit dem Angebot eines konkreten Tagesgeld-Objekts zu tun
    private double monatsverzinsung(double init, boolean angebot) {
        double jz = angebot ? this.angebotszinsen : this.normalzinsen;
        double mz = jahresZuMonatszins(jz);
        return init * (1 + mz/100);
    }
	
    //Diese Methode ist public, weil wir von ausserhalb der Klasse Tagesgeld die Verzinsung eines
    //  Tagesgeld-Objekts berechnen koennen wollen
    //Daher ist sie auch nicht statisch, denn sie hat mit dem Angebot eines konkreten Tagesgeld-Objekts zu tun
	public double verzinse(double init, int monate) {
	    if (monate == 0) {
	       return init;
	    }
        double vorher = this.verzinse(init,monate - 1);
        double rest = 0.0;
        double grenze = Math.min(this.getMaxBetrag(),100000); //100.000 € als Kontostandsgrenze gemaess Aufgabe 7 c)
        if (vorher > grenze) {
            rest = vorher - grenze;
            vorher = grenze;
        }
        
        return monatsverzinsung(vorher,monate <= this.getAngebotsmonate()) + rest;
	}
    
    //Diese Methode ist public, da wir von ausserhalb der Klasse Tagesgeld das beste Tagesgeld fuer eine
    //  bestimmte Situation berechnen koennen wollen
    //Sie ist ausserdem statisch, da sie nicht auf einem Tagesgeld-Objekt aufgerufen wird, sondern eine
    //  Anzahl von Tagesgeld-Objekten uebergeben bekommt
    public static Tagesgeld bestesTagesgeld(double init, int monate, Tagesgeld... ts) {
        if (ts == null) {
            return null; //Methode darf sich in diesem Fall beliebig verhalten, also insb. null zurueckgeben
        }
        return ts[bestesTagesgeld(init, monate, 0, ts)];
    }
    
    //Diese Hilfsmethode ist private, da sie nur von obiger Methode gleichen Namens aufgerufen werden soll
    //Sie ist aber aus denselben Gruenden wie diese statisch
    private static int bestesTagesgeld(double init, int monate, int pos, Tagesgeld... ts) {
        if (pos == ts.length - 1) {
            return pos;
        }
        int bestes = bestesTagesgeld(init,monate,pos + 1,ts);
        if (ts[pos].verzinse(init,monate) > ts[bestes].verzinse(init,monate)) {
            return pos;
        } else {
            return bestes;
        }
    }



    // ---------------- Ergaenzungen aus den Hausaufgaben -------------------

    //public, da Zugriff auf die Methode von aussen gewünscht ist
    //Nicht-static, da Methode Zugriff auf die Objektattribute (wie angebotsmonate, angebotszinsen und normalzinsen) benoetigt.
    public double optimaleVerzinsung (double init, int nm){ //nm sind die Normalmonate
        if (nm == -angebotsmonate) {return init;} //Auf Basis von nm wird runtergezaehlt, wenn Bedingung wahr, dann sind nm und angebotsmonate durchlaufen
        double verzinseNachher = monatsverzinsung(optimaleVerzinsung(init, nm-1), nm <= 0); //Verzinsung wird zuletzt angewandt
        double verzinseVorher = optimaleVerzinsung(monatsverzinsung(init, nm <= 0), nm-1); //Verzinsung wird zuerst angewandt
        return Math.max(verzinseNachher, verzinseVorher);
    }
    /*
    Normalerweise greift diese Aufgabe auf die Berechnung der Zinsen und der Zinseszinsen entsprechend der Formel
    K = K0 * (1+(p/100))^n zurück, wobei K0 der Kontostand zu Beginn ist, p die Zinsen für das Konto in Prozent (daher
    die Division durch 100) und n die Anlagezeit in Jahren. In der Aufgabe liegen dabei zwei Zinssaetze mit einer Laufzeit
    von nm Normalmonaten und am Angebotsmonaten vor, sodass sich die Formel zu K = K0 * (1+pn/100)^nm*(1+pa/100)^am, wobei
    pn der Normalezinssatz und pa der Angebotszinssatz ist. Diese Berechnung ist - entsprechend der geltenden mathematischen
    Gesetze - Kommutativ, laesst sich also beliebig umstellen.
    Das trifft jedoch nicht auf die Implementation in Java über Doubles zu: Diese sind als Fliesskommazahlen nicht Kommutativ,
    sodass beim Vertauschen der Reihenfolge der Multiplikation (wie es in der Aufgabe geschieht) zu maginal unterschiedlichen
    Ergebnissen kommen kann. Das ist auf Bitshifts und die daraus resultierende Ungenauigkeiten beim Rechnen mit Fliesskommazahlen
    zurueckzufuehren, aufgrund der Art und Weise der Implementation von Fliesskommazahlen in Rechnern mit Mantisse und Exponenten.
     */



    //Rekursive Hilfsmethode zur Verkuerzung um die Kuerzeste Laufzeit: Ist verkuerze false, wird rekursiv die kuerzeste Angebotslaufzeit berechnet, die echt groesser 0 ist (sonst Fehlerrückgabe von Integer.MAX_VALUE). Ist verkuerze true, werden die Angebotsmonate der Objekte verkuerzt.
    //private Methode, da es sich um eine interne Hilfsmethode handelt, die entsprechend dem Prinzip der Datenabstraktion nach aussen hin abgeschirmt werden soll. Static, da kein Zugriff auf nicht-übergebene Objektattribute (also durch [Objekt].[Attribut]) notwendig.
    private static int verkuerzeUmKuerzesteLaufzeitHelp(boolean verkuerze, int i, int min, Tagesgeld... ts){
        if (!verkuerze) { //verkuerze false: Berechne die kuerzeste Laufzeit
            if (i >= ts.length - 1) { //Dann ist nur noch ein Element im var arg -> gebe direkt den int zurück
                if (ts[i].getAngebotsmonate() > 0) {
                    return ts[i].getAngebotsmonate();
                }
                else {
                    return Integer.MAX_VALUE; //Wenn Angebotsmonate = 0, soll der Fehlerzustand Integer.MAX_VALUE() zurueckgegeben werden
                }
            }
            if (ts[i].getAngebotsmonate() == 0) { //Darf nicht null sein
                min = verkuerzeUmKuerzesteLaufzeitHelp(verkuerze, i + 1, min, ts); //Falls Angebotsmonate = 0, ist das Minimum automatisch die Angebotsmonate des naechsten Monats (bzw. ggf. Integer.MAX_VALUE() fuer das letzte Element des var args.
            } else {
                min = Math.min(ts[i].getAngebotsmonate(), verkuerzeUmKuerzesteLaufzeitHelp(verkuerze, i + 1, min, ts)); //Bilde das Minimum aus den aktuellen Angebotsmonaten und den Angebotsmonaten des naechsen ELements
            }
            return min;
        }
        else { //verkuerze true: Verkuerze die Laufzeiten
            if (i > ts.length - 1) {
                return 0;
            }
            ts[i].setAngebotsmonate(ts[i].getAngebotsmonate() - min); //setAngebotsmonate settet das Maximum zwischen 0 und der Eingabe -> in sofern ist eine if-Abfrage nicht notwendig, da automatisch Angebotsmonate nicht negativ.
            verkuerzeUmKuerzesteLaufzeitHelp(verkuerze, i + 1, min, ts); //rufe rekursiv das naechste Element des var args auf und verkuerze dort ebenfalls.
            return 0;
        }
    }


    //Zunaechst soll die Methode die kuerzeste echt positive (>0) Laufzeit der Angebotsmonate berechnen. Wenn verkuerze true ist, sollen zusaetzlich alle anderen Tagesgeldkonten um genau diese Laufzeit verringert werden. Gibt es keine kleinste echt-positive Laufzeit >0, so wird Integer.MAX_VALUE zurückgegeben.
    //public Methode, da man die Methode von aussen aufrufen koennen soll. Static, da kein Zugriff auf nicht-übergebene Objektattribute (also durch [Objekt].[Attribut]) erfolgen soll.
    public static int verkuerzeUmKuerzesteLaufzeit(boolean verkuerze, Tagesgeld... ts) {
        if (ts.length == 0 || ts == null) {
            return Integer.MAX_VALUE;
        }
        int min = verkuerzeUmKuerzesteLaufzeitHelp(false,0, Integer.MAX_VALUE, ts); //verkuerze muss false sein, damit die kuerzesten Angebotsmonate berechnet werden.
        if (min == Integer.MAX_VALUE) {
            return min; //Dann gibt es keine Angebotsmonate, die echt groesser 0 sind -> Aufgabenstellung: return Integer.MAX_VALUE. Ein return muss frueher stattfinden, da sonst eventuell verkuerzt wird.
        }
        if (verkuerze) {
            verkuerzeUmKuerzesteLaufzeitHelp(true, 0, min, ts); //verkuerze muss true sein, um die Angebotsmonate zu berechnen. Das wird aber auch ueber die if-Abfrage abgefragt.
        }
        return min;
    }



    //public, da die Methode von ausserhalb der Klasse verwendet werden soll
    //static, weil es nicht auf Variablen der eigenen Klasseninstanz zugreift, sondern die relevanten Tagesgeld-Objekte als var arg uebergeben bekommt
    public static double verzinseParallel(double init, int monate, Tagesgeld... ts){
        return verzinseParallelHelper(init, monate, 0, ts); //nutzt die Helper-Methode und gibt deren Ergebnis zurueck
    }


    //private weil es eine Hilfsmethode fuer die Implementation von verzinseParallel ist und nach der Datenabstraktion nicht ausserhalb der Klasse aufgerufen werden soll
    //static, weil es nicht auf Variablen der eigenen Klasseninstanz zugreifen soll, sondern die relevanten Tagesgeld-Objekte als Array uebergeben bekommt
    private static double verzinseParallelHelper(double init, int monate, int i, Tagesgeld... ts){
         if(i > ts.length - 1) {//Keine unbenutzten Tagesgelder -> Man kann nichts mehr verzinsen -> init Restguthaben wird direkt zurueckgegeben
             return init;
         }
         if(ts[i].getAngebotsmonate() <= 0){//Keine Angebotsmonate -> Verzinse mit naechstem Tagesgeld
             return verzinseParallelHelper(init, monate, i+1, ts);
         }
         double maxBetrag = Math.min(ts[i].getMaxBetrag(), 100000);//Nach der Aufgabenstellung besteht eine Verzinsungsgrenze von 100.000 (oder maxBetrag, je nachdem, was niedriger ist)
         if(init <= maxBetrag) {//Betrag kann vollstaendig in Angebotsmonaten angelegt werden -> verzinse mit diesem Tagesgeld
             return ts[i].verzinse(init, monate);
         }
         if(init > maxBetrag) {//Betrag kann teilweise in Angebotsmonaten angelegt werden -> Rekursion mit Rest des Betrags addiert zum verzinsten Teilbetrag
             return verzinseParallelHelper(init - maxBetrag, monate, i+1, ts) + ts[i].verzinse(maxBetrag, monate);
         }
         //this should never be reached (alternative to throwing error)
         return 0;
    }



//     ---------------------- Tests fuer verzinseParallel --------------------------------
    public static void main(String... args) {
        Tagesgeld t0 = new Tagesgeld(5000,9,3.5,1.5);
        Tagesgeld t1 = new Tagesgeld(50000,6,3.0,2.0);
        Tagesgeld t2 = new Tagesgeld(150000,5,4.5,1.75);
        Tagesgeld t3 = new Tagesgeld(60000,8,3.5,1.25);

        System.out.println(10080.148839674815 + " (expected)");
        System.out.println(verzinseParallel(10000,3,t0,t1,t2,t3));
        System.out.println(10160.572744118412 + " (expected)");
        System.out.println(verzinseParallel(10000,6,t0,t1,t2,t3));
        System.out.println(10272.751782132696 + " (expected)");
        System.out.println(verzinseParallel(10000,12,t0,t1,t2,t3));

        System.out.println();

        System.out.println(100910.9285190934 + " (expected)");
        System.out.println(verzinseParallel(100000,3,t0,t1,t2,t3));
        System.out.println(101725.24878187178 + " (expected)");
        System.out.println(verzinseParallel(100000,6,t0,t1,t2,t3));
        System.out.println(102682.28545476876 + " (expected)");
        System.out.println(verzinseParallel(100000,12,t0,t1,t2,t3));

        System.out.println();

        System.out.println(252032.2085361965 + " (expected)");
        System.out.println(verzinseParallel(250000,3,t0,t1,t2,t3));
        System.out.println(253841.61267414704 + " (expected)");
        System.out.println(verzinseParallel(250000,6,t0,t1,t2,t3));
        System.out.println(255859.91900185865 + " (expected)");
        System.out.println(verzinseParallel(250000,12,t0,t1,t2,t3));

        System.out.println();

        System.out.println(1002032.2085361965 + " (expected)");
        System.out.println(verzinseParallel(1000000,3,t0,t1,t2,t3));
        System.out.println(1003841.6126741471 + " (expected)");
        System.out.println(verzinseParallel(1000000,6,t0,t1,t2,t3));
        System.out.println(1005859.9190018587 + " (expected)");
        System.out.println(verzinseParallel(1000000,12,t0,t1,t2,t3));
    }
}
