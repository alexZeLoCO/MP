import java.io.Serializable;

public abstract class FiguraGeometrica implements Serializable {
	
	public abstract double  perimetro();
	public abstract double area();
	/**
	* Calcula el área total de un array de figuras geométricas
	* @param figs array de figuras geométricas
	* @return el área total de todas las figuras
	*/
	static public double areaTotal(FiguraGeometrica[] figs) {
		double areaTotal = 0;
		for (FiguraGeometrica f: figs)
			areaTotal += f.area();
		return areaTotal;
	}
}
