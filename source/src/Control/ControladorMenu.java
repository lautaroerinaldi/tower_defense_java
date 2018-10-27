package Control;
import java.awt.Event;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Modelo.Juego.Juego;
import Vista.VentanaAcercaDe;
import Vista.VentanaCreditos;
import Vista.VentanaPrincipal;
import Vista.VistaMenu;
import Vista.VistaPresentacion;

public class ControladorMenu implements ActionListener {

	private VistaMenu menu;
	private ControladorJuego controladorJuego;
	private OpcionMenu status;
	private String ruta;
	private VentanaAcercaDe ventanaAcercaDe;
	private VentanaCreditos ventanaCreditos;
	private boolean activado;
	
	public ControladorMenu(){
		this.activado = false;
	}
	
	public void actionPerformed(ActionEvent ev) {
		this.activado = true;
		if(ev.getActionCommand() == VistaMenu.getOPCION_NUEVO()){
			status = OpcionMenu.NUEVO;
		}
		
		if(ev.getActionCommand() == VistaMenu.getOPCION_SALIR()){
			juegoCerrar();
		}
		
		if(ev.getActionCommand() == VistaMenu.getOPCION_ABRIR()){
			cargarJuego();
			status = OpcionMenu.CARGAR;
		}
		
		if(ev.getActionCommand() == VistaMenu.getOPCION_GUARDAR()){
			guardarJuego();			
		}
		
		if(ev.getActionCommand() == VistaMenu.getOPCION_ACERCA_DE()){
			mostrarAcercaDe();
		}
			
		
		if(ev.getActionCommand() == VistaMenu.getOPCION_CREDITOS()){
			mostrarCreditos();
		}
			
	}
		
	public void cargarJuego(){
		 this.ruta = VentanaPrincipal.getInstancia().abrir();
	}
		
	public void guardarJuego(){
		this.ruta = VentanaPrincipal.getInstancia().guardar();
		this.controladorJuego.guardarJuego(ruta);
	}
		
	public void mostrarAcercaDe(){
		this.ventanaAcercaDe.mostrar();
	}
		
	public void juegoCerrar(){
		System.exit(0);
	}
		
	public void mostrarCreditos(){
		this.menu.setEnabled(false);
		this.ventanaCreditos.mostrar();
	}

	public void setVistaMenu(VistaMenu menu, VistaPresentacion vistaPresentacion) {
		this.menu = menu;
	}

	public OpcionMenu getOpcion(){
		return this.status;
	}

	public void setControladorJuego (ControladorJuego controladorJuego){
		this.controladorJuego = controladorJuego;
	}
	
	public String getRuta(){
		return this.ruta;
	}

	public void setVentanaAcercaDe(VentanaAcercaDe ventanaAcercaDe) {
		this.ventanaAcercaDe = ventanaAcercaDe;
		
	}

	public void setVentanaCreditos(VentanaCreditos ventanaCreditos) {
		this.ventanaCreditos = ventanaCreditos;
	}
	
	public boolean getActivado(){
		return this.activado;
	}
	
	public void setActivado(boolean b){
		this.activado = b;
	}

	public void setOpcion(Object object) {
		this.status = null;
	}
	
	public void descactivarOpciones(){
		this.menu.getJuegoNuevo().setEnabled(false);
		this.menu.getJuegoCargar().setEnabled(false);
		this.menu.getJuegoGuardar().setEnabled(true);
	}
	
	public void activarOpciones(){
		this.menu.getJuegoNuevo().setEnabled(true);
		this.menu.getJuegoCargar().setEnabled(true);
		this.menu.getJuegoGuardar().setEnabled(false);		
	}
}
