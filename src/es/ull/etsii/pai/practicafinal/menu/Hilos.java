package es.ull.etsii.pai.practicafinal.menu;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JApplet;

@SuppressWarnings("serial")
public class Hilos extends JApplet implements Runnable {
   
    PantallaInicial pInicial;		// Pantalla inicial del juego.
//    PantallaRecord pEditor;		// Pantalla editor del juego.
    
    @Override
    public void init() {
        setSize(500, 400);
        pInicial = new PantallaInicial(); 
        
        this.getContentPane().add(pInicial);	// Hilo del gif
        Thread hiloPInicial = new Thread(this);
        System.out.println("Creando hilos");
        hiloPInicial.start();
    }
    
//    public void creditos() {
//    	setSize(500, 500);
//        pEditor = new PantallaRecord(); 
//        
//        this.getContentPane().add(pEditor);	// Hilo del editor
//        Thread HiloRecord = new Thread(this);
//        HiloRecord.start();
//    }
    
    
    void sleep(int n)
    {
        try {
            Thread.sleep(n);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    @Override
    public void run() {
        repaint();
    }
    
}