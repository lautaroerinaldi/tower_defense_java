package Vista;
import java.awt.Image;

import javax.swing.ImageIcon;

import Modelo.Juego.Posicionable;

public class ListaImagenes {

	private static ListaImagenes instancia = null;

	private Image imagenPresentacion;
	private Image imagenPosicionable;
	private Image imagenFondo;
	private Image imagenTerreno;

	private String rutaFondo = "img/fondo.jpg";
	private String rutaPresentacion = "img/inicio.jpg";
	private String rutaPosicionable = "img/";
	private String rutaTerreno = "img/terreno";


	private ListaImagenes(){
		this.imagenPresentacion = new ImageIcon (getClass().getClassLoader().getResource(rutaPresentacion)).getImage();
		this.imagenFondo = new ImageIcon (getClass().getClassLoader().getResource(rutaFondo)).getImage();
	}

	public static ListaImagenes getInstancia(){
		if (instancia == null)
			instancia = new ListaImagenes();
		return instancia;
	}

	/**
	 * Devuelve la imagen correspondiente segun objeto recibido
	 * @param posicionable	Objeto posicionable en terreno
	 * @param perspectiva	String que indica con que perspectiva se dibuja el enemigo
	 * @return				Imagen correspondiente
	 */
	
	public synchronized Image getImagenPosicionable(Posicionable posicionable, String perspectiva){
		this.rutaPosicionable = "img/";
		String ruta = posicionable.getClass().getCanonicalName();

		int posicion = ruta.lastIndexOf(".");
		ruta = ruta.substring(posicion + 1, ruta.length());

		this.rutaPosicionable += ruta;
		this.rutaPosicionable += perspectiva;
		this.rutaPosicionable += ".png";
		
		this.imagenPosicionable = new ImageIcon (getClass().getClassLoader().getResource(this.rutaPosicionable)).getImage();

		return this.imagenPosicionable;

	}

	public Image getImagenPresentacion(){
		return this.imagenPresentacion;
	}

	public Image getImagenFondo() {
		return this.imagenFondo;
	}

	/**
	 * Devuelve la imagen del terreno correspondiente
	 * @param nivel		Nivel del juego
	 * @return			Imagen del terreno
	 */
	public Image getImagenTerreno(int nivel){
        String aux=rutaTerreno+String.valueOf(nivel)+".jpg";
        this.imagenTerreno = new ImageIcon (getClass().getClassLoader().getResource(aux)).getImage();
		return this.imagenTerreno;
	}

}
