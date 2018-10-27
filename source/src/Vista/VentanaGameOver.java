package Vista;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modelo.Juego.Juego;

public class VentanaGameOver extends JFrame{
	private static final int ancho = 410;
	private static final int alto = 265;
		
	private JLabel etiqueta;
	private JLabel etiqueta2;
	private JPanel panel;
		
	public VentanaGameOver(){
		setLayout(new BorderLayout());
		setSize(this.ancho, this.alto);
		setTitle("Game Over");
		this.setResizable(false);
		
		panel = new JPanel();
		ImageIcon imagenGano = new ImageIcon(getClass().getClassLoader().getResource("img/gano.jpg"));
		ImageIcon imagenPerdio = new ImageIcon(getClass().getClassLoader().getResource("img/gameover.jpg"));
		etiqueta=new JLabel(imagenPerdio);
		etiqueta2 = new JLabel(imagenGano);
	}
		
	/**
	 * Muestra la pantalla de fin de juego segun haya ganado o perdido el jugador
	 *
	 */
	public void mostrar(){
		if(Juego.getInstancia().getJugador().getVida() == 0){
			etiqueta.setVisible(true);
			panel.add(etiqueta).setBounds(0, 0, 407, 223);
		}
		else{
			etiqueta2.setVisible(true);
			panel.add(etiqueta2).setBounds(0, 0, 407, 223);
		}
		this.add(panel);
		this.setVisible(true);
	}
		
	public void ocultar(){
		this.setVisible(false);
	}
	
}
