public sealed class Pflanze permits BlauerEisenhut, Salbei, Rosengewaechs {

	protected int maxLaenge, wachstum, laenge;


	public Pflanze(int maxLaenge, int wachstum, int laenge) {
		this.maxLaenge = maxLaenge;
		this.wachstum = wachstum;
		this.laenge = laenge <= maxLaenge ? laenge : 0; //Falls momentaneLaenge groesser als maxLaenge, soll Laenge stattdessen auf 0 gesetzt werden
	}

	public int getMaxLaenge() {
		return this.maxLaenge;
	}
	public int getWachstum() {
		return this.wachstum;
	}
	public int getLaenge() {
		return this.laenge;
	}
	

	public void waessern(){
		int newlaenge = this.wachstum + this.laenge;
		this.laenge = newlaenge > this.maxLaenge ? this.maxLaenge : newlaenge; //Wenn Pflanze gegossen wird, waechst sie um die Wachstumsrate, allerdings maximal bis zur maxLaenge.
	}

	public void schneiden(int x){
		int newlength = this.laenge - x;
		this.laenge = newlength > 0 ? newlength : 0; //Beim Schneiden darf keine negative Groesse entstehen!
	}

}
