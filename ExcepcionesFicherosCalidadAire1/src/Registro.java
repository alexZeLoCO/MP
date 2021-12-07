import java.io.Serializable;

public class Registro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String zona;
	private String fecha;
	private int contaminante;
	
	public Registro (String zona, String fecha, int contaminante) {
		this.setZona(zona);
		this.setFecha(fecha);
		this.setContaminante(contaminante);
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getContaminante() {
		return this.contaminante;
	}

	public void setContaminante(int contaminante) {
		this.contaminante = contaminante;
	}
	
	
}
