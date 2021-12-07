
public class VectorDinamicoFiguraGeometrica extends VectorDinamicoObjects{

	public VectorDinamicoFiguraGeometrica(){
		
	}
	
	public VectorDinamicoFiguraGeometrica(VectorDinamicoFiguraGeometrica vDE){
		super(vDE);
	}
	
	public VectorDinamicoFiguraGeometrica(int size){
		super(size);
	}
	
	public VectorDinamicoFiguraGeometrica(FiguraGeometrica[] v){
		super((Object[])v);
	}
	
	@Override	
	public FiguraGeometrica get(int i){
		return (FiguraGeometrica)super.get(i);
	}


}




