package Modelo.Obstaculo;

import java.util.Iterator;

import Modelo.Terreno.Punto;
import Modelo.Enemigo.Enemigo;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class Pegote extends Obstaculo{

	private static int detenerEnemigosPorCiclos;
	
	private Punto posicion = null;
	
	private static int costo = 20;
	
	public Pegote(Punto posicion){
		this.posicion = posicion;
	}
	
	public Pegote() {}
	
	public int getCosto(){
		return costo;
	}
	
	public static Pegote recuperar(Element elementoPegote) {
		Iterator it = elementoPegote.elementIterator();
	    while(it.hasNext()){
	    	Element elementoPunto = (Element)it.next();
	    	if (elementoPunto.getName() == "Posicion"){
	    		int posicionX = Integer.valueOf(elementoPunto.attributeValue("PosicionX"));
	    		int posicionY = Integer.valueOf(elementoPunto.attributeValue("PosicionY"));
	    		return new Pegote (new Punto( posicionX , posicionY ));
	    	}
	    	
	    }
	        
	    return new Pegote();
	    }
	
	
	public Element guardar(){
		Element elementoPegote = DocumentHelper.createElement("Pegote");
		
		if(this.getPosicion() != null){
        	Element elementoPosicion    = DocumentHelper.createElement("Posicion");
        	elementoPosicion.addAttribute("PosicionX",String.valueOf(this.getPosicion().getX()));
        	elementoPosicion.addAttribute("PosicionY",String.valueOf(this.getPosicion().getY()));
        	elementoPegote.add(elementoPosicion);
        }
		return elementoPegote;
	}

	public void causarEfecto(Enemigo enemigo){
		detenerEnemigosPorCiclos=enemigo.getVelocidadMaxima()*3;
		enemigo.setTurnosRestantesParaActuar(detenerEnemigosPorCiclos);
	}	

	public Punto getPosicion(){
		return this.posicion;
	}
	
	public void setPosicion( Punto posicion){
		this.posicion = posicion;
	}
	
	public Punto getPosicionAnt() {
		return this.posicion;
	}

}
