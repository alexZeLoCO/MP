package jeroquest.units;

import jeroquest.boardgame.Dice;
import jeroquest.logic.Game;

import javax.swing.*;

public class Swarm extends Monster {

	// initial values for the attributes
	protected static final int MOVEMENT = 5;

	private Wasp [] wasps = new Wasp[5];

	/**
	 * Create a Swarm from its name
	 *
	 * @param name name of the Swarm
	 */
	public Swarm(String name) {
		super(name, MOVEMENT, 0, 0, 0);

		for (int i = 0; i < this.getWasps().length; i++) {
			if (Dice.roll(2)==1) {
				this.getWasps()[i] = new Wasp("Wasp" + i);
			} else {
				this.getWasps()[i] = new Hornet ("Hornet" + i);
			}

			this.setBody(this.getBody()+this.getWasps()[i].getBody());
			this.setAttack(this.getAttack()+this.getWasps()[i].getAttack());
			this.setDefence(this.getDefence()+this.getWasps()[i].getDefence());

			this.setAttackInitial(this.getAttack());
			this.setBodyInitial(this.getBody());
			this.setDefenceInitial(this.getDefence());

		}
	}

	@Override
	public String toString() {
		return String.format("%s:\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n",this.getName(),
				this.getWasps()[0],this.getWasps()[1],this.getWasps()[2],this.getWasps()[3],this.getWasps()[4]);
	}

	public Wasp[] getWasps() {
		return this.wasps;
	}

	@Override
	public void combat(Character c, Game currentGame) { // attacks to c and c defends itself
		for (int i=0;i<this.getWasps().length && c.isAlive(); i++) {
			if (this.getWasps()[i].isAlive()) {
				this.getWasps()[i].combat(c, currentGame);
			}
		}
	}

	@Override
	public int defend(int impacts) {
		int wounds = 0;

		Wasp wasp = this.getWasps()[0];
		for (int i=0;i<this.getWasps().length && !this.getWasps()[i].isAlive(); i++) {
			if (this.getWasps()[i].isAlive()) {
				wasp = this.getWasps()[i];
			}
		}

		impacts = Math.min(1, impacts);

		wounds = wasp.defend(impacts);

		this.setBody(this.getBody()-wounds);

		if (!wasp.isAlive()) {
			this.setDefence(this.getDefence()-wasp.getDefence());
			this.setAttack(this.getAttack()-wasp.getAttack());
		}

		//wasp ==> this.getWasps()[i]

		return wounds;
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
		return 'S';
	}

	/************************************************
	 * Interface GraphicElement implementation
	 **********************************************/

	// Icon of a Swarm
	private static Icon icon = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/swarm.gif"));

	public Icon getImage() {
		return icon;
	}

}
