
public class Hero extends Character {
	
	private String player;
	
	public Hero (String itsName, int movement, int attack, int defence, int body) {
		super(itsName, movement, attack, defence, body);
	}
	
	public void setPlayerName (String name) {
		this.player = name;
	}
	
	public String getPlayerName () {
		return player;
	}
	
	@Override
	public int defend(int impacts) {
		// TODO Auto-generated method stub
		return 0;
	}
}
