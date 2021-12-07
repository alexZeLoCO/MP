
public class Circle {

	public double radius;
	
	public Circle (double r) {
		setRadius(r);
	}
	
	public Circle (Circle c) {
		this(c.getRadius());
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setRadius (double r) {
		if (r>=0) {
			radius=r;
		} else {
			System.out.print("Radius not valid. Input positive radius: ");
		}
	}
	
	public double perimeter () {
		return 2*Math.PI*getRadius();
	}
	
	public double area () {
		return Math.PI*Math.pow(getRadius(), 2);
	}
}
