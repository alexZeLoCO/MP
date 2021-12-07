
/**
 * Clase abstracta que modela a todo estudiante de postgrado
 * @author puente
 *
 */
public abstract class EPostGrado extends Estudiante {
	private double beca;
	private String tituloPostgrado;

	/**
	 * Constructor de EPostGrado por componentes, recibe los valores iniciales tanto para los atributos propios como los heredados
	 * @param nombre - nombre del estudiante
	 * @param dni - dni del estudiante
	 * @param tasas - tasas del estudiante
	 * @param beca - beca del estudiante
	 * @param tituloPostgrado - titulación que cursa
	 */
	public EPostGrado(String nombre, String dni, double tasas, double beca, String tituloPostgrado) {
		super(nombre, dni, tasas);
		setBeca(beca);
		setTituloPostgrado(tituloPostgrado);
	}

/**
 * Observador de la beca
 * @return la beca
 */
	public double getBeca() {
		return beca;
	}

	/**
	 * Modificador de la beca
	 * @param beca - el nuevo valor para la beca
	 */
	public void setBeca(double beca) {
		this.beca = beca;
	}

	/** 
	 * Observador del titulo de postgrado
	 * @return el titulo que estudia
	 */
	public String getTituloPostgrado() {
		return tituloPostgrado;
	}

	/** 
	 * Modificador del título de postgrado
	 * @param tituloPostgrado - nuevo título que está estudiando
	 */
	public void setTituloPostgrado(String tituloPostgrado) {
		this.tituloPostgrado = tituloPostgrado;
	}

	@Override
	public double salario() {
		return getTasas()-getBeca();
	}
	
	@Override
	public String toString() {
		return String.format("%s\n\tTitulo Postgrado: %s\tBeca: %f.", super.toString(),getTituloPostgrado(),getBeca());
	}
}
