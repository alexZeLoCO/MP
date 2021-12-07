package jeroquest.units;

import javax.swing.*;

public class Wasp extends Monster {

	// initial values for the attributes
	protected static final int MOVEMENT = 0;
	protected static final int ATTACK = 1;
	protected static final int DEFENCE = 1;
	protected static final int BODY = 1;

	/**
	 * Create a wasp from its name
	 *
	 * @param name name of the wasp
	 */
	public Wasp(String name) {
		super(name, MOVEMENT, ATTACK, DEFENCE, BODY);
	}


	public Wasp(String name, int attack, int body) {
		super(name, MOVEMENT, attack, DEFENCE, body);
	}
}
