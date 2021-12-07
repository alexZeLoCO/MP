public class CirculoConCentro extends Circulo{
	private static final long serialVersionUID = 1L;
	private Punto centro;
	
	public CirculoConCentro(int x, int y, int r)
	{
		super(r);
		centro = new Punto(x,y);
	}
	
	public Punto getCentro()
	{
		return centro;
	}
	
	void setCentro(int x, int y)
	{
		centro.setX(x);
		centro.setY(y);
	}
	
	void setCentro(Punto p)
	{
		centro = p;
	}
	
	public static boolean solapa(CirculoConCentro c1, CirculoConCentro c2)
	{
		double d = Punto.distancia(c1.getCentro(), c2.getCentro());
		
		boolean resultado =  d < (c1.getRadio() + c2.getRadio()); 
		if(resultado)
			return resultado;	
		else
			return resultado;
				
	}
	
	public void absorbe(CirculoConCentro c)
	{
		double sup1 = this.area();
		double sup2 = c.area();

		// Calculamos la proporcion que supone la superficie del circulo actual frente a la suma de superficies de ambos circulos
		double ratio = sup1 / (sup1 + sup2);
		
		// Desplazamos la X del circulo actual hacia la X del circulo c en proporcion a la ratio anterior
		int newX = (int) (this.getCentro().getX() + 
					(c.getCentro().getX() - this.getCentro().getX()) * ratio);

		// Desplazamos la Y del circulo actual hacia la Y del circulo c en proporcion a la ratio anterior
		int newY = (int) (this.getCentro().getY() + 
				(c.getCentro().getY() - this.getCentro().getY()) * ratio);

		// El nuevo radio supondra un area del circulo resultado de la suma de areas de los circulos originales
		// es decir sqrt(circulo_actual.radio^2 + c.radio^2)
		int newRadio = (int) Math.round(Math.sqrt(Math.pow(this.getRadio(), 2)+Math.pow(c.getRadio(),2)));

		// actualizamos propiedades
		this.setCentro(newX, newY);
		this.setRadio(newRadio);
	
		// minimizamos el circulo absorvido
		c.setCentro(new Punto(0,0));
		c.setRadio(0);
		
	}
	
	//@Override
	public String toString()   
	{
		return "("+getCentro()+"|"+getRadio()+")";
	}
}
