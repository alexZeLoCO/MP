package jeroquest.units;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jeroquest.boardgame.Dice;
import jeroquest.logic.Game;
import jeroquest.utils.DynamicVectorCharacters;

public class Guardian extends Character {

	// initial values for the attributes
	protected static final int MOVEMENT = 5;
	protected static final int ATTACK = 5;
	protected static final int DEFENCE = 5;
	protected static final int BODY = 5;

	/**
	 * Create a mummy from its name
	 * 
	 * @param name name of the mummy
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

	// Icon of a mummy
	private static Icon icon = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/guardian.PNG"));

	public Icon getImage() {
		return icon;
	}

	@Override
	public int defend(int impacts) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isEnemy(Character c) {
		return !(c instanceof Guardian) && ((Suspect)c).isViolent();
	}

	@Override
	public boolean actionCombat(Game currentGame) {
		// Attack a random enemy
		DynamicVectorCharacters targets = validTargets(currentGame);

		if (targets.length() > 0) {
			for (Character target : targets.vectorNormal()) {
				System.out.println(
						this.getName() + this.getPosition() + " attacks to " + target.getName() + target.getPosition());
				this.combat(target, currentGame);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void combat(Character c, Game currentGame) { // attacks to c and c defends itself
		if (c instanceof Hero) {
			if (((Hero)c).getWeapon()==null) {
				((Hero) c).setWeapon(new Weapon ("Handcuffs", 0));
			} else {
				((Hero) c).setWeapon(null);
			}
		} else {
			((Monster)c).setNoPlayTurns(2);
		}
		((Suspect)c).setViolent(false);
	}
}
