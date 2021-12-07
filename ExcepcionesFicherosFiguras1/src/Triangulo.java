public class Triangulo extends FiguraGeometrica{
	private static final long serialVersionUID = 1L;
	private Punto v1;
	private Punto v2;
	private Punto v3;
	
	public Triangulo(int x1, int y1, int x2, int y2, int x3, int y3) {
		setV1(new Punto(x1,y1));
		setV2(new Punto(x2,y2));
		setV3(new Punto(x3,y3));
	}

	public Punto getV1() {
		return v1;
	}

	public void setV1(Punto v1) {
		this.v1 = v1;
	}

	public Punto getV2() {
		return v2;
	}

	public void setV2(Punto v2) {
		this.v2 = v2;
	}

	public Punto getV3() {
		return v3;
	}

	public void setV3(Punto v3) {
		this.v3 = v3;
	}

	public double perimetro() {
		return Punto.distancia(v1, v2)+Punto.distancia(v2, v3)+Punto.distancia(v3, v1);
	}
	
	public double area() {
		// calculo con la formula de Heron
		double s = 0.5*perimetro();
		return Math.sqrt(s*(s-Punto.distancia(v1, v2))*
				(s-Punto.distancia(v2, v3))*
				(s-Punto.distancia(v3, v1)));
		
	}
	
	public String toString() {
		return String.format("Triangulo: \nv1= %s \nv2= %s\nv3= %s\n", v1, v2, v3);
	}
}
