package Prueba;

import java.io.IOException;

import Modelo.Enemigo.*;
import Modelo.Excepciones.EnemigoMuertoException;
import Modelo.Juego.ListaEnemigos;
import Modelo.Terreno.*;
import Modelo.Torre.*;

import junit.framework.TestCase;

public class TestTorre extends TestCase {

	private Torre torreBlanca,torreDorada,torrePlateada;
	private Punto posicion, posicionNueva;
	private ListaEnemigos lista;
	private Enemigo enemigo;
	private Terreno terreno;
	private Mosca enemigoMosca;
	private Arania enemigoArania;
	private Hormiga enemigoHormiga;
	private Cucaracha enemigoCucaracha;
	private Casillero casillero;

	protected void setUp() throws Exception {
		super.setUp();
		this.lista         = new ListaEnemigos();
		this.terreno       = Terreno.getInstancia();
		
		this.torreDorada   = new TorreDorada();
		this.torreBlanca   = new TorreBlanca();
		this.torrePlateada = new TorrePlateada();
		
		this.posicion      = new Punto(3,7);
		this.posicionNueva = new Punto(0,5);
		
		this.lista.add(new Hormiga());
		this.lista.add(new Mosca());
		this.lista.add(new Cucaracha());
		
	}
	
	public void tearDown(){
		System.gc();
	}
	
	public void testAtributos(){
		assertEquals(4,this.torreDorada.getAlcance());
		assertEquals(3,this.torrePlateada.getAlcance());
		assertEquals(2,this.torreBlanca.getAlcance());

		assertEquals(4,this.torreDorada.getDanio());
		assertEquals(2,this.torrePlateada.getDanio());
		assertEquals(1,this.torreBlanca.getDanio());
	
		assertEquals(50,this.torreDorada.getCosto());
		assertEquals(20,this.torrePlateada.getCosto());
		assertEquals(10,this.torreBlanca.getCosto());
	}
	
	public void testGetPosicion(){
		assertEquals(null,this.torreDorada.getPosicion());
		
		assertEquals(null,this.torrePlateada.getPosicion());
		
		assertEquals(null,this.torreBlanca.getPosicion());
		
	}
	
	public void testSetPosicion(){
		this.torreDorada.setPosicion(this.posicion);
		assertEquals(this.posicion, this.torreDorada.getPosicion());
		
		this.torrePlateada.setPosicion(this.posicion);
		assertEquals(this.posicion,this.torrePlateada.getPosicion());
		
		this.torreBlanca.setPosicion(this.posicion);
		assertEquals(posicion,torreBlanca.getPosicion());

	}

	public void testActuar(){
		this.terreno.cargarCamino(1);
		this.casillero= (CasilleroCamino)Terreno.getInstancia().getCasillero(new Punto(3,8));
		
		this.torreDorada.setTerreno();
		this.torreDorada.setPosicion(new Punto(9,9));
		Enemigo enemigoMosca,enemigoHormiga,enemigoArania, enemigoCucaracha;
		
		try{
			this.torreDorada.actuar();
		}catch(EnemigoMuertoException e){
			fail("mato un enemigo sin que haya un enemigo en el terreno");
		}
		
		prepararTest();
		
		
		try{
			this.torreDorada.actuar();
		}catch(EnemigoMuertoException e){
			fail("mato un enemigo sin que haya un enemigo en su radio");
		}
		
		this.torreDorada.setPosicion(new Punto(2,3));
		
		try{
			this.torreDorada.getBala();
			this.torreDorada.actuar();
			}catch(EnemigoMuertoException e){
			fail("mato enemigo teniendo vida ");
				
		}
		
		this.enemigoMosca.setVidaRestante(1);
			
		try{
			this.torreDorada.getBala();
			this.torreDorada.actuar();
			fail("no mato enemigo");
			}catch(EnemigoMuertoException e){
				CasilleroCamino otroCasillero = (CasilleroCamino)Terreno.getInstancia().getCasillero(this.posicionNueva); 
				assertEquals(1, otroCasillero.getTodosLosEnemigos().size());
		}
		
	}

	private void prepararTest() {
		this.enemigoMosca     = new Mosca();
		this.enemigoArania    = new Arania();
		this.enemigoHormiga   = new Hormiga();
		this.enemigoCucaracha = new Cucaracha();
		
		this.enemigoMosca.setCasillero(casillero);
		this.enemigoHormiga.setCasillero(casillero);
		this.enemigoArania.setCasillero(casillero);
		this.enemigoCucaracha.setCasillero(casillero);
		
		
		this.enemigoHormiga.setPosicion(this.posicion);
		this.enemigoCucaracha.setPosicion(this.posicion);
		this.enemigoMosca.setPosicion(this.posicionNueva);
		this.enemigoArania.setPosicion(this.posicionNueva);
		
		Terreno.getInstancia().agregarEnemigo(enemigoHormiga ,this.posicion);
		Terreno.getInstancia().agregarEnemigo(enemigoCucaracha, this.posicion);
		Terreno.getInstancia().agregarEnemigo(enemigoMosca, this.posicionNueva);
		Terreno.getInstancia().agregarEnemigo(enemigoArania, this.posicionNueva);
		
		
		
	}
	
}