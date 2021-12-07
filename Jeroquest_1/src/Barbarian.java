/**
 * Programming Methodology Practice.
 * Jeroquest - An example of Object Oriented Programming.
 * Class Barbarian
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo Cerdán
 * @version 1
 * 
 */

public class Barbarian extends Character {
	// initial value for the attributes
	protected static final int MOVEMENT = 7;
	protected static final int ATTACK = 1;
	protected static final int DEFENSE = 2;
	protected static final int BODY = 8;

	/**
	 * Create a barbarian with its name
	 * @param itsName Barbarian's name
	 */
	public Barbarian(String itsName)
	{
		// setting the attributes with the initial values
		super(itsName, MOVEMENT, ATTACK, DEFENSE, BODY);
	}
	
	/** 
	 * The barbarian defends itself from an attack
	 * (Implementing an inherited abstract method)
	 * @param impacts the total number of impacts to try to block or receive
	 * @return the number of wounds suffered
	 */
	public int defend(int impacts)
	{
		// trying to block the impacts with its defense
		for(int totalDefenseDices = getDefense(); (impacts > 0) && (totalDefenseDices > 0);
				totalDefenseDices--)
			if (Dice.roll() > 4) // a 5 or 6 is necessary to block an impact
				impacts --;
		
		// if there are unblocked impacts reduce body points
		if(impacts > 0)
		{
			// a character life cannot be lower than zero
			setBody(Math.max(0, getBody() - impacts));
			System.out.printf("The barbarian " + this.getName() + " cannot block %d impacts%s", impacts,
					(isAlive() ? "\n" : " and dies\n"));
		}
		else
		{
			System.out.printf("The barbarian blocks completely the attack\n");
		}
		
		return impacts;
	}


	/** 
	 * Generate a printable String version of the object
	 *  (overriden method)
	 * @return the barbarian's printable info as a String 
	 */
	public String toString()
	{
		return String.format("The barbarian: %s", super.toString());
	}

}
