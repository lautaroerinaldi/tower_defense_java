package Modelo.Terreno;

import Modelo.Torre.Torre;


public class CasilleroTerreno extends Casillero{
	
	private Torre torre;
	
	/**
	 * @param posicion del casillero dentro del terreno
	 */
	public CasilleroTerreno(Punto posicion) {
		super(posicion);
		this.torre=null;
	}
	
	public boolean tieneTorre(){
		if (this.torre!=null) return true;
		    else return false;
	}
	
	public void setTorre(Torre torre){
		    this.torre=torre;
	}
	
	public Torre getTorre(){
		return this.torre;
	}
	
}
