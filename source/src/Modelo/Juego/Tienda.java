package Modelo.Juego;
import Modelo.Excepciones.IndiceInvalidoException;
import Modelo.Excepciones.SinDineroException;
import Modelo.Obstaculo.Arena;
import Modelo.Obstaculo.Pegote;
import Modelo.Torre.TorreBlanca;
import Modelo.Torre.TorreDorada;
import Modelo.Torre.TorrePlateada;

public class Tienda {

	private static Tienda instancia;
	private Stock lista;
	
	/**
	 * Inicializa los productos en stock que tiene la tienda
	 */
	private Tienda(){
		lista = new Stock();
		lista.add(new Pegote());
		lista.add(new Arena());
		lista.add(new TorreDorada());
		lista.add(new TorrePlateada());
		lista.add(new TorreBlanca());
	}
	
	public static Tienda getInstancia(){
		if (instancia == null)
			instancia = new Tienda();
		return instancia;
	}
	
	/**
	 * Si el cliente tiene dinero suficiente como para comprar el negociable
	 * que está en el índice de la lista, le devuelve una nueva instancia del
	 * mismo.
	 * @param indice Elemento de la lista de Stock que el cliente quiere comprar
	 * @param cliente 
	 * @throws SinDineroException si el cliente no tiene suficiente dinero
	 * para comprar el negociable
	 * @throws IndiceInvalidoException si indice no es un elemento válido del Stock
	 * @return Un nuevo Negociable
	 */
	public Negociable vender(int indice, Cliente cliente){
		if ((indice >=0) && (indice<lista.size())){
			Negociable vendido= lista.getNegociable(indice); 
			if (this.lista.getNegociable(indice).getCosto() <= cliente.getDinero()){
				try {
					lista.set(indice, vendido.getClass().newInstance());
				}
				catch (InstantiationException e) {
				}
				catch (IllegalAccessException e) {
				}
				cliente.modificarDinero(vendido.getCosto()* (-1));
			}
			else
				throw new SinDineroException();
			return vendido;
		}
		else
			throw new IndiceInvalidoException();
		
	}
	/**
	 * La tienda le compra al cliente un negociable, y le da la mitad del dinero
	 * que cuesta el mismo
	 * @param negociable Negociable que se quiere vender a la tienda
	 * @param cliente Cliente a quien se le pagará por el negociable 
	 */
	public void comprar (Negociable negociable, Cliente cliente){
		int precioObtenido= negociable.getCosto()/2;
		cliente.modificarDinero(precioObtenido);
	}
	
	/** 
	 * @return Una lista con todos los negociables que puede vender la tienda
	 */
	public Stock obtenerStock(){
		Stock ProductosEnVenta= new Stock();
		ProductosEnVenta.addAll(lista);
		return ProductosEnVenta;
	}
	
}
