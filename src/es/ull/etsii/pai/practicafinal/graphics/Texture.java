package es.ull.etsii.pai.practicafinal.graphics;

import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Texture extends TexturePaint implements Serializable {
	private static final long serialVersionUID = 8536298189505108339L;
	String name ;
	Rectangle2D anchor;
	
	public Rectangle2D getAnchor() {
		return anchor;
	}
	public void setAnchor(Rectangle2D anchor) {
		this.anchor = anchor;
	}
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
}
