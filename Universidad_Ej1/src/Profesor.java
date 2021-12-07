
/**
 * Clase que modela a los profesores
 * @author puente
 *
 */
public class Profesor extends Personal implements PersonalInvestigador{
	private double salarioAnual;
	private double cotizacionAnual;
	private String departamento;
	private GrupoInvestigacion grupo;

	/**
	 * Constructor por componente de la clase Profesor, recibe tanto los valores iniciales de sus propios atributos como de los heredados
	 * @param nombre - nombre del estudiante
	 * @param dni - dni del profesor
	 * @param salarioAnual - salario anual del profesor
	 * @param cotizacionAnual - cotización anual del profesor
	 * @param departamento - departamento del profesor
	 */
	public Profesor(String nombre, String dni, double salarioAnual, double cotizacionAnual, String departamento) {
		super(nombre, dni);
		this.salarioAnual = salarioAnual;
		this.cotizacionAnual = cotizacionAnual;
		this.departamento = departamento;
	}
	
	/**
	 * Observador del salario anual
	 * @return el actual salario anual
	 */
	public double getSalarioAnual() {
		return salarioAnual;
	}

	/**
	 * Modificador del salario anual
	 * @param salarioAnual - el nuevo salario anual
	 */
	public void setSalarioAnual(double salarioAnual) {
		this.salarioAnual = salarioAnual;
	}

	/**
	 * Observador de la cotización anual
	 * @return la cotización anual
	 */
	public double getCotizacionAnual() {
		return cotizacionAnual;
	}

	/** 
	 * Modificador de la cotización anual
	 * @param cotizacionAnual - la nueva cotización anual
	 */
	public void setCotizacionAnual(double cotizacionAnual) {
		this.cotizacionAnual = cotizacionAnual;
	}


	/**
	 * Observador del departamento
	 * @return el departamento actual
	 */
	public String getDepartamento() {
		return departamento;
	}

	/** 
	 * Modificador del departamento
	 * @param departamento - el nuevo departamento
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public double salario () {
		return (getCotizacionAnual()+getSalarioAnual())*(-1);
	}
	
	public void setGrupoInvestigacion (GrupoInvestigacion grupo) {
		this.grupo=grupo;
	}
	
	public GrupoInvestigacion getGrupoInvestigacion () {
		return this.grupo;
	}
	
	@Override
	public String toString() {
		String txt = "";
		if (getGrupoInvestigacion() != null) {
			if (getGrupoInvestigacion().getIP().getNombre() == getNombre()) {
				txt = "(IP)";
			}
			return String.format("Profesor:\n\tNombre: %s %s\t%s.\n\tGrupo de Investigacion: %s."
						+ "\n\tSalario Anual: %f\tCotizacion Anual: %f\tSalario: %f.\n", getNombre(),txt,getDni(),getGrupoInvestigacion().getNombre(),getSalarioAnual(),getCotizacionAnual(),salario());
		} 
		return String.format("Profesor:\n\t%s.\n\tSalario Anual: %f\tCotizacion Anual: %f\tSalario: %f.\n", super.toString(),getSalarioAnual(),getCotizacionAnual(),salario());
	}
}
