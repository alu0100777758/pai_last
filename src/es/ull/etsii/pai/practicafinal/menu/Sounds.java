package es.ull.etsii.pai.practicafinal.menu;

import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;

public class Sounds extends Thread {
	public static synchronized void playSound(String music) {
		  new Thread(new Runnable() {

			// The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		    	File file = new File(music); 
		      try {
		    	    
		        Clip sound = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
		        sound.open(AudioSystem.getAudioInputStream(file));
		        sound.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
}