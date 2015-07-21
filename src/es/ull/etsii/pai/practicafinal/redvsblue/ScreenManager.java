package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Dimension;

public class ScreenManager {
	public static int WINDOW_WIDTH = 1200;						// Ancho de la ventana.
	public static int WINDOW_HEIGHT = 800;
	private static ScreenManager instance = null;
	private double rate_x = 1;
	private double rate_y = 1;
	public static  ScreenManager getInstance(){
		if(instance == null)
			instance = new ScreenManager();
		return instance;
	}// Alto de la ventana.
	public int getWindWidth() {
		return WINDOW_WIDTH;
	}
	public int getWindHeight() {
		return WINDOW_HEIGHT;
	}
	public double getRate_x() {
		return rate_x;
	}
	public void setRate_x(double rate_x) {
		this.rate_x = rate_x;
	}
	public double getRate_y() {
		return rate_y;
	}
	public void setRate_y(double rate_y) {
		this.rate_y = rate_y;
	}
	public void reset() {
		setRate_x(1);
		setRate_y(1);
	}
	public Dimension getScreenDimensions() {
		return new Dimension(getWindWidth(), getWindHeight());
	} 
	public void updateRate(double width, double height){
		setRate_x(width/getWindWidth());
		setRate_y(height/getWindHeight());
	}
	public int getCurrentWidth(){
		return (int)(double)(getWindWidth()*getRate_x());
	}
	public int getCurrentHeight(){
		return (int)(double)(getWindHeight()*getRate_y());
	}
	
}
