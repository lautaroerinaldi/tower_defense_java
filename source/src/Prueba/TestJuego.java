package Prueba;

import java.io.IOException;

import junit.framework.*;
import Modelo.Enemigo.Enemigo;
import Modelo.Excepciones.*;
import Modelo.Juego.*;
import Modelo.Torre.*;


public class TestJuego extends TestCase {
	
	public void testConstructor() {
		Juego juego = Juego.getInstancia();
		assertNotNull(juego);
	}
	
	public void testCargarNivel(){
		Juego juego = Juego.getInstancia();
		juego.cargarNivel();
		assertEquals(28, juego.getListaActuables().size());
	}
	
	public void testCargarTorre(){
		Juego juego = Juego.getInstancia();
		Torre torre = new TorreBlanca();
		juego.agregarTorre(torre);
		assertEquals("Modelo.Torre.TorreBlanca", juego.getListaTorresAgregar().getTorre(0).getClass().getCanonicalName());
	}
	
	public void testCargarTorres(){
		Juego juego = Juego.getInstancia();
		juego.cargarTorres();
		assertEquals(true, juego.getListaTorresAgregar().isEmpty());
		assertEquals(29, juego.getListaActuables().size());
		assertEquals("Modelo.Torre.TorreBlanca", juego.getListaActuables().getActuable(28).getClass().getCanonicalName());	
	}
	
	public void testPasoEnemigo(){
		Juego juego = Juego.getInstancia();
		Enemigo enemigo = (Enemigo) juego.getListaActuables().getActuable(0);
		juego.pasoEnemigo(enemigo);
		
		assertEquals(1, juego.getListaActuablesAEliminar().size());
		assertEquals("Modelo.Enemigo.Hormiga", juego.getListaActuablesAEliminar().getActuable(0).getClass().getCanonicalName());
		
		assertEquals(14, juego.getJugador().getVida());
	}
	
	public void testMurioEnemigo(){
		Juego juego = Juego.getInstancia();
		Enemigo enemigo = (Enemigo) juego.getListaActuables().getActuable(1);
		juego.murioEnemigo(enemigo);
		
		assertEquals(2, juego.getListaActuablesAEliminar().size());
		assertEquals("Modelo.Enemigo.Hormiga", juego.getListaActuablesAEliminar().getActuable(1).getClass().getCanonicalName());
		
		assertEquals(42, juego.getJugador().getDinero());
		assertEquals(16, juego.getJugador().getPuntaje());		
	}
	
	public void testAgregarListaActuablesAEliminar(){
		Juego juego = Juego.getInstancia();
		Torre torre = (Torre) juego.getListaActuables().getActuable(28);
		juego.agregarListaActuablesAEliminar(torre);
		assertEquals(3, juego.getListaActuablesAEliminar().size());
		assertEquals("Modelo.Torre.TorreBlanca", juego.getListaActuablesAEliminar().getActuable(2).getClass().getCanonicalName());
	}
	
	public void testEliminarActuables(){
		Juego juego = Juego.getInstancia();
		juego.eliminarActuables();
		assertEquals(true, juego.getListaActuablesAEliminar().isEmpty());
		assertEquals(26, juego.getListaActuables().size());
	}
	
	public void testGameOver(){
		Juego juego = Juego.getInstancia();
		try{
			juego.gameOver();
			fail("Se esperaba FinalJuegoException");
		}
		catch(FinalJuegoException e){
			assertTrue(true);
		}
	}
			
	public void testGuardar(){
		Juego juego = Juego.getInstancia();
		try{
			juego.salvar("utils/save/test.xml");
		}
		catch(IOException e){
			fail("Error al guardar el estado del juego");
		}
	}
	
	public void testRecuperar(){
		try{
			Juego juego = Juego.recuperar("utils/save/test.xml");
			assertEquals(1,juego.getNivel());
			assertEquals(14, juego.getJugador().getVida());
			assertEquals(42, juego.getJugador().getDinero());
			assertEquals(16, juego.getJugador().getPuntaje());
		}
		catch(IOException e){
			fail("Error al recuperar el estado del juego");
		}
	}
	
}
