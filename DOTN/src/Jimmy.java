import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Jimmy extends Entity{
	
	private boolean close;
	private static Color color = Color.cyan;
	
	public Jimmy (int i, int j) {
		super(i,j,9);
	}
	
	public Jimmy () {
		super(randomSpawn());
	}
	
	public static Color getColor() {
		return color;
	}
	
	public boolean isClose() {
		return close;
	}
	
	public void setClose(boolean close) {
		this.close=close;
	}
	
	private static int [] randomSpawn() {

		int []pos = new int [2];
		switch (Dice.roll(14)) {			//Random hunter spawn
		//Spawns chosen in a clockwise direction starting from 0,0.
		case 1:
			pos[0]=3;
			pos[1]=10;
			break;
		case 2:
			pos[0]=3;
			pos[1]=28;
			break;
		case 3:
			pos[0]=4;
			pos[1]=7;
			break;
		case 4:
			pos[0]=4;
			pos[1]=16;
			break;
		case 5:
			pos[0]=5;
			pos[1]=28;
			break;
		case 6:
			pos[0]=9;
			pos[1]=25;
			break;
		case 7:
			pos[0]=14;
			pos[1]=6;
			break;
		case 8:
			pos[0]=16;
			pos[1]=6;
			break;
		case 9:
			pos[0]=21;
			pos[1]=20;
			break;
		case 10:
			pos[0]=21;
			pos[1]=25;
			break;
		case 11:
			pos[0]=31;
			pos[1]=5;
			break;
		case 12:
			pos[0]=31;
			pos[1]=7;
			break;
		case 13:
			pos[0]=31;
			pos[1]=15;
			break;
		case 14:
			pos[0]=31;
			pos[1]=31;
			break;
		}
		return pos;
	}
	
	@Override
	public boolean possibleMove (int d) {
		switch (d) {
		case 2:
			return (getI()+1<Game.matrix.length && getJ()<Game.matrix.length &&
					getI()+1>=0 && getJ()>=0 &&
					(Console.map[getI()+1][getJ()]!=8 ||				//If player inside
					Console.fmap[getI()+1][getJ()]==2) && 					//And it was a room
					Game.matrix[getI()+1][getJ()].getValue()!=0);
		case 4:
			return (getI()<Game.matrix.length && getJ()-1<Game.matrix.length &&
					getI()>=0 && getJ()-1>=0 &&
					(Console.map[getI()][getJ()-1]!=8 ||				//If player inside
					Console.fmap[getI()][getJ()-1]==2) && 					//And it was a room
					Game.matrix[getI()][getJ()-1].getValue()!=0);
		case 8:
			return (getI()-1<Game.matrix.length && getJ()<Game.matrix.length &&
					getI()-1>=0 && getJ()>=0 &&
					(Console.map[getI()-1][getJ()]!=8 ||				//If player inside
					Console.fmap[getI()-1][getJ()]==2) && 					//And it was a room
					Game.matrix[getI()-1][getJ()].getValue()!=0);
		case 6:
			return (getI()<Game.matrix.length && getJ()+1<Game.matrix.length &&
					getI()>=0 && getJ()+1>=0 &&
					(Console.map[getI()][getJ()+1]!=8 ||				//If player inside
					Console.fmap[getI()][getJ()+1]==2) && 					//And it was a room
					Game.matrix[getI()][getJ()+1].getValue()!=0);
		}
		return false;
	}
	
	/*
	public Color getColor() {
		return Color.cyan;
		
		if (isClose()) {
			return Color.cyan;
		} else {
			Color [] colors = {Color.gray,Color.white,Color.green,Color.black,Color.black,Color.orange,Color.orange,Color.orange,Color.red,Color.cyan};
			return colors[Console2.getFmap()[getI()][getJ()]];
		}
		
	
	}
*/
}
