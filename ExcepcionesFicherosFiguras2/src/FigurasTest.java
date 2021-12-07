import java.io.*;
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
			
			System.out.println("Serialización:");
			
			serializaVDFiguraGeometrica(v2,"Serializado.ser");
			VectorDinamicoFiguraGeometrica w= deserializaVDFiguraGeomtrica("Serializado.ser");
			for(int i=0;i<w.length();i++) {
				System.out.println(w.get(i).toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	public static VectorDinamicoFiguraGeometrica leeFichero(String nombreFichero) throws Exception {
		Scanner in = null;
		VectorDinamicoFiguraGeometrica v = new VectorDinamicoFiguraGeometrica ();
		try {
			in = new Scanner (new FileInputStream (nombreFichero));
			in.useDelimiter(",|\r\n");
			while (in.hasNext()) {
				try {
					int n = in.nextInt();
					switch (n) {
					case (0):
						v.add(new Circulo (in.nextInt()));
						break;
					case (1):
						v.add(new CirculoConCentro (in.nextInt(),in.nextInt(),in.nextInt()));
						break;
					case (2):
						v.add(new Triangulo (in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt()));
						break;				
					}
					if (n<0 || n>2) {
						in.nextLine();
						throw new MyException ();
					}
				} catch (MyException e) {
					System.err.println(e.getMessage());
				}
			}
		} catch (IOException e) {
			throw new Exception ("Lanzada desde leeFichero()\t"+e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return v;
	}
	
	
	public static void escribeFichero(String nombreFichero, VectorDinamicoFiguraGeometrica v) throws IOException {
		Formatter out = null;
		try {
			out = new Formatter (new FileOutputStream(nombreFichero));
			int j = 0;
			for (Object o : v.vectorNormal()) {
				if (o instanceof Triangulo) {
					j++;
					out.format("El triángulo número %d:\t%s;\t%s;\t%s y su perímetro es %-10.4f\n",j,((Triangulo)o).getV1().toString(),
							((Triangulo)o).getV2().toString(),((Triangulo)o).getV3().toString(),((Triangulo)o).perimetro());
				}
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null) {
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
			e.printStackTrace();
		} finally {
			if (out != null) {
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
			in = new ObjectInputStream (new FileInputStream (fichero_in));
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



