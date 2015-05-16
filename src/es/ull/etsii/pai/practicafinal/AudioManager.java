package es.ull.etsii.pai.practicafinal;

import java.applet.Applet;
import java.net.URL;

public class AudioManager {
	private URL res = getClass().getResource("rocketShot.wav");
	
	public void startAudio() {
		Applet.newAudioClip(res).play();
	}
}