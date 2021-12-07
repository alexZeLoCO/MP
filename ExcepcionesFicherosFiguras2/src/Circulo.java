
public class Circulo  extends FiguraGeometrica {

	private int radio;
	
	public Circulo(int r)
	{
		radio = r;
	}
	
	public void setRadio(int r)
	{
		radio = r;
	}
	
	public int getRadio()
	{
		return radio;
	}
	
	public double perimetro()
	{
		return 2 * Math.PI * getRadio();
	}
	
	public double area()
	{
		return Math.PI * Math.pow(getRadio(), 2);
	}
	
	public String toString() {
		return String.format("Circulo de radio %d",radio);
	}

}
