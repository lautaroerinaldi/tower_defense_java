package Control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Modelo.Excepciones.PosicionInvalidaException;
import Modelo.Juego.Juego;
import Modelo.Juego.Mochila;
import Modelo.Juego.Negociable;
import Modelo.Terreno.Casillero;
import Modelo.Terreno.Punto;
import Modelo.Terreno.Terreno;
import Modelo.Terreno.CasilleroTerreno;
import Vista.VentanaPrincipal;

public class ControladorMochila extends MouseAdapter {
	private ArrayList zonas;
	private Negociable negociable;
	private ArrayList clases;

	public ControladorMochila() {
		this.zonas = new ArrayList();
		this.negociable = null;
		this.clases= new ArrayList();
	}

	public void agregarZona(Zona zona, String nombreCanonicoDelNegociable){
		zonas.add(zona);
		clases.add(nombreCanonicoDelNegociable);
	}

	public void mousePressed(MouseEvent evt){
		for (int i=0; i< zonas.size(); i++)
			if (((Zona)(zonas.get(i))).contenido(evt.getX(), evt.getY()))
				 this.negociable = Juego.getInstancia().getJugador().getObjeto((String)clases.get(i));
	}

	public void mouseReleased(MouseEvent evt){
		if (negociable!=null){
			double tempX = evt.getX()/30;
			double tempY = evt.getY()/30;

			int nuevaPosicionX = (int)tempX;
			int nuevaPosicionY = (int)tempY;

			if (((nuevaPosicionX >= 0) && (nuevaPosicionX < 20)) && ((nuevaPosicionY >= 0) && (nuevaPosicionY < 20)))
				try{
					Juego.getInstancia().getJugador().agregarObjeto(negociable, new Punto(nuevaPosicionX, nuevaPosicionY));
					negociable=null;
				}
			catch (PosicionInvalidaException Pie)
			{
				VentanaPrincipal.getInstancia().mostrarMensaje("Posición Inválida", "Fiuba Defence - Error");
			}
		}
	}	
}