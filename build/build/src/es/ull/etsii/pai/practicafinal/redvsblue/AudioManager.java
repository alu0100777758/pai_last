package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import javafx.scene.media.AudioClip;
import java.util.ArrayList;

public class AudioManager {
	public static final String SOUNDS_FOLDER = "/sounds/";
	public static final int MAX_CONCURRENT_SOUNDS = 15;							// Numero maximo de sonidos reproduciendo a la vez.
	private static ArrayList<AudioClip> clips = new ArrayList<AudioClip>();		// Truco sucio para engañar al planificador.
	private static ArrayList<AudioClip> loops = new ArrayList<AudioClip>();		// Lista de clips ejecutandose indefinidamente.
	private static boolean sfx = true;											// Determina si estan activados los efectos de sonido.
	private static boolean music = true;										// Determina si esta activada la musica TODO cambiar el comportamiento por defecto 
																				//	(por defecto desactivada por comodidad durante desarrollo)
	/**
	 * Comienza la reproduccion del audio indicado por parametro.
	 * @param name
	 */
	public static void startAudio(String name) {
		if (!isSfx())
			return;
		
		if (name.length() > 0) {
//			AudioClip audio = Applet.newAudioClip(AudioManager.class
//					.getResource("/sounds/" + name));
			AudioClip audio = new AudioClip(AudioManager.class.getResource("/sounds/" + name).toString());
//			getClips().add(audio);
			audio.play();
			if (getClips().size() >= 2 * MAX_CONCURRENT_SOUNDS) {
				for (int i = 0; i < MAX_CONCURRENT_SOUNDS; i++)
					getClips().get(0).stop();
					getClips().remove(0);
			}
		}
	}

	/**
	 * Reproduce de forma ciclica el clip con nombre indicado.
	 * @param name
	 */
	public static void reproduceAudio(String name) {
		if (!isMusic()) 
			return;
			
		if (name.length() > 0) {
			AudioClip audio = new AudioClip(AudioManager.class
					.getResource(SOUNDS_FOLDER + name).toString());
			audio.play();
			getLoops().add(audio);
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
	 * Para un determinado clip.
	 * @param name
	 */
	public static void stopAudio(String name) {
		AudioClip audio = new AudioClip(AudioManager.class
				.getResource(SOUNDS_FOLDER + name).toString());
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
	
	public static boolean isSfx() {
		return sfx;
	}

	public static void setSfx(boolean sfx) {
		AudioManager.sfx = sfx;
	}

	public static boolean isMusic() {
		return music;
	}

	public static void setMusic(boolean music) {
		AudioManager.music = music;
		if (!music)
			stopAll();
	}

	

}