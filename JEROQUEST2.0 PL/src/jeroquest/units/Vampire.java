package jeroquest.units;

import jeroquest.boardgame.Dice;
import jeroquest.logic.Game;
import jeroquest.utils.DynamicVectorXYLocation;

import javax.swing.*;

public class Vampire extends Monster implements Leader {

	// initial values for the attributes
	protected static final int MOVEMENT = 1;
	protected static final int ATTACK = 3;
	protected static final int DEFENCE = 0;
	protected static final int BODY = 3;
	private int reserva;
	private boolean leader;

	/**
	 * Create a Vampire from its name
	 *
	 * @param name name of the Vampire
	 */
	public Vampire(String name) {
		super(name, MOVEMENT, ATTACK, DEFENCE, BODY);
		this.setReserva(0);
		this.setLeader(false);
	}

	public boolean isLeader() {
		return this.leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	public void setReserva(int reserva) {
		this.reserva=reserva;
	}

	public int getReserva () {
		return this.reserva;
	}

	@Override
	public boolean isEnemy(Character c) {
		return (c instanceof Hero  || (!(c instanceof Vampire) && c.getBody()<c.getBodyInitial()));
		//Heroes U otros personajes (monstruos) que hayan perdido vida Y no sean vampiros
	}

	@Override
	public DynamicVectorXYLocation validPositions(Game currentGame) {

		int i=0;
		while (i<currentGame.getCharacters().length && currentGame.getCharacters()[i].isAlive() &&
				!this.isEnemy(currentGame.getCharacters()[i]) && currentGame.getCharacters()[i].validPositions(currentGame)!=null) {
			i++;
		}

		/*
		for (int i=0; i<currentGame.getCharacters().length && !this.isEnemy(currentGame.getCharacters()[i]); i++) {
		}
		*/

		DynamicVectorXYLocation positions = null;
		if (i<currentGame.getCharacters().length) {
			 positions = currentGame.getCharacters()[i].validPositions(currentGame);
		}

		return positions;
	}

	@Override
	public void combat(Character c, Game currentGame) { // attacks to c and c defends itself
		int body = c.getBody();

		if (aliveLeader(currentGame)) {
			c.defend(this.attack()+1);
			if (!c.isAlive()) {
				currentGame.getBoard().removePiece(c);
			}
		} else {
			super.combat(c, currentGame);
		}

		if (c.getBody() < body && c instanceof Sabroso) {
			this.setReserva(this.getReserva()+((Sabroso)c).sangrado());
		}
	}

	/**
	 * Finds wehter Vampires leader is alive or not
	 * @param currentGame - Current game
	 * @return boolean true iff vampires leader is alive
	 */
	public boolean aliveLeader (Game currentGame) {
		for (Character c : currentGame.getCharacters()) {
			if (c instanceof Vampire && c.isAlive() && ((Vampire)c).isLeader()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int defend(int impacts) {
		int wounds = 0;

		if (this.getReserva() >= impacts) {			//La reserva cubre por completo el daño
			this.setReserva(this.getReserva()-impacts);		//Reserva - daño
			impacts=0;			//Daño restante 0
		} else {			//La reserva NO cubre por completo el daño
			impacts=impacts-this.getReserva();			//Impactos restantes
			this.setReserva(0);			//Reserva gastada por completo
		}

		// if any unblocked impact, decrement body points
		if (impacts > 0) {
			// the life of a character cannot be lower then zero
			wounds = Math.min(getBody(), impacts);
			setBody(getBody() - wounds);
			System.out.printf("The monster " + this.getName() + " cannot block %d impacts%s", impacts,
					(isAlive() ? "\n" : " and dies\n"));
		} else {
			System.out.printf("The monster %s blocks the attack using blood. Blood remaining: %d\n", this.getName(), this.getReserva());
		}

		return wounds;
	}

	/************************************************
	 * Interface Piece implementation
	 **********************************************/

	/**
	 * Generate a text representation of the character in the board (implementing an
	 * abstract method)
	 * 
	 * @return the text representation of the object in the board
	 */
	public char toChar() {
		return 'V';
	}

	/************************************************
	 * Interface GraphicElement implementation
	 **********************************************/

	// Icon of a Vampire
	private static Icon icon = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/triki.gif"));

	public Icon getImage() {
		return icon;
	}

}
