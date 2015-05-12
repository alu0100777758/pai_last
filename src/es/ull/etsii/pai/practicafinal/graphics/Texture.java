package es.ull.etsii.pai.practicafinal.graphics;

import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Texture extends TexturePaint implements Serializable {
	private static final long serialVersionUID = 8536298189505108339L;
	String name ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Texture(String txtr, Rectangle2D anchor) throws IOException {
		super(ImageIO.read(Texture.class.getClassLoader().getResource(txtr)), anchor);
		setName(txtr);
	}
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeChars(getName());
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException {
		setName(getName());
	}

}
