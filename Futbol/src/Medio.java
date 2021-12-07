
public class Medio extends DeCampo implements Defensivo, Ofensivo {
	private int recuperaciones;
	private int goles;
	
	public Medio (String nombre, int dorsal, int minutos) {
		super (nombre, dorsal, minutos);
		this.setRecuperaciones (0);
		this.setGoles(0);
	}
	
	public void setRecuperaciones(int recuperaciones) {
		this.recuperaciones = recuperaciones;
	}

	public void setGoles (int goles) {
		this.goles = goles;
	}
	
	public void setValoracion () {
		super.valoracion = this.getPasesRealizados()/this.getMinutosJugados() + Math.pow(this.getRecuperaciones()*this.getGoles(), 1.0/4);
	}
	
	//Implementation of Interface Defensivo//
	public int getRecuperaciones () {
		return this.recuperaciones;
	}
		
	//Implementation of Interface Ofensivo//
	public int getGoles () {
		return goles;
	}
	
	@Override
	public String toString () {
		return String.format("%sMedio:\n\tRecuperaciones:%d\tGoles:%d",
				super.toString(),this.getRecuperaciones(),this.getGoles());
	}
}
