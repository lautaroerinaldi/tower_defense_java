package Prueba;

import Modelo.Enemigo.Enemigo;
import Modelo.Enemigo.Hormiga;
import Modelo.Enemigo.Mosca;
import Modelo.Obstaculo.Arena;
import Modelo.Obstaculo.Obstaculo;
import Modelo.Obstaculo.Pegote;
import junit.framework.TestCase;

public class TestObstaculo extends TestCase {

	private Enemigo enemigoHormiga,enemigoMosca;
	private Obstaculo pegote,arena;
	
	public void setUp() {
		this.arena          = new Arena();
		this.pegote         = new Pegote();
		this.enemigoMosca   = new Mosca();
		this.enemigoHormiga = new Hormiga();
	}
	
	public void testGetCosto(){
		assertEquals(20, this.pegote.getCosto());
		assertEquals(10, this.arena.getCosto());
	}
	
	public void testCausarEfecto(){
		this.arena.causarEfecto(this.enemigoHormiga);
			assertEquals(12, this.enemigoHormiga.getTurnosRestantesParaActuar());
		
		this.pegote.causarEfecto(this.enemigoMosca);
			assertEquals(21, this.enemigoMosca.getTurnosRestantesParaActuar());	
	}
}