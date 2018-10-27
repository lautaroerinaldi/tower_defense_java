package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.swing.JPanel;

import Modelo.Juego.Juego;
import Modelo.Juego.Jugador;

public class VistaJugador extends JPanel{
	
	private static int ancho = 100;
	private static int alto = 250;

	private BufferedImage buffer;
	private Jugador jugador;
	private Color colorFondo;
		
	public VistaJugador(){
		this.colorFondo = Color.BLACK;
		this.setBounds(700,0,ancho, alto);
		this.buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		this.setVisible(false);
	}
	
	private void paintBuffer(){
		Graphics gB = this.buffer.getGraphics();
		gB.setColor(this.colorFondo);
		gB.fillRect(0,0,ancho, alto);
		Font font = new Font("Verdana",Font.BOLD, 12);
		gB.setFont(font);
		gB.setColor(Color.RED);
		gB.drawString(String.valueOf(this.jugador.getVida()),0,45);
		gB.setColor(Color.YELLOW);
		gB.drawString(String.valueOf(this.jugador.getDinero()),0,100);
		gB.setColor(Color.BLUE);
		gB.drawString(String.valueOf(this.jugador.getPuntaje()),0,75);
		gB.setColor(Color.WHITE);
		gB.drawString(String.valueOf(this.jugador.getCantidad("TorreBlanca")),0,135);
		gB.setColor(Color.LIGHT_GRAY);
		gB.drawString(String.valueOf(this.jugador.getCantidad("TorrePlateada")),0,160);
		gB.setColor(Color.YELLOW);
		gB.drawString(String.valueOf(this.jugador.getCantidad("TorreDorada")),0,185);
		gB.setColor(Color.BLUE);
		gB.drawString(String.valueOf(this.jugador.getCantidad("Pegote")),0,210);
		gB.setColor(Color.YELLOW);
		gB.drawString(String.valueOf(this.jugador.getCantidad("Arena")),0,240);
		gB.setColor(this.colorFondo);
	}

	public void paint(Graphics g){	
		this.paintBuffer();
		g.drawImage(this.buffer,0,0,this);
	}
	
	public void update(){
		Graphics gr = this.getGraphics();
		this.paint(gr);
	}
	
	public void setJugador(Jugador jugador){
		this.jugador = jugador;
	}

}
