
/**
 * Programming Methodology Practice.
 * Jeroquest - An example of Object Oriented Programming.
 * Class Character
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */

import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Character implements Piece, GraphicElement, Serializable {

	private static final long serialVersionUID = 1L;
	// attributes with the current values for the character
	protected int movement; // units of movement per turn
	protected int attack; // total of attack dices
	protected int defence; // total of defence dices
	protected int body; // body points

	// attributes with the initial values for the character
	protected int movementInitial; // units of movement per turn
	protected int attackInitial; // total of attack dices
	protected int defenceInitial; // total of defence dices
	protected int bodyInitial; // body points

	protected String name; // character's name

	/**
	 * Create a character from its name and its initial values for the attributes,
	 * initially its position is null (outside of the board)
	 * 
	 * @param name     character's name
	 * @param movement units of movement per turn
	 * @param attack   total of attack dices
	 * @param defence  total of defence dices
	 * @param body     body points
	 */
	public Character(String name, int movement, int attack, int defence, int body) {

		this.name = name;

		// setting the initial values
		this.movementInitial = movement;
		this.attackInitial = attack;
		this.defenceInitial = defence;
		this.bodyInitial = body;

		// setting the current values
		this.movement = movement;
		this.attack = attack;
		this.defence = defence;
		this.body = body;

		// null position (outside of the board)
		this.position = null;
	}

	/**
	 * Get the units of movement per turn
	 * 
	 * @return character's units of movement
	 */
	public int getMovement() {
		return movement;
	}

	/**
	 * Get the dices for attack
	 * 
	 * @return character's attack dices
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Get the dices for defence
	 * 
	 * @return character's defence dices
	 */
	public int getDefence() {
		return defence;
	}

	/**
	 * Get the body points
	 * 
	 * @return character's body points
	 */
	public int getBody() {
		return body;
	}

	/**
	 * Getter for movementInitial
	 * 
	 * @return movementInitial
	 */
	public int getMovementInitial() {
		return movementInitial;
	}

	/**
	 * Getter for attackInitial
	 * 
	 * @return attackInitial
	 */
	public int getAttackInitial() {
		return attackInitial;
	}

	/**
	 * Getter for defenceInitial
	 * 
	 * @return defenceInitial
	 */
	public int getDefenceInitial() {
		return defenceInitial;
	}

	/**
	 * Getter for bodyInitial
	 * 
	 * @return bodyInitial
	 */
	public int getBodyInitial() {
		return bodyInitial;
	}

	/**
	 * Get the name
	 * 
	 * @return the name of the character
	 */
	public String getName() {
		return name;
	}

	/**
	 * Checks if a character has any body points left
	 * 
	 * @return true if it is alive, false otherwise
	 */
	public boolean isAlive() {
		return body > 0;
	}

	/**
	 * Computes the number of impacts that the attack of the character will make,
	 * for that it rolls as many dices as dictated by the attribute attack
	 * 
	 * @return the number of impacts made
	 */
	public int attack() {
		int impacts = 0;
		for (int x = 0; x < getAttack(); x++)
			if (Dice.roll() > 3)
				impacts++;
		return impacts;
	}

	/**
	 * Simulates an combat with other character, it does the attack and the defence.
	 * If the attack kills the opponent it is moved out of the board
	 * 
	 * @param c           character being attacked
	 * @param currentGame the current game
	 */
	public void combat(Character c, Game currentGame) { // attacks to c and c defends itself
		c.defend(this.attack());
		if (!c.isAlive()) {
			currentGame.getBoard().removePiece(c);
		}
	}

	/**
	 * Defence from an attack (abstract method: each subclass must define its own
	 * behaviour)
	 * 
	 * @param impacts total number of impacts to try to block or suffer
	 * @return the number of suffered wounds
	 */
	public abstract int defend(int impacts);

	/**
	 * Checks that the character given as argument is an enemy. An enemy is any
	 * character that is not of the same type
	 * 
	 * @param c character to test the hostility
	 * @return true if it is an enemy
	 */
	public boolean isEnemy(Character c) {
		return this.getClass() != c.getClass();
	}

	/**
	 * Combat action for the character
	 * 
	 * @param currentGame game in which the character has to act
	 * @return true if the character has to fight an enemy, false otherwise
	 */
	public boolean actionCombat(Game currentGame) {
		// Attack a random enemy
		DynamicVectorCharacters targets = Jeroquest.validTargets(currentGame, this);

		if (targets.length() > 0) {
			Character target = targets.get(Dice.roll(targets.length()) - 1);
			String msg = this.getName() + this.getPosition() + " attacks to " + target.getName() + target.getPosition();
			System.out.println(msg);
			this.combat(target, currentGame);
			return true;
		}
		return false;
	}

	/**
	 * Movement action for the character
	 * 
	 * @param currentGame game in which the character has to act
	 * @return the number of squares that it has moved
	 */
	public int actionMovement(Game currentGame) {
		// Random movement in the board
		String msg = this.getName() + this.getPosition() + " moves to ";
		System.out.print(msg);
		DynamicVectorXYLocation validPositions = Jeroquest.validPositions(currentGame, this);
		int mov = this.getMovement();

		while ((validPositions.length() > 0) && (mov > 0)) {
			// if it can it moves in a direction chosen randomly
			XYLocation newPosition = validPositions.get(Dice.roll(validPositions.length()) - 1);
			currentGame.getBoard().movePiece(this, newPosition);
			mov--;
			System.out.println(this.getPosition());

			// window
			Jeroquest.monitor.showGame();
			MyKeyboard.pressEnter();

			validPositions = Jeroquest.validPositions(currentGame, this);
		}

		if (Jeroquest.blocked(currentGame, this)) {
			System.out.print("<<<BLOCKED");
		}
		System.out.println();
		// window
		Jeroquest.monitor.showGame();
		MyKeyboard.pressEnter();

		return this.getMovement() - mov;
	}

	/**
	 * AI: character's Artificial Intelligence It does the actions in a given turn:
	 * attack and movement
	 * 
	 * @param currentGame game in which the character has to act
	 */
	public void resolveTurn(Game currentGame) {

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

	}

	/**
	 * Generate a printable representation of the object (overridden method)
	 * 
	 * @return The printable representation of the character
	 */
	@Override
	public String toString() {
		return String.format("%s (moves:%d attack:%d defence:%d body:%d/%d)", getName(), getMovement(), getAttack(),
				getDefence(), getBody(), getBodyInitial());
	}

	/************************************************
	 * Interface GraphicElement implementation
	 **********************************************/

	// Icon for an abstract character
	protected static Icon icon = new ImageIcon(ClassLoader.getSystemResource("unknown.png"));

	/**
	 * Returns the icon associated to an abstract character
	 * 
	 * @return the icon associated to the class Character
	 */
	public Icon getImage() {
		return icon;
	}

	/**
	 * Shows an icon representing the character in the layer 2
	 * 
	 * @param w graphic panel to show the icon
	 */
	public void show(MyPanelBoard w) {
		XYLocation pos = getPosition();
		// if the character is dead is outside of the board
		if (pos != null) {
			// a JLabel is created the icon
			JLabel lab = new MyJLabelCharacter(this);

			// the label is added to the layer 2
			w.add(lab, Integer.valueOf(2));
		}

	}

	/************************************************
	 * Interface Piece implementation
	 **********************************************/
	// by composition
	protected XYLocation position; // position in the board

	/**
	 * Get the position in the board
	 * 
	 * @return the position of the character in the board
	 */
	public XYLocation getPosition() {
		return position;
	}

	/**
	 * Set the position of the character in the board
	 * 
	 * @param pos new position of the character in the board
	 */
	public void setPosition(XYLocation pos) {
		position = pos;
	}

	/**
	 * Generate a text version to represent the character in the board
	 * 
	 * @return a text (as char) representation of the character
	 */
	public char toChar() {
		// if the subclass doesn't change it, the characters will appear
		// as '?'s
		return '?';
	}

}
