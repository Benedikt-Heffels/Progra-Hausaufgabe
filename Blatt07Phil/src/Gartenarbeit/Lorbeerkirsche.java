package Gartenarbeit;

public final class Lorbeerkirsche extends Rosengewaechs {

	/*
	 * Constructors
	 */
	public Lorbeerkirsche() {
		super(20,2,1,3);
	}
	
	/*
	 * Methods
	 */
	@Override
	public void schneiden(int x){
		int newlength = laenge - x/2;
		laenge = newlength > 0 ? newlength : 0;
	}
}
