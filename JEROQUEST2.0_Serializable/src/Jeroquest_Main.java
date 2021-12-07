
// Demo of the class Jeroquest

/**
 * Main program to test the game Jeroquest
 * 
 * @author Jorge Puente Peinador
 * @author Juan Luis Mateo
 * @version 1.4
 *
 */
public class Jeroquest_Main {

	public static void main(String[] args) {

		Jeroquest jq = new Jeroquest();
		String s = MyKeyboard.initialMenu();
		if (s.equals("1")) {
			jq.loadSerializedGame();
		} else {
			// let's play a game with 3 Heroes against 4 Monsters
			// in a board of 7 by 10
			// in 20 turns
			jq.newGame(3, 4, 7, 10, 20);
		}
		jq.toPlay();
	}

}
