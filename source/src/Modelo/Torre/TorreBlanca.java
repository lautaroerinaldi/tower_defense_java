package Modelo.Torre;

import java.util.Iterator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Modelo.Terreno.Punto;


public class TorreBlanca extends Torre{

	private static int costo=10;
	private static int danio=1;
	private static int alcance=2;
		
	public TorreBlanca() {
		super();
	}
	
	public TorreBlanca(Punto posicion){
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
		Element elementoTorreBlanca = DocumentHelper.createElement("TorreBlanca");
        
        if(this.getPosicion() != null){
        	Element elementoPosicion    = DocumentHelper.createElement("Posicion");
        	elementoPosicion.addAttribute("PosicionX",String.valueOf(this.getPosicion().getX()));
        	elementoPosicion.addAttribute("PosicionY",String.valueOf(this.getPosicion().getY()));
        	elementoTorreBlanca.add(elementoPosicion);
        }
        return elementoTorreBlanca;
	}

	
	/**
	 * 
	 * @param elementoTorreBlanca elemento a recuperar
	 * @return torreBlanca torre con datos validos con que se guardo
	 */	
	public static TorreBlanca recuperar(Element elementoTorreBlanca) {
		TorreBlanca torreBlanca = new TorreBlanca ();
		Iterator iterator = elementoTorreBlanca.elementIterator();
		
		while (iterator.hasNext()){
			Element elemento = (Element) iterator.next();
			
			if (elemento.getName() == "Posicion")
				torreBlanca.setPosicion(TorreBlanca.recuperarPosicion(elemento));
		}
		
		return torreBlanca;
    }

	private static Punto recuperarPosicion(Element elementoPosicion) {
		int posicionX = Integer.valueOf(elementoPosicion.attributeValue("PosicionX"));
	    int posicionY = Integer.valueOf(elementoPosicion.attributeValue("PosicionY"));
	    
	    return new Punto (posicionX, posicionY);    
	}


}
