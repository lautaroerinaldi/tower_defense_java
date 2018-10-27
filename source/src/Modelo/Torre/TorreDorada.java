package Modelo.Torre;

import java.util.Iterator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Modelo.Terreno.Punto;


public class TorreDorada extends Torre{

	private static int costo=50;
	private static int danio=4;
	private static int alcance=4;
	
	public TorreDorada() {
		super();
	}
	
	public TorreDorada(Punto posicion){
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
		Element elementoTorreDorada = DocumentHelper.createElement("TorreDorada");
		
		if(this.getPosicion() != null){
        	Element elementoPosicion    = DocumentHelper.createElement("Posicion");
        	elementoPosicion.addAttribute("PosicionX",String.valueOf(this.getPosicion().getX()));
        	elementoPosicion.addAttribute("PosicionY",String.valueOf(this.getPosicion().getY()));
        	elementoTorreDorada.add(elementoPosicion);
        }
        return elementoTorreDorada;
	}

	
	/**
	 * 
	 * @param elementoTorreDorada elemento a recuperar
	 * @return torreDorada torre con datos validos con que se guardo
	 */	
	public static TorreDorada recuperar(Element elementoTorreDorada) {
		TorreDorada torreDorada = new TorreDorada ();
		Iterator iterator = elementoTorreDorada.elementIterator();
		
		while (iterator.hasNext()){
			Element elemento = (Element) iterator.next();
			
			if (elemento.getName() == "Posicion")
				torreDorada.setPosicion(TorreDorada.recuperarPosicion(elemento));
		}
		
		return torreDorada;
    }

	private static Punto recuperarPosicion(Element elementoPosicion) {
		int posicionX = Integer.valueOf(elementoPosicion.attributeValue("PosicionX"));
	    int posicionY = Integer.valueOf(elementoPosicion.attributeValue("PosicionY"));
	    
	    return new Punto (posicionX, posicionY);    
	}

	

}
