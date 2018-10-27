package Vista;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Control.ControladorBoton;
import Control.ControladorMochila;
import Control.ControladorTienda;

public class VistaPresentacion extends JPanel{

	private static final int WIDTH = 800;     //ancho de la imagen
	private static final int HEIGHT = 600; //alto de la imagen

	private ImageIcon imagen1;
	private ImageIcon imagen2;
	private JLabel etiqueta;
	private ControladorTienda controladorTienda;
	private ControladorMochila controladorMochila;
	private JButton btnStart;
	private ControladorBoton controladorBoton;
	
	public VistaPresentacion(ControladorTienda controladorTienda, ControladorMochila controladorMochila, ControladorBoton controladorBoton){
		super();
		this.setLayout(null);
		this.setBounds(0, 0, WIDTH, HEIGHT);
		imagen1 = new ImageIcon(getClass().getClassLoader().getResource("img/inicio.JPG"));
		imagen2 = new ImageIcon(getClass().getClassLoader().getResource("img/fondo.jpg"));
		etiqueta=new JLabel(imagen1);
		this.dibujarFondo(etiqueta);
		this.controladorTienda=controladorTienda;
		this.controladorMochila=controladorMochila;
		this.controladorBoton = controladorBoton;
	}

	
	/**
	 * Cambia la imagen de la presentacion por la que esta de fondo.
	 * Agrega el boton y los controladores correspondiente.
	 *
	 */
	public void cambiar() {
		etiqueta.setVisible(false);
		etiqueta.setIcon(imagen2);
		etiqueta.addMouseListener(controladorTienda);
		etiqueta.addMouseListener(controladorMochila);
		
		
		btnStart = new JButton("Ejecutar Nivel");
		this.add(btnStart).setBounds(640,510,120,30);
		btnStart.addActionListener(this.controladorBoton);
		this.setFocusable(true);
		
		this.dibujarFondo(etiqueta);
	}
	
	public void dibujarFondo(JLabel etiqueta ){
		etiqueta.setBounds(0, 0, 800, 600);
		etiqueta.setVisible(true);
		this.add(etiqueta);
	}
	
	public void desactivarBoton() {
		btnStart.setEnabled(false);
	}

	public void activarBoton() {
		btnStart.setEnabled(true);
	}

	public JButton getBoton() {
		return this.btnStart;
	}

}
