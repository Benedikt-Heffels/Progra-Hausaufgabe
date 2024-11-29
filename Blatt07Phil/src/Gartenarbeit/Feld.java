package Gartenarbeit;

public class Feld {

	public static Pflanze auswahl(PflanzenPaar pair) {
        Pflanze res;
        switch (pair){
            case PflanzenPaar(Rosengewaechs r, _) -> {
                res = r;
            }
            case PflanzenPaar(_, Rosengewaechs r) -> {
                res = r;
            }
            case PflanzenPaar(Salbei s, BlauerEisenhut b) -> { //Wenn BlauerEisenhut vorne steht muss er eh immer genommen werden -> hier einziger Edge Case
                res = s.laenge >= 5 ? b : s;
            }
            default -> res = pair.p1();
        }
        if(res instanceof Rosengewaechs r) {
            while (r.laenge < r.maxLaenge) {
                r.waessern();
            }
            if (r instanceof Himbeere h) {
                h.schneiden(1);
            }
            return r;
        }
        return res;
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