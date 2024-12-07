public class Feld {

	public static Pflanze auswahl(PflanzenPaar pair) {
        Pflanze resultPflanze;
        switch (pair){
            case PflanzenPaar(Rosengewaechs r, _) -> { //Rosengewaechse werden vor allem anderen bevorzugt.
                resultPflanze = r;
            }
            case PflanzenPaar(_, Rosengewaechs r) -> { //Rosengewaechse werden vor allem anderen bevorzugt
                resultPflanze = r;
            }
            case PflanzenPaar(BlauerEisenhut b, Salbei s) -> { //Wenn Laenge des Salbeis >= 5: Waehle BlauerEisenhut, sonst: Otto kann sich nicht entscheiden: Waehle erste Pflanze des Paars -> Blauer Eisenhut
                return b;
            }
            case PflanzenPaar(Salbei s, BlauerEisenhut b) -> { //Wenn Laenge des Salbeis >= 5: Waehle BlauerEisenhut, sonst: Otto kann sich nicht entscheiden: Waehle erste Pflanze des Paars -> salbei
                resultPflanze = s.getLaenge() >= 5 ? b : s;
            }
            case PflanzenPaar(Pflanze p, _) -> { //Wenn Otto sich nicht entscheiden kann, waehlt er immer die erste Pflanze
                resultPflanze = p;
            }
        }
        if(resultPflanze instanceof Rosengewaechs r) { //Falls die Pflanze ein Rosengewaechs ist, waessere bis sie die maximale Groesse erreicht hat
            while (r.getLaenge() < r.getMaxLaenge()) {
                r.waessern();
            }
            if (r instanceof Himbeere h) { //Falls Pflanze eine Himbeere, schneidet Otto sie im Anschluss um 1 runter
                h.schneiden(1);
            }
            return r;
        }
        return resultPflanze;
	}
	
	public static void main(String[] args) {
        System.out.println("Start");
		Himbeere himbeere = new Himbeere();
		Lorbeerkirsche lorbeerkirsche = new Lorbeerkirsche();
		Salbei salbeiLang = new Salbei();
        lorbeerkirsche.waessern();
        lorbeerkirsche.schneiden(6);
        salbeiLang.waessern();
        salbeiLang.waessern();
        salbeiLang.waessern();
        salbeiLang.waessern();
		Salbei salbei = new Salbei();
		BlauerEisenhut blauerEisenhut = new BlauerEisenhut();
        blauerEisenhut.schneiden(1);

        PflanzenPaar pair1 = new PflanzenPaar(himbeere, salbei);
        PflanzenPaar pair2 = new PflanzenPaar(salbei, lorbeerkirsche);
        PflanzenPaar pair3 = new PflanzenPaar(lorbeerkirsche, blauerEisenhut);
        PflanzenPaar pair4 = new PflanzenPaar(salbeiLang, blauerEisenhut);
        PflanzenPaar pair5 = new PflanzenPaar(salbei, blauerEisenhut);

        System.out.println("Auswahl:" + auswahl(pair1) + ", Laenge:" + auswahl(pair1).getLaenge());
        System.out.println("Auswahl:" + auswahl(pair2) + ", Laenge:" + auswahl(pair2).getLaenge());
        System.out.println("Auswahl:" + auswahl(pair3) + ", Laenge:" + auswahl(pair3).getLaenge());
        System.out.println("Auswahl:" + auswahl(pair4) + ", Laenge:" + auswahl(pair4).getLaenge());
        System.out.println("Auswahl:" + auswahl(pair5) + ", Laenge:" + auswahl(pair5).getLaenge());
	}
	
}