/** Represents graphically circles in a window
 *  @author Programming Methodology Professors
 *  @version 1.0  */

import javax.swing.JFrame; // needed for the window
import javax.swing.JPanel; // needed for the panel inside of the window
import java.awt.Graphics; // needed to paint the content of the window

import java.io.BufferedReader; // needed (and below) to read keystrokes from the keyboard
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class to represent graphical information
 * using a panel and an objects array to show 
 * @author Programming Methodology Professors
 *
 */
public class MyWindow{

	private MyPanel panel; // panel to show the content
	private JFrame aplicacion = null;
	
	/**
	 * Constructor of MyWindow
	 * @param title the title of the window
	 */
	public MyWindow(String title)
	{
		// create the object of type JFrame that represents the window
		aplicacion = new JFrame(title);
		
		// create a panel
		panel = new MyPanel();		
		
		// set what to do when the window is closed -> exit the application
		aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		
		// add the panel to the window
		aplicacion.add(panel);
		// set the window dimensions
		aplicacion.setSize(350, 350);
		// make the window visible
		aplicacion.setVisible(true);
	}
	
	/**
	 * Set the array of objects to be shown and force to update the visualization
	 * @param cirs new array of objects to show
	 */
	public void showCircles(CircleWithCentre [] cirs)
	{
		panel.setCircles(cirs);
		// force to repaint the circles in the window
		panel.repaint();
	}
	
	/**
	 * Close the window and frees the used resources
	 */
	public void close()
	{
		aplicacion.dispose();
	}
	
}


/**
 * Class MyPanel, represents a content panel to show in a window
 * specifically an array of objects
 * @author Programming Methodology Professors
 *
 */
class MyPanel extends JPanel 
{
	// NOTE: this variable definition avoids a warning related to serialized classes
	private static final long serialVersionUID = 1L;

	// array of objetcs to show
	private CircleWithCentre[] circles = null;

	/**
	 * Observer of circles
	 * @return circles
	 */
	public CircleWithCentre[] getCircles() {
		return circles;
	}

	/**
	 * modifier of circles
	 * @param circles new array with circles
	 */
	public void setCirculos(CircleWithCentre[] circles) {
		this.circles = circles;
	}

	/**
	 * Method  to show a circle in the current panel
	 * @param label text to show in the centre of the circle
	 * @param c circle to show
	 * @param g the object of class Graphics where to paint
	 */
	private void showCircle(String label, CircleWithCentre c, Graphics g)
	{
		int offset = -(int)c.getRadio();
		int cX = (int)(c.getCentro().getX());
		int cY = (int)(c.getCentro().getY());
		int cRadio = (int)(c.getRadio());
		// paint the circle
		g.drawOval(
				cX + offset, 
				cY + offset,
				2 * cRadio, 2 * cRadio);

		// paint the text
		g.drawString(label, cX + cRadio + offset - 5, cY + cRadio + offset + 5);
	}

	/**
	 * Method to repaint the array of objects
	 *        @param g the object of class Graphics where to paint
	 * (NOTE: JAVA invokes this method whenever it is necessary to repaint
	 *        the panel (ex.: maximize, minimizer, move the window,...)
	 */
	public void paintComponent(Graphics g)
	{	
		// ask the parent class to paint its content
		super.paintComponent(g);

		// paint the circles in the array
		Integer x2 = 0;
		if (this.circles != null)
			for(CircleWithCentre c: this.circles){
				showCircle(x2.toString(),c,g);
				x2++;
			}


	}

}


/**
 * Static class MyKeyboard to have access to the pressed keys in the keyboard
 * from the text console. 
 * (The windows have a different system, based on events, to detect user interaction) 
 * @author Programming Methodology Professors
 *
 */
class MyKeyboard {
	/**
	 * Waits until the Enter key is pressed
	 */
	static	public void pressEnter() {

		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Press Enter to continue");
		try {
			keyboard.readLine();
		}
		catch (IOException ex1) {
		}
	}
}
