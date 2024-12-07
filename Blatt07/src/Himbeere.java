public final class Himbeere extends Rosengewaechs {


	public Himbeere() {
		super(10,1,1,2);
	} //Werte vorgegeben aus Aufgabenstellung
	

	@Override
	public void schneiden(int x){ //Muss hier komplett neu implementiert werden, da sonst Rueckgriff auf Methode aus Rosengewaechs (ueberschreibt Methode aus Pflanze).
		int newlength = laenge - x;
		laenge = newlength > 0 ? newlength : 0; //Beim Schneiden darf keine negative Groesse entstehen!
	}

}
