import java.awt.Color;

public class Entity extends Node{
	protected int i;		//Y
	protected int j;		//X
	private int Type;
	
	public Entity (int x, int y, int Type) {
		setI(x);
		setJ(y);
		setType(Type);
	}
	
	private void setType(int Type) {
		this.Type=Type;
	}
	
	public  int getType() {
		return Type;
	}
	
	public Entity (int [] pos) {
		this(pos[0], pos[1], 0);
	}
	
	public void setJ (int x) {
		j=x;
	}
	
	public void setI (int y) {
		i=y;
	}
	
	public void setCoordinates(int x, int y) {
		setJ(x);
		setI(y);
	}
	
	public int getJ() {
		return j;
	}
	
	public int getI() {
		return i;
	}
	
	public int [] getCoordinates () {
		int [] coord = {getI(),getJ()};
		return coord;
	}
	
	public static boolean close (Entity a, Entity b) {
		return Math.sqrt(18)>Math.sqrt(Math.pow(a.getI()-b.getI(),2)+Math.pow(a.getJ()-b.getJ(), 2));
	}
		
	public boolean possibleMove (int d) {
		switch (d) {
		case 2:
			return (getI()+1<Game.matrix.length && getJ()<Console2.getMap().length &&
					getI()+1>=0 && getJ()>=0 &&
					Console2.getMap()[getI()+1][getJ()]!=0);
		case 4:
			return (getI()<Game.matrix.length && getJ()-1<Console2.getMap().length &&
					getI()>=0 && getJ()-1>=0 &&
					Console2.getMap()[getI()][getJ()-1]!=0);
		case 8:
			return (getI()-1<Game.matrix.length && getJ()<Console2.getMap().length &&
					getI()-1>=0 && getJ()>=0 &&
					Console2.getMap()[getI()-1][getJ()]!=0);
		case 6:
			return (getI()<Game.matrix.length && getJ()+1<Console2.getMap().length &&
					getI()>=0 && getJ()+1>=0 &&
					Console2.getMap()[getI()][getJ()+1]!=0);
		}
		return false;
	}

	/*
	public static Color getColor() {
		if (getType()==8) {
			return Player.getColor();
		} else {
			return Jimmy.getColor();
		}
	}
	*/
	
}
