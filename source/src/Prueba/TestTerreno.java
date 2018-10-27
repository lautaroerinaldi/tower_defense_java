package Prueba;


import java.util.Collection;
import java.util.Iterator;

import Modelo.Enemigo.*;
import Modelo.Excepciones.EnemigoNoEstaEnTerrenoException;
import Modelo.Excepciones.PosicionInvalidaException;
import Modelo.Juego.ListaEnemigos;
import Modelo.Obstaculo.Arena;
import Modelo.Obstaculo.Obstaculo;
import Modelo.Terreno.*;
import Modelo.Torre.Torre;
import Modelo.Torre.TorreDorada;

import junit.framework.TestCase;


public class TestTerreno extends TestCase {

	Terreno terreno;
	public void setUp(){
		terreno=Terreno.getInstancia();

	}
	
	public void testGetInstancia(){
	    assertEquals(terreno,Terreno.getInstancia());
		
	}
	
	public void testGetCasillero(){
		assertEquals(terreno.getCasillero(new Punto(0,0)).getPosicion().getX(),0);
		assertEquals(terreno.getCasillero(new Punto(0,0)).getPosicion().getY(),0);		
		assertEquals(terreno.getCasillero(new Punto(5,5)).getPosicion().getX(),5);
		assertEquals(terreno.getCasillero(new Punto(5,5)).getPosicion().getY(),5);
		
		try{
			terreno.getCasillero(new Punto(20,20));
			fail("no lanzo exepcion al pedir casillero fuera de rango");
		}catch(PosicionInvalidaException e){
			assertTrue(true);
		}
	}
	
    public void testGetElementos(){
		try{
			terreno.cargarCamino(1);
			}catch(Exception e){}

		Enemigo enemigo=new Hormiga();
		Enemigo enemigo2=new Hormiga();
	    terreno.agregarEnemigo(enemigo,new Punto(0,1));
	    terreno.agregarEnemigo(enemigo2,new Punto(0,2));

		Torre torre=new TorreDorada();
		Torre torre2=new TorreDorada();
		terreno.agregarTorre(torre,new Punto(16,2));
		terreno.agregarTorre(torre2,new Punto(8,3));

		Obstaculo obstaculo=new Arena();
		Obstaculo obstaculo2=new Arena();
		terreno.agregarObstaculo(obstaculo,new Punto(0,2));
		terreno.agregarObstaculo(obstaculo2,new Punto(0,3));

        Collection elementos=terreno.getElementos();
        assertEquals(6,elementos.size());
    }

	public void testEntrarAlCamino(){
		try{
		terreno.cargarCamino(1);
		}catch(Exception e){}

		Enemigo enemigo=new Hormiga();
	    terreno.entrarAlCamino(enemigo);

	    Collection<Enemigo> c=(((CasilleroCamino)terreno.getCasillero(new Punto(0,0))).getTodosLosEnemigos());
	    assertTrue(c.contains(enemigo));

		Enemigo enemigo2=new Hormiga();
	    terreno.entrarAlCamino(enemigo2);
	    assertTrue(c.contains(enemigo));
	    assertTrue(c.contains(enemigo2));

		Enemigo enemigo3=new Hormiga();
	    terreno.entrarAlCamino(enemigo3);
	    assertTrue(c.contains(enemigo3));
	    assertTrue(c.contains(enemigo));
	    assertTrue(c.contains(enemigo2));
	}


	public void testListarEnimigosEnRadio(){
		try{
			terreno.cargarCamino(1);
			}catch(Exception e){}

	    Torre torre=new TorreDorada();
	    terreno.agregarTorre(torre,new Punto(1,1));
		Collection<Enemigo> c=terreno.listarEnemigosEnRadio(torre);

		c=terreno.listarEnemigosEnRadio(torre);
		assertEquals(c.size(),0);

		terreno.agregarEnemigo(new Hormiga(),new Punto(0,2));
		terreno.agregarEnemigo(new Hormiga(),new Punto(0,2));
		terreno.agregarEnemigo(new Hormiga(),new Punto(0,2));
		terreno.agregarEnemigo(new Hormiga(),new Punto(0,2));

		c=terreno.listarEnemigosEnRadio(torre);
		assertEquals(c.size(),4);

	    Torre torre2=new TorreDorada();
	    terreno.agregarTorre(torre,new Punto(18,18));
    	c=terreno.listarEnemigosEnRadio(torre);
    	assertEquals(c.size(),0);

	}

	public void testAgregarTorre(){
		try{
			terreno.cargarCamino(1);
			}catch(Exception e){}

		Torre torre=new TorreDorada();
		Torre torre2=new TorreDorada();
		Punto punto= new Punto(1,2);
		terreno.agregarTorre(torre,punto);
		CasilleroTerreno casillero=(CasilleroTerreno)terreno.getCasillero(punto);
		assertSame(torre,casillero.getTorre());

		try{
			terreno.agregarTorre(torre2, punto);
			fail("no lanzo exeption al agregar en el mismo lugar 2 torres");
		}catch(PosicionInvalidaException pie){
			assertTrue(true);
		}
		try{
			terreno.agregarTorre(torre2,new Punto(0,1));
			fail("no lanzo exeption al agregar en el camino la torre");
		}catch(PosicionInvalidaException pie){
			assertTrue(true);
		}
		try{
			terreno.agregarTorre(torre2,new Punto(110,110));
			fail("no lanzo exeption al agregar la torre fuera de el rango de casilleros");
		}catch(PosicionInvalidaException pie){
			assertTrue(true);
		}

	}

	public void testSacarTorre(){
		try{
			terreno.cargarCamino(1);
			}catch(Exception e){}

		Torre torre=new TorreDorada();
		Punto punto= new Punto(8,2);
		terreno.agregarTorre(torre,punto);
		terreno.sacarTorre(torre);
		CasilleroTerreno casillero=(CasilleroTerreno)terreno.getCasillero(punto);
		assertNull(casillero.getTorre());

		Torre torre2=new TorreDorada();
		try{
			terreno.sacarTorre(torre2);
			fail("error al sacar una torre q no esta en el terreno");
		}catch(PosicionInvalidaException pie){
			assertTrue(true);
		}

	}

	public void testAgregarObstaculo(){
		try{
			terreno.cargarCamino(1);
			}catch(Exception e){}

		Punto punto=new Punto(0,1);
		Obstaculo obstaculo=new Arena();
	    terreno.agregarObstaculo(obstaculo, punto);
        CasilleroCamino casillero=(CasilleroCamino)terreno.getCasillero(punto);
        assertSame(casillero.getObstaculo(),obstaculo);

        Obstaculo obstaculo2=new Arena();
        try{
        	terreno.agregarObstaculo(obstaculo2, punto);
        	fail("pude agregar 2 obstaculos en el mismo casillero");
        }catch(PosicionInvalidaException pie){
        	assertTrue(true);
        }
        try{
        	terreno.agregarObstaculo(obstaculo2,new Punto(8,1));
        	fail("no lanzo exepcion al agregar obstaculo fuera del camino");
        }catch(PosicionInvalidaException pie){
        	assertTrue(true);
        }
        try{
        	terreno.agregarObstaculo(obstaculo2,new Punto(5,110));
        	fail("pude agregar un obstaculo fuera de los rangos del terreno");
        }catch(PosicionInvalidaException pie){
        	assertTrue(true);
        }

	}

	public void testSacarObstaculo(){
		try{
			terreno.cargarCamino(1);
			}catch(Exception e){}

		Punto punto=new Punto(0,3);
		Obstaculo obstaculo=new Arena();
		terreno.agregarObstaculo(obstaculo, punto);
		terreno.sacarObstaculo(obstaculo);
		CasilleroCamino casillero=(CasilleroCamino)terreno.getCasillero(punto);
		assertNull(casillero.getObstaculo());

		Obstaculo obstaculo2=new Arena();
		try{
			terreno.sacarObstaculo(obstaculo2);
			fail("no lanzo exception al intentar sacar un obstaculo q no esta en el camino");
		}catch(PosicionInvalidaException pie){
			assertTrue(true);
		}

	}

    public void testAgregarEnemigo(){
		try{
			terreno.cargarCamino(1);
			}catch(Exception e){}

		Punto punto=new Punto(0,1);
		Enemigo enemigo=new Hormiga();
	    terreno.agregarEnemigo(enemigo, punto);
        CasilleroCamino casillero=(CasilleroCamino)terreno.getCasillero(punto);
        ListaEnemigos lista=casillero.getTodosLosEnemigos();
        assertTrue(lista.contains(enemigo));

        Enemigo enemigo2=new Hormiga();
        try{
        	terreno.agregarEnemigo(enemigo2,new Punto(8,1));
        	fail("no lanzo exepcion al agregar enemigo fuera del camino");
        }catch(PosicionInvalidaException pie){
        	assertTrue(true);
        }
        try{
        	terreno.agregarEnemigo(enemigo2,new Punto(5,110));
        	fail("pude agregar un obstaculo fuera de los rangos del terreno");
        }catch(PosicionInvalidaException pie){
        	assertTrue(true);
        }
    }

    
	public void testSacarEnemigo(){
		try{
			terreno.cargarCamino(1);
			}catch(Exception e){}

		Enemigo enemigo=new Hormiga();
		terreno.entrarAlCamino(enemigo);
		terreno.sacarEnemigo(enemigo);
		CasilleroCamino casillero=(CasilleroCamino)terreno.getCasillero(new Punto(0,0));
		if (casillero.getTodosLosEnemigos().contains(enemigo)){
			fail("no elimino enemigo");
		}else assertTrue(true);

		try{
			terreno.sacarEnemigo(enemigo);
			fail("no lanzo exepcion al sacar enemigo q no esta en el terreno");
		}catch(EnemigoNoEstaEnTerrenoException eneete){
            assertTrue(true);
		}

	}

    public void testIterador(){
    	Casillero casillero=new CasilleroCamino();
    	Iterator it= terreno.iterator();
    	while(it.hasNext()){
    		try{
    		casillero=(Casillero)it.next();
    		}catch(Exception e){
    			fail("lanzo exception al recorrer terreno");
    		}
    	}
    	if((casillero.getPosicion().getX()==79) && (casillero.getPosicion().getY()==79))
    		assertTrue(true);
    }


    public void testCargarCamino(){
    	try{
    		terreno.cargarCamino(1);
    	}catch(Exception e){
    		fail("lanzoexepcion al cargar camino de xml");
    	}
        if(terreno.getCasillero(new Punto(0,0)) instanceof CasilleroPuerta){
        	assertTrue(true);
        }else{
        	fail("el 0,0 no era puerta");
        }
        if(terreno.getCasillero(new Punto(15,19)) instanceof CasilleroPuerta){
        	assertTrue(true);
        }else{
        	fail("el 15,19 no era puerta");
        }
        if(terreno.getCasillero(new Punto(0,3)) instanceof CasilleroCamino){
        	assertTrue(true);
        }else{
        	fail("el 0,3 no era camino");
        }
        if(terreno.getCasillero(new Punto(9,0)) instanceof CasilleroTerreno){
        	assertTrue(true);
        }else{
        	fail("el 9,0 no era terreno");
        }

    }



}


