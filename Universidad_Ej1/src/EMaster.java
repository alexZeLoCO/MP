
/**
 * Clase que modela los estudiantes de máster
 * @author puente
 *
 */
public class EMaster extends EPostGrado
{
	private static final double MINTAX = 2000; // constante compartida por todos los objetos de la clase
	private static final double MAXTAX = 4000; // constante compartida por todos los objetos de la clase

	/**
	 * Constructor de EMaster por componentes, recibe los valores iniciales tanto para los atributos propios como los heredados
	 * Se verifica que las tasas estén dentro de los límites antes de enviarlas al constructor de la superclase
	 * @param nombre - nombre del estudiante
	 * @param dni - su dni
	 * @param tasas - sus tasas
	 * @param beca - su beca
	 * @param tituloPostgrado - su titulo de postgrado
	 */
	public EMaster(String nombre, String dni, double tasas, double beca, String tituloPostgrado) {
		super(nombre, dni, Math.min(Math.max(tasas, MINTAX), MAXTAX), beca, tituloPostgrado);
	}
	
	@Override
	public String toString() {
		return String.format("Estudiante de master:\n\t%s.\n", super.toString());
	}
}
