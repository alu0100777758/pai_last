package es.ull.etsii.pai.practicafinal;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.ArrayList;

public class AudioManager {
	public static final int MAX_CONCURRENT_SOUNDS = 15;
	private URL res = getClass().getResource("/sounds/rocketShot.wav");
	private static ArrayList<AudioClip> clips = new ArrayList<AudioClip>();	//Truco sucio para engañar al planificador
	private static ArrayList<AudioClip> loops = new ArrayList<AudioClip>();
	public static void startAudio(String name) {
		AudioClip audio = Applet.newAudioClip( AudioManager.class.getResource("/sounds/"+name));
		getClips().add(audio);
		audio.play();
		if(getClips().size() >= 2*MAX_CONCURRENT_SOUNDS){
			for(int i = 0 ; i<MAX_CONCURRENT_SOUNDS; i++)
				getClips().remove(0);
		}
	}
	
	public static ArrayList<AudioClip> getLoops() {
		return loops;
	}

	public static void setLoops(ArrayList<AudioClip> loops) {
		AudioManager.loops = loops;
	}

	public static void reproduceAudio(String name) {
		if(name.length()>0){
		AudioClip audio = Applet.newAudioClip( AudioManager.class.getResource("/sounds/"+name));
		audio.loop();
		getLoops().add(audio);
		}
	}
	public static void stopAudio(String name) {
		AudioClip audio = Applet.newAudioClip( AudioManager.class.getResource("/sounds/"+name));
		audio.stop();
		getLoops().remove(audio);
	}

	public URL getRes() {
		return res;
	}

	public void setRes(URL res) {
		this.res = res;
	}

	public static ArrayList<AudioClip> getClips() {
		return clips;
	}

	public void setClips(ArrayList<AudioClip> clips) {
		this.clips = clips;
	}
	public static void stopAll() {
		for(AudioClip audio : getLoops())
			audio.stop();
		
	}
	
	
}