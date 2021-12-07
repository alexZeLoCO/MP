import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Player extends Entity{
	
	private boolean hidden;
	private static Color color = Color.red;
	
	public Player(int i, int j) {
		super(i, j,8);
		Hidden(false);
	}
	
	public Player () {
		this(15,15);
	}
	
	public void Hidden (boolean b) {
		hidden=b;
	}
	
	public boolean isHidden () {
		return hidden;
	}
	
	public static Color getColor() {
		return color;
	}
	
}
