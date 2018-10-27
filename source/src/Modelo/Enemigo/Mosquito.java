package Modelo.Enemigo;

import java.util.Iterator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Modelo.Terreno.Casillero;
import Modelo.Terreno.CasilleroCamino;
import Modelo.Terreno.Punto;

public class Mosquito extends Volador {

	private static int resistencia= 3 * factor;
	private static int velocidadHabitual= 2;
	
	public Mosquito(){
		super(resistencia);
		TurnosRestantesParaActuar= getVelocidadMaxima() - velocidadHabitual;
	}
	
	public int getResistencia(){
		return resistencia;
	}
	
	public int getVelocidad() {
		return velocidadHabitual;
	}
	
	public static Mosquito recuperar(Element elementoMosquito) {
		 Iterator it = elementoMosquito.elementIterator();
		 Mosquito mosquito = new Mosquito();
		 
		 while (it.hasNext()){
			 Element elemento = (Element)it.next();
	            if (elemento.getName() == "Posicion"){
	            	mosquito.setPosicion(Mosquito.recuperarPosicion(elemento));
	            }
		 }
	       
		 int turnosRestantesParaActuar = Integer.valueOf(elementoMosquito.attributeValue("TurnosRestantesParaActuar"));
		 int vidaRestante              = Integer.valueOf(elementoMosquito.attributeValue("VidaRestante"));
			
		 mosquito.setTurnosRestantesParaActuar(turnosRestantesParaActuar);
		 mosquito.setVidaRestante(vidaRestante);
		 return mosquito;

	}

	private static Punto recuperarPosicion(Element elementoPunto) {
		int posicionX = 0;
		int posicionY = 0;
		
		posicionX = Integer.valueOf(elementoPunto.attributeValue("PosicionX"));
		posicionY = Integer.valueOf(elementoPunto.attributeValue("PosicionY"));
		 
		return  new Punto (posicionX, posicionY);
		
	}

	public Element guardar() {

		Element elementoMosquito     = DocumentHelper.createElement("Mosquito");
		Element elementoPosicion     = DocumentHelper.createElement("Posicion");
		
		elementoPosicion.addAttribute("PosicionX", String.valueOf(this.getPosicion().getX()));
		elementoPosicion.addAttribute("PosicionY", String.valueOf(this.getPosicion().getY()));
		
		elementoMosquito.addAttribute("TurnosRestantesParaActuar", String.valueOf(this.getTurnosRestantesParaActuar()));
		elementoMosquito.addAttribute("VidaRestante", String.valueOf(this.getVidaRestante()));		
		
		elementoMosquito.add(elementoPosicion);
		
		return elementoMosquito;

	}

}
