
public class Delantero extends DeCampo implements Ofensivo {
	private int goles;
	
	public Delantero (String nombre, int dorsal, int minutos) {
		super (nombre, dorsal, minutos);
		this.setGoles(0);
	}
	
	public void setGoles (int goles) {
		this.goles = goles;
	}
	
	public void setValoracion () {
		super.valoracion = this.getPasesRealizados()/this.getMinutosJugados() + Math.sqrt(this.getGoles());
	}
	
	//Implementation of Interface Ofensivo//
	public int getGoles () {
		return goles;
	}
	
	@Override
	public String toString() {
		return String.format("%s\n\tDelantero:\n\tGoles:%d",
				super.toString(),this.getGoles());
	}
}
