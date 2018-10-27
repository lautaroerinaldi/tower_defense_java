package Modelo.Juego;

import java.util.Collection;
import java.util.Iterator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Modelo.Obstaculo.*;
import Modelo.Terreno.*;
import Modelo.Torre.*;

public class Jugador implements Cliente, Guardable{

	private Stock listaNegociablesEnJuego;
	private Mochila mochila;
	private int vida;
	private int dinero;
	private int puntaje;
		
	/**
	 * Contructor, inicializa todos los atributos en un estado válido.
	 */
	
	public Jugador(){
		this.vida = 15;
		this.dinero = 40;
		this.puntaje = 0;
		this.listaNegociablesEnJuego = new Stock();
		this.mochila = new Mochila();
	}
		
	public int getDinero(){
		return this.dinero;
	}
	
	public void setDinero (int sumaDinero){
		this.dinero = sumaDinero;
	}
	
	public int getPuntaje(){
		return this.puntaje;
	}
	
	public void setPuntaje (int puntaje) {
		this.puntaje = puntaje;
	}
	
	public int getVida() {
		return this.vida;
	}
	
	public void setVida (int vida) {
		this.vida = vida;
	}
	
	public Stock getListaNegociablesEnJuego(){
		return this.listaNegociablesEnJuego;
	}
	
	public Mochila getListaMochila(){
		return this.mochila;
	}
					
	public void modificarDinero(int sumaDinero){
		this.dinero += sumaDinero;
	}
	
	public int restarVida(){
		this.vida--;
		return (this.vida);
	}
				
	public void sumarPuntaje(int sumaPuntaje){
		this.puntaje += sumaPuntaje;
	}
	
	public void agregarNegociable(Negociable negociable) {
		this.listaNegociablesEnJuego.add(negociable);
	}
	
	/**
	 * Retorna la cantidad de objetos que hay en la mochila del tipo pedido
	 * @param objeto	objeto pedido
	 * @return			cantidad de objetos de ese tipo
	 */
	
	public int getCantidad(String objeto){
		int cant = 0;
		Iterator it = this.mochila.iterator();
		while(it.hasNext()){
			String ruta = it.next().getClass().getCanonicalName();
			int posicion = ruta.lastIndexOf(".");
			ruta = ruta.substring(posicion + 1, ruta.length());
			if(ruta.equals(objeto))
				cant++;
		}
		return cant;
	}
	
	/**
	 * Retorna de la mochila el objeto pedido
	 * @param objeto		objeto pedido
	 * @return				el objeto de ese tipo
	 */
	
	public Negociable getObjeto(String objeto){
		Iterator it = this.mochila.iterator();
		while(it.hasNext()){
			Negociable negociable = (Negociable)it.next();
			String ruta = negociable.getClass().getCanonicalName();
			int posicion = ruta.lastIndexOf(".");
			ruta = ruta.substring(posicion + 1, ruta.length());
			if(ruta.equals(objeto))
				return negociable;
		}
		return null;
	}
	
	/**
	 * Función que agrega una torre u obstaculo al terreno
	 * 
	 * @param negociable	Torre u obstaculo a agregar
	 * @param punto			Posicion en terreno
	 */
	
	public void agregarObjeto(Negociable negociable, Punto punto){
		if (negociable.getClass().getSuperclass() == Torre.class){
			Torre torre = (Torre)negociable;
	    	torre.setTerreno();
	    	Terreno.getInstancia().agregarTorre(torre, punto);
	    	Juego.getInstancia().agregarTorre(torre);
		}
		else {
			Obstaculo obstaculo = (Obstaculo)negociable;
			Terreno.getInstancia().agregarObstaculo(obstaculo, punto);
		}
		//Lo saco de la mochila
		if(this.mochila.contains(negociable))
			this.mochila.remove(negociable);
		//Lo agrego a la lista en juego
		agregarNegociable(negociable);
	}
	
	/**
	 * Función que saca una torre u obstaculo del terreno
	 * 
	 * @param negociable	Torre u obstaculo
	 */
	
	public void sacarObjeto(Negociable negociable){
		if (negociable.getClass().getSuperclass() == Torre.class){
			Torre torre = (Torre)negociable;
			Terreno.getInstancia().sacarTorre(torre);
		    Juego.getInstancia().agregarListaActuablesAEliminar(torre);
		}
		else {
			Obstaculo obstaculo = (Obstaculo)negociable;
			Terreno.getInstancia().sacarObstaculo(obstaculo);
		}
		this.listaNegociablesEnJuego.remove(negociable);
	}
	
	/**
	 * Función que agrega un objeto a la mochila cuando se lo compra.
	 * @param negociable	objeto comprado
	 */
	
	public void agregarObjetoAMochila(Negociable negociable){
		this.mochila.add(negociable);
	}
	
	/**
	 * Función que guarda en la mochila todos los objetos en juego. 
	 * Se ejecuta al finalizar el nivel.
	 *
	 */
	
	public void guardarObjetos(){
		if (!this.listaNegociablesEnJuego.isEmpty()){
			this.mochila.addAll(this.listaNegociablesEnJuego);
			this.listaNegociablesEnJuego.clear();
		}
	}
	
	/**
	 * Función que guarda el estado del jugador en un XML.
	 */

	public Element guardar() {
		Element elemJugador = DocumentHelper.createElement("Jugador");
        elemJugador.addAttribute("Vida", String.valueOf(this.vida));
        elemJugador.addAttribute("Dinero", String.valueOf(this.dinero));
        elemJugador.addAttribute("Puntaje", String.valueOf(this.puntaje));
        
        Element elemMochila = DocumentHelper.createElement("Mochila");
		Iterator it = this.mochila.iterator();
        while(it.hasNext()){
        	Guardable objGuardable=(Guardable)it.next();
        	elemMochila.add(objGuardable.guardar());
        }
        elemJugador.add(elemMochila);
        return elemJugador;
	}
	
	/**
	 * Función que recupera el estado del jugador.
	 * @param elem	Elemento Jugador
	 * @return		Jugador en un estado válido
	 */

	public static Jugador recuperar(Element elem) {
		Jugador jugador = Juego.getInstancia().getJugador();
		jugador.setVida(Integer.parseInt(elem.attributeValue("Vida")));
		jugador.setDinero(Integer.parseInt(elem.attributeValue("Dinero")));
		jugador.setPuntaje(Integer.parseInt(elem.attributeValue("Puntaje")));
		
		Iterator i = elem.elementIterator();
		Element mochila = (Element)i.next();
		Iterator it = mochila.elementIterator();
		Element elementoMochila;
		while(it.hasNext()){
			elementoMochila = (Element)it.next();
			if (elementoMochila.getName() == "TorreBlanca")
            	jugador.agregarObjetoAMochila(TorreBlanca.recuperar(elem));
            
            if (elementoMochila.getName() == "TorrePlateada")
            	jugador.agregarObjetoAMochila(TorrePlateada.recuperar(elem));
            
            if (elementoMochila.getName() == "TorreDorada")
            	jugador.agregarObjetoAMochila(TorreDorada.recuperar(elem));
            
            if (elementoMochila.getName() == "Arena")
            	jugador.agregarObjetoAMochila(Arena.recuperar(elem));
            
            if (elementoMochila.getName() == "Pegote")
            	jugador.agregarObjetoAMochila(Pegote.recuperar(elem));	
		}
		return jugador;	
	}
		
}
