
public class Portero extends Jugador implements Defensivo {
	private int goles_parados;
	private int goles_encajados;
	
	public Portero (String nombre, int dorsal, int minutos, int goles_encajados, int goles_parados) {
		super (nombre, dorsal, minutos);
		this.setGolesParados(goles_parados);
		this.setGolesEncajados(goles_encajados);
		this.setValoracion();
	}
	
	public void setGolesParados (int goles) {
		this.goles_parados = goles;
	}
	
	public void setGolesEncajados (int goles) {
		this.goles_encajados = goles;
	}
	
	public int getGolesEncajados () {
		return this.goles_encajados;
	}
	
	public int getGolesParados () {
		return this.goles_parados;
	}
	
	@Override
	public void setValoracion () {
		if (this.getGolesEncajados()/(this.getMinutosJugados()*100)>=1) {
			super.valoracion = 0;
		} else {
			super.valoracion = this.getGolesParados()/this.getGolesEncajados();
		}
	}
	
	//Implementation of Interface Defensivo//
	public int getRecuperaciones () {
		return this.getGolesParados();
	}
	
	@Override
	public String toString () {
		return String.format("%s\n\tPortero:\n\tGoles parados:%d\tGoles encajados:%d",
				super.toString(),this.getGolesParados(),this.getGolesEncajados());
	}	
}
