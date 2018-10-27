package Modelo.Enemigo;

import java.util.Iterator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Modelo.Terreno.Casillero;
import Modelo.Terreno.CasilleroCamino;
import Modelo.Terreno.Punto;

public class Abeja extends Volador {

	private static int resistencia= 4 * factor;
	private static int velocidadHabitual= 4;
	
	public Abeja(){
		super(resistencia);
		TurnosRestantesParaActuar= getVelocidadMaxima() - velocidadHabitual;
	}
	
	public int getResistencia(){
		return resistencia;
	}
	
	public int getVelocidad() {
		return velocidadHabitual;
	}

	public static Abeja recuperar(Element elementoAbeja) {
		 Iterator it = elementoAbeja.elementIterator();
		 Abeja abeja = new Abeja();
		 
		 while (it.hasNext()){
			 Element elemento = (Element)it.next();
	            if (elemento.getName() == "Posicion"){
	            	abeja.setPosicion(Abeja.recuperarPosicion(elemento));
	            }
		 }
	       
		 int turnosRestantesParaActuar = Integer.valueOf(elementoAbeja.attributeValue("TurnosRestantesParaActuar"));
		 int vidaRestante              = Integer.valueOf(elementoAbeja.attributeValue("VidaRestante"));
			
		 abeja.setTurnosRestantesParaActuar(turnosRestantesParaActuar);
		 abeja.setVidaRestante(vidaRestante);
		 return abeja;

	}

	private static Punto recuperarPosicion(Element elementoPunto) {
		int posicionX = 0;
		int posicionY = 0;
		
		posicionX = Integer.valueOf(elementoPunto.attributeValue("PosicionX"));
		posicionY = Integer.valueOf(elementoPunto.attributeValue("PosicionY"));
		 
		return  new Punto (posicionX, posicionY);
		
	}
		 
	

	public Element guardar() {

		Element elementoAbeja    = DocumentHelper.createElement("Abeja");
		Element elementoPosicion     = DocumentHelper.createElement("Posicion");
		
		elementoPosicion.addAttribute("PosicionX", String.valueOf(this.getPosicion().getX()));
		elementoPosicion.addAttribute("PosicionY", String.valueOf(this.getPosicion().getY()));
		
		elementoAbeja.addAttribute("TurnosRestantesParaActuar", String.valueOf(this.getTurnosRestantesParaActuar()));
		elementoAbeja.addAttribute("VidaRestante", String.valueOf(this.getVidaRestante()));		
		
		elementoAbeja.add(elementoPosicion);
		
		return elementoAbeja;

	}

}
