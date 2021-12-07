import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Formatter;
import java.util.Scanner;

public class FigurasTest {
	public static void main(String[] args) {
		try {
			VectorDinamicoFiguraGeometrica v;
			v= leeFichero("Datos.txt");
			for(int i=0;i<v.length();i++) {
				System.out.println(v.get(i).toString());
			}
			escribeFichero("Salida.txt",v);
			
			
			VectorDinamicoFiguraGeometrica v2 = new VectorDinamicoFiguraGeometrica();
			v2.add(new Triangulo(1,2,3,4,5,6));
			v2.add(new Circulo(7));
			v2.add(new CirculoConCentro(8,9,10));
			
			serializaVDFiguraGeometrica(v2,"Serializado.ser");
			VectorDinamicoFiguraGeometrica w= deserializaVDFiguraGeomtrica("Serializado.ser");
			for(int i=0;i<w.length();i++) {
				System.out.println(w.get(i).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	public static VectorDinamicoFiguraGeometrica leeFichero(String nombreFichero) {
		Scanner in = null;
		try {
		in = new Scanner (new FileInputStream(nombreFichero));
		VectorDinamicoFiguraGeometrica vector = new VectorDinamicoFiguraGeometrica();
		boolean added;
		in.useDelimiter(",|\r\n");
		while (in.hasNext()) {
			added=false;
			switch (in.nextInt()) {
			case (0):
				vector.add(new Circulo (in.nextInt()));
				added=true;
				break;
			case (1):
				vector.add(new CirculoConCentro(in.nextInt(),in.nextInt(),in.nextInt()));
				added=true;
				break;
			case (2):
				vector.add(new Triangulo(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt()));
				added=true;
				break;
			}
			if (!added) {
				in.nextLine();
				throw new IOException ("El valor no describe ninguna figura.");
			}
		}
		return vector;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			if (in!=null) {
				in.close();
			}
		}
		return null;
	}
	
	public static void escribeFichero(String nombreFichero, VectorDinamicoFiguraGeometrica v) throws Exception {
		Formatter out = null;
		try {
			out = new Formatter (nombreFichero);
			int n=0;
			for (Object o : v.vectorNormal()) {
				if (o instanceof Triangulo) {
					n++;
					out.format("El triángulo número %d:\t%s;\t%s;\t%s y su perímetro es %-10.4f\n",
							n, ((Triangulo)o).getV1().toString(), ((Triangulo)o).getV2().toString(), ((Triangulo)o).getV3().toString(),
							((Triangulo)o).perimetro());
				}
			}
		} finally {
			if (out!=null) {
				out.close();
			}
		}
	}
	
	public static void serializaVDFiguraGeometrica(VectorDinamicoFiguraGeometrica v1,String fichero) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream (new FileOutputStream (fichero));
			out.writeObject(v1);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			if (out!=null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static VectorDinamicoFiguraGeometrica deserializaVDFiguraGeomtrica(String fichero_in) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(fichero_in));
			
			return (VectorDinamicoFiguraGeometrica) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
}



