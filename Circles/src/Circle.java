
public class Circle {

	private double radius;
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(obj.getClass()!=this.getClass())
			return false;
		return ((Circle)obj).radius == this.radius;
	}
	
	public Circle(double r)
	{
		radius = r;
	}
	
	public Circle(Circle c)
	{
		radius = c.getRadius();
	}
	
	public void setRadius(double r)
	{
		radius = r;
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	public double perimeter()
	{
		return 2 * Math.PI * getRadius();
	}
	
	public double area()
	{
		return Math.PI * Math.pow(getRadius(), 2);
	}
}
