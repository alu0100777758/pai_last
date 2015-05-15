package es.ull.etsii.pai.practicafinal.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 */
public class Sonidos extends Thread{
//    String mensaje;
    String sonido;
    int timeRep=0;
    boolean stop=false;
    Player apl;
	private String mensaje;
    
    
    public Sonidos(){
//        mensaje="Hola";
    	File fichero = new File("datos.txt"); 
        System.out.println(fichero.getAbsolutePath());  
        try {
			apl = new Player(new FileInputStream("sonido.mp3"));
			fichero = new File("datos.txt"); 
	        System.out.println(fichero.getAbsolutePath());  
		} catch (FileNotFoundException | JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fichero = new File("datos.txt"); 
	        System.out.println(fichero.getAbsolutePath());  
		}
    }
    
    public Sonidos(String sonido){
        this.sonido=sonido;
        File fichero = new File("datos.txt"); 
        System.out.println(fichero.getAbsolutePath());
        try {
			apl = new Player(new FileInputStream("Resources/Backgrounds/sonido.mp3"));
		} catch (FileNotFoundException | JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public Sonidos(String sonido,boolean stop){
        this.sonido=sonido;
        stop=false;
    }
    public void run(){
    	try {
			apl.play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        repSonido(sonido);
//        System.out.println("Este proceso ha terminado:"+this.getName());
    }

    public void setMensaje(String msj){
        this.mensaje = msj;
    }
    
    public void repSonido(String sonido){
    	try {
			apl = new Player(new FileInputStream("sonido.mp3"));
		} catch (FileNotFoundException | JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		apl.play();
//		Thread.sleep(10);
//		apl.close();
    	
    	
    	
//        SourceDataLine soundLine = null;
//        int BUFFER_SIZE = 64*1024;  // 64 KB
//        // Set up an audio input stream piped from the sound file.
//        try {
//           File soundFile = new File(sonido);
//           AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
//           AudioFormat audioFormat = audioInputStream.getFormat();
//           DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
//           soundLine = (SourceDataLine) AudioSystem.getLine(info);
//           soundLine.open(audioFormat);
//           soundLine.start();
//           int nBytesRead = 0;
//           byte[] sampledData = new byte[BUFFER_SIZE];
//           while (nBytesRead != -1) {
//              nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
//              if (nBytesRead >= 0) {
//                 // Writes audio data to the mixer via this source data line.
//                 soundLine.write(sampledData, 0, nBytesRead);
//              }
//           }
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
//            System.err.println("Error: "+ex.getMessage());
//        } finally {
//           soundLine.drain();
//           soundLine.close();
//        }
    }
}