package Modelo.Terreno;

public class Punto implements Comparable {
	private int x;
	private int y;
	
	public Punto(int x,int y){
		this.x=x;
		this.y=y;
	}
    public int getX(){
    	return this.x;
    }
    public int getY(){
    	return this.y;
    }
    public void setX(int x){
    	this.x=x;
    }
    public void setY(int y){
    	this.y=y;
    }
	
	
	public boolean igual(Punto punto){
		if ( (this.x == punto.x) && (this.y == punto.y))
			return true;
		else
			return false;
		
	}
	
	public int compareTo(Object o){
		Punto punto = (Punto)o;
		if (this.igual(punto))
			return 0;
		else
			return -1;
	}
	
}
