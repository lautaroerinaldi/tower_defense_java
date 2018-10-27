package Prueba;

import Modelo.Enemigo.*;
import Modelo.Excepciones.EnemigoEnSalidaException;
import Modelo.Terreno.CasilleroCamino;
import Modelo.Terreno.CasilleroPuerta;
import Modelo.Terreno.Punto;
import Modelo.Terreno.TipoDePuerta;
import junit.framework.TestCase;


public class TestCasilleroPuerta extends TestCase {
	public void testAgregarEnemigo(){
		Enemigo enemigo=new Hormiga();
		CasilleroCamino casillero=new CasilleroPuerta(new Punto(5,5),TipoDePuerta.SALIDA);	
		try{
			casillero.agregarEnemigo(enemigo);
			fail("no lanzo EnemigoEnSalidaException");
		}catch(EnemigoEnSalidaException eese){
			assertTrue(true);
		}
		
		casillero=new CasilleroPuerta(new Punto(5,5),TipoDePuerta.ENTRADA);	
		casillero.agregarEnemigo(enemigo);
		assertEquals(casillero.getTodosLosEnemigos().size(),1);
	}

}
