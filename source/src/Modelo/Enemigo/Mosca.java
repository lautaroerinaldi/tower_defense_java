package Modelo.Enemigo;

import java.util.Iterator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Modelo.Terreno.Casillero;
import Modelo.Terreno.CasilleroCamino;
import Modelo.Terreno.Punto;

public class Mosca extends Volador {

	private static int resistencia       = 3 * factor;
	private static int velocidadHabitual = 4;
	
	public Mosca(){
		super(resistencia);
		TurnosRestantesParaActuar= getVelocidadMaxima() - velocidadHabitual;
	}
	
	public int getResistencia(){
		return resistencia;
	}
	
	public int getVelocidad() {
		return velocidadHabitual;
	}
	
	public static Mosca recuperar(Element elementoMosca) {
		 Iterator it = elementoMosca.elementIterator();
		 Mosca mosca = new Mosca();
		 
		 while (it.hasNext()){
			 Element elemento = (Element)it.next();
	            if (elemento.getName() == "Posicion"){
	            	mosca.setPosicion(Mosca.recuperarPosicion(elemento));
	            }
		 }
	       
		 int turnosRestantesParaActuar = Integer.valueOf(elementoMosca.attributeValue("TurnosRestantesParaActuar"));
		 int vidaRestante              = Integer.valueOf(elementoMosca.attributeValue("VidaRestante"));
			
		 mosca.setTurnosRestantesParaActuar(turnosRestantesParaActuar);
		 mosca.setVidaRestante(vidaRestante);
		 return mosca;

	}

	private static Punto recuperarPosicion(Element elementoPunto) {
		int posicionX = 0;
		int posicionY = 0;
		
		posicionX = Integer.valueOf(elementoPunto.attributeValue("PosicionX"));
		posicionY = Integer.valueOf(elementoPunto.attributeValue("PosicionY"));
		 
		return  new Punto (posicionX, posicionY);
		
	}


	public Element guardar() {

		Element elementoMosca    = DocumentHelper.createElement("Mosca");
		Element elementoPosicion    = DocumentHelper.createElement("Posicion");
		
		elementoPosicion.addAttribute("PosicionX", String.valueOf(this.getPosicion().getX()));
		elementoPosicion.addAttribute("PosicionY", String.valueOf(this.getPosicion().getY()));
		
		elementoMosca.addAttribute("TurnosRestantesParaActuar", String.valueOf(this.getTurnosRestantesParaActuar()));
		elementoMosca.addAttribute("VidaRestante", String.valueOf(this.getVidaRestante()));		
		
		elementoMosca.add(elementoPosicion);
		
		return elementoMosca;

	}

}
