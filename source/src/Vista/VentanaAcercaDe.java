package Vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class VentanaAcercaDe extends JFrame{
	private static final int ancho = 410;
	private static final int alto = 265;
	
	private JLabel etiqueta;
	private JPanel panel;
	
	public VentanaAcercaDe(){
		setLayout(new BorderLayout());
		setSize(this.ancho, this.alto);
		setTitle("Acerca de...");
		this.setResizable(false);
		
		panel = new JPanel();
		ImageIcon imagen = new ImageIcon(getClass().getClassLoader().getResource("img/acercade.jpg"));
		etiqueta=new JLabel(imagen);
	}
	
	public void mostrar(){
		etiqueta.setVisible(true);
		panel.add(etiqueta).setBounds(0, 0, 407, 223);
		this.add(panel);
		this.setVisible(true);
	}
	
	public void ocultar(){
		this.setVisible(false);
	}

}
