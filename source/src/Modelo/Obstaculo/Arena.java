package Modelo.Obstaculo;

import java.util.Iterator;

import Modelo.Terreno.Punto;
import Modelo.Enemigo.Enemigo;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class Arena extends Obstaculo {

	private static int costo = 10;
	private Punto posicion = null;
	
	public Arena() {}
	
	public Arena (Punto posicion){
		this.posicion = posicion;
	}
	
	public int getCosto(){
		return costo;
	}
	
	public void setPosicion(Punto posicion){
		this.posicion = posicion;
	}
	
	public void causarEfecto (Enemigo enemigo){
		enemigo.setTurnosRestantesParaActuar(enemigo.getTurnosRestantesParaActuar()*2);
	}
	
	public Punto getPosicion(){
		return this.posicion;
	}
	
	public Element guardar(){
		Element elementoArena = DocumentHelper.createElement("Arena");
		
		if(this.getPosicion() != null){
        	Element elementoPosicion    = DocumentHelper.createElement("Posicion");
        	elementoPosicion.addAttribute("PosicionX",String.valueOf(this.getPosicion().getX()));
        	elementoPosicion.addAttribute("PosicionY",String.valueOf(this.getPosicion().getY()));
        	elementoArena.add(elementoPosicion);
        }
        return elementoArena;
	}

	public static Arena recuperar(Element elementoArena) {
	    Iterator it = elementoArena.elementIterator();
	    while(it.hasNext()){
	    	Element elementoPunto = (Element)it.next();
	    	if (elementoPunto.getName() == "Posicion"){
	    		int posicionX = Integer.valueOf(elementoPunto.attributeValue("PosicionX"));
	    		int posicionY = Integer.valueOf(elementoPunto.attributeValue("PosicionY"));
	    		return new Arena (new Punto( posicionX , posicionY ));
	    	}
	    	
	    }
	        
	    return new Arena();
    }

	public Punto getPosicionAnt() {
		return this.posicion;
	}

}
