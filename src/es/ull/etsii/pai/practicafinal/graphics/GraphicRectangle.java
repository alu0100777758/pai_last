package es.ull.etsii.pai.practicafinal.graphics;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import es.ull.etsii.pai.redvsblue.ResourceManager;
import es.ull.etsii.pai.redvsblue.ScreenManager;

public class GraphicRectangle extends Rectangle implements Drawable {
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
		double xrate = ScreenManager.getInstance().getRate_x();
		double yrate = ScreenManager.getInstance().getRate_y();
		if (isTexturized() && !isImage())
			texturize(getTexturePath());
		Graphics2D g2 = (Graphics2D) g.create();
		if (isImage()) {
			BufferedImage bimage = ResourceManager.getInstance()
					.getBufferedImage(getTexturePath());
			if (isFlipImage())
				bimage = createFlipped(bimage);
			g2.drawImage(bimage, (int) (getLocation().getX() * xrate),
					(int) (getLocation().getY() * yrate),
					(int) (getWidth() * xrate), (int) (getHeight() * yrate),
					null);
		} else {
			g2.setPaint(getPaint());
			g2.fill(new Rectangle((int) (getLocation().getX() * xrate),
					(int) (getLocation().getY() * yrate),
					(int) (getWidth() * xrate), (int) (getHeight() * yrate)));
		}

		g2.dispose();
	}

	private void texturize(String texturePath2) {
		double xrate = ScreenManager.getInstance().getRate_x();
		double yrate = ScreenManager.getInstance().getRate_y();
		Rectangle a = getTextureAnchor();
		setPaint(new TexturePaint(ResourceManager.getInstance()
				.getBufferedImage(getTexturePath()), new Rectangle((int) (a
				.getLocation().getX() * xrate),
				(int) (a.getLocation().getY() * yrate),
				(int) (a.getWidth() * xrate), (int) (a.getHeight() * yrate))));
		setTexturized(false);
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint color) {
		this.paint = color;
	}

	private static BufferedImage createFlipped(BufferedImage image) {
		AffineTransform at = new AffineTransform();
		at.concatenate(AffineTransform.getScaleInstance(-1, 1));
		at.concatenate(AffineTransform.getTranslateInstance(-image.getWidth(),
				0));
		return createTransformed(image, at);
	}

	private static BufferedImage createTransformed(BufferedImage image,
			AffineTransform at) {
		BufferedImage newImage = new BufferedImage(image.getWidth(),
				image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.transform(at);
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return newImage;
	}

}
