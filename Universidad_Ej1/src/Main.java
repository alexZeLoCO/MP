
public class Main {

	public static void main(String[] args) {
		ProgramaDoctorado pd = new ProgramaDoctorado("Informatica", 3000);

		Personal vU[] = {
				new EGrado("Nombre EGrado 03", "DNI 03", 1000, "Grado 03"),
				new EGrado("Nombre EGrado 04", "DNI 04", 1100, "Grado 04"),
				new EMaster("Nombre EMaster 05", "DNI 05", 1200, 1500, "Ingeniería Informática"),
				new EMaster("Nombre EMaster 06", "DNI 06", 1300, 1600,  "Big Data"),
				new EDoctorado("Nombre EDoctorado 07", "DNI 07", 1400, 1700,  "Ingeniería Informática", pd),
				new EDoctorado("Nombre EDoctorado 08", "DNI 08", 1500, 1800,  "Ingeniería Informática", pd),	
				new Profesor("Nombre Profesor 01", "DNI 01", 30000, 6000, "Dpto 01"),
				new Profesor("Nombre Profesor 02", "DNI 02", 40000, 10000, "Dpto 02"),
		};

		System.out.println("array:");
		for(Personal p: vU)
			System.out.println(p);

		double totalSaldo = saldoTotal(vU);
		System.out.println("El saldo total es: " + totalSaldo );

		int totalMaster = estudiantesMaster(vU, "Ingeniería Informática");
		System.out.println("El total de alumnos del Máster de Ingniería Informática son: " + totalMaster);

		double totalIngresos = cobroTotal(vU);
		System.out.println("El total de ingresos es: " + totalIngresos);

		ordenaPorCobro(vU);
		System.out.println("array ordenado:");
		for(Personal p: vU)
			System.out.println(p);


		GrupoInvestigacion g = new GrupoInvestigacion ("IA","IA",((Profesor)vU[0]));
		GrupoInvestigacion g2 = new GrupoInvestigacion ("IB","IB",((Profesor)vU[1]));
		((EDoctorado)vU[4]).setGrupoInvestigacion(g);
		((Profesor)vU[1]).setGrupoInvestigacion(g);
		((EDoctorado)vU[5]).setGrupoInvestigacion(g2);
		System.out.println("Miembros IA");
		componentes(vU,"IA");
	}

	/*
	saldoTotal(vU). Calcula y retorna el saldo total de la institución. Este saldo es la
	suma de los saldos individuales. El saldo individual de un estudiante de grado o máster es
	positivo y su importe corresponde a las tasas que paga. El saldo de un profesor es
	negativo y corresponde a la suma del salario y la cotización. En el caso de los estudiantes
	de doctorado el saldo es la diferencia entre las tasas que pagan y la beca que reciben.
	 */
	private static double saldoTotal(Personal[] vU) {
		double total = 0;
		for (Personal i : vU) {
			total=total+i.salario();
		}
		return total;
	}

	/*
	estudiantesMaster(vU,”Ingenieria Informática”). Calcula y retorna el
	número de estudiantes que cursan o han cursado el master con nombre “Ingeniería
	Informática”.
	 */
	private static int estudiantesMaster(Personal[] vU, String master) {
		int total = 0;
		for (Personal i : vU) {
			if (i instanceof EPostGrado && ((EPostGrado) i).getTituloPostgrado()==master) {
				total++;
			}
		}
		return total;
	}

	/*
	cobroTotal(vU). Es la suma de lo que cobran los miembros de la institución en
	salarios. Actualmente son los profesores pero en un futuro se quiere incluir a otros tipos
	de empleados aún no contemplados. El método no debería verse modificado ante la
	aparición de nuevos tipos de asalariados.
	 */
	private static double cobroTotal(Personal[] vU) {
		double total = 0;
		for (Personal i : vU) {
			if (!(i instanceof Estudiante)) {
				total=total+i.salario();
			}
		}
		return total;
	}

	/*
	ordenaPorCobro(vU). Ordena el vector de modo que queden primero los que
	cobran y luego los que no cobran.
	 */
	private static void ordenaPorCobro(Personal[] vU) {
		Personal memory;
		for (int i=0;i<vU.length;i++) {
			for (int j=0;j<vU.length;j++) {
				if (vU[i].salario() < 0 && vU[j].salario() > 0) {
					memory = vU[i];
					vU[i]=vU[j];
					vU[j]=memory;
				}
			}
		}
	}

	private static void componentes (Personal vU [], String nombreGrupo) {
		for (Personal i : vU) {
			if (i instanceof PersonalInvestigador && ((PersonalInvestigador) i).getGrupoInvestigacion() != null &&
					((PersonalInvestigador) i).getGrupoInvestigacion().getNombre() == nombreGrupo) {
				System.out.println((PersonalInvestigador)i);
			} 
		}
	}
}


