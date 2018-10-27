package Modelo.Terreno;

import Modelo.Enemigo.Enemigo;
import Modelo.Excepciones.EnemigoNoEstaEnTerrenoException;
import Modelo.Juego.ListaEnemigos;
import Modelo.Obstaculo.Obstaculo;

public class CasilleroCamino extends Casillero {

	private CasilleroCamino proximaPosicion;
	private ListaEnemigos enemigos;
	private Obstaculo obstaculo;
	
	/**
	 * crea un casillero de terreno sin una posicion asignada dentro del terreno
	 */
	public CasilleroCamino() {
		super();
		this.proximaPosicion=null;	
		this.enemigos = new ListaEnemigos(); 
	}
	
	/**crea un casillero de camino en una posicion y sin continuacion de camino
	 * @param posicion en donde crea el casillero 
	 */
	public CasilleroCamino(Punto posicion) {
		super(posicion);
		this.proximaPosicion=null;	
		this.enemigos = new ListaEnemigos(); 
	}
	
	/**
	 * saca un enemigo del casillero
	 * @param enemigo ennemigo a sacar del casillero
	 */
	public void quitarEnemigo(Enemigo enemigo){
		if (enemigos.contains(enemigo)){
			enemigos.remove(enemigo);
	    }else throw new EnemigoNoEstaEnTerrenoException();
	}
	
	/**
	 * @param enemigo enemigo a agregaar en el casillero
	 */
	public void agregarEnemigo(Enemigo enemigo){
		if (!enemigos.contains(enemigo)){
		    enemigos.add(enemigo);
		    enemigo.setCasillero(this);
		}
	}
	
	public void setObstaculo (Obstaculo obstaculo){
		this.obstaculo=obstaculo;
	}
	
	public void quitarObstaculo (){
		this.obstaculo=null;
	}
	
	public Obstaculo getObstaculo(){
		return this.obstaculo;	
	}
	
	public boolean tieneObstaculo (){
        if(this.obstaculo!=null)
        	return true;
        else return false;
	}
	
	/**
	 * devuelve el proximo casillero donde continua el camino
	 */
	public CasilleroCamino getProximaPosicion(){
		return this.proximaPosicion;
	}
	
	protected void setProximaPosicion(CasilleroCamino casillero){
		this.proximaPosicion=casillero;
	}
	
	/**
	 * @return ListaEnemigos lista con todos los enemigos q hay en el casillero
	 */
	public ListaEnemigos getTodosLosEnemigos(){
		return this.enemigos;
	}
	

}
