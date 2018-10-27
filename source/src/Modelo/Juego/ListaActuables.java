package Modelo.Juego;
import java.util.ArrayList;
import java.util.Iterator;

import Modelo.Enemigo.Enemigo;
import Modelo.Torre.Torre;

public class ListaActuables extends ArrayList{
		
	/**
	 * Constructor, llama al contructor del ancestro.
	 *
	 */
	
	public ListaActuables(){
		super();
	}
	
	/**
	 * Redefinici�n del m�todo add para agregar s�lo elementos de tipo Actuable
	 * 
	 * @param o	objeto a agregar
	 * @return	true si pudo agregar, false en caso contrario
	 */
	
	public boolean add(Object o){
		Class clase = o.getClass().getSuperclass();
		
		if (clase == Torre.class){
			return super.add(o);
		}
		else if (clase.getSuperclass() == Enemigo.class){
			return super.add(o);
		}
		return false;
	}
	
	/**
	 * Redefinici�n del m�todo isEmpty para chequear si quedan enemigos
	 * @return	true si no quedan enemigos, false en caso contrario
	 */
	
	public boolean isEmpty(){
		Iterator it = iterator();
				
		while (it.hasNext()){
			if (it.next().getClass().getSuperclass().getSuperclass() == Enemigo.class)
				return false;
		}
		return true;
	}
	
	/**
	 * Redefinici�n del m�todo getActuable para devolver un objeto de tipo Actuable
	 * 
	 * @param indice	indice en la lista
	 * @return	un objeto de tipo Actuable
	 */
	
	public Actuable getActuable(int indice){
		return (Actuable)super.get(indice);
	}
}
