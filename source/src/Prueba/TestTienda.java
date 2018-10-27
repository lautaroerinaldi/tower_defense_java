package Prueba;

import Modelo.Excepciones.IndiceInvalidoException;
import Modelo.Juego.Juego;
import Modelo.Juego.Jugador;
import Modelo.Juego.Negociable;
import Modelo.Juego.Stock;
import Modelo.Juego.Tienda;
import Modelo.Excepciones.SinDineroException;
import junit.framework.TestCase;

public class TestTienda extends TestCase {
	Tienda miTienda;
	Jugador unJugador;

	public void setUp() throws Exception {
		this.miTienda= Tienda.getInstancia();
		this.unJugador= Juego.getInstancia().getJugador();
		this.unJugador.setDinero(40);
	}

	public void testObtenerStock(){
		Stock lista= miTienda.obtenerStock();

		assertEquals("Pegote", getNombreElemento(lista.getNegociable(0).getClass().getCanonicalName()));
		assertEquals("Arena", getNombreElemento(lista.getNegociable(1).getClass().getCanonicalName()));
		assertEquals("TorreDorada", getNombreElemento(lista.getNegociable(2).getClass().getCanonicalName()));
		assertEquals("TorrePlateada", getNombreElemento(lista.getNegociable(3).getClass().getCanonicalName()));
		assertEquals("TorreBlanca", getNombreElemento(lista.getNegociable(4).getClass().getCanonicalName()));
		
	}
	
	public void testVenderUnNegociable(){
		miTienda.vender(0, unJugador);
		assertEquals(20, unJugador.getDinero());
		miTienda.vender(0, unJugador);
		assertEquals(0, unJugador.getDinero());
	}
	
	public void testVenderVariosNegociable(){
		miTienda.vender(0, unJugador);
		assertEquals(20, unJugador.getDinero());
		miTienda.vender(1, unJugador);
		assertEquals(10, unJugador.getDinero());
		miTienda.vender(1, unJugador);
		assertEquals(0, unJugador.getDinero());
	}
	
	public void testVenderTorreDorada(){
		unJugador.setDinero(65);
		miTienda.vender(2, unJugador);
		assertEquals(15, unJugador.getDinero());
	}
	
	public void testNoAlcanzaElDinero(){
		try{
			miTienda.vender(2, unJugador);
			fail();
		}
		catch (SinDineroException excepcion){
			assertTrue(true);
		}
		try{
			miTienda.vender(0, unJugador);
			miTienda.vender(1, unJugador);
			miTienda.vender(3, unJugador);
			miTienda.vender(0, unJugador);
			miTienda.vender(1, unJugador);
			miTienda.vender(3, unJugador);
			fail();
		}
		catch (SinDineroException excepcion){
			assertTrue(true);
		}
	}
	
	public void testVendeNegociableCorrecto(){
		unJugador.modificarDinero(150);
		
		assertEquals("Pegote", getNombreElemento(miTienda.vender(0, unJugador).getClass().getCanonicalName()));
		assertEquals("Arena", getNombreElemento(miTienda.vender(1, unJugador).getClass().getCanonicalName()));
		assertEquals("TorreDorada", getNombreElemento(miTienda.vender(2, unJugador).getClass().getCanonicalName()));
		assertEquals("TorrePlateada", getNombreElemento(miTienda.vender(3, unJugador).getClass().getCanonicalName()));
		assertEquals("TorreBlanca", getNombreElemento(miTienda.vender(4, unJugador).getClass().getCanonicalName()));

	}
	
	public void testVenderNegociablesDistintos(){
		unJugador.modificarDinero(300);
		
		Negociable Pegote1=miTienda.vender(0, unJugador);
		Negociable Arena1=miTienda.vender(1, unJugador);
		Negociable TorreDorada1=miTienda.vender(2, unJugador);
		Negociable TorrePlateada1=miTienda.vender(3, unJugador);
		Negociable TorreBlanca1=miTienda.vender(4, unJugador);
		Negociable Pegote2=miTienda.vender(0, unJugador);
		Negociable Arena2=miTienda.vender(1, unJugador);
		Negociable TorreDorada2=miTienda.vender(2, unJugador);
		Negociable TorrePlateada2=miTienda.vender(3, unJugador);
		Negociable TorreBlanca2=miTienda.vender(4, unJugador);
		
		assertFalse(Pegote1==Pegote2);
		assertFalse(Arena1==Arena2);
		assertFalse(TorreDorada1==TorreDorada2);
		assertFalse(TorrePlateada1==TorrePlateada2);
		assertFalse(TorreBlanca1==TorreBlanca2);
	}
	
	public void testChequearIndiceInvalido(){
		try{
			miTienda.vender(5, unJugador);
			fail();
		}
		catch (IndiceInvalidoException excepcion){
			assertTrue(true);
		}
	}
	private String getNombreElemento(String nombre){
		String retorno = nombre;
		int posicion = retorno.lastIndexOf(".");
		retorno = retorno.substring(posicion + 1, retorno.length());
		return retorno;
	}
}
