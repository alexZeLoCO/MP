package jeroquest.units;

import jeroquest.boardgame.Dice;
import jeroquest.logic.Game;
import jeroquest.utils.DynamicVectorCharacters;

import javax.swing.*;

public class Guardian extends Character {

	// initial values for the attributes
	protected static final int MOVEMENT = 5;
	protected static final int ATTACK = 5;
	protected static final int DEFENCE = 5;
	protected static final int BODY = 5;

	/**
	 * Create a Guardian from its name
	 *
	 * @param name name of the Guardian
	 */
	public Guardian(String name) {
		super(name, MOVEMENT, ATTACK, DEFENCE, BODY);
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

	// Icon of a Guardian
	private static Icon icon = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/guardian.PNG"));

	@Override
	public int defend(int impacts) {
		return 0;
	}

	@Override
	public boolean isEnemy(Character c) {
		return (!(c instanceof Guardian || c instanceof Virus) && ((Suspect)c).isViolent());
	}

	@Override
	public boolean actionCombat(Game currentGame) {
		// Attack a random enemy
		DynamicVectorCharacters targets = validTargets(currentGame);

		if (targets.length() > 0) {
			for (Character target : targets.vectorNormal()) {
				if (target instanceof Hero) {			//Heroe
					if (((Hero)target).getWeapon() == null) {			//Sin arma
						((Hero)target).setWeapon(new Weapon ("Handcuffs", 0));		//==> Esposas
					} else {			//con arma
						((Hero)target).setWeapon (null);			//==> Quita arma
					}
				} else {			//Monstruo
					((Monster)target).setNoPlayTurns(2);		//==> 2 turnos sin jugar
				}
				((Suspect)target).setViolent(false);
			}
			return true;
		}
		return false;
	}

	public Icon getImage() {
		return icon;
	}

}
