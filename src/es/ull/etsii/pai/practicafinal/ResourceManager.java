package es.ull.etsii.pai.practicafinal;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import es.ull.etsii.pai.practicafinal.graphics.Texture;

public class ResourceManager {
	private int windWidth = 1200;						// Ancho de la ventana.
	private int windHeight = 800;						// Alto de la ventana.
	private static ResourceManager instance = null;		// Unica instancia de esta clase.
	private HashMap<String, BufferedImage> bufferedImages = new HashMap<String, BufferedImage>(); // Mapa de imagenes con su nombre asociado.
	
	/**
	 * Constructor privado.
	 */
	private ResourceManager(){}
	
	/**
	 * Obtiene una imagen a partir de un path.
	 * @param path
	 * @return
	 */
	public BufferedImage getBufferedImage(String path){
		BufferedImage found = getBufferedImages().get(path);
		if(found == null)
			try {
				found = ImageIO.read(Texture.class.getClassLoader().getResource(path));
				getBufferedImages().put(path, found);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return found;
	}
	/**
	 * Getters y setters.
	 * @return
	 */
	public HashMap<String, BufferedImage> getBufferedImages() {
		return bufferedImages;
	}
	
	public static  ResourceManager getInstance(){
		if(instance == null)
			instance = new ResourceManager();
		return instance;
	}
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
