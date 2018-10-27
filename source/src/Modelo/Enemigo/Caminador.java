package Modelo.Enemigo;
import Modelo.Excepciones.CasilleroSinProximoException;
import Modelo.Terreno.CasilleroCamino;

public abstract class Caminador extends Enemigo{
	
	public Caminador(int vida){
		super(vida);
	}
	
	/**
	 * Avanza a la proxima posici�n del camino, y si esta tiene obst�culo
	 * le permite causar su efecto.  
	 */
	protected void mover(){
		if (((CasilleroCamino)casillero).getProximaPosicion()!=null)
		{
			((CasilleroCamino)casillero).quitarEnemigo(this);
			this.casilleroAnt = this.casillero;
			this.casillero=((CasilleroCamino)casillero).getProximaPosicion();
			
			((CasilleroCamino)casillero).agregarEnemigo(this);
			if (((CasilleroCamino)casillero).tieneObstaculo())
				((CasilleroCamino)casillero).getObstaculo().causarEfecto(this);
		}
		else
			throw new CasilleroSinProximoException();
	}	
}

