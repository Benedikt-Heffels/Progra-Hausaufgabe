public sealed class Rosengewaechs extends Pflanze permits Himbeere, Lorbeerkirsche {


	protected int verbreitung;

	public Rosengewaechs(int maxLaenge, int wachstum, int laenge, int verbreitung) {
		super(maxLaenge, wachstum, laenge);
		this.verbreitung = verbreitung;
	}


	public int getVerbreitung() {
		return this.verbreitung;
	}
	

	@Override
	public void waessern()
	{
		int newlaenge = this.wachstum*this.verbreitung + this.laenge;
		this.laenge = newlaenge > this.maxLaenge ? this.maxLaenge : newlaenge; //Wenn Rose gegossen wird, waechst sie um die Wachstumsrate*Verbreitung, allerdings maximal bis zur maxLaenge.
	}

	@Override
	public void schneiden(int x){
		this.laenge = this.laenge == 0 ? 0 : 1;
	} //Rosengewaechse werden immer auf die Laenge 1 runtergeschnitten, wachsen jedoch auch nicht durch das schneiden (case laenge = 0)


}
