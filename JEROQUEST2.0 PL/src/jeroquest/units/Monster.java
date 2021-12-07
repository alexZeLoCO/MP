package jeroquest.units;

import jeroquest.boardgame.Dice;
import jeroquest.logic.Game;
import jeroquest.utils.DynamicVectorCharacters;

/**
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Class Monster - abstract class that represents the monsters in
 * the game
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */

public abstract class Monster extends Character implements Suspect, Carrier {

	private boolean violent;
	private int NoPlayTurns;
	private boolean infected;

	/**
	 * Create a monster from its name and initial values for the attributes,
	 * initially its position is null (outside of the board)
	 * 
	 * @param name     name of the character
	 * @param movement squares to move per turn
	 * @param attack   dices to roll for an attack without a weapon
	 * @param defence  dices to roll for the defence
	 * @param body     initial life
	 */
	public Monster(String name, int movement, int attack, int defence, int body) {
		super(name, movement, attack, defence, body);
		setViolent(false);
		setNoPlayTurns(0);
		setInfected(false);
	}

	public void setInfected (boolean infection) {
		this.infected=infection;
	}

	public boolean isInfected () {
		return this.infected;
	}

	public void setNoPlayTurns(int NoPlayTurns) {
		this.NoPlayTurns = NoPlayTurns;
	}

	public int getNoPlayTurns() {
		return this.NoPlayTurns;
	}

	public boolean isViolent() {
		return this.violent;
	}

	public void setViolent(boolean violent) {
		this.violent = violent;
	}

	/**
	 * The monster defends itself from an attack (Implementation of an abstract
	 * inherited method)
	 * 
	 * @param impacts the total number of impacts to try to block or suffer
	 * @return the number of suffered wounds
	 */
	public int defend(int impacts) {
		int wounds = 0;

		// it tries to block the impacts with the defence
		for (int totalDefenceDices = getDefence(); (impacts > 0) && (totalDefenceDices > 0); totalDefenceDices--)
			if (Dice.roll() == 6) // a 6 is necessary to block an impact
				impacts--;

		// if any unblocked impact, decrement body points
		if (impacts > 0) {
			// the life of a character cannot be lower then zero
			wounds = Math.min(getBody(), impacts);
			setBody(getBody() - wounds);
			System.out.printf("The monster " + this.getName() + " cannot block %d impacts%s", impacts,
					(isAlive() ? "\n" : " and dies\n"));
		} else {
			System.out.printf("The monster %s blocks the attack\n", this.getName());
		}

		return wounds;
	}

	@Override
	public boolean actionCombat(Game currentGame) {
		// Attack a random enemy
		DynamicVectorCharacters targets = validTargets(currentGame);

		if (targets.length() > 0) {
			if (this.isInfected()) {
				for (Character c : targets.vectorNormal()) {
					if (c instanceof Hero || c instanceof Monster) {
						((Carrier)c).setInfected(true);
					}
				}
			}

			Character target = targets.get(Dice.roll(targets.length()) - 1);

			System.out.println(
					this.getName() + this.getPosition() + " attacks to " + target.getName() + target.getPosition());
			this.combat(target, currentGame);
			return true;
		}
		return false;
	}

	@Override
	public void resolveTurn(Game currentGame) {

		if (this.getNoPlayTurns()==0) {
			super.resolveTurn(currentGame);
		} else {
			this.setNoPlayTurns(this.getNoPlayTurns()-1);
			System.out.printf("%s is in a No Play Turn.\t No Play turns remaining: %d\n",this.getName(), this.getNoPlayTurns());
			if (((Carrier)this).isInfected()) {
				this.setBody(this.getBody()-1);
			}
			if (!this.isAlive()) {
				currentGame.getBoard().removePiece(this);
			}
		}
	}

	/**
	 * Check if the character given as argument is an enemy of the current one. An
	 * enemy will be all characters that are monsters (Overridden method)
	 * 
	 * @param c character to check
	 * @return true if it an enemy of the current character
	 */
	@Override
	public boolean isEnemy(Character c) {
		return (c instanceof Hero);
	}
}
