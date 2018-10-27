package Control;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ControladorSonido {
	
	private Clip sonido;
	private boolean recursosDisponibles;
	
	public void comenzar(){
		try {      
            // Se obtiene un Clip de sonido
            this.sonido = AudioSystem.getClip();
            
            // Se carga con un fichero wav
            this.sonido.open(AudioSystem.getAudioInputStream(new File("utils/sonido/cautious-path.wav")));
            
            // Comienza la reproducción
           	this.sonido.loop(Clip.LOOP_CONTINUOUSLY);
        }
		catch (Exception e) {
			this.recursosDisponibles = false;
		}
	}
	
	public void finalizar(){        
		// Se cierra el clip.
		if(this.recursosDisponibles)
			this.sonido.close();
	}

}
