package jeroquest.units;

import jeroquest.boardgame.Dice;
import jeroquest.logic.Game;

/**
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Class Monster - abstract class that represents the monsters in
 * the game
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */

public abstract class Monster extends Character {

	private int noPlayTurns;

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
		this.setNoPlayTurns(0);
	}

	public void setNoPlayTurns (int n) {
		this.noPlayTurns=n;
	}

	public int getNoPlayTurns() {
		return this.noPlayTurns;
	}

	@Override
	public void resolveTurn(Game currentGame) {
		if (this.getNoPlayTurns()==0) {
			// Attack to a random enemy
			actionCombat(currentGame);

			// Move randomly through the board
			actionMovement(currentGame);

			// Possibles improvement (among others):
			// - Move towards the closest enemy / with less body points /...
			// A.- First in Xs and later in Ys
			// B.- First in the coordinate with difference with the target's position
			// - AI: check if there is free way until the target
			// - What to do if our way is blocked by allies?
			// - Stop is there is an enemy at attack range
			// and if the square is free and inside of the board move to that position
		} else {
			this.setNoPlayTurns(this.getNoPlayTurns()-1);
			System.out.printf("%s is in a No Play Turn for being violent!. No Play Turns remaining: %d.",this.getName(),this.getNoPlayTurns()-1);
		}
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
