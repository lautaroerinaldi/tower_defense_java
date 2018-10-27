package Modelo.Juego;

import java.util.ArrayList;

import Modelo.Torre.Torre;

public class ListaTorres extends ArrayList {

	/**
	 * Constructor, llama al contructor del ancestro.
	 *
	 */
	
	public ListaTorres(){
		super();
	}
	
	/**
	 * Redefinición del método add para agregar sólo elementos de tipo Torre
	 * 
	 * @param o	objeto a agregar
	 * @return	true si pudo agregar, false en caso contrario
	 */
	
	public boolean add(Object o){
		if (o.getClass().getSuperclass() == Torre.class){
			return super.add(o);
		}
		return false;
	}
	
	/**
	 * Redefinición del método getTorre para devolver un objeto de tipo Torre
	 * 
	 * @param indice	indice en la lista
	 * @return	un objeto de tipo Torre
	 */
	
	public Torre getTorre(int indice){
		return (Torre)super.get(indice);
	}
	
}
