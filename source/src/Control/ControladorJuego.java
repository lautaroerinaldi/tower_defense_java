package Control;

import java.io.IOException;
import java.util.Iterator;

import Modelo.Enemigo.Enemigo;
import Modelo.Excepciones.ArchivoInvalidoException;
import Modelo.Excepciones.EnemigoEnSalidaException;
import Modelo.Excepciones.EnemigoMuertoException;
import Modelo.Excepciones.FinalJuegoException;
import Modelo.Excepciones.NivelInvalidoException;
import Modelo.Juego.Actuable;
import Modelo.Juego.Juego;
import Modelo.Juego.Posicionable;
import Modelo.Terreno.Punto;
import Modelo.Torre.Torre;
import Modelo.Torre.TorreBlanca;
import Modelo.Torre.TorreDorada;
import Modelo.Torre.TorrePlateada;
import Vista.VentanaGameOver;
import Vista.VentanaPrincipal;
import Vista.VistaJugador;
import Vista.VistaPresentacion;
import Vista.VistaTerreno;

public class ControladorJuego {

	private Juego juego;
	private ControladorBoton controlador;
	private VistaJugador vistaJugador;
	private VistaPresentacion vistaPresentacion;
	private VentanaPrincipal ventanaPrincipal;
	private ControladorMenu controladorMenu;
	private VistaTerreno vistaTerreno;
	private boolean juegoRecuperado;
	private ControladorSonido controladorSonido;
	private VentanaGameOver ventanaGameOver;


	/**
	 * Constructor
	 * @param controladorBoton	controla el boton ejecutar nivel
	 * @param controladorMenu	controla la barra de menu
	 * @param controladorSonido 
	 */
	public ControladorJuego(ControladorBoton controladorBoton, ControladorMenu controladorMenu, ControladorSonido controladorSonido) {
		this.controlador = controladorBoton;
		this.controladorMenu = controladorMenu;
		this.controladorSonido = controladorSonido;
		this.ventanaPrincipal = VentanaPrincipal.getInstancia();
		this.juegoRecuperado = false;
	}


	/**
	 * Ejecuta acción correspondiente según item del menu
	 *
	 */
	public void comenzar() {

		//espero opcion del menu
		while(true){
			if (controladorMenu.getOpcion() == OpcionMenu.NUEVO){
				this.juegoRecuperado = false;
				this.juego = Juego.getInstancia();
				break;
			}
			if (controladorMenu.getOpcion() == OpcionMenu.CARGAR){
				try{
					cargarJuego(this.controladorMenu.getRuta());
					this.juegoRecuperado = true;
					break;
				}
				catch(Exception e){
					controladorMenu.setOpcion(null);
					System.out.println(e);

				}
			}
			try {
				Thread.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nuevoJuego();
}


	/**
	 * Prepara el ambiente para comenzar el juego
	 *
	 */
	private void nuevoJuego(){
		controladorMenu.descactivarOpciones();
		this.controladorSonido.comenzar();
		vistaPresentacion.cambiar();
		ventanaPrincipal.agregarVistaJugador(vistaJugador);
		vistaJugador.setJugador(this.juego.getJugador());
		vistaJugador.update();
		ventanaPrincipal.agregarVistaTerreno(this.vistaTerreno);
		vistaTerreno.setNivel(this.juego.getNivel());
		vistaTerreno.update();
		ventanaPrincipal.mostrar();
		try{
			ejecutarJuego();
		}
		catch (FinalJuegoException e){
			vistaJugador.update();
			gameOver();
		}
	}


	/**
	 * Función que ejecuta el ciclo del juego
	 *
	 */
	public void ejecutarJuego(){
		while (juego.getNivel() <= Juego.getCantidadNiveles()){
			if(!this.juegoRecuperado)
				juego.cargarNivel();
			this.juegoRecuperado = false;
			this.vistaTerreno.setNivel(this.juego.getNivel());
			
			//espero a que aprete el boton ejecutar nivel
			while(true){
				if (controlador.getComenzarNivel())
					break;
				try {
					Thread.sleep(4);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				vistaJugador.update();
				vistaTerreno.update();
			}
			
			controlador.nivelComenzado();
			ejecutarNivel();
			juego.cerrarNivel();
			juego.incrementarNivel();
			controlador.nivelTerminado();
		}
		this.juego.gameOver();
	}

	/**
	 * Función que ejecuta un determinado nivel del juego.
	 *
	 * @throws NivelInvalidoException	si no pudo cargar de XML
	 */
	public void ejecutarNivel(){

		while (!juego.getListaActuables().isEmpty()) {
			//Cargo las torres para que actuen
			juego.cargarTorres();

			//Inicia movimiento
			Iterator iterador = juego.getListaActuables().iterator();
        	while (iterador.hasNext()){
				Actuable actuable = (Actuable)iterador.next();
				try {
					if (actuable.getClass().getSuperclass() == Torre.class){
						this.vistaTerreno.setBala(((Torre)actuable).getBala());
					}
					actuable.actuar();
					vistaJugador.update();
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				catch (EnemigoEnSalidaException e) {
					juego.pasoEnemigo((Enemigo)actuable);
				}
				catch (EnemigoMuertoException e) {
					juego.murioEnemigo(e.getEnemigoMuerto());
				}
			}
        	vistaTerreno.update();

           	//Elimino enemigos muertos
        	juego.eliminarActuables();
    		
		}

	}

	/**
	 * Funcion que se ejecuta cuando finaliza en juego
	 *
	 */
	
	public void gameOver(){
		this.controladorSonido.finalizar();
		this.controladorMenu.activarOpciones();
		this.ventanaGameOver.mostrar();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * Guarda el estado del juego en un archivo XML elegido por el usuario
	 * @param ruta	nombre del archivo
	 */
	public void guardarJuego(String ruta){
		if(ruta != null){
			int posicion = ruta.lastIndexOf(".");
			String extension = ruta.substring(posicion + 1, ruta.length()).toLowerCase();
			if(!extension.equals("xml"))
				this.ventanaPrincipal.mostrarMensaje("Archivo de juego inválido. Se esperaba un XML", "Fiuba defence 09 - Error");
			else{
				try{
					this.juego.salvar("utils/save/" + ruta);
				}
				catch(IOException e){
					this.ventanaPrincipal.mostrarMensaje("Se ha producido un inconveniente en la grabación del juego. Por favor intente guardar nuevamente", "Fiuba defence 09 - Error");
				}
				this.ventanaPrincipal.mostrarMensajeExito("Se ha grabado con éxito el estado del juego", "Fiuba defence 09");
			}
		}
	}

	/**
	 * Recupera el estado del juego
	 * @param ruta
	 * @throws IOException
	 */
	
	public void cargarJuego(String ruta) throws IOException{
		if(ruta != null){
			try{
				this.juego = Juego.recuperar("utils/save/" + ruta);
				this.juegoRecuperado = true;
			}
			catch(IOException e){
				this.ventanaPrincipal.mostrarMensaje("Se ha producido un inconveniente en la recuperación del juego. Por favor intente recuperar nuveamente", "Fiuba defence 09 - Error");
				throw e;
			}
		}
		else
			throw new ArchivoInvalidoException();
	}

	public void setVistaPresentacion(VistaPresentacion vistaPresentacion) {
		this.vistaPresentacion = vistaPresentacion;
	}

	public void setVistaTerreno(VistaTerreno panel) {
		this.vistaTerreno = panel;
	}


	public void setVentanaGameOver(VentanaGameOver ventanaGameOver) {
		this.ventanaGameOver = ventanaGameOver;
	}

	public void setVistaJugador(VistaJugador vistaJugador) {
		this.vistaJugador = vistaJugador;
	}
	
}
