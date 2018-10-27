package Vista;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Control.ControladorMenu;

public class VistaMenu extends JMenuBar{

	private static final String OPCION_NUEVO = "Nuevo";
	private static final String OPCION_ABRIR = "Cargar juego";
	private static final String OPCION_GUARDAR = "Guardar juego";
	private static final String OPCION_SALIR = "Salir";
	private static final String OPCION_ACERCA_DE = "Acerca De...";
	private static final String OPCION_CREDITOS = "Creditos";
	private static final String OPCION_JUEGO = "Juego";
	
	private JMenuItem juegoNuevo;
	private JMenuItem juegoGuardar; 
	private JMenuItem juegoSalir; 
	private JMenuItem juegoCargar;
	private JMenuItem juegoAcercaDe;
	private JMenuItem juegoCreditos;
	private JMenu menuJuego;
	private JMenu menuAyuda;
	private ControladorMenu controladorMenu;
	
	public VistaMenu(ControladorMenu controladorMenu){
		this.controladorMenu = controladorMenu;
		crearMenu();
	}
	
	private void crearMenu(){
		
		menuJuego = new JMenu ("Juego");
		menuJuego.setActionCommand(OPCION_JUEGO);
		menuJuego.addActionListener(this.controladorMenu);
		menuAyuda = new JMenu ("Ayuda");
		menuAyuda.addActionListener(this.controladorMenu);
		
		juegoNuevo    = new JMenuItem(OPCION_NUEVO);
		juegoNuevo.setActionCommand(OPCION_NUEVO);
		juegoNuevo.addActionListener(this.controladorMenu);
		
		juegoCargar   = new JMenuItem(OPCION_ABRIR);
		juegoCargar.setActionCommand(OPCION_ABRIR);
		juegoCargar.addActionListener(this.controladorMenu);
		
		juegoGuardar  = new JMenuItem(OPCION_GUARDAR);
		juegoGuardar.setActionCommand(OPCION_GUARDAR);
		juegoGuardar.addActionListener(this.controladorMenu);
		juegoGuardar.setEnabled(false);
		
		juegoSalir    = new JMenuItem(OPCION_SALIR);
		juegoSalir.setActionCommand(OPCION_SALIR);
		juegoSalir.addActionListener(this.controladorMenu);
		
		juegoAcercaDe = new JMenuItem(OPCION_ACERCA_DE);
		juegoAcercaDe.setActionCommand(OPCION_ACERCA_DE);
		juegoAcercaDe.addActionListener(this.controladorMenu);
		
		juegoCreditos = new JMenuItem (OPCION_CREDITOS);
		juegoCreditos.setActionCommand(OPCION_CREDITOS);
		juegoCreditos.addActionListener(this.controladorMenu);
		
		menuJuego.add(juegoNuevo);
		menuJuego.add(juegoCargar);
		menuJuego.add(juegoGuardar);
		menuJuego.add(new JMenuItem("__________"));
		menuJuego.add(juegoSalir);
		
		menuAyuda.add(juegoCreditos);
		menuAyuda.add(juegoAcercaDe);
		
		this.add(menuJuego);
		this.add(menuAyuda);
		
		
		this.setFocusable(true);
		this.requestFocus();
		this.setVisible(true);
		
	}

	public static String getOPCION_ABRIR() {
		return OPCION_ABRIR;
	}

	public static String getOPCION_ACERCA_DE() {
		return OPCION_ACERCA_DE;
	}

	public static String getOPCION_CREDITOS() {
		return OPCION_CREDITOS;
	}

	public static String getOPCION_GUARDAR() {
		return OPCION_GUARDAR;
	}

	public static String getOPCION_NUEVO() {
		return OPCION_NUEVO;
	}

	public static String getOPCION_SALIR() {
		return OPCION_SALIR;
	}
	
	public static String getOPCION_JUEGO() {
		return OPCION_JUEGO;
	}

	public JMenuItem getJuegoAcercaDe() {
		return juegoAcercaDe;
	}

	public JMenuItem getJuegoCargar() {
		return juegoCargar;
	}

	public JMenuItem getJuegoCreditos() {
		return juegoCreditos;
	}

	public JMenuItem getJuegoGuardar() {
		return juegoGuardar;
	}

	public JMenuItem getJuegoNuevo() {
		return juegoNuevo;
	}

	public JMenuItem getJuegoSalir() {
		return juegoSalir;
	}
	
}
