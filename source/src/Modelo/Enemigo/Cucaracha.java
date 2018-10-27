package Modelo.Enemigo;

import java.util.Iterator;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Modelo.Terreno.Casillero;
import Modelo.Terreno.CasilleroCamino;
import Modelo.Terreno.Punto;

public class Cucaracha extends Caminador{

	private static int resistencia= 4 * factor;
	private static int velocidadHabitual= 5;
	
	public Cucaracha(){
		super(resistencia);
		TurnosRestantesParaActuar= getVelocidadMaxima() - velocidadHabitual;
	}
	
	public int getResistencia(){
		return resistencia;
	}
	
	public int getVelocidad() {
		return velocidadHabitual;
	}

	public static Cucaracha recuperar(Element elementoCucaracha) {
		 Iterator it = elementoCucaracha.elementIterator();
		 Cucaracha cucaracha = new Cucaracha();
		 
		 while (it.hasNext()){
			 Element elemento = (Element)it.next();
	            if (elemento.getName() == "Posicion"){
	            	cucaracha.setPosicion(Cucaracha.recuperarPosicion(elemento));
	            }
		 }
	       
		 int turnosRestantesParaActuar = Integer.valueOf(elementoCucaracha.attributeValue("TurnosRestantesParaActuar"));
		 int vidaRestante              = Integer.valueOf(elementoCucaracha.attributeValue("VidaRestante"));
			
		 cucaracha.setTurnosRestantesParaActuar(turnosRestantesParaActuar);
		 cucaracha.setVidaRestante(vidaRestante);
		 return cucaracha;

	}

	private static Punto recuperarPosicion(Element elementoPunto) {
		int posicionX = 0;
		int posicionY = 0;
		
		posicionX = Integer.valueOf(elementoPunto.attributeValue("PosicionX"));
		posicionY = Integer.valueOf(elementoPunto.attributeValue("PosicionY"));
		 
		return  new Punto (posicionX, posicionY);
		
	}


	public Element guardar() {

		Element elementoCucaracha    = DocumentHelper.createElement("Cucaracha");
		Element elementoPosicion     = DocumentHelper.createElement("Posicion");
		
		elementoPosicion.addAttribute("PosicionX", String.valueOf(this.getPosicion().getX()));
		elementoPosicion.addAttribute("PosicionY", String.valueOf(this.getPosicion().getY()));
		
		elementoCucaracha.addAttribute("TurnosRestantesParaActuar", String.valueOf(this.getTurnosRestantesParaActuar()));
		elementoCucaracha.addAttribute("VidaRestante", String.valueOf(this.getVidaRestante()));		
		
		elementoCucaracha.add(elementoPosicion);
		
		return elementoCucaracha;

	}

}
