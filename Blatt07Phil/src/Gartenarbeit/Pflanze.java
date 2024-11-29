package Gartenarbeit;

public sealed class Pflanze permits BlauerEisenhut, Salbei, Rosengewaechs {

	/*
	 * Attributes
	 */
	protected int maxLaenge, wachstum, laenge;

	/*
	 * Constructors
	 */
	public Pflanze(int maxLaenge, int wachstum, int laenge) {
		this.maxLaenge = maxLaenge;
		this.wachstum = wachstum;
		this.laenge = laenge <= maxLaenge ? laenge : 0;
	}

	/*
	 * Getter
	 */
	public int getMaxLaenge() {
		return maxLaenge;
	}
	public int getWachstum() {
		return wachstum;
	}
	public int getLaenge() {
		return laenge;
	}
	
	/*
	 * Methods
	 */
	public void waessern(){
		int newlaenge = wachstum + laenge;
		laenge = newlaenge > maxLaenge ? maxLaenge : newlaenge;
	}

	public void schneiden(int x){
		int newlength = laenge - x;
		laenge = newlength > 0 ? newlength : 0;
	}

}
