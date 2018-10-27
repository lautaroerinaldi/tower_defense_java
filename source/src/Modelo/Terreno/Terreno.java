package Modelo.Terreno;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import Modelo.Enemigo.Enemigo;
import Modelo.Excepciones.PosicionInvalidaException;
import Modelo.Juego.Guardable;
import Modelo.Juego.Juego;
import Modelo.Juego.Jugador;
import Modelo.Juego.ListaEnemigos;
import Modelo.Obstaculo.Obstaculo;
import Modelo.Torre.Torre;




public class Terreno implements Guardable{

/* -----------------------------------------------------------------------------------------*/
/* ------------------------------------ATRIBUTOS--------------------------------------------*/
/* -----------------------------------------------------------------------------------------*/

	private static Terreno instancia;
	//constantes de fila y columna
	public final int CANTIDADEFILAS=20;
	public final int CANTIDADECOLUMNAS=20;

	private CasilleroPuerta puertaDeEntrada;

	private Casillero[][] terreno;

/* -----------------------------------------------------------------------------------------*/
/* ------------------------------------METODOS----------------------------------------------*/
/* -----------------------------------------------------------------------------------------*/
	/*
	 * crea un terreno sin camino de dimensiones dadas por CANTIDADEFILAS y CANTIDADECOLUMNAS
	 */
	private Terreno(){
		this.terreno = new Casillero[CANTIDADEFILAS][CANTIDADECOLUMNAS];
		this.puertaDeEntrada=null;
	    for (int i=0;i<this.CANTIDADEFILAS;i++){
	    	for (int j=0;j<this.CANTIDADECOLUMNAS;j++){
	    		this.terreno[i][j]=new CasilleroTerreno(new Punto(i,j));
	    	}
	    }
	}

	public static Terreno getInstancia(){
		if (instancia == null)
			instancia = new Terreno();
		return instancia;
	}

	public Casillero getPuertaDeEntrada(){
		return this.puertaDeEntrada;
	}
	
	public Casillero getCasillero(Punto punto){
		try{
		return this.terreno[punto.getX()][punto.getY()];
		}catch(IndexOutOfBoundsException e){
			throw new PosicionInvalidaException();
		}
	}

	/*
	 * coloca al enemigo en el principio del camino
	 */
	public void entrarAlCamino(Enemigo enemigo){
		this.puertaDeEntrada.agregarEnemigo(enemigo);
	}


	public void cargarCamino(int nivel){
		String rutaTerreno="utils/Niveles/terreno";
        String aux=rutaTerreno+String.valueOf(nivel)+".xml";
		try {
			cargarCamino(aux);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * carga el camino de un archivo XML
	 */
	public void cargarCamino(String archivo)throws IOException{
		this.reiniciarTerreno();
	    SAXReader reader = new SAXReader();
	    try{
	        Document document = reader.read(archivo);
	        Element elemRaiz = document.getRootElement();
	        this.cargarCamino(elemRaiz);
	    }catch(DocumentException ex){
	        throw new IOException();
	    }
	}


	private void cargarCamino(Element elemRaiz) {
		CasilleroCamino caminoAnterior=null;
		Iterator it=elemRaiz.elementIterator();
		CasilleroCamino caminoActual=null;
		while(it.hasNext()){
			Element elem=(Element)it.next();
			Punto posicion=new Punto(Integer.parseInt(elem.attributeValue("X")),Integer.parseInt(elem.attributeValue("Y")));
			//defino el tipo de casillero segun el nombre del elemento
			if(elem.getName()=="Camino")
				caminoActual=new CasilleroCamino(posicion);
			if(elem.getName()=="PuertaEntrada"){
				caminoActual = new CasilleroPuerta(posicion,TipoDePuerta.ENTRADA);
				this.puertaDeEntrada=(CasilleroPuerta)caminoActual;
			}
			if(elem.getName()=="PuertaSalida")
				caminoActual = new CasilleroPuerta(posicion,TipoDePuerta.SALIDA);
			//guardo el casillero
			terreno[posicion.getX()][posicion.getY()]= caminoActual;
			//enlazo con el resto del camino
			if(caminoAnterior !=null)
    			caminoAnterior.setProximaPosicion(caminoActual);
			caminoAnterior=caminoActual;
		}
    }

	/*
	 * saca el camino del terreno
	 */
	private void reiniciarTerreno(){
		for (int i=0;i<CANTIDADEFILAS;i++){
			for (int j=0;j<CANTIDADECOLUMNAS;j++){
					Punto posicion=new Punto(i,j);
					CasilleroTerreno casillero=new CasilleroTerreno(posicion);
					this.terreno[i][j]=casillero;
			}
		}
		this.puertaDeEntrada=null;

	}


	/**
	 * devuelve los enemigos dentro del radio de la torre
	 * @param torre torre que pide los enemigos dentro de su radio
	 * @return ListaEnemigos enemigos dentro del radio de la torre
	 */
	public ListaEnemigos listarEnemigosEnRadio(Torre torre ) {
		ListaEnemigos lista= new ListaEnemigos();
		CasilleroCamino casillero=puertaDeEntrada;
		while (casillero.getProximaPosicion()!=null){
			//pienso la posicion como un vector y saco la distancia entre la posicion
			//de la torre y la del camino
			float numeroAuxiliar=casillero.getPosicion().getX()-torre.getPosicion().getX();
			float numeroAuxiliar2=casillero.getPosicion().getY()-torre.getPosicion().getY();
			numeroAuxiliar*=numeroAuxiliar;
			numeroAuxiliar2*=numeroAuxiliar2;

			numeroAuxiliar+=numeroAuxiliar2;
			numeroAuxiliar= (float)Math.sqrt(numeroAuxiliar);

			if (numeroAuxiliar<=torre.getAlcance()){
				lista.addAll(casillero.getTodosLosEnemigos());
			}
			casillero=casillero.getProximaPosicion();
		}
		return lista;
	}

	/**
	 * agrega una torre en el terrreno
	 * @param torre torre a agregar en el terreno
	 * @param punto posicion donde agregar la torre
	 * @throws PosicionInvalidaException la lanza si la poscicion esta ocupada o fiera
	 * de los limites del terrreno o si es camino
	 */
	public void agregarTorre (Torre torre,Punto punto) {
	    try{
     	     CasilleroTerreno casillero=(CasilleroTerreno)this.getCasillero(punto);
             if(!casillero.tieneTorre()){
	         casillero.setTorre(torre);
	         torre.setPosicion(punto);
	         }else{
		        throw new PosicionInvalidaException();
	         }
	    }catch (IndexOutOfBoundsException ioobe){
		//si la matriz no tiene ese indice es pq el punto no corresponde con
	    //la posicion de un casillero de terreno
		    throw new PosicionInvalidaException();
	    }
	    catch (ClassCastException e){
	    	//si no pude castear es pq el punto no correspondia a una parte de terreno
			throw new PosicionInvalidaException();
	    }
	}

	/**
	 * saca la torre del terrreno
	 * @param torre torre a sacar del terrreno
	 * @throws PosicionInvalidaException la lanza si la torre no esta en el terreno
	 */
	public void sacarTorre (Torre torre) {
		if(torre.getPosicion()!=null){
            CasilleroTerreno casillero=(CasilleroTerreno) this.getCasillero(torre.getPosicion());
            casillero.setTorre(null);
		}else throw new PosicionInvalidaException();
	}

	/**
	 * agrega un obstaculo en el camino
	 * @param obstaculo obstaculo a agregar en el camino
	 * @param punto posicion donde agregar el obstaculo
	 * @throws PosicionInvalidaException
	 */
	public void agregarObstaculo(Obstaculo obstaculo, Punto punto) {
		try{
		    CasilleroCamino casillero=(CasilleroCamino) this.getCasillero(punto);
		    if (!casillero.tieneObstaculo()){
		    	casillero.setObstaculo(obstaculo);
		    	obstaculo.setPosicion(punto);
		    }else{
		    	throw new PosicionInvalidaException();
		    }
		}catch (IndexOutOfBoundsException ioobe){
		//si la matriz no tiene ese indice es pq el punto no corresponde con
	    //la posicion de un casillero del terreno
		    throw new PosicionInvalidaException();
	    }
	    catch (ClassCastException e){
	    	//si no pude castear es pq el punto no correspondia a una parte de camino
			throw new PosicionInvalidaException();
	    }
	}

	/**
	 * saca el obstaculo del camino
	 * @param obstaculo obstaculo a sacar del camino
	 */
	public void sacarObstaculo(Obstaculo obstaculo){
		boolean encontrado=false;
		CasilleroCamino casillero= this.puertaDeEntrada;
	    while (!encontrado && casillero.getProximaPosicion()!=null){
	    	if (casillero.tieneObstaculo()){
	    		if (casillero.getObstaculo()==obstaculo){
	    			casillero.quitarObstaculo();
	    			encontrado=true;
	    		}
	    	}
	    	casillero=casillero.getProximaPosicion();
	    }
	    if (encontrado==false){
	    	throw new PosicionInvalidaException();
	    }

	}

	public void agregarEnemigo(Enemigo enemigo, Punto punto) {
		try{
		    CasilleroCamino casillero=(CasilleroCamino) this.getCasillero(punto);
		    casillero.agregarEnemigo(enemigo);
		}catch (IndexOutOfBoundsException ioobe){
		//si la matriz no tiene ese indice es pq el punto no corresponde con
	    //la posicion de un casillero del terreno
		    throw new PosicionInvalidaException();
	    }
	    catch (ClassCastException e){
	    	//si no pude castear es pq el punto no correspondia a una parte de camino
			throw new PosicionInvalidaException();
	    }
	}


	public void sacarEnemigo(Enemigo enemigo) {
		((CasilleroCamino)enemigo.getCasillero()).quitarEnemigo(enemigo);
	}


	/**
	 *
	 * @return salida del camino
	 */
	public Casillero getCasilleroSalida(){
		CasilleroCamino casilleroSalida = this.puertaDeEntrada;
		while (casilleroSalida.getProximaPosicion()!= null)
			casilleroSalida = casilleroSalida.getProximaPosicion();

		return casilleroSalida;
	}

	/**
	 * Función que guarda el estado del terreno en un XML.
	 */
	public Element guardar() {
		Element elemTerreno = DocumentHelper.createElement("Terreno");
		Collection c=this.getElementos();
		Iterator it=c.iterator();
        while(it.hasNext()){
        	Guardable objGuardable=(Guardable)it.next();
        	elemTerreno.add(objGuardable.guardar());
        }
		return elemTerreno;
	}

	/**
	 *
	 * @return coleccion con todos los elementos q contiene el terreno (Enemigo,Obstaculo,Torre)
	 */
	public Collection getElementos(){
		Collection coleccion=new LinkedList();
		Iterator it=this.iterator();
		while(it.hasNext()){
			Casillero casillero=(Casillero)it.next();
			if(casillero instanceof CasilleroTerreno){
				if(((CasilleroTerreno)casillero).tieneTorre())
				    coleccion.add(((CasilleroTerreno)casillero).getTorre());
			}
			if(casillero instanceof CasilleroCamino || casillero instanceof CasilleroPuerta){
                if(((CasilleroCamino)casillero).tieneObstaculo())
                    coleccion.add(((CasilleroCamino)casillero).getObstaculo());
				coleccion.addAll( ((CasilleroCamino)casillero).getTodosLosEnemigos() );
			}
		}
		return coleccion;

	}


	public Iterator iterator(){
		return new IteradorTerreno(this);
	}


	public class IteradorTerreno implements Iterator{
        private Terreno terreno;
		private  int fila;
        private int columna;
        private Boolean termino;

        public IteradorTerreno(Terreno terreno){
        	this.terreno=terreno;
        	this.fila=0;
        	this.columna=0;
        	this.termino=false;
        }

		public boolean hasNext() {
			return (!this.termino);
		}

		public Object next() {
			if(!this.termino){
				Casillero actual=this.terreno.getCasillero(new Punto(fila,columna));
                if(this.columna<CANTIDADECOLUMNAS-1){
                	this.columna++;
                }else{
                	columna=0;
                	if(this.fila<CANTIDADEFILAS-1) this.fila++;
                	else this.termino=true;
                }
            return actual;
            }
			return null;
		}

		public void remove() {
			// TODO Auto-generated method stub
		}

    }

}








