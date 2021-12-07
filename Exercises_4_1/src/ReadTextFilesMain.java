import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ReadTextFilesMain {

	public static void main(String[] args) {
		Scanner in = null;
		String fileName = "censoPerrosPeligrosos.txt";
		try {
			in = new Scanner(new FileInputStream(fileName));

			while(in.hasNext()){
				// 1 - Run this program as it is
				// 2 - Comment out the next line and uncomment one of the following in each new execution
				System.out.println(in.nextLine());
				//System.out.println(in.next());
				//System.out.println(in.next() + '\t' + in.next()  + '\t' + in.nextInt()  + '\t' + in.next()  + '\t' + in.nextInt());
				//System.out.println(in.next() + ' ' + in.next()  + ' ' + in.nextInt()  + ' ' + in.next()  + ' ' + in.nextInt());
			}
			
		} catch(IOException e) {
			System.err.println(e.getMessage());
		} finally {
			if (in!=null) in.close();
		}

	}
}
