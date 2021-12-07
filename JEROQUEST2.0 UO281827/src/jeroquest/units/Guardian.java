package jeroquest.units;

import jeroquest.logic.Game;

import javax.swing.*;

public class Guardian extends Character {
	// initial values for the attributes
	protected static final int MOVEMENT = 5;
	protected static final int ATTACK = 5;
	protected static final int DEFENCE = 5;
	protected static final int BODY = 5;

	/**
	 * Create a barbarian from its name and the player name
	 *
	 * @param name   barbarian's name
	 */
	public Guardian(String name) {
		super(name, MOVEMENT, ATTACK, DEFENCE, BODY);
	}

	@Override
	public void combat(Character c, Game currentGame) { // attacks to c and c defends itself
		if (c instanceof Hero) {
			if ( ((Hero) c).hasWeapon()) {
				System.out.printf("%s was violent and has been taken by %s",c.getName(), this.getName());
				((Hero) c).setWeapon (new Weapon ());
			} else {
				System.out.printf("%s was violent and has been handcuffed by %s",c.getName(), this.getName());
				((Hero) c).setWeapon(new Weapon("Handcuffs", 0));
			}
		} else {
			System.out.printf("%s was violent and has been given 2 No Play Turns by %s",c.getName(), this.getName());
			((Monster) c).setNoPlayTurns(2);
		}
		c.setViolent(false);
	}


	@Override
	public boolean isEnemy(Character c) {
		return c.isViolent();
	}

	public int defend (int n) {
		return 0;
	}

	/************************************************
	 * Interface Piece implementation
	 **********************************************/

	/**
	 * Generate a text representation of the character in the board (implementing an
	 * abstract method)
	 * 
	 * @return the text representation of the object in the board
	 */
	public char toChar() {
		return 'X';
	}

	/************************************************
	 * Interface GraphicElement implementation
	 **********************************************/

	// Barbarian icon
	private static Icon icon = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/guardian.PNG"));

	public Icon getImage() {
		return icon;
	}

}
