package Modelo.Torre;

import java.util.Observable;

import Modelo.Enemigo.Enemigo;
import Modelo.Excepciones.EnemigoMuertoException;
import Modelo.Juego.Posicionable;
import Modelo.Terreno.Punto;
import Vista.VistaTerreno;

public class Bala extends Observable implements Posicionable {
	private int danio;
	private Punto posicion;
	private VistaTerreno panelBuffer;


	/**
	 * Constructor que inicializa la bala con un danio
	 * @param danio Es el danio que va a causar la bala
	 */
	public Bala(int danio) {
		this.danio	  = danio;
		this.posicion = null;
		this.panelBuffer = VistaTerreno.getInstancia();
	}

	/**
	 * Causa el danio a un enemigo
	 * @param enemigo Enemigo es el enemigo a cual se la va a causar el danio
	 * @throws EnemigoMuertoException Excepcion que se lanza cuando muere un enemigo
	 */
	public void disparar(Enemigo enemigo){

		while ( this.posicion.compareTo(enemigo.getPosicion()) != 0 ){
			this.avanzar(enemigo.getPosicion());
			setChanged();
			notifyObservers();

			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		enemigo.causarDanio(this.danio);

		if (enemigo.getVidaRestante()< 1)
			throw new EnemigoMuertoException(enemigo);
	}


	/**
	 *
	 * @param posicion posicion de la bala en el terreno
	 */
	public void setPosicion (Punto posicion){
		this.posicion = posicion;
	}

	/**
	 * metodo que avanza la posicion de la bala en direccion del enemigo
	 * @param posicionEnemigo posicion del enemigo al cual se le dispara
	 */
	public void  avanzar(Punto posicionEnemigo){

		if ( this.posicion.getX() < posicionEnemigo.getX())
				this.posicion.setX( this.posicion.getX() + 1);
		else
			if (this.posicion.getX() > posicionEnemigo.getX())
				this.posicion.setX( this.posicion.getX() - 1);

		if (this.posicion.getY() > posicionEnemigo.getY())
				this.posicion.setY( this.posicion.getY() - 1);
		else
			if (this.posicion.getY() < posicionEnemigo.getY())
				this.posicion.setY( this.posicion.getY() + 1);

	}


	/**
	 * @return retorna la posicion actual de la bala
	 */
	public Punto getPosicion() {
		return this.posicion;
	}

	public Punto getPosicionAnt() {
		return this.posicion;
	}

}
