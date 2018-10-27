package Prueba;
import junit.framework.*;
import Modelo.Juego.*;
import Modelo.Obstaculo.Pegote;
import Modelo.Terreno.*;
import Modelo.Torre.*;

public class TestJugador extends TestCase {

	private Jugador jugador;
	private Negociable negociable;
	private Negociable negociable2;
	
	public void setUp() {
		jugador = new Jugador();
		negociable = new TorreDorada();
		negociable2 = new Pegote();
	}
	
	public void tearDown(){
		System.gc();
	}

	
	public void testAgregarNegociable(){
		this.jugador.agregarNegociable(this.negociable);
		assertEquals(1, this.jugador.getListaNegociablesEnJuego().size());
		assertEquals("Modelo.Torre.TorreDorada", this.jugador.getListaNegociablesEnJuego().getNegociable(0).getClass().getCanonicalName());
	}
	
	public void testAgregarObjetoAMochila(){
		this.jugador.agregarObjetoAMochila(negociable2);
		assertEquals(1, this.jugador.getListaMochila().size());
		assertEquals("Modelo.Obstaculo.Pegote", this.jugador.getListaMochila().getNegociable(0).getClass().getCanonicalName());
	}
	
	public void testGetCantidad(){
		this.jugador.agregarObjetoAMochila(negociable);
		this.jugador.agregarObjetoAMochila(negociable2);
		
		assertEquals(1, this.jugador.getCantidad("TorreDorada"));
		assertEquals(1, this.jugador.getCantidad("Pegote"));
	}
	
	public void testGetObjeto(){
		this.jugador.agregarObjetoAMochila(negociable);
		this.jugador.agregarObjetoAMochila(negociable2);
		
		assertEquals("Modelo.Torre.TorreDorada", this.jugador.getObjeto("TorreDorada").getClass().getCanonicalName());
		assertEquals("Modelo.Obstaculo.Pegote", this.jugador.getObjeto("Pegote").getClass().getCanonicalName());
	}
	
	public void testGuardarObjetos(){
		this.jugador.agregarNegociable(this.negociable);
		this.jugador.agregarNegociable(this.negociable2);
		this.jugador.guardarObjetos();
		
		assertEquals(true, this.jugador.getListaNegociablesEnJuego().isEmpty());
		assertEquals(2, this.jugador.getListaMochila().size());
		assertEquals("Modelo.Torre.TorreDorada", this.jugador.getListaMochila().getNegociable(0).getClass().getCanonicalName());
		assertEquals("Modelo.Obstaculo.Pegote", this.jugador.getListaMochila().getNegociable(1).getClass().getCanonicalName());
	}
		
	public void testAgregarObjeto() {
		Terreno.getInstancia().cargarCamino(1);
		this.jugador.agregarObjetoAMochila(negociable);
		this.jugador.agregarObjetoAMochila(negociable2);
		
		this.jugador.agregarObjeto(negociable, new Punto(4,15));
		this.jugador.agregarObjeto(negociable2, new Punto(0,1));
		
		assertEquals(true, this.jugador.getListaMochila().isEmpty());
		assertEquals(2, this.jugador.getListaNegociablesEnJuego().size());
		assertEquals("Modelo.Torre.TorreDorada", this.jugador.getListaNegociablesEnJuego().getNegociable(0).getClass().getCanonicalName());
		assertEquals("Modelo.Obstaculo.Pegote", this.jugador.getListaNegociablesEnJuego().getNegociable(1).getClass().getCanonicalName());		
	}
		
	
	public void testSacarObjeto() {
		Terreno.getInstancia().cargarCamino(1);
		this.jugador.agregarObjetoAMochila(negociable);
		this.jugador.agregarObjeto(negociable, new Punto(4,15));
		
		this.jugador.sacarObjeto(negociable);
		
		assertEquals(true, this.jugador.getListaNegociablesEnJuego().isEmpty());
	}

}
