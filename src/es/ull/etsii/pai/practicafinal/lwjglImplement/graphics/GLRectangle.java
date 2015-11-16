package es.ull.etsii.pai.practicafinal.lwjglImplement.graphics;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import es.ull.etsii.pai.practicafinal.redvsblue.ResourceManager;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;



public class GLRectangle extends Rectangle implements DrawableGL {
	private static final long serialVersionUID = 7387261326610347073L;
	private boolean texturized = false;
	private String texturePath = null;
	private Rectangle textureAnchor = null;
	private boolean image = false;
	private boolean flipImage = false;
	private double rotation = 0;
	private boolean isRotated = false;
	private int texture = -1;
	
	public GLRectangle(int x1, int y1, int width , int height, int textureID){
		this(x1, y1, width, height);
		setTexture(textureID);
	}

	public GLRectangle(int x1, int y1, int width, int height) {
		super(x1, y1, width, height);
	}

	
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
//			if(isRotated()){
//				bimage = createRotated(bimage, getRotation());
//			}
			g2.drawImage(bimage, (int) (getLocation().getX() * xrate),
					(int) (getLocation().getY() * yrate),
					(int) (getWidth() * xrate), (int) (getHeight() * yrate),
					null);
		} else {
//			g2.setPaint(getPaint());
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
//		setPaint(new TexturePaint(ResourceManager.getInstance()
//				.getBufferedImage(getTexturePath()), new Rectangle((int) (a
//				.getLocation().getX() * xrate),
//				(int) (a.getLocation().getY() * yrate),
//				(int) (a.getWidth() * xrate), (int) (a.getHeight() * yrate))));
//		setTexturized(false);
	}



	private static BufferedImage createFlipped(BufferedImage image) {
		AffineTransform at = new AffineTransform();
		at.concatenate(AffineTransform.getScaleInstance(-1, 1));
		at.concatenate(AffineTransform.getTranslateInstance(-image.getWidth(),
				0));
		return createTransformed(image, at);
	}
	private static BufferedImage createRotated(BufferedImage image, double grades){
		// create the transform, note that the transformations happen
        // in reversed order (so check them backwards)
        AffineTransform at = new AffineTransform();

//        // 4. translate it to the center of the component
//        at.translate(getWidth() / 2, getHeight() / 2);
        at.rotate(grades);

        // 1. translate the object so that you rotate it around the 
        //    center (easier :))
        at.translate(-image.getWidth()/2, -image.getHeight()/2);

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
	public void enlarge( int xincrease, int yincrease){
		setSize((int)getWidth()+xincrease,(int)getHeight()+yincrease);
	}

	@Override
	public void draw() {
		glPushMatrix();
        if(getTexture() > 0)
        	glBindTexture(GL_TEXTURE_2D, getTexture());
        glTranslatef((float)getBounds().getMinX(), (float)getBounds().getMinY(),0);
        glBegin(GL_QUADS);
        {
           glTexCoord2f(0, 0);
           glVertex2f(0,0);
           
           glTexCoord2f(1, 0);
           glVertex2f((int)getBounds().getMaxX(),0);
           
           glTexCoord2f(1, 1);
           glVertex2f((int)getBounds().getMaxX(),(int) getMaxY());
           
           glTexCoord2f(0, 1);
           glVertex2f(0, (int)getBounds().getMaxY());
        }
        glEnd();
        glPopMatrix();
	}
	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public boolean isRotated() {
		return isRotated;
	}

	public void setRotated(boolean isRotated) {
		this.isRotated = isRotated;
	}

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


	public int getTexture() {
		return texture;
	}


	public void setTexture(int texture) {
		this.texture = texture;
	}
	
}
