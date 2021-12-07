
public class Point {

	private int x;
	private int y;
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(obj.getClass()!=this.getClass())
			return false;
		Point p = ((Point)obj);
		return p.x == this.x && p.y == this.y;
	}
	
	public Point(int newX, int newY)
	{
		x = newX;
		y = newY;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	public void setX(int newX)
	{
		x = newX;
	}
	
	public void setY(int newY)
	{
		y = newY;
	}	
	
	public static double distance(Point a, Point b)
	{
		return Math.sqrt(
				Math.pow(a.getX()-b.getX(), 2) + Math.pow(a.getY()-b.getY(), 2));
	}
	
	@Override
	public String toString()   
	{
		return "["+getX()+","+getY()+"]";
	}
}
