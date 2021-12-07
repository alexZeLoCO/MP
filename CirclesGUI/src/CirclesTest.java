/**
 * Main program for the visualization of circles in a window
 * @author Programming Methodology Professors
 * @version 1.0
 *
 */



public class CirclesTest {

	// Window where to show the circles
	private static MyWindow w = null;
	
	/**
	 * Program's main function
	 * @param args
	 */
	public static void main(String[] args) {

		// total number of circles to show
		final int TOTAL = 10;
		// array of circles with centre
		CircleWithCentre[] circles = new CircleWithCentre[TOTAL];

		// Create and show the application's window
		// (initially without circles)
		iniciaVentana();

		// Generate circles
		// centre with coordinates X and Y in the range [1..250]
		// and radius [1..50]
		for(int x = 0; x < TOTAL; x++)
			circles[x] = new CircleWithCentre(Dice.roll(250),Dice.roll(250),Dice.roll(50));

		// CONSOLE: show circles in text format
		for(CircleWithCentre c: circles)
			System.out.println(c);	

		// WINDOW: show circles (in the window)
		showCircles(circles);

		// We wait until the Enter key is pressed
		// MyKeyboard is a static class defined in MyWindow.java
		MyKeyboard.pressEnter();	


		// close and frees the resources used by the window
		closeWindow();


	}

	/**
	 * Creates and show the window with the title "Circles"
	 */
	 private static void iniciaVentana()
	 {
		 w = new MyWindow("Circles"); 
	 }
	 
	 /**
	  * Closes the window 
	  */
	 private static void closeWindow()
	 {
		 w.close();
	 }
	 
	 /**
	  * Updates the array of circles to show in the window
	  * and repaint them
	  * @param circles array of circles to show
	  */
	 private static void showCircles(CircleWithCentre[] circles)
	 {
	 	// Show the circles in the window
		w.showCircles(circles);
	 }
}
