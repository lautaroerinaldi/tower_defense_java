package Prueba;

import junit.framework.*;

public class Prueba {

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestJuego.class);
		suite.addTestSuite(TestJugador.class);
		suite.addTestSuite(TestTerreno.class);
		suite.addTestSuite(TestBala.class);
		suite.addTestSuite(TestCasilleroCamino.class);
		suite.addTestSuite(TestCasilleroPuerta.class);
		suite.addTestSuite(TestObstaculo.class);
		suite.addTestSuite(TestEnemigo.class);
		suite.addTestSuite(TestTorre.class);
		suite.addTestSuite(TestTienda.class);
		return suite;
		}

}





