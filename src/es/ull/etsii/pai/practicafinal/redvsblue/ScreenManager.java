package es.ull.etsii.pai.practicafinal.redvsblue;

import java.awt.Dimension;

public class ScreenManager {
	private int windWidth = 1366;						// Ancho de la ventana.
	private int windHeight = 768;
	private static ScreenManager instance = null;
	private double rate_x = 1;
	private double rate_y = 1;
	public static  ScreenManager getInstance(){
		if(instance == null)
			instance = new ScreenManager();
		return instance;
	}// Alto de la ventana.
	public int getWindWidth() {
		return windWidth;
	}
	public void setWindWidth(int windWidth) {
		this.windWidth = windWidth;
	}
	public int getWindHeight() {
		return windHeight;
	}
	public void setWindHeight(int windHeight) {
		this.windHeight = windHeight;
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
	
}
