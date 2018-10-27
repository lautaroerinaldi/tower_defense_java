package Modelo.Enemigo;
import Modelo.Excepciones.CasilleroSinProximoException;
import Modelo.Terreno.CasilleroCamino;

public abstract class Volador extends Enemigo {
	
	public Volador(int vida){
		super(vida);
	}
	/**
	 * Avanza a la proxima posici�n del camino, no tiene en cuenta si hay obst�culos
	 */
	protected void mover(){
		if (((CasilleroCamino)casillero).getProximaPosicion()!=null)
		{
			((CasilleroCamino)casillero).quitarEnemigo(this);
			this.casilleroAnt = this.casillero;
			this.casillero=((CasilleroCamino)casillero).getProximaPosicion();
			((CasilleroCamino)casillero).agregarEnemigo(this);
		}
		else
			throw new CasilleroSinProximoException();
	}
}
