package Prueba;
import Modelo.Enemigo.*;
import Modelo.Excepciones.EnemigoMuertoException;
import Modelo.Torre.Bala;
import junit.framework.TestCase;
import Modelo.Terreno.Punto;
import Modelo.Terreno.Terreno;

public class TestBala extends TestCase {

	private Bala bala,otraBala;	
	private Enemigo enemigo;
	private Terreno terreno;
	
	protected void setUp() throws Exception {
		super.setUp();
		Punto posicionBala    = new Punto(18,18);
		Punto posicionEnemigo = new Punto(10,10);
		int danio  = 2;
		int danio2 = 60;
		this.bala     = new Bala(danio);
		this.otraBala = new Bala(danio2);
		
		this.bala.setPosicion(posicionBala);
		this.otraBala.setPosicion(posicionBala);
	
		this.enemigo  = new Mosca();
		this.enemigo.setPosicion(posicionEnemigo);
		this.enemigo.setCasillero(this.terreno.getInstancia().getCasillero(posicionBala));
	}
	
	public void tearDown(){
		System.gc();
	}

	public void testDisparar(){
		try{	
			this.bala.disparar(this.enemigo);
			assertEquals(43,this.enemigo.getVidaRestante());
		
		}catch(EnemigoMuertoException e){
			fail("mato enemigo teniendo vida restante");
		}
		
		try{
			this.otraBala.disparar(enemigo);
			fail("no mato enemigo cuando este no tenia vida");
		}catch(EnemigoMuertoException e){
			assert(true);
		}
	}
	
}
