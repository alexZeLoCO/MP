
public class Weapon {
	
	private String name;
	private int attack;
	
	public Weapon (String s, int n) {
		setName(s);
		setAttack(n);
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName (String s) {
		this.name=s;
	}
	
	public int getAttack () {
		return this.attack;
	}
	
	public void setAttack (int n) {
		this.attack=n;
	}
	
	@Override
	public String toString () {
		return String.format("Weapon:\n \tName: %s \tAttack: %d",	getName(),getAttack());
	}
}
