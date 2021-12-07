package jeroquest.units;

public class Hornet extends Wasp {

	// initial values for the attributes
	protected static final int ATTACK = 2;
	protected static final int BODY = 2;

	/**
	 * Create a hornet from its name
	 *
	 * @param name name of the hornet
	 */
	public Hornet(String name) {
		super(name, ATTACK, BODY);
	}


}
