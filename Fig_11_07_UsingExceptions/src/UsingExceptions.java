// Fig. 11.7: UsingExceptions.java
// Throwable methods getMessage, getStackTrace and printStackTrace.

public class UsingExceptions 
{
	public static void main( String[] args )
	{
		try 
		{ 
			method1(); // call method1 
		} // end try
		catch ( Exception exception ) // catch exception thrown in method1
		{ 
			System.err.printf( "%s\n\n", exception.getMessage() );
			exception.printStackTrace(); // print exception stack trace

			// obtain the stack-trace information
			StackTraceElement[] traceElements = exception.getStackTrace();

			System.out.println( "\nStack trace from getStackTrace:" );
			System.out.println( "Class\t\tFile\t\t\tLine\tMethod" );

			// loop through traceElements to get exception description
			for ( StackTraceElement element : traceElements ) 
			{
				System.out.printf( "%s\t", element.getClassName() );
				System.out.printf( "%s\t", element.getFileName() );
				System.out.printf( "%s\t", element.getLineNumber() );
				System.out.printf( "%s\n", element.getMethodName() );
			} // end for
		} // end catch
	} // end main

	// call method2; throw exceptions back to main
	public static void method1() throws Exception
	{
		try {
			method2();
		} catch (Exception exception) {
			System.err.printf( "%s\n\n", exception.getMessage() );
			throw new Exception (exception.getMessage() + "capturado por method1");
		}

	} // end method method1

	// call method3; throw exceptions back to method1
	public static void method2() throws Exception
	{
		try {
			method3();
		} catch (Exception exception) {
			System.err.printf( "%s\n\n", exception.getMessage() );
			throw new Exception (exception.getMessage() + "capturado por method2");
		}
	} // end method method2

	// throw Exception back to method2
	public static void method3() throws Exception
	{
		throw new Exception( "Exception thrown in method3" );
	} // end method method3
} // end class UsingExceptions

/**************************************************************************
 * (C) Copyright 1992-2010 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/