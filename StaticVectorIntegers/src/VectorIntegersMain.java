// Test program for the class VectorIntegers


public class VectorIntegersMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create a vector from an array
		int[] v = { 1, 2, 3, 4, 5 };
		// int [] v = null;
		VectorIntegers vI1 = new VectorIntegers(v);
		vI1.show();

		// vOI[2]=26 // We would like that
		vI1.set(2, 26);
		vI1.show();

		vI1.set(1, vI1.get(2) + 10);
		vI1.show();

		// showing via toString()
		System.out.println(vI1);

		// Now some interesting algorithms (implemented with static methods ...Test)

		VectorIntegers vI2 = new VectorIntegers(new int[] { 7, 3, 6, 5, 26, 36, 4 });
		System.out.println("Vector2:" + vI2);

		// 1.- Check if a value is in the vector
		System.out.print("The element 5 ");
		if (member(vI2, 5))
			System.out.print("is");
		else
			System.out.print("is not");
		System.out.println(" in the vector vector");

		System.out.println("The element 8 is " + (member(vI2, 8) ? "" : "not") + " in the vector");

		// 2.- Get the first position in the vector of a value ( or -1 if the value is
		// not in the vector)
		System.out.println("Position of 6 in the vector: " + position(vI2, 6));
		System.out.println("Position of 12 in the vector: " + position(vI2, 12));

		// 3.- Get the maximum value of the vector
		int max = searchMaximum(vI2);
		System.out.println("The maximum is: " + max);

		// 4.- Sort the elements of the vector in ascending order
		sortVector(vI2);
		System.out.println("After sorting:" + vI2);

		// 5.- Check if all elements of a vector are contained in another

		boolean h = isContained(vI1,vI2);	//SI TODOS LOS ELEMENTOS DE VI1 ESTÁN EN VI2

		System.out.println(h);

		// 6.- Check if a vector is sorted

		// boolean h1 = isSorted(vOI);

		// 7.- Check if a sorted vector contains a value x (dichotomic search) and if
		// true return a position
		// in which x is

		// int pos = dichotomicSearch(23,vOI);
		// System.out.println(pos);

		// 8.- Given two vectors, tell if the values of the first one appear in the
		// second in the same order and consecutive
		// from any position in the second. If true, return the position of the first
		// time they appear

		// int pos1 = isSubVector(vOI1,vOI2);

		// . . .
	}

	// Methods implementation . . .

	/**
	 * Checks if a element is in the vector
	 * 
	 * @param v vector of integers
	 * @param x value to search for
	 * @return true if the value is present or false otherwise
	 */
	public static boolean member(VectorIntegers v, int x) {
		//RECORREMOS EL VECTOR EN BUSCA DEL VALOR X
		boolean encontrado=false;			//VARIABLE CENTINELA
		
			//PARA TANTAS VECES COMO ELEMENTOS TENGA EL VECTOR O SE ENCUENTRE EL ELEMENTO QUE BUSCAMOS...
		for (int i=0;i<v.length() && !encontrado;i++) {
			if (v.get(i)==x) {			//SI EL ELEMENTO ES IGUAL AL QUE BUSCAMOS
				encontrado=true;			//CAMBIAMOS PARIDAD DEL CENTINELA ==> BUCLE FOR SE DETIENE
			}
		}
		return encontrado;			//RETORNA CENTINELA

	}

	/**
	 * Finds the first position of an element in the vector
	 * 
	 * @param v vector of integers
	 * @param x value to find
	 * @return the first position of the value in the vector, or -1 is it is not
	 *         present
	 */
	public static int position(VectorIntegers v, int x) {
		boolean encontrado=false;			//VARIABLE CENTINELA
		int pos=-1;			//POSICIÓN DEL PRIMER ELEMENTO, SI NO SE ENCUENTRA SE DEVOLVERÁ -1
		for (int i=0;i<v.length() && !encontrado;i++) {
			if (v.get(i)==x) {			//SI EL ELEMENTO ES IGUAL AL QUE BUSCAMOS
				encontrado=true;			//CAMBIAMOS PARIDAD DEL CENTINELA ==> BUCLE FOR SE DETIENE
				pos = i;		//SI SE ENCUENTRA RETORNAR POSICIÓN
			}
		}
		return pos;

	}

	/**
	 * Search the maximum value of a non-null vector of integers
	 * 
	 * @param v vector of integers
	 * @return the maximum value
	 */
	private static int searchMaximum(VectorIntegers v) {
		int max=v.get(0);			//EL PRIMER ELEMENTO ES SIEMPRE EL MAYOR
		for (int i=1;i<v.length();i++) {		//PARA CADA ELEMENTO DEL VECTOR
			if (v.get(i)>max) {		//SI ES MAYOR AL MÁS GRANDE
				max =v.get(i);		//ACTUALIZAR MAYOR
			}
		}	
		return max;			//RETORNAR MAYOR
	}

	/**
	 * Sorts the elements of a vector in ascending order
	 * 
	 * @param v vector to sort
	 */
	private static void sortVector(VectorIntegers v) {
		for (int i=0;i<v.length()-1;i++) {
			if (v.get(i)<v.get(i+1)) {
				int valor = v.get(i);
				v.set(i, v.get(i+1));
				v.set(i+1, valor);
			}
		}
		
	}

	private static boolean isContained(VectorIntegers vI1, VectorIntegers vI2) {
		boolean contained=true;			//CENTINELA
		for (int i=0;i<vI1.length() && contained==true;i++) {			//PARA CADA ELEMENTO DEL VECTOR 1
			contained=member(vI1,vI2.get(i));		//COMPROBAR TODOS LOS ELEMENTOS CON EL 2
		}
		for (int i=0;i<vI2.length() && contained==true;i++) {			//PARA CADE ELEMENTO DEL VECTOR 2
			contained=member(vI2,vI1.get(i));		//COMPROBAR TODOS LOS ELEMENTOS  CON EL 1
		}
		return contained;			//RETORNAR CENTINELA
	}

}
