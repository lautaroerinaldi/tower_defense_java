package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Control.ControladorMenu;

public class VentanaPrincipal extends JFrame{

	private static final int ancho = 800;
	private static final int alto = 655;
	private JPanel p;
	private VistaMenu menu;
	private ControladorMenu controladorMenu;
	private static VentanaPrincipal instancia = null;

	private VentanaPrincipal(){
		setLayout(new BorderLayout());
		this.setResizable(false);
		p = new JPanel();
		p.setLayout(null);
		p.setBackground(Color.WHITE);
		getContentPane().add(p);
		setSize(this.ancho, this.alto);
		setTitle("Fiuba defence 09");
		this.setVisible(false);
		//terminar el programa cuando cierra
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static VentanaPrincipal getInstancia(){
		if (instancia == null)
			instancia = new VentanaPrincipal();
		return instancia;
	}

	public JPanel getPanel(){
		return this.p;
	}

	public VistaMenu getMenu(){
		return this.menu;
	}

	public void setMenu(VistaMenu menu) {
		this.setJMenuBar(menu);
	}

	public void mostrar(){
		this.setVisible(true);
	}

	public void setControladorMenu(ControladorMenu controladorMenu){
		this.controladorMenu = controladorMenu;
	}

	public void agregarVistaJugador(VistaJugador vistaJugador) {
		this.p.add(vistaJugador);
	}

	public void agregarVistaTerreno (VistaTerreno panel){
		this.p.add(panel);
	}
	
	public void agregarVistaPresentacion(VistaPresentacion vistaPresentacion){
		this.p.add(vistaPresentacion);
	}

	public String abrir(){
		FileDialog fd = new FileDialog(this,"Abrir...",FileDialog.LOAD);
		fd.setDirectory("save");
		fd.show();
		return fd.getFile();
	}

	public String guardar(){
		FileDialog fd = new FileDialog(this,"Guardar...",FileDialog.SAVE);
		fd.setDirectory("save");
		fd.show();
		return fd.getFile();
	}

	public void mostrarMensaje(String mensaje, String titulo) {
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarMensajeExito(String mensaje, String titulo) {
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

}
