package jeroquest.units;

import javax.swing.*;

public class Wasp extends Monster {

	// initial values for the attributes
	protected static final int MOVEMENT = 0;
	protected static final int ATTACK = 1;
	protected static final int DEFENCE = 1;
	protected static final int BODY = 1;

	/**
	 * Create a Wasp from its name
	 *
	 * @param name name of the Wasp
	 */
	public Wasp(String name) {
		super(name, MOVEMENT, ATTACK, DEFENCE, BODY);
	}

	public Wasp (String name, int ATTAK, int DEFENCE){
		super (name, MOVEMENT, ATTAK, DEFENCE, BODY);
	}

	// He protec he attak ^^
}
