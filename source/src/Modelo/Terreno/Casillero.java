package Modelo.Terreno;


public abstract class Casillero {

	private Punto posicion;
	
	public Casillero(){
		this.posicion=null;
	}
	
	public Casillero(Punto posicion){
		this.posicion=posicion;
	}
		
	public void setPosicion(Punto posicion){
		this.posicion=posicion;
	}
	
	public Punto getPosicion(){
		return this.posicion;
	}
}


