package es.ull.etsii.pai.practicafinal;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.ArrayList;

public class AudioManager {
	public static final int MAX_CONCURRENT_SOUNDS = 15;
	private URL res = getClass().getResource("/sounds/rocketShot.wav");
	private static ArrayList<AudioClip> clips = new ArrayList<AudioClip>();	//Truco sucio para engañar al planificador
	
	public static void startAudio(String name) {
		AudioClip audio = Applet.newAudioClip( AudioManager.class.getResource("/sounds/"+name));
		getClips().add(audio);
		audio.play();
		if(getClips().size() >= 2*MAX_CONCURRENT_SOUNDS){
			for(int i = 0 ; i<MAX_CONCURRENT_SOUNDS; i++)
				getClips().remove(0);
		}
		
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
	
	
}