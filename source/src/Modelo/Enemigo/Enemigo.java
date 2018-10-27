package Modelo.Enemigo;
import org.dom4j.Element;

import Modelo.Juego.Actuable;
import Modelo.Juego.Guardable;
import Modelo.Juego.Posicionable;
import Modelo.Terreno.Casillero;
import Modelo.Terreno.Punto;
import Modelo.Terreno.Terreno;

public abstract class Enemigo implements Actuable, Posicionable, Guardable{

	protected int vidaRestante;
	protected Casillero casillero;
	protected Casillero casilleroAnt;
	protected int TurnosRestantesParaActuar;
	protected Punto posicion;
	private static int velocidadMaxima= 7;
	protected static int factor= 15;
	
	/**
	 *  Inicializa la vida del enemigo con la resistencia que este tiene
	 * @param vida
	 */
	
	public Enemigo(int vida){
		vidaRestante= vida;
	}
	
	public abstract int getVelocidad();
	
	/**
	 * Devuelve la vida que le queda al enemigo, si es cero, significa que el enemigo
	 * está muerto
	 * @return
	 */
	public int getVidaRestante(){
		return this.vidaRestante;
	}
	
	public abstract int getResistencia();
	
	public abstract Element guardar();
	
	
	/**
	 * Disminuye la vida del enemigo 
	 * @param danio
	 */
	public void causarDanio(int danio){
		this.vidaRestante-= danio;
	}
	/**
	 * Se requiere haber colocado al enemigo en un casillero que tenga siguiente
	 */ 
	protected abstract void mover();
	
	/**
	 * Si le falta un turno restante para actuar, se mueve a la próxima posición
	 */
	public void actuar(){
		if (this.TurnosRestantesParaActuar == 1)
		{
			this.TurnosRestantesParaActuar= velocidadMaxima- this.getVelocidad();
			this.mover();
		}
		else
			this.TurnosRestantesParaActuar--;
	}
	
	public int getTurnosRestantesParaActuar(){
		return this.TurnosRestantesParaActuar;
	}
	
	public void setTurnosRestantesParaActuar(int turnosRestantes){
		this.TurnosRestantesParaActuar= turnosRestantes;
	}
	
	public void setCasillero(Casillero casillero){
		this.casillero= casillero;
		if(this.casilleroAnt == null)
			this.casilleroAnt = casillero;
	}
	
	public void setVidaRestante(int vida){
		this.vidaRestante = vida;
	}
	
	public Casillero getCasillero(){
		return this.casillero;
	}
	
	public int getVelocidadMaxima(){
		return velocidadMaxima;
	}
	
	public Punto getPosicion(){
		return this.casillero.getPosicion();
	}
	
	public Punto getPosicionRecuperada(){
		return this.posicion;
	}
	
	public void setPosicion(Punto posicion) {
		this.casillero = Terreno.getInstancia().getCasillero(posicion); 
		this.posicion  = posicion;
	}
	
	public Punto getPosicionAnt(){
		return this.casilleroAnt.getPosicion();
	}

	public static int getFactor() {
		return factor;
	}

	public static void setVelocidadMaxima(int velocidadMaxima) {
		Enemigo.velocidadMaxima = velocidadMaxima;
	}	

}
