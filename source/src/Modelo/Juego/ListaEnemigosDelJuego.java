package Modelo.Juego;

import java.util.ArrayList;

public class ListaEnemigosDelJuego extends ArrayList {
	
	/**
	 * Constructor, llama al contructor del ancestro.
	 *
	 */
	
	public ListaEnemigosDelJuego(){
		super();
	}
	
	/**
	 * Redefinición del método add para agregar sólo elementos de tipo Class
	 * 
	 * @param o	objeto a agregar
	 * @return	true si pudo agregar, false en caso contrario
	 */
	
	public boolean add(Object o){
		if (o.getClass() == Class.class){
			return super.add(o);			
		}
		return false;
	}
	
	/**
	 * Redefinición del método getEnemigoDelJuego para devolver un objeto de tipo Class
	 * 
	 * @param indice	indice en la lista
	 * @return	un objeto de tipo Class
	 */
	
	public Class getEnemigoDelJuego(int indice){
		return (Class)super.get(indice);
	}

}
