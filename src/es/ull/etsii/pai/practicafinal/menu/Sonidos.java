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
    }
}