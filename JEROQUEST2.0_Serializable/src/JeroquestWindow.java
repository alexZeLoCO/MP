
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JFrame; // necesario para la ventana
import javax.swing.JLabel; // muestra texto e im�genes

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import java.io.BufferedReader; // necesarios para leer pulsaciones de teclas del teclado
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Class JeroquestWindow, to represent graphical information by
 * means of a panel and it object Game to show
 * 
 * @author Programming Methodology Professors
 *
 */
public class JeroquestWindow extends JFrame {
	// version of the class 1 (necessary for possible serialisations of the objects
	// of this class)
	private static final long serialVersionUID = 1L;

	private MyPanelBoard panel = null; // panel to show the content

	/**
	 * Constructor of the window
	 * 
	 * @param theGame the object Game to visualise
	 */
	public JeroquestWindow(Game theGame) {
		// create the JFrame object that represents the window
		super("Monitor Jeroquest");

		// create a panel board
		panel = new MyPanelBoard(theGame);

		// action to do when the window is closed -> end the application
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add the panel to the window
		add(panel);
		// set the dimensions of the window
		pack();
		// set that the window cannot change size
		setResizable(false);
		// make the window visible
		setVisible(true);
	}

	public void showGame() {
		panel.showCharacters();
	}

	/**
	 * Close the window and free the used resources
	 */
	public void close() {
		dispose();
	}

}

/**
 * Class MyPanelBoard, represents a panel with the elements of the current game:
 * the board, the characters and the current round
 * 
 * @author Programming Methodology Professors
 *
 */
class MyPanelBoard extends JLayeredPane {
	// this avoids a warning referred to serialisable windows
	private static final long serialVersionUID = 1L;

	private Game theGame = null; // game to show

	// icons of the board elements
	Icon square = new ImageIcon(getClass().getResource("square.png"));

	// Label with the current round
	JLabel currentRoundJLabel = null;

	/**
	 * Panel constructor with the characters and the board
	 * 
	 * @param g the game
	 */
	public MyPanelBoard(Game g) {
		this.theGame = g;
		initialiseBoard(g.getBoard().getRows(), g.getBoard().getColumns());
	}

	/**
	 * Create the graphical representation of the board in the panel
	 * 
	 * @param rows    number of rows in the board
	 * @param columns number of columns in the board
	 */
	private void initialiseBoard(int rows, int columns) {
		// create the matrix of squares
		setPreferredSize(new Dimension(32 * (1 + columns), 32 * (1 + 1 + rows)));

		// create the labels for the coordinates
		for (Integer x = 0; x < columns; x++) {
			JLabel label = new JLabel(x.toString());
			label.setBounds(16 + x * 32, rows * 32, 32, 32);
			add(label, Integer.valueOf(1));
		}
		for (Integer x = 0; x < rows; x++) {
			JLabel label = new JLabel(x.toString());
			label.setBounds(16 + columns * 32, x * 32, 32, 32);
			add(label, Integer.valueOf(1));
		}

		// create a grid with the size of the board filling each square
		for (int x = 0; x < rows; x++)
			for (int y = 0; y < columns; y++) {
				// label with the image of an empty square
				JLabel c = new JLabel(square);
				// dimensions (32x32), position (X,Y)
				c.setBounds(y * 32, x * 32, 32, 32);
				// show the icon in its position
				add(c, Integer.valueOf(1));
			}

		// create the label with the current round
		currentRoundJLabel = new JLabel(
				"Round: " + this.theGame.getCurrentRound() + "/" + this.theGame.getTotalRounds());
		currentRoundJLabel.setBounds(16, (rows + 1) * 32, 128, 32);
		add(currentRoundJLabel, Integer.valueOf(1));
	}

	/**
	 * Convert the char \n of a String to <br>
	 * in HTML to make that the tooltip of a JLabel can be visualised correctly
	 * 
	 * @param orig string to format
	 * @return a formatted String as HTML
	 */
	public static String convertToMultiline(String orig) {
		return "<html>" + orig.replaceAll("\n", "<br>") + "</html>";
	}

	/**
	 * Show all characters in the board as icons, where their "tool tip" is the
	 * information of the method toString() of each object
	 */
	protected void showCharacters() {
		// show the current round
		currentRoundJLabel.setText("Round: " + this.theGame.getCurrentRound() + "/" + this.theGame.getTotalRounds());
		// delete labels for previous characters
		for (Component comp : getComponents())
			if (getLayer(comp) == 2) // if it is in the layer 2, then it is the icon of a character
				this.remove(comp);

		// recreate the characters
		for (Character cj : theGame.getCharacters())
			cj.show(this);

		// If is is necessary to show anything else in the board, the code will be here
		// using the information of the object "theGame"

		// update its container what will refresh the screen
		validate();
		repaint();
	}
}

/**
 * Class MyJLabelCharacter, models a graphical label of Java with a character
 * associated to it Having the character allows it to show the "life bar" over
 * the icon
 * 
 * @author Programming Methodology Professors
 *
 */
class MyJLabelCharacter extends JLabel {
	// this avoids a warning referred to serialisable windows
	private static final long serialVersionUID = 1L;

	private Character cj;

	/**
	 * Constructor to create the icon of a character in the board
	 * 
	 * @param cj character to show
	 */
	public MyJLabelCharacter(Character cj) {
		// associate the icon of the character with the JLabel
		super(((GraphicElement) cj).getImage());
		this.cj = cj;

		// size 32x32 and placed in its position (X,Y)
		XYLocation pos = cj.getPosition();
		setBounds(pos.getY() * 32, pos.getX() * 32, 32, 32);
		// set as tooltip the description of the character
		setToolTipText(MyPanelBoard.convertToMultiline(cj.toString()));
	}

	/**
	 * Create a bar showing the current life of the character The colour indicate
	 * the current value with respect to the initial one (the maximum)
	 * 
	 * @param g            Graphics object to paint over
	 * @param currentValue current life
	 * @param maximum      initial life
	 */
	private void lifeBar(Graphics g, int currentValue, int maximum) {
		// scaling so that the maximum fits in 32 pixels
		int width = (int) Math.round(currentValue * 32 / maximum);

		// bar white frame
		g.setColor(Color.WHITE);
		g.drawRect(0, 28, 31, 3);

		// bar black background
		g.setColor(Color.BLACK);
		g.fillRect(1, 29, 30, 2);

		// set the colour of the bar...
		// GREEN: if it has more than 75% of life
		if (cj.getBody() > 0.75 * cj.getBodyInitial())
			g.setColor(Color.GREEN);
		// YELLOW: if it has between (50%..75%] of life
		else if (cj.getBody() > 0.5 * cj.getBodyInitial())
			g.setColor(Color.YELLOW);
		// RED: if the life is <= 50%
		else
			g.setColor(Color.RED);
		// Draw the bar
		g.fillRect(1, 29, width - 2, 2);

	}

	/**
	 * Paint the character in its position and over the icon the bar of life
	 * 
	 * @param g Graphics object where to paint
	 */
	@Override
	public void paintComponent(Graphics g) {

		// the method of JLabel will show the icon of the character
		super.paintComponent(g);

		// paint over the icon the bar of life
		lifeBar(g, cj.getBody(), cj.getBodyInitial());
	}
}

/**
 * Static class MyKeyboard to have access to the pressed keys in the keyboard
 * from the text console. (The windows have a different system, based on events,
 * to detect user interaction)
 * 
 * @author Programming Methodology Professors
 *
 */
class MyKeyboard {

	/**
	 * Default constructor, private to avoid creating objects of this class
	 */
	private MyKeyboard() {

	}

	/**
	 * Waits until the Enter key is pressed
	 */
	static public void pressEnter() {

		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Press Enter to continue");
		try {
			keyboard.readLine();
		} catch (IOException ex1) {
		}
	}

	public static String continueOrSaveAndExit() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Press \nEnter to continue " + "\n (1) to serialize the current game");
		return keyboard.nextLine();
	}

	public static String initialMenu() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Press \nEnter for a new game" + "\n (1) to start with a serialized game");
		return keyboard.nextLine();
	}
}
