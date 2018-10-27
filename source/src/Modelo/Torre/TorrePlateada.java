package Modelo.Torre;

import java.util.Iterator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Modelo.Terreno.Punto;


public class TorrePlateada extends Torre{

	private static int costo=20;
	private static int danio=2;
	private static int alcance=3;
	
	public TorrePlateada() {
		super();
	}
	
	public TorrePlateada(Punto posicion){
		super(posicion);
	}
	
	public  int getCosto(){
		return costo;
	}

	public  int getDanio(){
		return danio;
	}

	public  int getAlcance(){
		return alcance;
	}
	
	
	/**
	 * @return Element elemento que representa a la torre
	 */
	public Element guardar(){
		Element elementoTorrePlateada = DocumentHelper.createElement("TorrePlateada");
        
		if(this.getPosicion() != null){
        	Element elementoPosicion    = DocumentHelper.createElement("Posicion");
        	elementoPosicion.addAttribute("PosicionX",String.valueOf(this.getPosicion().getX()));
        	elementoPosicion.addAttribute("PosicionY",String.valueOf(this.getPosicion().getY()));
        	elementoTorrePlateada.add(elementoPosicion);
        }
        return elementoTorrePlateada;
	}

	
	/**
	 * 
	 * @param elementoTorrePlateada elemento a recuperar
	 * @return torrePlateada torre con datos validos con que se guardo
	 */	
	public static TorrePlateada recuperar(Element elementoTorrePlateada) {
		TorrePlateada torrePlateada = new TorrePlateada ();
		Iterator iterator = elementoTorrePlateada.elementIterator();
		
		while (iterator.hasNext()){
			Element elemento = (Element) iterator.next();
			
			if (elemento.getName() == "Posicion")
				torrePlateada.setPosicion(TorrePlateada.recuperarPosicion(elemento));
		}
		
		return torrePlateada;
    }

	private static Punto recuperarPosicion(Element elementoPosicion) {
		int posicionX = Integer.valueOf(elementoPosicion.attributeValue("PosicionX"));
	    int posicionY = Integer.valueOf(elementoPosicion.attributeValue("PosicionY"));
	    
	    return new Punto (posicionX, posicionY);    
	}

	
}
