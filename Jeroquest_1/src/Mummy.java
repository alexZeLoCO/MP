/**
 * Programming Methodology Practice.
 * Jeroquest - An example of Object Oriented Programming.
 * Class Mummy
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo Cerdán
 * @version 1
 * 
 */

public class Mummy extends Character {

	// valores iniciales de los atributos
	protected static final int MOVEMENT = 4;
	protected static final int ATTACK = 3;
	protected static final int DEFENSE = 4;
	protected static final int BODY = 2;

	/**
	 * Create a mummy with its name
	 * @param itsName Mummy's name
	 */
	public Mummy(String itsName)
	{
		// setting the attributes with the initial values
		super(itsName, MOVEMENT, ATTACK, DEFENSE, BODY);
	}
	
	/** 
	 * The mummy defends itself from an attack
	 * (Implementing an inherited abstract method)
	 * @param impacts the total number of impacts to try to block or receive
	 * @return the number of wounds suffered
	 */
	public int defend(int impacts)
	{
		// trying to block the impacts with its defense
		for(int totalDefenseDices = defense; (impacts > 0) && (totalDefenseDices > 0); totalDefenseDices--)
			if (Dice.roll() == 6) // a 6 is necessary to block an impact
				impacts --;
		
		// if there are not blocked impacts reduce body points
		if(impacts > 0)
		{
			// a character life cannot be lower than zero
			body = Math.max(0, body - impacts);
			System.out.printf("The mummy " + this.getName() + " cannot block %d impacts%s", impacts,
					(isAlive() ? "\n" : " and dies\n"));
		}
		else
		{
			System.out.printf("The mummy blocks completely the attack\n");
		}
		
		return impacts;
	}
	
	/** 
	 * Generate a printable String version of the object
	 *  (overriden method)
	 * @return the mummy's printable info as a String 
	 */
	public String toString()
	{
		return String.format("The mummy: %s", super.toString());
	}
}
