package jeroquest.logic;

import jeroquest.units.Guard;

import jeroquest.boardgame.Board;
import jeroquest.boardgame.Dice;
import jeroquest.boardgame.Direction;
import jeroquest.boardgame.XYLocation;
import jeroquest.gui.JeroquestWindow;
import jeroquest.gui.MyKeyboard;
import jeroquest.units.Character;
import jeroquest.units.DirtyRat;
import jeroquest.units.Goblin;
import jeroquest.units.Hero;
import jeroquest.units.Monster;
import jeroquest.units.Suspect;
import jeroquest.units.Swarm;
import jeroquest.units.Wasp;
import jeroquest.units.Zombie;
import jeroquest.utils.DynamicVectorCharacters;

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
	private static JeroquestWindow monitor;

	public static void showGame() {
		if (monitor != null)
			monitor.showGame();
	}

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
			MyKeyboard.pressEnter();
			
			// increment round
			nextRound();
		}

		// CONSOLE - show the end of the game
		System.out.println("END OF THE GAME");
		System.out.println("Winners: " + highestBody());
		
		DynamicVectorCharacters rats = new DynamicVectorCharacters ();
		for (Character c : currentGame.getCharacters()) {
			if (c instanceof DirtyRat && c.isAlive()) {
				int j=0;
				for (int i=0;i<rats.length() && c.getBody()<rats.get(i).getBody(); i++) {
					j++;
				}
				rats.insert(j,c);
			}
		}
		
		for (Character c : rats.vectorNormal()) {
			System.out.println(c);
		}
		
		showUndamagedWasps(currentGame);
		
		MyKeyboard.pressEnter();

		// GUI - Close the window
		monitor.close();
	}

	public static void showUndamagedWasps (Game currentGame) {
		for (Character c : currentGame.getCharacters()) {
			if (c instanceof Swarm && c.isAlive()) {
				for (Wasp w : ((Swarm)c).getWasps()) {
					if (w.getBody() == w.getBodyInitial()) {
						System.out.print(w);
					}
				}
			}
		}
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
	public void newGame(int numHeroes, int numMonsters, int rows, int columns, int totalRounds) { // Ready for round 1
		currentGame = new Game(numHeroes, numMonsters, rows, columns, totalRounds);

		// place the characters in the board randomly
		placeCharacters();
	}

	/**
	 * create a new game from its components
	 *
	 */
	public void newGame() { // Ready for round 1
		currentGame = new Game();

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
		
		
		for (Character character : currentGame.getCharacters()) {
			
			//Reduce all zombies HP by 1 and remove if dead.
			if (character instanceof Zombie && character.isAlive()) {
				((Zombie) character).degradacion();
				if (!character.isAlive()) {
					System.out.printf("The zombie %s dies (again!).\n",character.getName());
					currentGame.getBoard().removePiece(character);
				}
			}
			
			//If rat is fearful then toggle the fear else regenerate 1 HP iff not full already
			if (character instanceof DirtyRat && character.isAlive()) {
				if (((DirtyRat)character).isFearful()) {
					((DirtyRat)character).toggleFear();
				} else {
					((DirtyRat)character).regenerate();
				}
			}
			
			if (character instanceof Guard) {
				((Guard)character).guard();
			}
		}
		
		Character memory;
		for (int i=0;i<currentGame.getCharacters().length;i++) {
			for (int j=i+1; j<currentGame.getCharacters().length;j++) {
				if (currentGame.getCharacters()[i] instanceof Suspect && currentGame.getCharacters()[i].isAlive() && currentGame.getCharacters()[i].isViolent() && 
					currentGame.getCharacters()[j] instanceof Suspect && currentGame.getCharacters()[j].isAlive() && !currentGame.getCharacters()[j].isViolent()) {
					memory = currentGame.getCharacters()[i];
					currentGame.getCharacters()[i]=currentGame.getCharacters()[j];
					currentGame.getCharacters()[j]=memory;
				}
			}
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

}
