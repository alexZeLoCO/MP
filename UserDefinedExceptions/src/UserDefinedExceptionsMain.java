// Fig. 11.2: DivideByZeroWithExceptionHandling.java
// Un poco retocador
// Handling ArithmeticExceptions .

import java.util.Scanner;

public class UserDefinedExceptionsMain {
	// demonstrates throwing an exception when a divide-by-zero occurs
	public static int quotient(int numerator, int denominator)
			throws ArithmeticException
	// throws ArithmeticException
	{
		if (denominator == 0)
			throw new DivisionByZeroException();
		return numerator / denominator; // possible division by zero
	} // end method quotient

	public static int quotient1(int numerator, int denominator)
			throws ArithmeticException
	// throws ArithmeticException
	{
		try {
		if (denominator == 0)
			throw new DivisionByZeroException();
		} catch (DivisionByZeroException e) {
			e.muestraMensaje();
			System.out.println("El cero no vale, intentalo otra vez.\n");
			if (numerator>=0) {
				return Integer.MAX_VALUE;
			} else {
				return Integer.MIN_VALUE;
			}
		}
		return numerator / denominator; // possible division by zero
	} // end method quotient
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // scanner for input
		boolean continueLoop = true; // determines if more input is needed

		do {
			try // read two numbers and calculate quotient
			{
				System.out.print("Please enter an integer numerator: ");
				int numerator = scanner.nextInt();
				System.out.print("Please enter an integer denominator: ");
				int denominator = scanner.nextInt();

				int result = quotient(numerator, denominator);
				System.out.printf("\nResult: %d / %d = %d\n", numerator,
						denominator, result);
				continueLoop = false; // input successful; end looping
			} // end try
			catch (DivisionByZeroException e) {
				// System.err.printf( "\nException: %s\n", e );
				e.muestraMensaje();
				System.out.println("El cero no vale, intentalo otra vez.\n");
				// throw arithmeticException;
			} // end catch
			catch (ArithmeticException arithmeticException) {
				System.err.printf("\nException: %s\n", arithmeticException);
				System.out
						.println("Zero is an invalid denominator. Please try again.\n");
				// throw arithmeticException;
			} // end catch
			
			
		} while (continueLoop); // end do...while
	} // end main
} // end class DivideByZeroWithExceptionHandling

/**************************************************************************
 * (C) Copyright 1992-2010 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/
