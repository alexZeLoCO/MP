

/**
 *  Class that allows representing dynamic vectors of Objects
 *  It includes operations like insert, remove, set, get, ...
 *
 */

public class DynamicVectorObjects {
	// data representation
	private Object [] data;
	
	// Constructors
	public DynamicVectorObjects(){
		data = new Object[0];
	}
	
	public DynamicVectorObjects(DynamicVectorObjects vDO){
		data = new Object[vDO.length()];
		for (int i=0; i<data.length; i++)
			data[i] = vDO.get(i);
	}
	
	public DynamicVectorObjects(int size){
		data = new Object [size];
		for (int i = 0; i<data.length; i++)
			data[i] = null;
	}
	
	public DynamicVectorObjects(Object [] v){
		data = new Object [v.length];
		for (int i = 0; i<data.length; i++)
			data[i] = v[i];
	}
	
	// Getters
	public int length() {
		return data.length;
	}
	
	public boolean isNull(){
		return data.length == 0;
	}
	
	public Object get(int i){
		return data[i];
	}
	
	public void muestra(){
		System.out.println("\nDynamicVectorObjects");
		System.out.printf("Length: %d", data.length);
		System.out.print("\nData: ");
		for(int i = 0; i<data.length; i++)
			System.out.printf(" %s", data[i]);
		System.out.println();
	}
	
	//Setters
	public void set(int i, Object x){
		data[i] = x;
	}
	
	public void add(Object x) { // adds at the end
		Object [] temp = new Object[data.length + 1];
		for (int i = 0; i<data.length; i++)
			temp[i] = data[i];
		temp[temp.length-1] = x;
		data = temp;
	}
	
	public void insert(int i, Object x){ // inserts in position i and displaces the next ones to the right
		Object [] temp = new Object[data.length + 1];
		for (int j = 0; j<i; j++)
			temp[j] = data[j];
		temp[i] = x;
		for (int j = i+1; j<temp.length; j++)
			temp[j] = data[j-1];
		data = temp;
	}
	
	public void remove(int i) { // removes the int in position i and displace the next ones to the left
		Object [] temp = new Object[data.length - 1];
		for (int j = 0; j<i; j++)
			temp[j] = data[j];
		for (int j = i; j<temp.length; j++)
			temp[j] = data[j+1];
		data = temp;
	}
	
	// Other methods
	public Object [] vectorNormal(){ // returns an Object [] 
		Object [] temp = new Object[this.length()];
		for (int i = 0; i<temp.length; i++)
			temp[i] = this.get(i);
		return temp;
	}

}


