import java.awt.*;
import javax.swing.*;

public class Node extends JComponent{

	private int value=0;
	final private static int side = 100;
	
	public Node (int n) {
		setValue(n);
		setPreferredSize(new Dimension (side,side));
	}
	
	public Node () {
		setPreferredSize(new Dimension (side,side));
	}
	
	public void setValue(int n) {
		try {
			boolean b = (n<0 || n>5 || n!=8 || n!=9);
		} catch (Exception b) {
			System.out.print("Error: Unrecognised value.");
		} 
		value=n;
		repaint();
	}
	
	public int getValue () {
		return this.value;
	}
	
	public int getSide () {
		return this.side;
	}
	
	@Override
	public String toString () {
		return String.format("%s",getValue());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		Color [] colors = {Color.gray,Color.white,Color.green,Color.black,Color.black,Color.orange,Color.orange,Color.orange,Color.red,Color.cyan};

		g.setColor(colors[getValue()]);
		g.fillRoundRect(0, 0, getSide(), getSide(),0,0);
			
		
		
	}
}
