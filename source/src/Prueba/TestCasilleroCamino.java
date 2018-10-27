package Prueba;

import java.util.Collection;

import Modelo.Enemigo.*;
import Modelo.Excepciones.EnemigoNoEstaEnTerrenoException;
import Modelo.Obstaculo.Arena;
import Modelo.Obstaculo.Obstaculo;
import Modelo.Terreno.*;
import junit.framework.TestCase;


public class TestCasilleroCamino extends TestCase {
	private CasilleroCamino casillero;

	public void setUp(){
		casillero=new CasilleroCamino();
	}
	
	public void tearDown(){
		System.gc();
	}

	public void testConstructor(){
		Punto posicion=new Punto(1,1);
		CasilleroCamino casillero=new CasilleroCamino(posicion);
		assertSame(casillero.getPosicion(),posicion);
		Collection<Enemigo> c=casillero.getTodosLosEnemigos();
		assertEquals(c.size(),0);
		assertNull(casillero.getProximaPosicion());
	}

	public void testQuitarEnemigo(){	
		Enemigo enemigo1=new Hormiga();
		try{
		casillero.quitarEnemigo(enemigo1);
		fail("no lanzo exception al sacar un enemigo que no esta en el casillero");
		}catch(EnemigoNoEstaEnTerrenoException eneete){
			assertTrue(true);
		}
		Enemigo enemigo2=new Hormiga();
		Enemigo enemigo3=new Hormiga();
		casillero.agregarEnemigo(enemigo1);
		casillero.agregarEnemigo(enemigo2);
		casillero.agregarEnemigo(enemigo3);
		
		casillero.quitarEnemigo(enemigo1);
		casillero.quitarEnemigo(enemigo2);
		casillero.quitarEnemigo(enemigo3);
		Collection<Enemigo> c=casillero.getTodosLosEnemigos();
		assertEquals(c.size(),0);
		
	}
	
	public void testTieneObstaculo(){
		assertFalse(casillero.tieneObstaculo());
		
		Obstaculo obstaculo=new Arena();
		casillero.setObstaculo(obstaculo);
		assertTrue(casillero.tieneObstaculo());
	}
	
	public void testGetTodosLosEnemigos(){
		Enemigo enemigo1=new Hormiga();
		Enemigo enemigo2=new Hormiga();
		Enemigo enemigo3=new Hormiga();
		casillero.agregarEnemigo(enemigo1);
		casillero.agregarEnemigo(enemigo2);
		casillero.agregarEnemigo(enemigo3);
		Collection<Enemigo> c=casillero.getTodosLosEnemigos();
		assertEquals(c.size(),3);
		
	}
}
