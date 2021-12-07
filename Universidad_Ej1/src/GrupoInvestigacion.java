
public class GrupoInvestigacion {
	
	private String nombre;
	private Profesor IP;
	private String tema;
	
	public GrupoInvestigacion (String nombre, String tema, 
			String nombreIP, String DNI, double salario, double cotizacion, String departamento) {

		IP = new Profesor (nombreIP,DNI,salario,cotizacion,departamento);
		setNombre(nombre);
		setTema(tema);
	}
	
	public GrupoInvestigacion (String nombre, String tema, 	Profesor IP) {
		this(nombre,tema,IP.getNombre(),IP.getDni(),IP.getSalarioAnual(),IP.getCotizacionAnual(),IP.getDepartamento());
	}
	
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}
	
	public void setTema (String tema) {
		this.tema = tema;
	}
	
	public String getNombre () {
		return this.nombre;
	}
	
	public String getTema () {
		return this.tema;
	}
	
	public Profesor getIP () {
		return this.IP;
	}
	
	public void setIP (Profesor IP) {
		this.IP = IP;
	}
}
