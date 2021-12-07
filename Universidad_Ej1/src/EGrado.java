
/**
 * Clase que modela a los estudiantes de Grado
 * @author puente
 *
 */
public class EGrado extends Estudiante {
	private String grado;
	private static final double MINTAX = 200; // constante compartida por todos los objetos de la clase
	private static final double MAXTAX = 1500; // constante compartida por todos los objetos de la clase

	/**
	 * Constructor de EPostGrado por componentes, recibe los valores iniciales tanto para los atributos propios como los heredados
	 * @param nombre - nombre del estudiante
	 * @param dni - dni del estudiante
	 * @param tasas - tasas del estudiante
	 * @param grado - grado que estudia
	 */
	public EGrado(String nombre, String dni, double tasas, String grado) {
		super(nombre, dni, Math.min(Math.max(tasas, MINTAX), MAXTAX));
		setGrado(grado);
	}

	/** 
	 * Observador del grado
	 * @return el grado estudiado
	 */
	public String getGrado() {
		return grado;
	}

	/**
	 * Modificador del grado
	 * @param grado - el neuvo grado que va a estudiar
	 */
	public void setGrado(String grado) {
		this.grado = grado;
	}
	@Override
	public String toString () {
		return String.format("Estudiante de grado:\n\t%s\n\tGrado:%s.\n", super.toString(),getGrado());
	}
}
