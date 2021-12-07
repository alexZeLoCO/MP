
public abstract class DeCampo extends Jugador{
	private int pases_realizados;
	
	public DeCampo (String nombre, int dorsal, int minutos) {
		super (nombre, dorsal, minutos);
		this.setPasesRealizados (0);
	}
	
	public void setPasesRealizados (int pases) {
		this.pases_realizados = pases;
	}
	
	public int getPasesRealizados () {
		return this.pases_realizados;
	}
	
	@Override
	public String toString () {
		return String.format("%s\n\tDe Campo:\nPases Realizados:%d",super.toString(),this.getPasesRealizados());
	}
}
