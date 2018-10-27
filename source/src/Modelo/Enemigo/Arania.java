package Modelo.Enemigo;

import java.util.Iterator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Modelo.Obstaculo.Arena;
import Modelo.Terreno.Casillero;
import Modelo.Terreno.CasilleroCamino;
import Modelo.Terreno.Punto;

public class Arania extends Caminador {
	
	private static int resistencia= 2 * factor;
	private static int velocidadHabitual= 3;
	
	public Arania(){
		super(resistencia);
		TurnosRestantesParaActuar= getVelocidadMaxima() - velocidadHabitual;
	}
	
	public int getResistencia(){
		return resistencia;
	}

	public int getVelocidad() {
		return velocidadHabitual;
	}

	public static Arania recuperar(Element elementoArania) {
		 Iterator it = elementoArania.elementIterator();
		 Arania arania = new Arania();
		 
		 while (it.hasNext()){
			 Element elemento = (Element)it.next();
	         if (elemento.getName() == "Posicion"){
	            	arania.setPosicion(Arania.recuperarPosicion(elemento));
	            }
		 }
	       
		 int turnosRestantesParaActuar = Integer.valueOf(elementoArania.attributeValue("TurnosRestantesParaActuar"));
		 int vidaRestante              = Integer.valueOf(elementoArania.attributeValue("VidaRestante"));
			
		 arania.setTurnosRestantesParaActuar(turnosRestantesParaActuar);
		 arania.setVidaRestante(vidaRestante);
		 return arania;

	}

	private static Punto recuperarPosicion(Element elementoPunto) {
		int posicionX = 0;
		int posicionY = 0;
		
		posicionX = Integer.valueOf(elementoPunto.attributeValue("PosicionX"));
		posicionY = Integer.valueOf(elementoPunto.attributeValue("PosicionY"));
		 
		return  new Punto (posicionX, posicionY);
		
	}

	public Element guardar() {

		Element elementoArania   = DocumentHelper.createElement("Arania");
		Element elementoPosicion = DocumentHelper.createElement("Posicion");
		
		elementoPosicion.addAttribute("PosicionX", String.valueOf(this.getPosicion().getX()));
		elementoPosicion.addAttribute("PosicionY", String.valueOf(this.getPosicion().getY()));
		
		elementoArania.addAttribute("TurnosRestantesParaActuar", String.valueOf(this.getTurnosRestantesParaActuar()));
		elementoArania.addAttribute("VidaRestante", String.valueOf(this.getVidaRestante()));		
		
		elementoArania.add(elementoPosicion);
		
		return elementoArania;

	}
	
}
