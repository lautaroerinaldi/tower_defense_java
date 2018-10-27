package Modelo.Juego;

import java.io.*;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Modelo.Enemigo.*;
import Modelo.Excepciones.*;
import Modelo.Terreno.*;
import Modelo.Torre.*;
import Modelo.Obstaculo.*;

public class Juego implements Guardable{
	
	private static final int cantidadDeNiveles = 7;
	private int nivel;
	private static Juego instancia = null;
	private Jugador jugador;
	private Tienda tienda;
	private ListaActuables listaActuables;
	private Terreno terreno;
	private ListaActuables listaActuablesAEliminar;
	private ListaTorres listaTorresAgregar;
		
	/**
	 * Constructor, inicializa todos los atributos en un estado válido.
	 * 
	 * @throws ClassNotFoundException
	 */
	
	private Juego() {
		this.nivel = 1;
		this.terreno = Terreno.getInstancia();
		this.tienda = Tienda.getInstancia();
		this.jugador = new Jugador();
		this.listaActuables = new ListaActuables();
		this.listaActuablesAEliminar = new ListaActuables();
		this.listaTorresAgregar = new ListaTorres();
	}
	
	/**
	 * Singleton
	 */
	
	public static Juego getInstancia(){
		if (instancia == null)
			instancia = new Juego();
		return instancia;
	}
	
	public static int getCantidadNiveles() {
		return cantidadDeNiveles;
	}
	
	public int getNivel() {
		return this.nivel;
	}

	public void setNivel(int nivel) {
    	this.nivel = nivel;
	}
	
	public Jugador getJugador(){
		return this.jugador;
	}
	
    public void setJugador(Jugador jugador) {
    	this.jugador = jugador;
	}
	
	public Terreno getTerreno(){
		return this.terreno;
	}
		
	public ListaActuables getListaActuables(){
		return this.listaActuables;
	}
		
	public ListaTorres getListaTorresAgregar(){
		return this.listaTorresAgregar;
	}
	
	public ListaActuables getListaActuablesAEliminar(){
		return this.listaActuablesAEliminar;
	}

	public void incrementarNivel() {
		this.nivel++;
	}
		
	/**
	 * Función que carga un nivel del juego desde un archivo.
	 * 
	 * @throws NivelInvalidoException
	 */
	
	public void cargarNivel() {
		this.terreno.cargarCamino(this.nivel);
		try {
			cargarEnemigos();
		}
		catch (Exception e){
			throw new NivelInvalidoException(this.nivel);
		}
	}
	
	public void cerrarNivel(){
		//Elimino todas las torres del juego
		this.listaActuables.clear();
		//Le digo al jugador que guarde las cosas en su mochila
		this.jugador.guardarObjetos();
	}
	
	/**
	 * Función que carga la lista de enemigos a actuar en ese nivel.
	 * 
	 * @throws DocumentException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */

	private void cargarEnemigos() throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		int posicion = 0;
		SAXReader reader = new SAXReader();
		Document document = reader.read("utils/Niveles/nivel" + this.nivel + ".xml");
        Element elemEnemigos = document.getRootElement();
        Iterator it = elemEnemigos.elementIterator();
        while(it.hasNext()){
            Element elemEnemigo = (Element)it.next();
            Class enemigoACrear = Class.forName("Modelo.Enemigo." + elemEnemigo.getName());
            Enemigo enemigo = (Enemigo)enemigoACrear.newInstance();
            setearEnemigo(enemigo, posicion);
    		this.listaActuables.add(enemigo);
    		posicion++;
        }
	}
	
	/**
	 * Función que coloca al enemigo en un estado válido para el juego
	 * agregandolo a la puerta de entrada.
	 * 
	 * @param enemigo
	 * @param posicionEnLista
	 */
	
	private void setearEnemigo(Enemigo enemigo, int posicionEnLista) {
		CasilleroPuerta entrada = (CasilleroPuerta)this.terreno.getPuertaDeEntrada();
		entrada.agregarEnemigo(enemigo);
		enemigo.setCasillero(entrada);
		enemigo.setTurnosRestantesParaActuar(posicionEnLista * 20 + 1);
	}
	
	/**
	 * Función que carga la lista de torres a actuar en ese nivel.
	 *
	 */
	
	public void cargarTorres() {
		if (!this.listaTorresAgregar.isEmpty()){
			this.listaActuables.addAll(this.listaTorresAgregar);
			this.listaTorresAgregar.clear();
		}
	}
	
	/**
	 * Función que agrega una torre en lista temporal para luego ser agregada al juego.
	 * @param torre	torre a agregar
	 */
	
	public void agregarTorre(Torre torre) {
		this.listaTorresAgregar.add(torre);
	}

	/**
	 * Función que se ejecuta cuando un enemigo llega a la puerta de salida.
	 *  
	 * @param enemigo
	 */
	
	public void pasoEnemigo(Enemigo enemigo){
		agregarListaActuablesAEliminar(enemigo);
		if (jugador.restarVida() == 0)
			gameOver();
	}
	
	/**
	 * Función que se ejecuta cuando una torre mata a un enemigo.
	 * 
	 * @param enemigo
	 */
	
	public void murioEnemigo(Enemigo enemigo){
		agregarListaActuablesAEliminar(enemigo);
		jugador.modificarDinero(2);
		int sumaPuntaje = enemigo.getResistencia()+ enemigo.getVelocidad();
		jugador.sumarPuntaje(sumaPuntaje);
	}
	
	/**
	 * Función que recibe una torre o enemigo y lo almacena en una lista temporal para 
	 * luego ser eliminada/o del juego.
	 * @param actuable
	 */
	
	public void agregarListaActuablesAEliminar(Actuable actuable) {
		this.listaActuablesAEliminar.add(actuable);
	}
	
	/**
	 * Función que elimina los enemigos muertos o las torres vendidas para que no actuen mas.
	 *
	 */
	public void eliminarActuables(){
		if (!this.listaActuablesAEliminar.isEmpty()){
			this.listaActuables.removeAll(this.listaActuablesAEliminar);
			this.listaActuablesAEliminar.clear();
		}
	}
	 
	/**
	 * Función que se ejecuta cuando ganó o perdió el jugador.
	 *
	 * @throws FinalJuegoException
	 */
	
	public void gameOver(){
		throw new FinalJuegoException();
	}
    
	/**
	 * Función que guarda el estado del juego en un XML.
	 */
    
    public void salvar(String ruta) throws IOException{
        Document doc = DocumentHelper.createDocument();
        doc.add(this.guardar());
        FileWriter writer = new FileWriter(ruta);
        doc.write(writer);
        writer.close();
    }
    
    /**
     * Función que va generando el XML con el estado de todos los objetos del juego.
     */
    
    public Element guardar() {
        Element elemJuego = DocumentHelper.createElement("Juego");
        Element elemNivel = DocumentHelper.createElement("Nivel");
        elemNivel.addAttribute("Valor", String.valueOf(this.nivel));
        elemJuego.add(elemNivel);
        elemJuego.add(this.jugador.guardar());
        elemJuego.add(this.terreno.guardar());
        return elemJuego;
    }
    
    /**
     * Función que recupera el estado del juego.
     * @param archivo		Ruta del archivo guardado
     * @return
     * @throws IOException
     */
    
    public static Juego recuperar(String archivo) throws IOException{
        Juego juego = Juego.getInstancia();
        SAXReader reader = new SAXReader();
        try{
	        Document document = reader.read(archivo);
	        Element elemJuego = document.getRootElement();
			juego = recuperar(elemJuego, juego);
	    }catch(DocumentException ex){
            throw new IOException();
        }
        return juego;
    }

	/**
     * Función que va parseando el XML y dejando el juego en un estado válido. 
     * @param elemJuego	Elemento raíz
     * @param juego		Juego
     * @return			El juego recuperado
     * @throws ClassNotFoundException 
     * @throws ClassNotFoundException 
     */
    public static Juego recuperar(Element elemJuego, Juego juego){
        Iterator it = elemJuego.elementIterator();
        while(it.hasNext()){
            Element elem = (Element)it.next();
            
            if (elem.getName() == "Nivel"){
            	juego.setNivel(recuperarNivel(elem));        	
            	juego.getTerreno().cargarCamino(juego.getNivel());
            }
            
            if (elem.getName() == "Jugador")
            	juego.setJugador(Jugador.recuperar(elem));
            
            if(elem.getName() == "Terreno")
            	juego.recuperarTerreno(elem, juego);
        }
        return juego;
    }

    
	private void recuperarTerreno(Element elemTerreno, Juego juego) {
		Iterator it = elemTerreno.elementIterator();
        while(it.hasNext()){
            Element elem = (Element)it.next();
            if (elem.getName() == "TorreBlanca")
            	juego.agregarTorreRecuperada(TorreBlanca.recuperar(elem), juego);
            
            if (elem.getName() == "TorrePlateada")
            	juego.agregarTorreRecuperada(TorrePlateada.recuperar(elem), juego);
            
            if (elem.getName() == "TorreDorada")
            	juego.agregarTorreRecuperada(TorreDorada.recuperar(elem), juego);
            
            if (elem.getName() == "Hormiga")   	
            	juego.agregarEnemigoRecuperado(Hormiga.recuperar(elem), juego);
            
            if (elem.getName() == "Arania")
            	juego.agregarEnemigoRecuperado(Arania.recuperar(elem), juego);
            
            if (elem.getName() == "Mosca")
            	juego.agregarEnemigoRecuperado(Mosca.recuperar(elem), juego);
            	
            if (elem.getName() == "Cucaracha")
            	juego.agregarEnemigoRecuperado(Cucaracha.recuperar(elem), juego);
            
            if (elem.getName() == "Abeja")
            	juego.agregarEnemigoRecuperado(Abeja.recuperar(elem), juego);
            
            if (elem.getName() == "Mosquito")
            	juego.agregarEnemigoRecuperado(Mosquito.recuperar(elem), juego);
            
            if (elem.getName() == "Pulga")
            	juego.agregarEnemigoRecuperado(Pulga.recuperar(elem), juego);
            
            if (elem.getName() == "Arena")
            	juego.agregarObstaculoRecuperado(Arena.recuperar(elem), juego);
            
            if (elem.getName() == "Pegote")
            	juego.agregarObstaculoRecuperado(Pegote.recuperar(elem), juego);
        }   
	}

	private void agregarObstaculoRecuperado(Obstaculo obstaculo, Juego juego) {
		juego.getJugador().agregarObjeto(obstaculo, obstaculo.getPosicion());
	}

	private void agregarTorreRecuperada(Torre torre, Juego juego) {
		juego.getJugador().agregarObjeto(torre, torre.getPosicion());
	}
	
	private void agregarEnemigoRecuperado(Enemigo enemigo, Juego juego){
		juego.getListaActuables().add(enemigo);
		juego.getTerreno().agregarEnemigo(enemigo, enemigo.getPosicionRecuperada());		
	}

	private static int recuperarNivel(Element elem) {
    	String nivel = elem.attributeValue("Valor");
        return Integer.parseInt(nivel);
	}

}
