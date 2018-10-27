package Prueba;

import junit.framework.TestCase;



import Modelo.Enemigo.Arania;
import Modelo.Enemigo.Cucaracha;
import Modelo.Enemigo.Enemigo;
import Modelo.Enemigo.Hormiga;
import Modelo.Enemigo.Mosca;
import Modelo.Obstaculo.Arena;
import Modelo.Obstaculo.Pegote;
import Modelo.Terreno.CasilleroPuerta;
import Modelo.Terreno.Punto;
import Modelo.Terreno.Terreno;

public class TestEnemigo extends TestCase{
	Enemigo hormiga;
	Enemigo mosca;
	Enemigo cucaracha;
	Enemigo arania;

	public void setUp(){
		Enemigo.setVelocidadMaxima(6);
		hormiga= new Hormiga();
		mosca= new Mosca();
		cucaracha= new Cucaracha();
		arania= new Arania();
	}
			
	public void testConstructoresEnemigos() {
		assertEquals(1*Enemigo.getFactor(), hormiga.getResistencia());
		assertEquals(2*Enemigo.getFactor(), arania.getResistencia());
		assertEquals(3*Enemigo.getFactor(), mosca.getResistencia());
		assertEquals(4*Enemigo.getFactor(), cucaracha.getResistencia());
		
		assertEquals(1, hormiga.getVelocidad());
		assertEquals(3, arania.getVelocidad());
		assertEquals(4, mosca.getVelocidad());
		assertEquals(5, cucaracha.getVelocidad());
		
		assertEquals(5, hormiga.getTurnosRestantesParaActuar());
		assertEquals(3, arania.getTurnosRestantesParaActuar());
		assertEquals(2, mosca.getTurnosRestantesParaActuar());
		assertEquals(1, cucaracha.getTurnosRestantesParaActuar());
		
		assertEquals(1*Enemigo.getFactor(), hormiga.getVidaRestante());
		assertEquals(2*Enemigo.getFactor(), arania.getVidaRestante());
		assertEquals(3*Enemigo.getFactor(), mosca.getVidaRestante());
		assertEquals(4*Enemigo.getFactor(), cucaracha.getVidaRestante());
	}
	
		public void testConstructorEnemigo(){
		assertEquals(6, hormiga.getVelocidadMaxima());
		assertEquals(6, arania.getVelocidadMaxima());
		assertEquals(6, mosca.getVelocidadMaxima());
		assertEquals(6, cucaracha.getVelocidadMaxima());
	}
	
	public void testMoverArania(){
		Terreno unTerreno= Terreno.getInstancia();
		unTerreno.cargarCamino(0);
		CasilleroPuerta entrada = (CasilleroPuerta)unTerreno.getPuertaDeEntrada();
		
		entrada.agregarEnemigo(arania);
		arania.setCasillero(entrada);
		
		unTerreno.agregarObstaculo(new Arena(), new Punto(1, 1));
		unTerreno.agregarObstaculo(new Pegote(), new Punto (5, 5));
		
		String turnosQueDebenQuedar= new String();
		String turnosQueQuedan= new String();
		turnosQueDebenQuedar="321654321321321321181716151413121110987654321321321321321321321321321321321321321321";
		turnosQueQuedan="3";
		
		for(int i=0; i < 74; i++){
			arania.actuar();
			turnosQueQuedan=turnosQueQuedan.concat(String.valueOf(arania.getTurnosRestantesParaActuar()));
		}
		if (!turnosQueQuedan.equals(turnosQueDebenQuedar))
			throw new RuntimeException();
		
	}
	public void testMoverHormiga(){
		Terreno unTerreno= Terreno.getInstancia();
		unTerreno.cargarCamino(0);
		CasilleroPuerta Entrada = (CasilleroPuerta)unTerreno.getCasillero(new Punto(0, 0));
		
		Entrada.agregarEnemigo(hormiga);
		hormiga.setCasillero(Entrada);
		
		unTerreno.agregarObstaculo(new Pegote(), new Punto (1, 1));
		unTerreno.agregarObstaculo(new Arena(), new Punto(4, 4));
		unTerreno.agregarObstaculo(new Pegote(), new Punto (5, 5));
		
		String turnosQueDebenQuedar= new String();
		String turnosQueQuedan= new String();
		turnosQueDebenQuedar="5432118171615141312111098765432154321543211098765432118171615141312111098765432154321543215432154321543215432154321543215432154321543215432154321";//5432154321543215432110987654321543215432154321543215432154321543215432154321543215432154321109876543215432154321543215432154321543215432154321543215432154321543211817161514131211109876543215432154321543215432154321543215432154321543215432154321543215432154321543215432154321543215432154321109876543215432154321543215432154321543215432110987654321";
		turnosQueQuedan="5";
		
		for(int i=0; i < 125; i++){
			hormiga.actuar();
			turnosQueQuedan=turnosQueQuedan.concat(String.valueOf(hormiga.getTurnosRestantesParaActuar()));
		}
		
		if (!turnosQueQuedan.equals(turnosQueDebenQuedar))
			throw new RuntimeException();
		
	}
	
	public void testMoverCucaracha(){
		Terreno unTerreno= Terreno.getInstancia();
		unTerreno.cargarCamino(0);
		CasilleroPuerta Entrada = (CasilleroPuerta)unTerreno.getCasillero(new Punto(0, 0));
		
		Entrada.agregarEnemigo(cucaracha);
		cucaracha.setCasillero(Entrada);
		
		unTerreno.agregarObstaculo(new Pegote(), new Punto (5, 5));
		unTerreno.agregarObstaculo(new Arena(), new Punto(11, 11));
		unTerreno.agregarObstaculo(new Arena(), new Punto(12, 12));
		unTerreno.agregarObstaculo(new Pegote(), new Punto (13, 13));
		
		String turnosQueDebenQuedar= new String();
		String turnosQueQuedan= new String();
		turnosQueDebenQuedar="1111118171615141312111098765432111111212118171615141312111098765432111111";//11112111111111817161514131211109876543211111111111112111111111111118171615141312111098765432111111211111111111111121";
		turnosQueQuedan="1";
		
		for(int i=0; i < 54; i++){
			cucaracha.actuar();
			turnosQueQuedan=turnosQueQuedan.concat(String.valueOf(cucaracha.getTurnosRestantesParaActuar()));
		}
		
		if (!turnosQueQuedan.equals(turnosQueDebenQuedar))
			throw new RuntimeException();
		
	}
	
	public void testMoverMosca(){
		Terreno unTerreno= Terreno.getInstancia();
		unTerreno.cargarCamino(0);
		CasilleroPuerta Entrada = (CasilleroPuerta)unTerreno.getCasillero(new Punto(0, 0));
		
		Entrada.agregarEnemigo(mosca);
		mosca.setCasillero(Entrada);
		
		unTerreno.agregarObstaculo(new Arena(), new Punto(1, 1));
		unTerreno.agregarObstaculo(new Pegote(), new Punto (2, 2));
		unTerreno.agregarObstaculo(new Arena(), new Punto(4, 4));
		unTerreno.agregarObstaculo(new Pegote(), new Punto (5, 5));
		unTerreno.agregarObstaculo(new Arena(), new Punto(11, 11));
		unTerreno.agregarObstaculo(new Arena(), new Punto(12, 12));
		unTerreno.agregarObstaculo(new Pegote(), new Punto (13, 13));
		
		String turnosQueDebenQuedar= new String();
		String turnosQueQuedan= new String();
		turnosQueDebenQuedar="21212121212121212121212121212121212121";//212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121";
		turnosQueQuedan="2";
		
		for(int i=0; i < 37; i++){
			mosca.actuar();
			turnosQueQuedan=turnosQueQuedan.concat(String.valueOf(mosca.getTurnosRestantesParaActuar()));
		}
		
		if (!turnosQueQuedan.equals(turnosQueDebenQuedar))
			throw new RuntimeException();
		
	}
	
}
