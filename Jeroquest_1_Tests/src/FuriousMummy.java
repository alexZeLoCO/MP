
public class FuriousMummy extends Mummy {
		
	public FuriousMummy (String itsName) {
		super(itsName);
	}
	
	@Override
	/** 
	 * Computes the attack of a character getting the number of impacts,
	 * to do so roll as many dices as indicated by the attribute attack
	 * @return the number of impacts
	 */
	public int attack() {
		int impacts = 0;
		for (int x = 0; x < getAttack(); x++)
			if (Dice.roll() > 3)
				impacts++;
		return 2*impacts;
	}
	
	@Override
	/** 
	 * The mummy defends itself from an attack
	 * (Implementing an inherited abstract method)
	 * @param impacts the total number of impacts to try to block or receive
	 * @return the number of wounds suffered
	 */
	public int defend(int impacts)
	{
		setBody(Math.max(0, getBody() - impacts));
		System.out.printf("The mummy " + this.getName() + " cannot block %d impacts%s", impacts,
				(isAlive() ? "\n" : " and dies\n"));
		return impacts;
	}
}
