public final class Lorbeerkirsche extends Rosengewaechs {


	public Lorbeerkirsche() {
		super(20,2,1,3);
	} //Werte vorgegeben aus Aufgabenstellung
	

	@Override
	public void schneiden(int x){
		int newlength = laenge - x/2; //Geschnitten werden soll nur um die abgerundete Haelfte der Schnittlaenge. Fuer die int-Konvertierung werden dabei die Nachkommastellen automatisch abgeschnitten -> Wird abgerundet
		laenge = newlength > 0 ? newlength : 0; //Beim Schneiden darf keine negative Groesse entstehen!
	}
}
