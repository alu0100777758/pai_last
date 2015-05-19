package es.ull.etsii.pai.practicafinal;

public class ScreenManager {
	private int windWidth = 1200;						// Ancho de la ventana.
	private int windHeight = 800;
	private static ScreenManager instance = null;
	private ScreenManager(){}
	
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
	
}
