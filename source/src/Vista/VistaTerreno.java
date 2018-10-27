package Vista;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Modelo.Juego.Posicionable;
import Modelo.Terreno.Terreno;
import Modelo.Torre.Bala;

public class VistaTerreno extends JPanel implements Observer{

	private static VistaTerreno instancia;

	private Image fondo;

	private static int ancho = 600;
	private static int alto = 600;

	private int nivel=1;
	private BufferedImage buffer;
	private Graphics gB, graficoPanel;
	private Terreno terreno;

	private int posicionXDelTablero;

	private int posicionYDelTablero;

	private Image imagenAPintar;

	private VistaTerreno(){
		this.setBounds(0,0,ancho,alto);
		this.buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		this.terreno=Terreno.getInstancia();
		this.setVisible(true);
	}

	public static VistaTerreno getInstancia(){
		if(instancia == null)
			instancia = new VistaTerreno();
		return instancia;
	}

    public void setNivel(int nivel){
    	this.nivel=nivel;
    	this.fondo=ListaImagenes.getInstancia().getImagenTerreno(this.nivel);
    }

	private void paintBuffer(){
		this.gB = this.buffer.getGraphics();
    	gB.drawImage(this.fondo,0,0,this.ancho,this.alto,this);
		Collection c=this.terreno.getElementos();
        Iterator it=c.iterator();

        while(it.hasNext()){
    		Posicionable elem=(Posicionable)it.next();
    		if(elem != null)
    			if(!(elem.getPosicion().getX()==Terreno.getInstancia().getPuertaDeEntrada().getPosicion().getX() && elem.getPosicion().getY()==Terreno.getInstancia().getPuertaDeEntrada().getPosicion().getY()))
    			    this.pintar(elem);
    	}
	}

	public void paint(Graphics g){
		this.paintBuffer();
		g.drawImage(this.buffer,0,0,this);
	}

	public void update(){
		this.graficoPanel = this.getGraphics();
		this.paint(this.graficoPanel);
	}

	/**
	 *
	 * @param posicionable objeto a pintar
	 */
	public void pintar (Posicionable posicionable){
		this.posicionXDelTablero = posicionable.getPosicion().getX()*30;
		this.posicionYDelTablero = posicionable.getPosicion().getY()*30;
		
		String perspectiva = getPerspectiva(posicionable);

		this.imagenAPintar = ListaImagenes.getInstancia().getImagenPosicionable(posicionable, perspectiva);
		gB.drawImage(imagenAPintar, this.posicionXDelTablero, this.posicionYDelTablero, 30, 30, this);
	}

	private String getPerspectiva(Posicionable posicionable) {
		int posX = posicionable.getPosicion().getX();
		int posY = posicionable.getPosicion().getY();
		int posXAnt = posicionable.getPosicionAnt().getX();
		int posYAnt = posicionable.getPosicionAnt().getY();
		
		if((posX == posXAnt) && (posY > posYAnt))
			return "Abajo";
		if((posX > posXAnt) && (posY == posYAnt))
			return "Derecha";
		if((posX == posXAnt) && (posY < posYAnt))
			return "Arriba";
		if((posX < posXAnt) && (posY == posYAnt))
			return "Izquierda";
		return "";
	}

	public void update(Observable observado, Object arg) {
		pintar((Posicionable)observado);
		this.graficoPanel.drawImage(this.buffer,0,0,this);
		update();
	}

	public void setBala (Bala bala){
		bala.addObserver(this);
	}

}
