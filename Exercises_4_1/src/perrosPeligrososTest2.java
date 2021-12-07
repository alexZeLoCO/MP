import java.util.Formatter;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class perrosPeligrososTest2 {

	public static void main(String[] args) {
		//System.out.print(ordenaRaza("censoPerrosPeligrosos.txt").toString());
		//razasEnFichero(ordenaRaza("censoPerrosPeligrosos.txt"),"censoPerrosPeligrosos.txt","Output.txt");
		diagrama("Output.txt","Diagrama.txt");
	}

	public static DynamicVectorStrings ordenaRaza(String ruta) {
		DynamicVectorStrings razas = new DynamicVectorStrings();
		Scanner in = null;

		String raza;

		try {
			in = new Scanner(new FileInputStream(ruta));

			razas.add(in.next());
			in.next();
			in.nextInt();
			in.next();
			in.nextInt();

			while (in.hasNext()) {
				raza = in.next();
				in.next();
				in.nextInt();
				in.next();
				in.nextInt();

				if (!razas.has(raza)) {
					razas.add(raza);
				}
			}

			for (int x=0;x<2;x++) {
				for (int i = 0; i < razas.length(); i++) {
					for (int j = i + 1; j < razas.length(); j++) {
						try {
							if (((String) razas.get(i)).compareTo(((String) razas.get(j))) > 0
									&& ((String) razas.get(i)).compareTo(((String) razas.get(j+1))) < 0) {
								razas.insert(j + 1, razas.get(i));
								razas.remove(i);
							}
						} catch (IndexOutOfBoundsException e) {
							razas.add(razas.get(i));
							razas.remove(i);
						}
					}
				}
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			if (in != null)
				in.close();
		}
		return razas;
	}

	public static void razasEnFichero(DynamicVectorStrings razas, String rutaI, String rutaO) {
		int [] cantidades = new int [razas.length()];
		for (int i : cantidades) {
			i=0;
		}

		Scanner in = null;
		Formatter out = null;

		try {
			in = new Scanner (new FileInputStream (rutaI));
			while (in.hasNext()) {
				String raza = in.next();
				int i=0;
				while (i<razas.length() && !razas.get(i).equals(raza)) {
					i++;
				}

				in.next();		//Tamaño
				in.next();		//CP
				in.next();		//Barrio

				cantidades[i]=cantidades[i]+in.nextInt();
			}
			out = new Formatter (rutaO);

			int i=0;
			for (String razaO : razas.vectorNormal()) {
				out.format(razaO + "\t" + cantidades[i] + "\n");
				i++;
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	public static void diagrama (String nombreFicheroEntrada, String nombreFicheroSalida) {
		Scanner in = null;
		Formatter out = null;
		
		try {
			in = new Scanner (new FileInputStream (nombreFicheroEntrada));
			out = new Formatter (nombreFicheroSalida);
			

			while (in.hasNext()) {
				String raza;
				String s="";
				raza = in.next();
				int cantidad = in.nextInt();
				int c = cantidad;
				if (cantidad >= 100) {
					do {
						s = s + "c";
						cantidad=cantidad-100;
					} while (cantidad>=100);
				}
				for (int i=0;i<cantidad; i++) {
					s=s+"*";
				}
				out.format(raza + "\t" + s + "\t("+c+")\n");
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
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
