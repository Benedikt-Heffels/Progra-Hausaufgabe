package Gartenarbeit;

public sealed class Rosengewaechs extends Pflanze permits Himbeere, Lorbeerkirsche {

	/*
	 * Attributes
	 */
	protected int verbreitung;

	/*
	 * Constructors
	 */
	public Rosengewaechs(int maxLaenge, int wachstum, int laenge, int verbreitung) {
		super(maxLaenge, wachstum, laenge);
		this.verbreitung = verbreitung;
	}

	/*
	 * Getter
	 */
	public int getVerbreitung() {
		return verbreitung;
	}
	
	/*
	 * Methods
	 */
	@Override
	public void waessern()
	{
		int newlaenge = wachstum*verbreitung + laenge;
		laenge = newlaenge > maxLaenge ? maxLaenge : newlaenge;
	}

	@Override
	public void schneiden(int x){
		laenge = laenge == 0 ? 0 : 1;
	}


}
