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
        double grenze = Math.min(this.getMaxBetrag(),100000); //100.000 € als Kontostandsgrenze gemäß Aufgabe 7 c)
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



    // ---------------- Ergänzungen aus den Hausaufgaben -------------------

    private double verzinsenOhneHoechstgrenze_am(double init, int monate) {
        //Methode definiert im Tutorium. Hier die Subtraktion des Rests (Überschreitung des maxBetrags) entfernt, da diese nach Aufgabenstellung nicht nötig.
        //nicht-static, da Zugriff auf Attribute des Tagesgeld benoetigt. private, da interne Hilfsmethode (kein Zugriff von außen nötig und entsprechend der Datenkapselung erwuenscht)
        if (monate == 0) return init;
        boolean in_angebot = monate <= angebotsmonate; //Entscheidet, welcher Zinssatz
        return verzinsenOhneHoechstgrenze_am(monatsverzinsung(init, in_angebot), monate-1); //Rekursiv: Ruft die Methode mit dem Kontostand nach Monat (monatsverzinsung) erneut auf.
    }

    private double verzinseOhneHoechstgrenze_nm(double init, int monate, int nm) {
        //Rekursive Methode zur Bestimmung des Kontostandes nach monate Monate, wenn zuerst der Normalzins angewandt wird.
        //int monate sind die Monate, die verzinst wird; int nm sind die Normalmonate (waehrend diesen laueft die Verzinsung über die Normalzinsen)
        //nicht-static, da Zugriff auf Attribute des Tagesgeld benoetigt. private, da interne Hilfsmethode (kein Zugriff von außen nötig und entsprechend der Datenkapselung erwuenscht).
        if (monate == 0) return init;
        boolean in_nm = monate >= nm;
        boolean zinssatz = in_nm ? false : true; //Die Methode Monatsverzinsung geht bei einem boolean true davon aus, dass sie die Angebotszinsen anwenden kann. Das soll, wenn in_nm true, nicht geschehen.
        double nach_monat = monatsverzinsung(init, zinssatz); //Rückgriff auf Methode zur Berechnung der Monatsverzinsung aus Tutorium. Der max_Betrag wird in der Methode nicht betrachtet, sodass sie auch hier anwendbar ist.
        return verzinseOhneHoechstgrenze_nm(nach_monat, monate-1, nm);
    }

    public double optimaleVerzinsung(double init, int nm) {
        //int nm sind die Normalmonate. Nicht-static, da die Methode auf die Objektattribute zugreifen können muss. public, da die Methode von außerhalb aufrufbar sein soll.
        double endbetrag_am = verzinsenOhneHoechstgrenze_am(init, nm + angebotsmonate);
        double endbetrag_nm = verzinseOhneHoechstgrenze_nm(init, nm+angebotsmonate,nm);
        return Math.min(endbetrag_am, endbetrag_nm);
        /*
        @TODO: Erklärung, warum das Unterschiede macht (+ ggf. was besser ist).
         */
    }


    private static int verkuerzeUmKuerzesteLaufzeitHelp(boolean verkuerze, int i, int min, Tagesgeld... ts){
        if (!verkuerze) {
            if (i >= ts.length - 1) {
                return ts[i].getAngebotsmonate();
            }
            if (ts[i].getAngebotsmonate() == 0) { //Darf nicht null sein
                min = verkuerzeUmKuerzesteLaufzeitHelp(verkuerze, i + 1, min, ts);
            } else {
                min = Math.min(ts[i].getAngebotsmonate(), verkuerzeUmKuerzesteLaufzeitHelp(verkuerze, i + 1, min, ts));
            }
//        if(verkuerze) {
//            ts[i].setAngebotsmonate(ts[i].getAngebotsmonate() - min);
//        }
            return min;
        }
        else {
            if (i >= ts.length - 1) {
                return 0;
            }
            if (ts[i].getAngebotsmonate() != 0) {
                ts[i].setAngebotsmonate(ts[i].getAngebotsmonate() - min);
            }
            verkuerzeUmKuerzesteLaufzeitHelp(verkuerze, i + 1, min, ts);
            return 0;
        }
    }

    public static int verkuerzeUmKuerzesteLaufzeit(boolean verkuerze, Tagesgeld... ts) {
        if (ts.length == 0 || ts == null) {
            return Integer.MAX_VALUE;
        }
        int min = verkuerzeUmKuerzesteLaufzeitHelp(verkuerze,0, Integer.MAX_VALUE, ts);
        if (min == Integer.MAX_VALUE) {
            return min; //Dann gibt es keine Angebotsmonate, die echt größer 0 sind -> Aufgabenstellung: return Integer.MAX_VALUE
        }
        verkuerzeUmKuerzesteLaufzeitHelp(verkuerze,0, min, ts);
        return 0;
        //@TODO
    }

    public static double verzinseParallel(double init, int monate, Tagesgeld... ts) {
        return  0;
    }


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
