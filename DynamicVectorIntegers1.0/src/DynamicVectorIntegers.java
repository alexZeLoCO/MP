import java.io.Serializable;

// Class that allows representing dynamic vectors of integers (int)
// It includes operations like insert, remove, set, get, ...
// In this version the internal vector is adjusted for any operation to the number 
// of elements contained 

public class DynamicVectorIntegers implements Serializable {

	private static final long serialVersionUID = 1L;
	// data representation
	private int [] data;
	
	// Constructors
	public DynamicVectorIntegers(){
		data = null;
	}
	
	public DynamicVectorIntegers(DynamicVectorIntegers vOI){
		data = new int[vOI.length()];
		for (int i=0; i<data.length; i++)
			data[i] = vOI.get(i);
	}
	
	public DynamicVectorIntegers(int size){
		data = new int [size];
		for (int i = 0; i<data.length; i++)
			data[i] = 0;
	}
	
	public DynamicVectorIntegers(int [] v){
		if (v==null) data = null;
		else{
			data = new int [v.length];
			for (int i = 0; i<data.length; i++)
				data[i] = v[i];
		}
	}
	
	// Getters
	public int length() {
		if (data == null) return 0;
		else return data.length;
	}
	
	public boolean isEmpty(){
		return length() == 0;
	}
	
	public int get(int i){
		return data[i];
	}
	
	public void show(){
		System.out.println("\nDynamicVectorIntegers");
		System.out.printf("Length: %d", length());
		System.out.print("\nData: ");
		for(int i = 0; i<length(); i++)
			System.out.printf(" %d", data[i]);
		System.out.println();
	}
	
	public int position(int x) { // position of the first time x appears, -1 if not present
		int i=0;
		while (i<length()){
			if (get(i) == x) return i;
			i++;
		}
		return -1;
	}
	
	public boolean member(int x) {
		return position(x) != -1;
	}
	
	// Setters
	public void set(int i, int x){
		data[i] = x;
	}
	
	  
	public void insert(int i, int x){ // insert in i and displace the next ones to the right
		int [] temp = new int[length() + 1];
		for (int j = 0; j<i; j++)
			temp[j] = data[j];
		temp[i] = x;
		for (int j = i+1; j<temp.length; j++)
			temp[j] = data[j-1];
		data = temp;
	}
	
	public void add(int x) { // adds at the end
        insert(length(),x);
	}
	
	public void push(int x){
		insert(0,x);
	}
	
	public void erase(){
		data = null;
	}
	
	public void remove(int i) { // removes the int at position i and displace the next ones to the left
		int [] temp = new int[length() - 1];
		for (int j = 0; j<i; j++)
			temp[j] = data[j];
		for (int j = i; j<temp.length; j++)
			temp[j] = data[j+1];
		data = temp;
	}
	
	public int pop(){
		int x = data[0];
		remove(0);
		return x;
	}
	
}
