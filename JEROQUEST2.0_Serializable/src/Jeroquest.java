
/**
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Class Jeroquest - Represents the game Jeroquest and allows to
 * play it. For that it offers a method to create a new game and start the game.
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */

// The class Jeroquest includes an object Game and the method toPlay
// that implements the logic of the game

public class Jeroquest {

	private Game currentGame; // current game
	protected static JeroquestWindow monitor;

	/**
	 * Simulate a Jeroquest game
	 */
	public void toPlay() {
		// GUI - Create the window for the current game
		monitor = new JeroquestWindow(currentGame);

		// Start the game
		System.out.println("START OF THE GAME");

		// CONSOLE - print the initial state of the game
		System.out.println(currentGame);

		// GUI - update the game in the window
		monitor.showGame();
		MyKeyboard.pressEnter();

		// resolve the game in successive rounds
		while (noEndOfGame()) {
			// resolve the current round
			resolveRound();

			// CONSOLE - print the current state of the game
			System.out.println(currentGame);

			// GUI - update the game in the window
			monitor.showGame();

			// Continue the game or Save and exit
			String s = MyKeyboard.continueOrSaveAndExit();
			if (s.equals("1")) {
				System.out.println("Saving the game and exiting...");
				serializeCurrentGame();
				monitor.close();
				System.exit(0);
			}

			// increment round
			nextRound();
		}

		// CONSOLE - show the end of the game
		System.out.println("END OF THE GAME");
		System.out.println("Winners: " + highestBody());

		MyKeyboard.pressEnter();

		// GUI - Close the window
		monitor.close();
	}

	/**
	 * create a new game from its components
	 * 
	 * @param numHeroes   how many heroes
	 * @param numMonsters how many monsters
	 * @param rows        height of the board
	 * @param columns     width of the board
	 * @param totalRounds total number of rounds
	 */
	public void newGame(int numHeroes, int numMonsters, int rows, int columns, int totalRounds) {
		// Ready for round 1
		currentGame = new Game(numHeroes, numMonsters, rows, columns, totalRounds);

		// place the characters in the board randomly
		placeCharacters();
	}

	/**
	 * Go to the next round
	 */
	private void nextRound() {
		currentGame.setCurrentRound(currentGame.getCurrentRound() + 1);
	}

	/**
	 * Check it is the end of the game
	 * 
	 * @return true if the total number of turns has been reached or there no more
	 *         alive characters in both sides, false otherwise
	 */
	private boolean noEndOfGame() {
		return ((currentGame.getCurrentRound() <= currentGame.getTotalRounds()) && opponentsLeft());
	}

	/**
	 * Execute the round of the game: each alive character resolve its turn The
	 * round ends immediately if in any moment there are no any alive character in
	 * both sides
	 */
	private void resolveRound() {
		System.out.println("Round: " + currentGame.getCurrentRound());

		for (int x = 0; (x < currentGame.getCharacters().length) && opponentsLeft(); x++) {
			Character c = currentGame.getCharacters()[x];
			if (c.isAlive())
				c.resolveTurn(currentGame);
		}
	}

	/**
	 * Place the characters in the board randomly in valid positions: (free and
	 * inside of the board)
	 */
	private void placeCharacters() {
		int rows = currentGame.getBoard().getRows();
		int columns = currentGame.getBoard().getColumns();

		for (Character p : currentGame.getCharacters()) {
			// search a random position inside of the board
			int row = Dice.roll(rows) - 1;
			int col = Dice.roll(columns) - 1;
			// while the position is not valid
			while (!currentGame.getBoard().movePiece(p, new XYLocation(row, col))) {
				// search a new random position
				row = Dice.roll(rows) - 1;
				col = Dice.roll(columns) - 1;
			}
		}
	}

	/**
	 * Obtain which side has in total more body points
	 * 
	 * @return the name of the side with more body points
	 */
	private String highestBody() { // Returns the name of the class with highest value for the total body points in
									// the current state of the game
		int cHeroes = 0;
		int cMonsters = 0;

		for (Character c : currentGame.getCharacters()) {
			if (c instanceof Hero)
				cHeroes += c.getBody();
			else if (c instanceof Monster)
				cMonsters += c.getBody();
			// System.out.println(p.getClass());
		}
		if (cMonsters > cHeroes)
			return "Monsters";
		else if (cHeroes > cMonsters)
			return "Heroes";
		else
			return "Draw";
	}

	/**
	 * Check if there are characters alive of both sides
	 * 
	 * @return true if there are at least one character alive for each side
	 */

	public boolean opponentsLeft() { // Returns true if both sides have characters alive
		boolean heroesAlive = false;
		boolean monstersAlive = false;
		int x = 0;
		while ((x < currentGame.getCharacters().length) && !(heroesAlive && monstersAlive)) {
			if (currentGame.getCharacters()[x].isAlive())
				if (currentGame.getCharacters()[x] instanceof Hero)
					heroesAlive = true;
				else // this second if is necessary since there can be "neutral" characters (they
						// don't inherit neither from Monster nor from Hero)
				if (currentGame.getCharacters()[x] instanceof Monster)
					monstersAlive = true;
			x++;
		}

		return heroesAlive && monstersAlive;
	}

	/**
	 * Returns an array with the valid squares where a character can move directly
	 * from its current position:(N, S, E and W) (this method is static since it is
	 * given by the rules of the game)
	 * 
	 * @param currentGame game that contains the board and the characters
	 * @param cj          character to analyse
	 * @return the vector of positions (possibly free) where it can move
	 */
	public static DynamicVectorXYLocation validPositions(Game currentGame, Character cj) {

		DynamicVectorXYLocation positions = new DynamicVectorXYLocation();

		XYLocation position = cj.getPosition().south();
		if (currentGame.getBoard().freeSquare(position))
			positions.add(position);

		position = cj.getPosition().west();
		if (currentGame.getBoard().freeSquare(position))
			positions.add(position);

		position = cj.getPosition().east();
		if (currentGame.getBoard().freeSquare(position))
			positions.add(position);

		position = cj.getPosition().north();
		if (currentGame.getBoard().freeSquare(position))
			positions.add(position);

		return positions;
	}

	/**
	 * Returns an array with the valid targets for the current character (this
	 * method is static since it is given by the rules of the game)
	 * 
	 * @param currentGame the current game
	 * @param cj          the current character
	 * @return the valid targets for the character in its current position
	 */
	public static DynamicVectorCharacters validTargets(Game currentGame, Character cj) {
		// search targets
		DynamicVectorCharacters validTargets = new DynamicVectorCharacters();

		// iterate the characters in the game and add the valid targets, that is:
		for (Character character : currentGame.getCharacters()) {
			// 1.- the ones alive
			if (character.isAlive()) {
				// 2.- that are enemies
				if (cj.isEnemy(character)) {
					// 3.- that are at attack range
					if (areAtRange(cj.getPosition(), character.getPosition()))
						validTargets.add(character);
				}
			}
		}

		return validTargets;
	}

	/**
	 * Check if the positions of two characters are adjacent (this method is static
	 * since it is given by the rules of the game)
	 * 
	 * @param pos1 position of the first character
	 * @param pos2 position of the second character
	 * @return true is the squares are adjacent, false otherwise
	 */
	public static boolean areAtRange(XYLocation pos1, XYLocation pos2) {
		// pos2 to the North from pos1
		if ((pos1.getX() == pos2.getX() - 1) && (pos1.getY() == pos2.getY()))
			return true;

		// pos2 to the South from pos1
		if ((pos1.getX() == pos2.getX() + 1) && (pos1.getY() == pos2.getY()))
			return true;

		// pos2 to the East from pos1
		if ((pos1.getY() == pos2.getY() - 1) && (pos1.getX() == pos2.getX()))
			return true;

		// pos2 to the West from pos1
		if ((pos1.getY() == pos2.getY() + 1) && (pos1.getX() == pos2.getX()))
			return true;

		return false;
	}

	/**
	 * Check if the character is blocked, that means if it cannot move in any
	 * direction: N, S, E, W (this method is static since it is given by the rules
	 * of the game)
	 * 
	 * @param currentGame the current game
	 * @param c           character to check
	 * @return true if it cannot move in any direction
	 */
	public static boolean blocked(Game currentGame, Character c) {
		Board tab = currentGame.getBoard();
		return !(tab.freeSquare(c.getPosition().north()) || tab.freeSquare(c.getPosition().south())
				|| tab.freeSquare(c.getPosition().east()) || tab.freeSquare(c.getPosition().west()));
	}

	/**
	 * Generate a random position for movement
	 * 
	 * @return a direction: North, South, East or West
	 */
	public static Direction ranodmDirection() {
		int dir = Dice.roll(4);
		switch (dir) {
		case 1:
			return Direction.North;
		case 2:
			return Direction.South;
		case 3:
			return Direction.East;
		case 4:
		default:
			return Direction.West;
		}
	}

	/**
	 * Serializes the current game
	 */
	private void serializeCurrentGame() {
		
		this.currentGame
		// Read the filename from the keyboard

		// serialize the game to a file with that name

		// NOTE: Treat properly exceptions and files

		System.out.println("\nName of the file to save the current game: ");

	}

	public void loadSerializedGame() {
		// Read the filename from the keyboard

		// load the object game serialized from the file with that name

		// NOTE: Treat properly exceptions and files

		System.out.println("\nName of the file to read a saved game: ");
	}
}
