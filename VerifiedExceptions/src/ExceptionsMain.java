// Excepcion verificada (checked) 
 class MyVerifiedException extends Exception{ 
	 // <- Los tipos de excepción de usuario deben heredar de Exception o alguna de sus subclases
     // si se ajusta al tipo de excepción
	 // No deberían heredar de RuntimeException ya que estas están asociadas a errores de programación
	 // no detectados por el programador
	MyVerifiedException(String msg){
		super(msg);
	}
}

public class ExceptionsMain {

	// cláusula throws y excepciones "verificadas" (ej: MiExcepcion). Tres reglas:

	// REGLA 1.- "Debe" declararse en el método donde "existe la posibilidad" de que 
	// se genere la excepción verificada
	public static void f3() throws MyVerifiedException
	{
		// REGLA 2.- Sólo "puede" lanzarse en el método donde se declare vía 
		// throws -> throw new MiExcepcion("Mensaje de excepción...");

		// Vamos a lanzar una excepción verificada
		throw new MyVerifiedException("MiExcepcion en f3()");
	}

	public static void f1() {
		try{
			f3(); // <- método que ha declarado que puede generar una excepción MyVerifiedException
			
			/*
			 * EL ERROR SE DEBE A QUE SE LANZA UNA MYVERIFIEDEXCPTION Y EN F1 SE COGE UNA RUNTIMEEXCEPTION. SON DIFERENTES
			 */
		}
		// REGLA 3a.- Sólo "Debe" capturarse en el método que invoca directamente al método que genera la excepción
		catch(RuntimeException e){  
			// Por lo tanto, si comentamos esta sección catch se produce un error de compilación.
			// Ya que se invoca en la sección try a f3() que se ha declarado que puede generar una excepción MyVerifiedException
			System.err.println(e.getMessage());
			System.err.println("\n------------");
		}
	}

	// REGLA 3b.-  Si no queremos tratar dicha excepción en f2() 
	// podemos delegar su tratamiento en los métodos que llamen a f2(), 
	public static void f2() throws MyVerifiedException 	//<-- para ello incluiremos una cláusula throws en su declaración
	{
			f3(); // <- al declarar que podemos generar una excepcion MyVerifiedException no es obligatorio un try/catch/finally
	}
	
	// REGLA 31 + 3b .- Las dos cosas a la vez
	public static void f0() throws MyVerifiedException
	{
		try{
			f3(); // <- método que ha declarado que puede generar una excepcién MyVerifiedException
		}
		// REGLA 3a.- Sólo "Debe" capturarse en el mótodo que invoca directamente al mótodo que genera la excepción
		catch(RuntimeException e){ 
			// Por lo tanto, si comentamos esta sección catch se produce un error de compilación.
			// Ya que se invoca en la sección try a f3() que se ha declarado que puede generar una excepción MyVerifiedException
			System.err.println(e.getMessage());
			throw e;
		}
	}

	
	// Con throws podemos declarar (a título informativo) excepciones no verificadas
	// que pueda lanzar el método
	public static void g1() throws RuntimeException
	{
		throw new RuntimeException("Algo pasó");
	}
	

	public static void main(String[] args) {
		
		// invocamos a un método que gestiona correctamente las excepciones verificadas declaradas
		f1();
		
		// se produce un error al no utilizar un bloque try/catch/finally para este método que ha declarado 
		// que puede generar excepciones verificadas
		 f2();

		
		// invocar a un método que declara que puede lanzar excepciones no verificadas
		// no obliga a que haya que capturarlas y tratarlas
		g1(); 
	}

}

