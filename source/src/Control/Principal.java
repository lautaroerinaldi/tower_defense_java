package Control;

import Vista.VentanaAcercaDe;
import Vista.VentanaCreditos;
import Vista.VentanaGameOver;
import Vista.VentanaPrincipal;
import Vista.VistaJugador;
import Vista.VistaMenu;
import Vista.VistaPresentacion;
import Vista.VistaTerreno;

public class Principal {

	public static void main(String[] args) {
		VentanaPrincipal ventanaPrincipal = VentanaPrincipal.getInstancia();

		//Creo controladores
		ControladorMenu controladorMenu = new ControladorMenu();
		ControladorBoton controladorBoton = new ControladorBoton();
		ControladorSonido controladorSonido = new ControladorSonido();
		ControladorJuego controladorJuego = new ControladorJuego(controladorBoton, controladorMenu, controladorSonido);
		controladorMenu.setControladorJuego(controladorJuego);
		
		ControladorTienda controladorTienda= new ControladorTienda();
		controladorTienda.agregarZona(new Zona(738, 342, 19, 18)); //zonaPegote
		controladorTienda.agregarZona(new Zona(738, 384, 19, 20)); //zonaArena
		controladorTienda.agregarZona(new Zona(670, 397, 15, 20)); //zonaTorreDorada
		controladorTienda.agregarZona(new Zona(670, 360, 15, 20)); //zonaTorrePlateada
		controladorTienda.agregarZona(new Zona(670, 321, 16, 20)); //zonaTorreBlanca
		
		
		ControladorMochila controladorMochila=new ControladorMochila();
		controladorMochila.agregarZona(new Zona(651, 197, 19, 20), "Pegote"); //zonaPegote
		controladorMochila.agregarZona(new Zona(651, 223, 19, 20), "Arena"); //zonaArena
		controladorMochila.agregarZona(new Zona(653, 170, 18, 20), "TorreDorada"); //zonaTorreDorada
		controladorMochila.agregarZona(new Zona(654, 144, 16, 20), "TorrePlateada"); //zonaTorrePlateada
		controladorMochila.agregarZona(new Zona(654, 118, 16, 21), "TorreBlanca"); //zonaTorreBlanca

		//Creo vistas
		VistaPresentacion vistaPresentacion = new VistaPresentacion(controladorTienda, controladorMochila, controladorBoton);
		VistaMenu menu = new VistaMenu(controladorMenu);
		VistaJugador vistaJugador = new VistaJugador();
		VentanaAcercaDe ventanaAcercaDe = new VentanaAcercaDe();
		VentanaCreditos ventanaCreditos = new VentanaCreditos();
		VentanaGameOver ventanaGameOver = new VentanaGameOver();
		VistaTerreno panel = VistaTerreno.getInstancia();

		//Seteo vistas a los controladores
		controladorMenu.setVistaMenu(menu, vistaPresentacion);
		controladorMenu.setVentanaAcercaDe(ventanaAcercaDe);
		controladorMenu.setVentanaCreditos(ventanaCreditos);
		controladorBoton.setVistaPresentacion(vistaPresentacion);
		controladorJuego.setVistaJugador(vistaJugador);
		controladorJuego.setVistaTerreno(panel);
		controladorJuego.setVistaPresentacion(vistaPresentacion);
		controladorJuego.setVentanaGameOver(ventanaGameOver);

		//Agrego al frame principal
		ventanaPrincipal.agregarVistaPresentacion(vistaPresentacion);
		ventanaPrincipal.setMenu(menu);
		ventanaPrincipal.mostrar();

		controladorJuego.comenzar();

	}

}
