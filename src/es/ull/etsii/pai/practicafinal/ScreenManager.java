package es.ull.etsii.pai.practicafinal;

public class ScreenManager {
	private int windWidth = 1200;						// Ancho de la ventana.
	private int windHeight = 800;
	private static ScreenManager instance = null;
	private ScreenManager(){}
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
	
}
