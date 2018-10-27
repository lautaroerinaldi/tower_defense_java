package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vista.VistaPresentacion;

public class ControladorBoton implements ActionListener{

	private boolean comenzarNivel;
	private VistaPresentacion vistaPresentacion;

	public ControladorBoton(){
		this.comenzarNivel = false;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		this.comenzarNivel = true;
	}
	
	public void nivelComenzado(){
		this.comenzarNivel = false;
		this.vistaPresentacion.desactivarBoton();
	}
	
	public boolean getComenzarNivel(){
		return this.comenzarNivel;
	}

	public void nivelTerminado() {
		this.vistaPresentacion.activarBoton();
	}

	public void setVistaPresentacion(VistaPresentacion vistaPresentacion) {
		this.vistaPresentacion = vistaPresentacion;
	}

}
