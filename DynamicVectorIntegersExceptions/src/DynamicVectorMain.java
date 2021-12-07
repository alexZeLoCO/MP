
public class DynamicVectorMain {

	public static void main(String[] args) {

		/* Checking errors with wrong positions */
		testPositions();

		/* Checking errors when sending a null values as argument: constructors */
		testNullValues();

		/* Checking errors managing the memory space */
		testMemory();

		/*
		 * Show the maximum capacity that a vector can hold in the current computer with
		 * the current configuration
		 */
		long maxCapacity = maximumCapacity(new DynamicVectorIntegers());
		System.out.printf("The maximum capacity of a vector is: %d\n", maxCapacity);
	}

	private static void testPositions() {
		int[] vI = { 1, 2, 3, 4, 5 };
		DynamicVectorIntegers vD = new DynamicVectorIntegers(vI);

		System.out.println("Starting testPositions()...");

		/* get() */
		/* get access to an element in a negative position */
		try {
			vD.get(-1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("\tTest get(-1): OK - " + e.getMessage());
		}
		/* get access to an element in a position beyond the vector length */
		try {
			vD.get(vD.length());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("\tTest get(vD.length()): OK - " + e.getMessage());
		}
		/* other operation dealing with indices */
		/*
		 * set(int, int) remove(int) insert(int, int)
		 */
		System.out.println("testPositions(): OK");
	}

	private static void testNullValues() {

		System.out.println("Starting testNullValues()...");

		/* constructor from a null vector of integers */
		try {
		new DynamicVectorIntegers((int[]) null);
		} catch (IllegalArgumentException e) {
			System.out.println("\tTest new DynamicVectorIntegers((int[]) null): OK - " + e.getMessage());
		}
		/* constructor from a null DynamicVectorIntegers */
		try {
		new DynamicVectorIntegers((DynamicVectorIntegers) null);
		} catch (IllegalArgumentException e) {
			System.out.println("\tTest new DynamicVectorIntegers((DynamicVectorIntegers) null): OK - " + e.getMessage());
		}
		System.out.println("testNullValues(): OK");
	}

	private static void testMemory() {
		int[] vI = { 1, 2, 3, 4, 5 };
		DynamicVectorIntegers vOI = new DynamicVectorIntegers(vI);

		System.out.println("Starting testMemory()...");

		/* insert elements in vOI until it can take no more */
		try {
		infiniteInsertion(vOI);
		} catch (OutOfMemoryError e) {
			System.out.printf("\tTest infiniteInsertion(vOI): OK - \n\t\tLength: %d (%.2f MB), Capacity: %d (%.2f MB)\n", vOI.length(),
					(vOI.length() * 4) / (double) (1024 * 1024), vOI.capacity(), (vOI.capacity() * 4) / (double) (1024 * 1024));
		}
		/* QUESTION: which is the final length of the vector? */

		System.out.println("testMemory(): OK");
	}

	/**
	 * auxiliary method that inserts infinite number of elements in a
	 * DynamicVectorIntegers
	 * 
	 * @param vOI - The vector where to insert elements
	 */
	private static void infiniteInsertion(DynamicVectorIntegers vOI) {
		int previousCapacity;

		while (true) {
			// capacity of the vector before inserting a new element
			previousCapacity = vOI.capacity();

			// adds one more
			vOI.add(1);

			// if it is resized, show its size
			if (vOI.capacity() > previousCapacity)
				System.out.printf("Length: %d (%.2f MB), Capacity: %d (%.2f MB)\n", vOI.length(),
						(vOI.length() * 4) / (double) (1024 * 1024), vOI.capacity(), (vOI.capacity() * 4) / (double) (1024 * 1024));
		}

	}

	/**
	 * Return the maximum capacity that can have the vector given as argument
	 * 
	 * @param vOI - the DynamicVectorIntegers to analyse
	 * @return the maximum capacity of the DynamicVectorIntegers for the current
	 *         computer and the current configuration
	 */
	@SuppressWarnings("finally")
	private static long maximumCapacity(DynamicVectorIntegers vOI) {
		try {
			infiniteInsertion(vOI);
			} catch (OutOfMemoryError e) {

			} finally {
				return vOI.capacity();
			}
	}
}
