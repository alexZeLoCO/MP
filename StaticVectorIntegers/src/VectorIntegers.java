
// Class that allows us to represent vectors of integers  (int)
//  It includes methods like set, get, length, ...

public class VectorIntegers {

	// data representation
	private int[] data;

	// Constructors

	/**
	 * default constructor
	 */
	public VectorIntegers() { // default constructor
		data = null;			//CREA UN VECTOR VACÍO
	}

	/**
	 * copy constructor
	 * 
	 * 	DE UN VECTORINTEGERS YA EXISTENTE CREA OTRO QUE SEA IGUAL
	 * 
	 * @param vOI vector to replicate
	 */
	public VectorIntegers(VectorIntegers vOI) { // copy constructor
		data = new int[vOI.length()];
		for (int i = 0; i < data.length; i++)
			data[i] = vOI.get(i);
	}

	/**
	 * constructor from the vector size
	 * 
	 * 	CREA UN VECTORINTEGERS DEL TAMAÑO SIZE QUE SE PASA COMO PARÁMETRO CON TODOS SUS ELEMENTOS 0
	 * 
	 * @param size vector size
	 */
	public VectorIntegers(int size) {
		data = new int[size];
		for (int i = 0; i < data.length; i++)
			data[i] = 0;
	}

	/**
	 * constructor from a Java array
	 * 
	 * 	CREA UN VECTORINTEGERS CON LA MISMA LONGITUD Y DATOS QUE UN VECTOR NORMAL DE ENTEROS
	 * @param v array to duplicate
	 */
	public VectorIntegers(int[] v) {
		if (v == null)
			data = null;
		else {
			data = new int[v.length];
			for (int i = 0; i < data.length; i++)
				data[i] = v[i];
		}
	}

	// Getters

	/**
	 * Getter of the vector length
	 * 
	 * 	DEVUELVE LONGITUD DEL VALOR
	 * 
	 * @return vector length
	 */
	public int length() {
		if (data == null)
			return 0;
		else
			return data.length;
	}

	/**
	 * Getter to check if a vector has size 0
	 * 
	 * 	REVISA SI EL VECTOR NO ES VACÍO
	 *
	 * @return true if the vector is empty or true otherwise
	 */
	public boolean isEmpty() {
		return length() == 0;
	}

	/**
	 * Getter of one element from the vector
	 * 
	 * 	DEVUELVE EL VALOR EN POSICIÓN I DEL VECTOR
	 * 
	 * @param i element position
	 * @return element in that position
	 */
	public int get(int i) {
		return data[i];
	}

	@Override
	/**
	 * Vector in text format
	 * 
	 * 	IMPRIME EL VECTOR TOSTRING
	 * 
	 */
	public String toString() {
		String s;
		s = "[ ";
		for (int i = 0; i < length(); i++)
			s += data[i] + " ";
		s += "]";

		return s;
	}

	/**
	 * Getter that shows the vector in the console
	 * 
	 * 	IMPRIME EL VECTOR NO TOSTRING
	 * 
	 */
	public void show() {
		System.out.println("\nVectorIntegers");
		System.out.printf("Length: %d", length());
		System.out.print("\ndata: ");
		for (int i = 0; i < length(); i++)
			System.out.printf(" %d", data[i]);
		System.out.println();
	}

	// Setters

	/**
	 * Modifies the value of one element in the vector
	 * 
	 * 	EN LA POSICIÓN I PONE EL VALOR X
	 * 
	 * @param i element position
	 * @param x new value
	 */
	public void set(int i, int x) {
		data[i] = x;
	}

}
