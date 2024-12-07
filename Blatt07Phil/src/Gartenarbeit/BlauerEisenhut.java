package Gartenarbeit;

public final class BlauerEisenhut extends Pflanze {
	
	/*
	 * Constructors
	 */
	public BlauerEisenhut(){
		super(Integer.MAX_VALUE, 1, 1);
	}


    /*
     * Methods
     */

	@Override
	public void schneiden(int x){
		laenge = laenge == 0 ? 0 : 1;
	}

}
