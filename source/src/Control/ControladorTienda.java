package Control;
import Modelo.Excepciones.PosicionInvalidaException;
import Modelo.Excepciones.SinDineroException;
import Modelo.Juego.Negociable;
import Modelo.Juego.Tienda;
import Modelo.Juego.Juego;
import Modelo.Terreno.Casillero;
import Modelo.Terreno.CasilleroCamino;
import Modelo.Terreno.CasilleroTerreno;
import Modelo.Terreno.Punto;
import Modelo.Terreno.Terreno;
import Vista.VentanaPrincipal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ControladorTienda extends MouseAdapter {
	private ArrayList zonas;

	public ControladorTienda(){
		zonas=new ArrayList();
	}

	public void agregarZona(Zona zona){
		zonas.add(zona);
	}

	public void mouseClicked(MouseEvent evt){
		Negociable negociable=null;
		for (int i=0; i < zonas.size(); i++){
			if (((Zona)(zonas.get(i))).contenido(evt.getX(), evt.getY())){
				try{
					negociable= Tienda.getInstancia().vender(i,Juego.getInstancia().getJugador());
					Juego.getInstancia().getJugador().agregarObjetoAMochila(negociable);
				}
				catch(SinDineroException e){
					VentanaPrincipal.getInstancia().mostrarMensaje("No hay dinero Suficiente", "Fiuba Defence - ERROR");
				}
			}
		}
		
		double tempX = evt.getX()/30;
		double tempY = evt.getY()/30;

		int nuevaPosicionX = (int)tempX;
		int nuevaPosicionY = (int)tempY;

		if (((nuevaPosicionX >= 0) && (nuevaPosicionX < 20)) && ((nuevaPosicionY >= 0) && (nuevaPosicionY < 20))){
			Casillero casillero=Terreno.getInstancia().getCasillero(new Punto(nuevaPosicionX, nuevaPosicionY));
			if (casillero.getClass()==CasilleroTerreno.class){
				if (((CasilleroTerreno)casillero).tieneTorre())
					negociable= ((CasilleroTerreno)casillero).getTorre();
			}
			else
				if (casillero.getClass()==CasilleroCamino.class){
					if (((CasilleroCamino)casillero).tieneObstaculo())
						negociable=((CasilleroCamino)casillero).getObstaculo();
				}
			if (negociable!=null){
				Juego.getInstancia().getJugador().sacarObjeto(negociable);
				Tienda.getInstancia().comprar(negociable, Juego.getInstancia().getJugador());
			}
		}
		
	}
}