
/**
 * Clase que modela el Programa de doctorado
 * @author puente
 *
 */
public class ProgramaDoctorado {

	private String nombre;
	private double beca; 
	
	/**
	 * Constructor por componentes de la clase ProgramaDoctorado
	 * @param nombre - el nombre del programa
	 * @param beca - cuantía de la beca recibida por sus estudiantes
	 */
	public ProgramaDoctorado(String nombre, double beca) {
		super();
		this.beca = beca;
		this.nombre = nombre;

	}
	public void setBeca(double beca) {
		this.beca = beca;
	}
	public double getBeca() {
		return beca;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
