package es.ull.etsii.pai.practicafinal.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import es.ull.etsii.pai.practicafinal.Drawable;
import es.ull.etsii.pai.practicafinal.ResourceManager;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class GraphicRectangle extends Rectangle implements Drawable {
	// private Color color = Color.RED;
	private Paint paint = Color.RED;
	private boolean texturized = false;
	private String texturePath = null;
	private Rectangle textureAnchor = null;
	private boolean image = false;
	private boolean flipImage = false;
	
	
	public boolean isFlipImage() {
		return flipImage;
	}

	public void setFlipImage(boolean flipImage) {
		this.flipImage = flipImage;
	}

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
	public void paint(Graphics g) {
		if (isTexturized() && !isImage())
			texturize(getTexturePath());
		Graphics2D g2 = (Graphics2D) g.create();
		if (isImage()) {
			BufferedImage bimage = ResourceManager.getInstance().getBufferedImage(
					getTexturePath());
			if(isFlipImage())
				bimage = createFlipped(bimage);
			g2.drawImage(
					bimage, (int) getLocation().getX(),
					(int) getLocation().getY(), (int) getWidth(),
					(int) getHeight(), null);
		} else {
			g2.setPaint(getPaint());
			g2.fill(this);
		}

		g2.dispose();
	}

	private void texturize(String texturePath2) {
		setPaint(new TexturePaint(ResourceManager.getInstance()
				.getBufferedImage(getTexturePath()), getTextureAnchor()));
		setTexturized(false);
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint color) {
		this.paint = color;
	}
	private static BufferedImage createFlipped(BufferedImage image)
    {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(-1, 1));
        at.concatenate(AffineTransform.getTranslateInstance( -image.getWidth(),0));
        return createTransformed(image, at);
    }
	  private static BufferedImage createTransformed(
		        BufferedImage image, AffineTransform at)
		    {
		        BufferedImage newImage = new BufferedImage(
		            image.getWidth(), image.getHeight(),
		            BufferedImage.TYPE_INT_ARGB);
		        Graphics2D g = newImage.createGraphics();
		        g.transform(at);
		        g.drawImage(image, 0, 0, null);
		        g.dispose();
		        return newImage;
		    }

}
