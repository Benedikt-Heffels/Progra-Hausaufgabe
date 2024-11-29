package Gartenarbeit;

public final class Himbeere extends Rosengewaechs {

	/*
	 * Constructors
	 */
	public Himbeere() {
		super(10,1,1,2);
	}
	
	/*
	 * Methods
	 */
	@Override
	public void schneiden(int x){
		int newlength = laenge - x;
		laenge = newlength > 0 ? newlength : 0;
	}

}
