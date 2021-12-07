
public class CircleWithCentre{

	private Point centre;
	private Circle circle;
	
	public CircleWithCentre(int x, int y, double r)
	{
		centre = new Point(x,y);
		circle = new Circle(r);
	}
	
	public double getRadius()
	{
		return circle.getRadius();
	}
	
	public void setRadius(double r)
	{
		circle.setRadius(r);
	}
	
	public Point getCentre()
	{
		return centre;
	}
	
	public void setCentre(int x, int y)
	{
		centre.setX(x);
		centre.setY(y);
	}
	
	public void setCentre(Point p)
	{
		centre = p;
	}
	
	public double perimeter()
	{
		return 2 * Math.PI * getRadius();
	}
	
	public double area()
	{
		return Math.PI * Math.pow(getRadius(), 2);
	}
	
	public static boolean overlaps(CircleWithCentre c1, CircleWithCentre c2)
	{
		double d = Point.distance(c1.getCentre(), c2.getCentre());
		
		boolean resultado =  d < (c1.getRadius() + c2.getRadius()); 
		if(resultado)
			return resultado;	
		else
			return resultado;
				
	}
		
	@Override
	public String toString()   
	{
		return "("+getCentre()+"|"+getRadius()+")";
	}
}
