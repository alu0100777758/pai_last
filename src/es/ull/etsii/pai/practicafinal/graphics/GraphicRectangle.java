package es.ull.etsii.pai.practicafinal.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import es.ull.etsii.pai.practicafinal.Drawable;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class GraphicRectangle extends Rectangle implements Drawable {
	// private Color color = Color.RED;
	private Paint paint = Color.RED;
	private boolean texturized = false;
	private String texturePath = null;
	private Rectangle textureAnchor = null;
	private boolean image = false;
	
	public boolean isImage() {
		return image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public Rectangle getTextureAnchor() {
		return textureAnchor;
	}

	public void setTextureAnchor(Rectangle textureAnchor) {
		this.textureAnchor = textureAnchor;
	}

	public boolean isTexturized() {
		return texturized;
	}

	public void setTexturized(boolean texturized) {
		this.texturized = texturized;
	}

	public String getTexturePath() {
		return texturePath;
	}

	public void setTexturePath(String texturePath) {
		this.texturePath = texturePath;
	}

	public GraphicRectangle(int x1, int y1, int width, int height) {
		super(x1, y1, width, height);
	}

	@Override
	public void paint(Graphics g){
		if(isTexturized() && !isImage())
			texturize(getTexturePath());
		Graphics2D g2 = (Graphics2D) g.create();
		if(isImage()){
			
			BufferedImage image;
			try {
				image = ImageIO.read(Texture.class.getClassLoader().getResource(getTexturePath()));
				g2.drawImage(image, (int)getLocation().getX(),(int)getLocation().getY(),(int) getWidth(),(int)getHeight(), null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			g2.setPaint(getPaint());
			g2.fill(this);
		}
			
		g2.dispose();
	}

	private void texturize(String texturePath2){
		try {
			setPaint( new TexturePaint(ImageIO.read(Texture.class.getClassLoader().getResource(getTexturePath())), getTextureAnchor()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTexturized(false);
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint color) {
		this.paint = color;
	}

}
