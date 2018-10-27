package Modelo.Terreno;

import Modelo.Enemigo.Enemigo;
import Modelo.Excepciones.EnemigoEnSalidaException;


public class CasilleroPuerta extends CasilleroCamino {
    TipoDePuerta tipo;
    
    /**
     * crea una puerta en la posicion dada y del tipo pasado
     * @param posicion posicion dentro del terreno
     * @param tipo tipo de puerta
     */
	public CasilleroPuerta(Punto posicion,TipoDePuerta tipo) {
		super();
		this.tipo=tipo;
		this.setPosicion(posicion);	
	}
	
	/**
	 * si es una puerta de salida lanza una exception para avisar que el enemigo termino el camino
	 * @trows EnemigoEnSalidaException 
	 *  (non-Javadoc)
	 * @see Modelo.Terreno.CasilleroCamino#agregarEnemigo(Modelo.Enemigo.Enemigo)
	 */
	public void agregarEnemigo(Enemigo enemigo){
		if (this.tipo==TipoDePuerta.SALIDA){
			throw new EnemigoEnSalidaException();
		}else{
			super.agregarEnemigo(enemigo);
		}
	}
	

}
