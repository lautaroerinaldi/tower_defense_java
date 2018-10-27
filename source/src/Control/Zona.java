package Control;

public class Zona {
	public Zona(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public boolean contenido(int x, int y){
		if (((x>=this.getX())&&(x<=this.getX()+this.getAncho())) && (((y>=this.getY())&&(y<=this.getY()+this.getAlto()))))
			return true;
		return false;
	}
	private int x;
	private int y;
	private int ancho;
	private int alto;
}
