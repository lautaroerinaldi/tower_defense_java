package Modelo.Obstaculo;

import org.dom4j.Element;

import Modelo.Enemigo.Enemigo;
import Modelo.Juego.Guardable;
import Modelo.Juego.Negociable;
import Modelo.Juego.Posicionable;
import Modelo.Terreno.Punto;

public  abstract class Obstaculo implements Negociable, Guardable, Posicionable {

	public abstract  int getCosto();
	
	
	/**
	 * Metodo que causa efecto a un enemigo retardando el momento en el que debiera actuar  
	 *
	 */
	public abstract void causarEfecto(Enemigo enemigo);
	
	public abstract Element guardar();
	
	public abstract Punto getPosicion();
	
	public abstract void setPosicion(Punto posicion);
}
