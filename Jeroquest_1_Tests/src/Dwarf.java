
public class Dwarf extends Hero {

	protected static final int MOVEMENT = 6;
	protected static final int ATTACK = 1;
	protected static final int DEFENCE = 2;
	protected static final int BODY = 7;
	protected Weapon weapon;
	
	public Dwarf (String itsName) {
		super(itsName, MOVEMENT, ATTACK, DEFENCE, BODY);
		setWeapon(new Weapon("Hand axe", 2));
	}
	
	public void setWeapon (Weapon weapon) {
		this.weapon=weapon;
	}
	
	public Weapon getWeapon() {
		return this.weapon;
	}
	
	@Override
	public int getAttack() {
		if (getWeapon()!=null) {
			return getWeapon().getAttack();
		} else {
			return super.getAttack();
		}
	}
	
	/** 
	 * The dwarf defends itself from an attack
	 * (Implementing an inherited abstract method)
	 * @param impacts the total number of impacts to try to block or receive
	 * @return the number of wounds suffered
	 */
	public int defend(int impacts)
	{
		// trying to block the impacts with its defence
		for(int totalDefenceDices = getDefence(); (impacts > 0) && (totalDefenceDices > 0);
				totalDefenceDices--)
			if (Dice.roll() > 4) // a 5 or 6 is necessary to block an impact
				impacts --;
		
		// if there are unblocked impacts reduce body points
		if(impacts > 0)
		{
			// a character life cannot be lower than zero
			setBody(Math.max(0, getBody() - impacts));
			System.out.printf("The dwarf " + this.getName() + " cannot block %d impacts%s", impacts,
					(isAlive() ? "\n" : " and dies\n"));
		}
		else
		{
			System.out.printf("The dwarf blocks completely the attack\n");
		}
		
		return impacts;
	}
	
	/** 
	 * Generate a printable String version of the object
	 *  (overriden method)
	 * @return the dwarf's printable info as a String 
	 */
	public String toString()
	{
		if (getWeapon()!=null) {
		return String.format("The dwarf: %s \n\t%s.", super.toString(),getWeapon().toString());
		} else {
			return String.format("The dwarf: %s \n\tWeapon: %s.", super.toString(),"None");
		}
		
	}
}
