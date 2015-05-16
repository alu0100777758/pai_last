package es.ull.etsii.pai.practicafinal;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.ArrayList;

public class AudioManager {
	private URL res = getClass().getResource("rocketShot.wav");
	private ArrayList<AudioClip> clips = new ArrayList<AudioClip>();	//Truco sucio para engañar al planificador
	
	public void startAudio() {
		AudioClip audio = Applet.newAudioClip(res);
		getClips().add(audio);
		audio.play();
		
	}

	public URL getRes() {
		return res;
	}

	public void setRes(URL res) {
		this.res = res;
	}

	public ArrayList<AudioClip> getClips() {
		return clips;
	}

	public void setClips(ArrayList<AudioClip> clips) {
		this.clips = clips;
	}
	
	
}