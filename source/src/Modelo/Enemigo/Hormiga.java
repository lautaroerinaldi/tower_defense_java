package Modelo.Enemigo;

import java.util.Iterator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Modelo.Terreno.Casillero;
import Modelo.Terreno.CasilleroCamino;
import Modelo.Terreno.Punto;

public class Hormiga extends Caminador {

	private static int resistencia= 1 * factor;
	private static int velocidadHabitual= 1;
	
	public Hormiga(){
		super(resistencia);
		TurnosRestantesParaActuar= getVelocidadMaxima() - velocidadHabitual;
	}
	
	public int getResistencia(){
		return resistencia;
	}

	public int getVelocidad() {
		return velocidadHabitual;
	}

	public static Hormiga recuperar(Element elementoHormiga) {
		 Iterator it = elementoHormiga.elementIterator();
		 Hormiga hormiga = new Hormiga();
		 
		 while (it.hasNext()){
			 Element elemento = (Element)it.next();
	            if (elemento.getName() == "Posicion"){
	            	hormiga.setPosicion(Hormiga.recuperarPosicion(elemento));
	            }
		 }
	       
		 int turnosRestantesParaActuar = Integer.valueOf(elementoHormiga.attributeValue("TurnosRestantesParaActuar"));
		 int vidaRestante              = Integer.valueOf(elementoHormiga.attributeValue("VidaRestante"));
			
		 hormiga.setTurnosRestantesParaActuar(turnosRestantesParaActuar);
		 hormiga.setVidaRestante(vidaRestante);
		 return hormiga;

	}

	private static Punto recuperarPosicion(Element elementoPunto) {
		int posicionX = 0;
		int posicionY = 0;
		
		posicionX = Integer.valueOf(elementoPunto.attributeValue("PosicionX"));
		posicionY = Integer.valueOf(elementoPunto.attributeValue("PosicionY"));
		 
		return  new Punto (posicionX, posicionY);
		
	}

	public Element guardar() {

		Element elementoHormiga    = DocumentHelper.createElement("Hormiga");
		Element elementoPosicion     = DocumentHelper.createElement("Posicion");
		
		elementoPosicion.addAttribute("PosicionX", String.valueOf(this.getPosicion().getX()));
		elementoPosicion.addAttribute("PosicionY", String.valueOf(this.getPosicion().getY()));
		
		elementoHormiga.addAttribute("TurnosRestantesParaActuar", String.valueOf(this.getTurnosRestantesParaActuar()));
		elementoHormiga.addAttribute("VidaRestante", String.valueOf(this.getVidaRestante()));		
		
		elementoHormiga.add(elementoPosicion);
		
		return elementoHormiga;

	}

}
