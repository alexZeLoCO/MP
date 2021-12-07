package jeroquest.units;

import jeroquest.boardgame.Dice;
import jeroquest.logic.Game;
import jeroquest.utils.DynamicVectorCharacters;

import javax.swing.*;

public class Virus extends Character {

	// initial values for the attributes
	protected static final int MOVEMENT = 1;
	protected static final int ATTACK = 1;
	protected static final int DEFENCE = 0;
	protected static final int BODY = 7;
	private final boolean infected = true;

	/**
	 * Create a mummy from its name
	 *
	 * @param name name of the mummy
	 */
	public Virus(String name) {
		super(name, MOVEMENT, ATTACK, DEFENCE, BODY);
	}

	@Override
	public boolean actionCombat(Game currentGame) {
		return true;
	}

	@Override
	public boolean isEnemy(Character c) {
		return !((Carrier)c).isInfected();
	}

	public void degradacion () {
		this.setBody(this.getBody()-1);
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
		return 'C';
	}

	/************************************************
	 * Interface GraphicElement implementation
	 **********************************************/

	// Icon of a mummy
	private static Icon icon = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/virus.PNG"));

	@Override
	public int defend(int impacts) {
		return 0;
	}

	public Icon getImage() {
		return icon;
	}
}
