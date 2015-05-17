package es.ull.etsii.pai.practicafinal;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.ArrayList;

public class AudioManager {
	public static final int MAX_CONCURRENT_SOUNDS = 15;							// Numero maximo de sonidos reproduciendo a la vez.
	private static ArrayList<AudioClip> clips = new ArrayList<AudioClip>();		// Truco sucio para engañar al planificador
	private static ArrayList<AudioClip> loops = new ArrayList<AudioClip>();		// Lista de clips ejecutandose indefinidamente.
	
	/**
	 * Comienza la reproduccion del audio indicado por parametro.
	 * @param name
	 */
	public static void startAudio(String name) {
		AudioClip audio = Applet.newAudioClip( AudioManager.class.getResource("/sounds/"+name));
		getClips().add(audio);
		audio.play();
		if(getClips().size() >= 2*MAX_CONCURRENT_SOUNDS){
			for(int i = 0 ; i<MAX_CONCURRENT_SOUNDS; i++) {
				getClips().get(0).stop();
				getClips().remove(0);
			}
		}
	}
	/**
	 * Pausa todos los clips.
	 */
	public static void stopAll() {
		for(AudioClip audio : getLoops())
			audio.stop();
	}
	/**
	 * Reproduce de forma ciclica el clip con nombre indicado.
	 * @param name
	 */
	public static void reproduceAudio(String name) {
		if(name.length()>0){
			AudioClip audio = Applet.newAudioClip( AudioManager.class.getResource("/sounds/"+name));
			audio.loop();
			getLoops().add(audio);
		}

	}
	/**
	 * Para un determinado clip.
	 * @param name
	 */
	public static void stopAudio(String name) {
		AudioClip audio = Applet.newAudioClip( AudioManager.class.getResource("/sounds/"+name));
		audio.stop();
		getLoops().remove(audio);
	}
	/**
	 * Getters y Setters.
	 * @return
	 */
	public static ArrayList<AudioClip> getClips() {
		return clips;
	}

	public void setClips(ArrayList<AudioClip> clips) {
		this.clips = clips;
	}
	public static ArrayList<AudioClip> getLoops() {
		return loops;
	}

	public static void setLoops(ArrayList<AudioClip> loops) {
		AudioManager.loops = loops;
	}
	
	
}