package Modelo.Juego;

import java.util.ArrayList;

import Modelo.Obstaculo.Obstaculo;

public class ListaObstaculos extends ArrayList {

	/**
	 * Constructor, llama al contructor del ancestro.
	 *
	 */
	
	public ListaObstaculos(){
		super();
	}
	
	/**
	 * Redefinici�n del m�todo add para agregar s�lo elementos de tipo Obstaculo.
	 * 
	 * @param o	objeto a agregar
	 * @return	true si pudo agregar, false en caso contrario
	 */
	
	public boolean add(Object o){
		if (o.getClass().getSuperclass() == Obstaculo.class){
			return super.add(o);
		}
		return false;
	}
	
	/**
	 * Redefinici�n del m�todo getObstaculo para devolver un objeto de tipo Obstaculo.
	 * 
	 * @param indice	indice en la lista
	 * @return	un objeto de tipo Enemigo
	 */
	
	public Obstaculo getObstaculo(int indice){
		return (Obstaculo)super.get(indice);
	}
	
}
