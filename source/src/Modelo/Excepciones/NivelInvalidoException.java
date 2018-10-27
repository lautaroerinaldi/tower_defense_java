package Modelo.Excepciones;

@SuppressWarnings("serial")
public class NivelInvalidoException extends RuntimeException {

	private int nivel;
	
	public NivelInvalidoException(int nivel) {
		this.nivel = nivel;
	}
	
	public String toString () {
		return "Error al cargar nivel " + nivel;
	}

}
