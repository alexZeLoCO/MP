import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		try {
		procesaFichero("Calidad_aire_Gijón.csv");
		registrosATexto("valores_excesivos.txt");
		} catch (Exception e) {
			System.err.println("Se produjo un error:\n"+e.getMessage());
			e.printStackTrace();
		}
	}


	@SuppressWarnings("resource")
	private static void procesaFichero(String filename) throws Exception {
		Scanner in = null;
		ObjectOutputStream out = null;
		
		VectorDinamicoRegistro PM10 = new VectorDinamicoRegistro();
		VectorDinamicoRegistro PM25 = new VectorDinamicoRegistro();
		VectorDinamicoRegistro NO2  = new VectorDinamicoRegistro();
		
		String fecha;
		String zona;
		int pm10;
		int pm25;
		int no2;
		
		try {
			in = new Scanner (new FileInputStream (filename));
			in.useDelimiter(",|;|\r\n");
			in.nextLine();		//Titulos
			
			while (in.hasNext()) {
				try {
					fecha = in.next();
					zona = in.next();
					pm10 = in.nextInt();
					pm25 = in.nextInt();
					no2 = in.nextInt();
					
					if (pm10>50) {
						PM10.add(new Registro(zona,fecha,pm10));
					}
					if (pm25>25) {
						PM25.add(new Registro(zona,fecha,pm25));
					}
					if (no2>50) {
						NO2.add(new Registro(zona,fecha,no2));
					}
				} catch (InputMismatchException e) {
					in.nextLine();
					System.out.println(e.getMessage());
				} 
			}
			
			
			out = new ObjectOutputStream (new FileOutputStream ("pm10.ser"));
			out.writeObject(PM10);

			out = new ObjectOutputStream (new FileOutputStream ("pm25.ser"));
			out.writeObject(PM25);
			
			out = new ObjectOutputStream (new FileOutputStream ("no2.ser"));
			out.writeObject(NO2);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception ("Ha ocurrido una excepcion:\n"+e.getMessage());
		} finally {
			if (in!=null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	private static void registrosATexto(String filename) throws Exception {
		ObjectInputStream in = null;
		Formatter out = null;
		String [] nombres = {"PM10","PM25","NO2"};
		VectorDinamicoRegistro v;
		try {
			out = new Formatter (filename);
			out.format("%-20s%-15s%-10s%-10s\n","Fecha","Zona","Cont.","Valor");
			for (int i=0;i<nombres.length;i++) {
				in = new ObjectInputStream (new FileInputStream(nombres[i].toLowerCase()+".ser"));
				v=(VectorDinamicoRegistro)in.readObject();
				for (Object o : v.vectorNormal()) {
					out.format("%-20s%-15s%-10s%-10d\n",
							((Registro)o).getFecha(),((Registro)o).getZona(),nombres[i],((Registro)o).getContaminante());
				}
				out.format("==================================================================\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
