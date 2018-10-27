package Modelo.Torre;
import java.util.Iterator;

import org.dom4j.Element;

import Modelo.Enemigo.Enemigo;
import Modelo.Excepciones.EnemigoMuertoException;
import Modelo.Juego.Actuable;
import Modelo.Juego.Guardable;
import Modelo.Juego.ListaEnemigos;
import Modelo.Juego.Negociable;
import Modelo.Juego.Posicionable;
import Modelo.Terreno.Casillero;
import Modelo.Terreno.Punto;
import Modelo.Terreno.Terreno;

public abstract class Torre implements Negociable, Actuable, Posicionable, Guardable {

	protected Terreno terreno;
	private Punto posicion;
	private Bala bala;


	/**
	 * constructor que deja en un estado valido una torre, dejando la posicion inicial de
	 * la torre sin un lugar en el terreno
	 *
	 */

	public Torre(){
		posicion= null;
	}

	/**
	 * constructor que deja en un estado valido una torre, seteando la posicion inicial de
	 * la torre en el terreno
	 *
	 */

	public Torre(Punto posicion){
		this.posicion= posicion;
	}



	/**
	 * @return Retorna el costo de una torre
	 */

	public abstract int getCosto();


	/**
	 * @return Retorna el danio de una torre
	 */

	public abstract int getDanio();


	/**
	 * @return Retorna el alcance de una torre
	 */

	public abstract int getAlcance();


	/**
	 * Setea la posicion de la torre en el terreno
	 * @param posicion Nueva posicion de la torre en el terreno
	 */

	public void setPosicion(Punto posicion){
		this.posicion= posicion;
	}


	/**
	 * @return Retorna la posicion de la torre dentro del terreno
	 */

	public Punto getPosicion(){
		return this.posicion;
	}
	
	public Punto getPosicionAnt(){
		return this.posicion;
	}

	/**
	 *
	 * @param enemigo enemigo a disparar
	 */

	private void dispararEnemigoEnRadio(Enemigo enemigo){

		if (enemigo!= null){
		 	this.bala.setPosicion(new Punto(this.posicion.getX(), this.posicion.getY()));
			try{
				this.bala.disparar(enemigo);
			}catch (EnemigoMuertoException enemigoMuerto){
				this.terreno.sacarEnemigo(enemigo);
				throw enemigoMuerto;
			}
		}
	}


	/**
	 *
	 * @param listaEnemigosEnRadio lista de enemigos en alcance
	 * @return enemigo con menor distancia a la salida
	 */

	private Enemigo elegirEnemigoADisparar(ListaEnemigos listaEnemigosEnRadio){
			Enemigo enemigo;
			Casillero casilleroSalida = this.terreno.getCasilleroSalida();
			
			int indice=0, contador = 0;
			float distanciaMinima  = 20; // es lo mas lejos que puede estar una torre de un enemigo

			if(!listaEnemigosEnRadio.isEmpty()){

				Iterator it = listaEnemigosEnRadio.iterator();

				enemigo = listaEnemigosEnRadio.getEnemigo(0);
			
				while (it.hasNext()){
					enemigo= (Enemigo) it.next();
					
					float numeroAuxiliar  = casilleroSalida.getPosicion().getX()- enemigo.getPosicion().getX();
					float numeroAuxiliar2 = casilleroSalida.getPosicion().getY()- enemigo.getPosicion().getY();
					numeroAuxiliar  *= numeroAuxiliar;
					numeroAuxiliar2 *= numeroAuxiliar2;

					numeroAuxiliar += numeroAuxiliar2;
					numeroAuxiliar  = (float)Math.sqrt(numeroAuxiliar);

					if (numeroAuxiliar < distanciaMinima){
						distanciaMinima = numeroAuxiliar;
						indice = contador;
					}

					contador++;
				}

				return listaEnemigosEnRadio.getEnemigo(indice);
			}

		return null;
	}


	/**
	 * Setea un nuevo terreno a la torre
	 */
	public void setTerreno() {
		this.terreno = Terreno.getInstancia();
	}


	/**
	 * Metodo que dispara a un enemigo que este en su radio
	 * @throws EnemigoMuertoException Excepcion que se lanza cuando muere un enemigo
	 */
	public void actuar() {
		ListaEnemigos listaEnemigosEnRadio= this.terreno.listarEnemigosEnRadio(this);
		Enemigo enemigo = this.elegirEnemigoADisparar(listaEnemigosEnRadio);
		this.dispararEnemigoEnRadio(enemigo);
	}

	/**
	 * @return Element elemento que representa a la torre
	 */
	public abstract Element guardar();

	public Bala getBala (){
		this.bala = new Bala( this.getDanio());
		return this.bala;
	}
}
