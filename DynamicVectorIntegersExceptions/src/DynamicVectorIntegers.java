
// Class that allows representing dynamic vectors of integers (int)
// It includes operations like insert, remove, set, get, ...
// In this version the vector grows with a scaling factor INCREMENT, 
// and the space is freed when the occupancy level is below capacity()/C1 
// restriction: the default and minimum capacity is MINIMUM elements


public class DynamicVectorIntegers {
	// data representation
	private int[] data;

	private int length; // different to a data.length

	private static final int MINIMUM = 4; // minimum capacity

	private static final int INCREMENT = 2;

	private static final int C1 = 4; // 1/C1 is the limit to downsize

	// getters for length and capacity
	public int capacity() {
		return (data == null) ? 0 : data.length;
	}

	public int length() {
		return length;
	}

	// Constructors
	public DynamicVectorIntegers() {
		data = new int[MINIMUM];
		length = 0;
	}

	public DynamicVectorIntegers(int size) {
		data = new int[Math.max(size, MINIMUM)];
		length = 0;
	}

	public DynamicVectorIntegers(DynamicVectorIntegers vOI) {
		if (vOI == null) {
			throw new IllegalArgumentException("Valores nulos no permitidos.");
		}
		data = new int[Math.max(vOI.capacity(), MINIMUM)];
		for (int i = 0; i < vOI.length(); i++)
			data[i] = vOI.get(i);
		length = vOI.length();
	}

	public DynamicVectorIntegers(int[] v) {
		if (v == null) {
			throw new IllegalArgumentException("Valores nulos no permitidos.");
		}
		length = v.length; // length = capacity
		data = new int[Math.max(v.length, MINIMUM)]; // capacity = v.length
		for (int i = 0; i < length(); i++)
			data[i] = v[i];
	}

	public boolean isEmpty() {
		return length() == 0;
	}

	public int get(int p) {
		//Control de posiciones anómalas
		//Posición negativa
		if (p<0) {
			throw new IndexOutOfBoundsException ("Indice negativo");
		}
		if (p>=data.length) {
			throw new IndexOutOfBoundsException ("Indice " + p + ". Indice fuera de rango (" + data.length + ")");
		}
		return data[p];
	}

	public void show() {
		System.out.println("\nDynamicVectorIntegers");
		System.out.printf("Length: %d", length());
		System.out.printf(" Capacity: %d", capacity());
		System.out.print("\nData: ");
		for (int i = 0; i < length(); i++)
			System.out.printf(" %d", data[i]);
		System.out.println();
	}

	public int position(int x) { // position of the first time x appears, -1 if not present
		int i = 0;
		while (i < length()) {
			if (get(i) == x)
				return i;
			i++;
		}
		return -1;
	}

	public boolean member(int x) {
		return position(x) != -1;
	}

	// setters
	public void set(int p, int x) {
		data[p] = x;
	}

	public void add(int x) { // adds at the end
		insert(length(), x);
	}

	public void insert(int p, int x) {
		if (length() == capacity()) { // if the vector is full
			// resize to double
			int[] temp = new int[capacity() * INCREMENT];

			for (int i = 0; i < p; i++) // copy the previous values to the position i
				temp[i] = data[i];

			temp[p] = x; // set the new inserted value

			for (int i = p + 1; i <= length(); i++) // move only the positions with real values
				temp[i] = data[i - 1];

			data = temp;
		} else {
			for (int i = length(); i > p; i--) // move only the positions with real values (from the last)
				data[i] = data[i - 1];

			data[p] = x;

		}
		// length
		length++;
	}

	public void push(int x) {
		insert(0, x);
	}

	public void erase() {
		data = new int[MINIMUM]; // the minimum capacity is MINIMUM
		length = 0;
	}

	public void remove(int p) {
		// removes the value at position p and move the next ones to the left
		// if the length is lower than 1/C1, then resize to length * INCREMENT
		if (capacity() == MINIMUM || length() > capacity() / C1) {
			for (int i = p; i < length(); i++)
				data[i] = data[i + 1];
		} else {
			// resizing
			int[] temp = new int[Math.max(MINIMUM, length() * INCREMENT)];
			for (int i = 0; i < p; i++)
				temp[i] = data[i];
			for (int i = p; i < length() - 1; i++)
				temp[i] = data[i + 1];
			data = temp;
		}
		length = length - 1;
	}

	public int pop() {
		if (isEmpty()) {
			System.err.println("\nEmpty vector!!");
			return -1;
		} else {
			int x = data[0];
			remove(0);
			return x;
		}
	}

}
