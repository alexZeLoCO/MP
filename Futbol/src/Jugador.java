
public abstract class Jugador {

	private String nombre;
	private int dorsal;
	private int minutos_jugados;
	protected double valoracion;			//SOBRA
	
	public Jugador (String nombre, int dorsal, int minutos_jugados) {
		this.setNombre(nombre);
		this.setDorsal(dorsal);
		this.setMinutosJugados(minutos_jugados);
		this.setValoracion();
	}
	
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}
	
	public void setDorsal (int dorsal) {
		this.dorsal = dorsal;
	}
	
	public void setMinutosJugados (int minutos) {
		this.minutos_jugados = minutos;
	}
	
	public abstract void setValoracion ();		//PUBLIC ABSTRACT DOUBLE VALORACION ();
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getDorsal () {
		return this.dorsal;
	}
	
	public int getMinutosJugados () {
		return this.minutos_jugados;
	}
	
	public double getValoracion () {
		return this.valoracion;
	}
	
	@Override
	public String toString () {
		return String.format ("Jugador:\n\tNombre:%s\tDorsal:%d\tMinutos jugados:%d\tValoración:%f",
				this.getNombre(),this.getDorsal(),this.getMinutosJugados(),this.getValoracion());
	}
	
}
