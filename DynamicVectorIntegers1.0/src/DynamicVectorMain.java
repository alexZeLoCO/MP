import java.io.*;

// Program that test the class DynamicVectorIntegers
public class DynamicVectorMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		// Create a dynamic vector from a static one
		int [] v = {1,2,3,4,5};
		DynamicVectorIntegers vOI = new DynamicVectorIntegers(v);

		int [] v2 = {10,20,30,40,50};
		DynamicVectorIntegers vOI2 = new DynamicVectorIntegers(v2);
		vOI2.show();
		
		// Modify the dynamic vector and observe the changes

		vOI.add(5);
		vOI.add(25);
		vOI.show();

//-------------------SERIALIZACION-------------------
		ObjectOutputStream output = null;

		try {
			output = new ObjectOutputStream (new FileOutputStream ("dv1.ser"));
			output.writeInt(2);		//Write the number of serialized objects to avoid IOEOF
			output.writeObject(vOI);
			output.writeObject(vOI2);
			//output.writeObject(null);		//Write null in the end to mark the ending of file in order to avoid IOEOF
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				output.close();
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		}

//----------------DESERIALIZACION-------------------
		ObjectInputStream input = null;
		try {
			input = new ObjectInputStream (new FileInputStream ("dv1.ser"));
			System.out.println("\nDeserializando . . .");
			DynamicVectorIntegers vLeido;
			for (int i=0 ; i<input.readInt();i++) {		//For number of serialized objects in .ser
				vLeido = (DynamicVectorIntegers) input.readObject();
				vLeido.show();
			}
			/*		//Use when null in the end of file to avoid IOEOF
			vLeido = (DynamicVectorIntegers) input.readObject();
			while (vLeido!=null) {
				vLeido.show();
				vLeido = (DynamicVectorIntegers) input.readObject();
			}
			*/
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null) { input.close();	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		

		/*
		//vOI[2]=26; That would be nice
		vOI.set(2, 26);
		vOI.show();

		vOI.set(1, vOI.get(2)+10);
		vOI.show();


		vOI.remove(2);
		vOI.show();

		vOI.erase();
		vOI.show();
		vOI.insert(vOI.length(),100);
		vOI.show();


		vOI.push(1000);
		vOI.show();

		int x = vOI.pop();
		vOI.show(); 

	    vOI.remove(4);
		vOI.show();

		vOI.add(-25);
		vOI.show();

		 */





		// Now, some interesting algorithms (Implemented as static methods in the class ...Test)

		// 1.- Get from a vOI another that contain only the even numbers

		//DynamicVectorIntegers vOI1 = onlyEven(vOI);
		//vOI1.show();

		// 2.- -Check if all elements in a vOI1 are contained in another vOI

		//boolean h = isContained(vOI1,vOI);

		//System.out.println(h);

		// 3.- Given a vOI, remove all elements that are negative

		//int[] v1 = {-1,-2,1,-2,2,-3,-4,-5,3,-6,4};
		//DynamicVectorIntegers vOI3 = new DynamicVectorIntegers(v1);
		//removeNegatives(vOI3);
		//vOI3.show();

		// 4.- Of course: sort a vOI (ascending)

		//sort(vOI);

		// 5.- Check if a vector is sorted

		//boolean h1 = sorted(vOI);

		// 6.- The same as in 2., but assuming that the two vOIs are sorted

		//boolean h2 = isContainedSorted(vOI1,vOI);

		// 7.- Remove duplicated values in a sorted vOI

		//removeDuplicatesSorted(vOI);

		// 8.- Remove all instances of x in a vector (not necessarily sorted)

		//removeAllInstances(25,vOI);

		// 9.- Insert a new element in a vOI maintaining it sorted

		//insertSorted(23,vOI);

		// 10.- Given two sorted vOIs, get another vOI that contains all elements from both 
		//     sorted as well.

		//DynamicVectorIntegers vOI2 = mixSorted(vOI,vOI1);

		// 11.- Check if a sorted vOI contains an integer x (dichotomic search) and if so return the position 
		//      where x is

		//int pos = dichotomicSearch(23,vOI);
		//System.out.println(pos);

		// 12.- Given two vOIs, tell if the values from the first one appear in the second one in the same order and consecutively 
		//      from any position in the second. If this is true, return the position of the time this appears

		//int pos1 = isSubVector(vOI1,vOI2);


	}

	// Implementation of the methods . . .

	public static DynamicVectorIntegers onlyEven(DynamicVectorIntegers vOI){
		DynamicVectorIntegers vTEMP = new DynamicVectorIntegers();
		for(int i=0; i<vOI.length(); i++)
			if(vOI.get(i) % 2 == 0) vTEMP.add(vOI.get(i));
		return vTEMP;
	}

}
