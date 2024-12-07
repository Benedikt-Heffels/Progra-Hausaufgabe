public final class BlauerEisenhut extends Pflanze {
	
	/*
	 * Constructors
	 */
	public BlauerEisenhut(){
		super(Integer.MAX_VALUE, 1, 1);
	} //Werte gegeben aus Aufgabenstellung. Blauer Eisenhut hat keine maxLaenge, stattdessen wird der groesst moegliche Integer-Wert gesetzt


    /*
     * Methods
     */

	@Override
	public void schneiden(int x){
		this.laenge = this.laenge == 0 ? 0 : 1; //BlauerEisenhut wird immer auf die Laenge 1 runtergeschnitten
	}

}
