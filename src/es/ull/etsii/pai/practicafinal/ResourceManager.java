package es.ull.etsii.pai.practicafinal;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ResourceManager {
	private static ResourceManager instance = null;
	private HashMap<String, BufferedImage> bufferedImages = new HashMap<String, BufferedImage>(); 
	private ResourceManager(){}
	public  ResourceManager getInstance(){
		if(instance == null)
			instance = new ResourceManager();
		return instance;
	}
	BufferedImage getBufferedImage(String path){
		return null;
		
	}
}
