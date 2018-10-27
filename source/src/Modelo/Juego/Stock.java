package Modelo.Juego;

import java.util.ArrayList;
import java.util.Iterator;

import Modelo.Obstaculo.Obstaculo;
import Modelo.Torre.Torre;

public class Stock extends ArrayList {

	/**
	 * Constructor, llama al contructor del ancestro.
	 *
	 */
	
	public Stock(){
		super();
	}
	
	/**
	 * Redefinición del método add para agregar sólo elementos de tipo Negociable
	 * 
	 * @param o	objeto a agregar
	 * @return	true si pudo agregar, false en caso contrario
	 */
	
	public boolean add(Object o){
		Class clase = o.getClass().getSuperclass();
		
		if (clase == Torre.class){
			return super.add(o);
		}
		else if (clase == Obstaculo.class){
			return super.add(o);
		}
		return false;
	}

	/**
	 * Redefinición del método getNegociable para devolver un objeto de tipo Negociable
	 * 
	 * @param indice	indice en la lista
	 * @return	un objeto de tipo Negociable
	 */
	
	public Negociable getNegociable(int indice) {
		return (Negociable)super.get(indice);
	}
	
}
