package Modelo.Enemigo;

import java.util.Iterator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Modelo.Terreno.Casillero;
import Modelo.Terreno.CasilleroCamino;
import Modelo.Terreno.Punto;

public class Pulga extends Caminador {

	private static int resistencia= 3 * factor;
	private static int velocidadHabitual= 5;
	
	public Pulga(){
		super(resistencia);
		TurnosRestantesParaActuar= getVelocidadMaxima() - velocidadHabitual;
	}
	
	public int getResistencia(){
		return resistencia;
	}

	public int getVelocidad() {
		return velocidadHabitual;
	}

	public static Pulga recuperar(Element elementoPulga) {
		 Iterator it = elementoPulga.elementIterator();
		 Pulga pulga = new Pulga();
		 
		 while (it.hasNext()){
			 Element elemento = (Element)it.next();
	            if (elemento.getName() == "Posicion"){
	            	pulga.setPosicion(Pulga.recuperarPosicion(elemento));
	            }
		 }
	       
		 int turnosRestantesParaActuar = Integer.valueOf(elementoPulga.attributeValue("TurnosRestantesParaActuar"));
		 int vidaRestante              = Integer.valueOf(elementoPulga.attributeValue("VidaRestante"));
			
		 pulga.setTurnosRestantesParaActuar(turnosRestantesParaActuar);
		 pulga.setVidaRestante(vidaRestante);
		 return pulga;

	}

	private static Punto recuperarPosicion(Element elementoPunto) {
		int posicionX = 0;
		int posicionY = 0;
		
		posicionX = Integer.valueOf(elementoPunto.attributeValue("PosicionX"));
		posicionY = Integer.valueOf(elementoPunto.attributeValue("PosicionY"));
		 
		return  new Punto (posicionX, posicionY);
		
	}

	public Element guardar() {

		Element elementoPulga        = DocumentHelper.createElement("Pulga");
		Element elementoPosicion     = DocumentHelper.createElement("Posicion");
		
		elementoPosicion.addAttribute("PosicionX", String.valueOf(this.getPosicion().getX()));
		elementoPosicion.addAttribute("PosicionY", String.valueOf(this.getPosicion().getY()));
		
		elementoPulga.addAttribute("TurnosRestantesParaActuar", String.valueOf(this.getTurnosRestantesParaActuar()));
		elementoPulga.addAttribute("VidaRestante", String.valueOf(this.getVidaRestante()));		
		
		elementoPulga.add(elementoPosicion);
		
		return elementoPulga;

	}
	

}
