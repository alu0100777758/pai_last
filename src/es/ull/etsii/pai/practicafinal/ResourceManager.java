package es.ull.etsii.pai.practicafinal;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import es.ull.etsii.pai.practicafinal.graphics.Texture;

public class ResourceManager {
	private int windWidth = 1200;
	private int windHeight = 800;
	private static ResourceManager instance = null;
	private HashMap<String, BufferedImage> bufferedImages = new HashMap<String, BufferedImage>(); 
	
	public HashMap<String, BufferedImage> getBufferedImages() {
		return bufferedImages;
	}
	private ResourceManager(){}
	public static  ResourceManager getInstance(){
		if(instance == null)
			instance = new ResourceManager();
		return instance;
	}
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
