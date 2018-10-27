package Modelo.Juego;

import java.util.ArrayList;

import Modelo.Enemigo.Enemigo;

public class ListaEnemigos extends ArrayList {

	/**
	 * Constructor, llama al contructor del ancestro.
	 *
	 */
	
	public ListaEnemigos(){
		super();
	}
	
	/**
	 * Redefinición del método add para agregar sólo elementos de tipo Enemigo
	 * 
	 * @param o	objeto a agregar
	 * @return	true si pudo agregar, false en caso contrario
	 */
	
	public boolean add(Object o){
		if (o.getClass().getSuperclass().getSuperclass() == Enemigo.class){
			return super.add(o);
		}
		return false;
	}
	
	/**
	 * Redefinición del método getEnemigo para devolver un objeto de tipo Enemigo
	 * 
	 * @param indice	indice en la lista
	 * @return	un objeto de tipo Enemigo
	 */
	
	public Enemigo getEnemigo(int indice){
		return (Enemigo)super.get(indice);
	}
	
}
