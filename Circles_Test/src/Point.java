
public class Point {

	int x;
	int y;
	
	public Point (int x, int y) {
		setX(x);
		setY(y);
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	public static double distance (Point A,Point B) {
		return (Math.sqrt((double)Math.pow(A.getX()-B.getX(),2)+(double)Math.pow(A.getY()-B.getY(),2)));
	}
	
	
	@Override
	public String toString() {
		return String.format("Point: /n /t X coordinate: %d \n \t Y coordinate: %d.", getX(),getY());
	}
	
	
}
