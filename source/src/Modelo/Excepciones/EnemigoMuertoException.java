package Modelo.Excepciones;
import Modelo.Enemigo.Enemigo;

@SuppressWarnings("serial")
public class EnemigoMuertoException extends RuntimeException {

	private Enemigo enemigoMuerto;
	
	public EnemigoMuertoException(Enemigo enemigo) {
		this.enemigoMuerto=enemigo;
	}
	
	public Enemigo getEnemigoMuerto(){
		return enemigoMuerto;
	}

}
