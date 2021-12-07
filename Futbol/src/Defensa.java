
public class Defensa extends DeCampo implements Defensivo {
	private int recuperaciones;
	
	public Defensa (String nombre, int dorsal, int minutos) {
		super(nombre, dorsal, minutos);
		this.setRecuperaciones (0);
	}
	
	public void setRecuperaciones (int recuperaciones) {
		this.recuperaciones = recuperaciones;
	}
	
	public void setValoracion () {
		super.valoracion = this.getPasesRealizados()/this.getMinutosJugados() + Math.sqrt(this.getRecuperaciones());
	}
	
	//Implementation of Interface Defensivo//
	public int getRecuperaciones () {
		return this.recuperaciones;
	}

	@Override
	public String toString () {
		return String.format ("%s\n\tDefensa:Recuperaciones:%d",
				super.toString(),this.getRecuperaciones());
	}
}
