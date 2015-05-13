package es.ull.etsii.pai.practicafinal;

import java.awt.image.BufferedImage;

public class ResourceManager {
	private static ResourceManager instance = null;
	
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
