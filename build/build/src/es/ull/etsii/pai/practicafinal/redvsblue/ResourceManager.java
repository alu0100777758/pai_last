package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import es.ull.etsii.pai.practicafinal.graphics.Texture;

public class ResourceManager {
	public static final String NOT_FOUND = "textures/texture_not_found.png";
	private Random randGen = new Random();
	private BufferedImage notFound;
	private static String userDir =  System.getProperty("user.dir");
	private static ResourceManager instance = null;		// Unica instancia de esta clase.
	private HashMap<String, BufferedImage> bufferedImages = new HashMap<String, BufferedImage>(); // Mapa de imagenes con su nombre asociado.
	private HashMap<String, BufferedImage> runtimeBufferedImages = new HashMap<String, BufferedImage>(); // Mapa de imagenes con su nombre asociado
	/**
	 * Constructor privado.
	 */
	private ResourceManager(){
		try {
			setNotFound(ImageIO.read(Texture.class.getClassLoader().getResource(NOT_FOUND)));
		} catch (IOException e) {
			System.out.println("No se ha encontrado la textura \"Not Found\"");
			e.printStackTrace();
		}
	}
	
	public static  ResourceManager getInstance(){
		if(instance == null)
			instance = new ResourceManager();
		return instance;
	}
	
	public Random getRandGen() {
		return randGen;
	}
	public void setRandGen(Random randGen) {
		this.randGen = randGen;
	}

	/**
	 * Obtiene una imagen a partir de un path.
	 * @param path
	 * @return
	 */

	public BufferedImage getBufferedImage(String path){
		BufferedImage found = getBufferedImages().get(path);
		if(found == null){
			try {
				found = ImageIO.read(Texture.class.getClassLoader().getResource(path));
				getBufferedImages().put(path, found);
			} catch (Exception e) {
				found = null;
			}
		}
		if(found == null){
			try {
				System.out.println("no se ha encontrado en el jar buscando en textures " + getUserDir()+System.getProperty("file.separator")+path);
				found = ImageIO.read(new File(getUserDir()+System.getProperty("file.separator")+path));
				getBufferedImages().put(path, found);
			} catch (Exception e) {
				System.out.println("no se ha encontrado en el directorio");
				found = getBufferedImages().get(NOT_FOUND);
			}
		}
		return toCompatibleImage(found);
	}
	private BufferedImage toCompatibleImage(BufferedImage image)
	{
		// obtain the current system graphical settings
		GraphicsConfiguration gfx_config = GraphicsEnvironment.
			getLocalGraphicsEnvironment().getDefaultScreenDevice().
			getDefaultConfiguration();

		/*
		 * if image is already compatible and optimized for current system 
		 * settings, simply return it
		 */
		if (image.getColorModel().equals(gfx_config.getColorModel()))
			return image;

		// image is not optimized, so create a new image that is
		BufferedImage new_image = gfx_config.createCompatibleImage(
				image.getWidth(), image.getHeight(), image.getTransparency());

		// get the graphics context of the new image to draw the old image on
		Graphics2D g2d = (Graphics2D) new_image.getGraphics();

		// actually draw the image and dispose of context no longer needed
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		// return the new optimized image
		return new_image; 
	}
	public BufferedImage getRunTimeBufferedImage(String path){
		BufferedImage found = getBufferedImages().get(path);
		return found;
	}
	/**
	 * Getters y setters.
	 * @return
	 */
	public HashMap<String, BufferedImage> getBufferedImages() {
		return bufferedImages;
	}

	public static String getUserDir() {
		return userDir;
	}

	public static void setUserDir(String userDir) {
		ResourceManager.userDir = userDir;
	}

	public BufferedImage getNotFound() {
		return notFound;
	}

	public void setNotFound(BufferedImage notFound) {
		this.notFound = notFound;
	}

	public HashMap<String, BufferedImage> getRuntimeBufferedImages() {
		return runtimeBufferedImages;
	}

	public void setRuntimeBufferedImages(
			HashMap<String, BufferedImage> runtimeBufferedImages) {
		this.runtimeBufferedImages = runtimeBufferedImages;
	}
	
	
	
}
