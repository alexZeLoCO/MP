package jeroquest.units;

import jeroquest.boardgame.Dice;
import jeroquest.logic.Game;
import jeroquest.utils.DynamicVectorCharacters;

import javax.swing.*;

public class Swarm extends Monster {

	// initial values for the attributes
	protected static final int MOVEMENT = 5;
	protected static int ATTACK;
	protected static int DEFENCE;
	protected static int BODY=1;

	private Wasp [] wasps = new Wasp [5];

	/**
	 * Create a Swarm from its name
	 *
	 * @param name name of the Swarm
	 */
	public Swarm(String name) {
		super(name, MOVEMENT, ATTACK, DEFENCE, BODY);
		for (int i=0;i<wasps.length;i++) {
			if (Dice.roll(2)%2==0) {
				wasps[i] = new Wasp("wasp" + i);
			} else {
				wasps[i] = new Hornet("Hornet"+i);
			}
		}
		updateValues();
		this.setAttackInitial(this.getAttack());
		this.setBodyInitial(this.getBody());
		this.setDefenceInitial(this.getDefence());
	}

	public void updateValues() {
		int body=0;
		int defence=0;
		for (Wasp wasp : wasps) {
			if (wasp.isAlive()) {
				body=body+wasp.getBody();
				defence++;
			}
		}
		if (body==0) {
			System.out.printf("The monster " + this.getName() + " dies\n");
		}
		setAttack(body);
		setDefence(defence);
		setBody(body);
	}

	public Wasp[] getWasps () {
		return this.wasps;
	}

	@Override
	public void combat(Character c, Game currentGame) { // attacks to c and c defends itself
		int i=0;
		while (i<this.getWasps().length && c.isAlive()){
			if (wasps[i].isAlive()) {
				c.defend(wasps[i].attack());
			}
			if (!c.isAlive()) {
				currentGame.getBoard().removePiece(c);
			}
		}
	}

	public Wasp aliveWasp() {
		int i=0;
		while (i<getWasps().length && !getWasps()[i].isAlive()) {
			i++;
		}
		return getWasps()[i];
	}

	@Override
	public int defend(int impacts) {
		int wounds = 0;
		impacts = Math.min(1,impacts);

		Wasp target = aliveWasp();
		// it tries to block the impacts with the defence
		for (int totalDefenceDices = target.getDefence(); (impacts > 0) && (totalDefenceDices > 0); totalDefenceDices--)
			if (Dice.roll() == 6) // a 6 is necessary to block an impact
				impacts--;

		// if any unblocked impact, decrement body points
		if (impacts > 0) {
			// the life of a character cannot be lower then zero
			wounds = Math.min(target.getBody(), impacts);
			target.setBody(target.getBody() - wounds);
			System.out.printf("The monster " + target.getName() + " cannot block %d impacts%s", impacts,
					(target.isAlive() ? "\n" : " and dies\n"));
		} else {
			System.out.printf("The monster %s blocks the attack\n", target.getName());
		}
		this.updateValues();
		return wounds;
	}

	@Override
	public String toString() {
		return String.format("%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\nTotal stats:\n\tMovement:%d\n\tAttack:%d\n\tDefence:%d\n\tBody:%d.",
				this.getName(),wasps[0].toString(),wasps[1].toString(),wasps[2].toString(),wasps[3].toString(),wasps[4].toString(),
				this.getMovement(),this.getAttack(),this.getDefence(),this.getBody());
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
