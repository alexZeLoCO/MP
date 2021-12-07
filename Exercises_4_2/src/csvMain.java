import java.io.FileInputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class csvMain {
	static final String FILE_NAME_IN1= "EmpresasGijón.csv" ;//Input1
	static final String FILE_NAME_OUT1= "EmpresasGijón.txt" ;//Output1
	static final String FILE_NAME_IN2= "EmpresasGijón.txt" ;//Input2
	static final String FILE_NAME_OUT2= "EmpresasGijónbis.csv" ;//Output2
    
	public static void main(String[] args) {
		try {
		readCSV(FILE_NAME_IN1,FILE_NAME_OUT1);
		System.out.println("First part of the task done");

		writeCSV(FILE_NAME_IN2,FILE_NAME_OUT2);
		System.out.println("Second part of the task done");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} 
	}
	
	public static void readCSV(String fileNameIn, String fileNameOut) throws IOException
	{ 
		
		Scanner input = new Scanner (new FileInputStream(fileNameIn));
		Formatter output = new Formatter (fileNameOut);
		input.useDelimiter(",|\r\n");
		
		output.format("%-14d%13d%9d%42d%8d\n", 14, 13, 9, 42, 8);
		
		
		output.format("%-14s%13s%9s%42s%8s\n", input.next(), input.next(), input.next(), input.next(), input.next());
		
		while (input.hasNext()) {
			output.format("%-14s%13d%9d%42s%8d\n", input.next(), input.nextInt(), input.nextInt(), input.next(), input.nextInt());
		}
		
		
		if (input != null) {
			input.close();
		}
		if (output != null) {
			output.close();
		}
		
	}
		
	public static void writeCSV(String fileNameIn, String fileNameInOut) throws IOException
	{
		Scanner input = new Scanner (new FileInputStream (fileNameIn));
		Formatter output = new Formatter(fileNameInOut);
		
		input.useDelimiter("\r\n");
		
		String line = input.next();
		
		
		//			TEMPORAL FIX			//	
		String a = line.substring(0,14);
		String b = line.substring(14,14+13);
		String c = line.substring(14+13,14+13+9);
		String d = line.substring(14+13+9,14+13+9+42);
		String e = line.substring(14+13+9+42,14+13+9+42+8);
		
		
		
		if (input != null) {
			input.close();
		}
		if (output != null) {
			output.close();
		}
		
	}
	


	/**
	 * Removes blank spaces from a string. 
	 * The spaces consecutive and internal next to non blank characters are substituted by a '_'.
	 * This algorithm is coded using techniques based on automata theory
	 * @param s a string with blank spaces
	 * @return another string without blank spaces as prefix or sufix, and the internal ones
	 * are substituted by a '_'
	 */
	public static String treatBlanks(String s) { 
		String output= "";
		int state = 0;
		char c;
		for (int i=0; i<s.length(); i++) {
			c = s.charAt(i);
			switch (state) {
			case 0: if (c!=' '){
						output = output + c;
						state = 1;
					}
					break;
			case 1: if (c!=' ')
						output = output + c;
					else state = 2;
					break;
			case 2: if (c!=' ') {
						output = output + '_' + c;
						state = 1;
					}		
			}
		}	
		return output;
				
	}
		
}
