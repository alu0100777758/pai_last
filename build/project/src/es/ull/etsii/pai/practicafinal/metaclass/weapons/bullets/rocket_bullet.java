package es.ull.etsii.pai.practicafinal.metaclass.weapons.bullets;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import es.ull.etsii.pai.practicafinal.redvsblue.Bullet;
import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.prct9.geometry.Point2D;


public class rocket_bullet extends Bullet {
	private static final long serialVersionUID = 7377268883952126650L;
	public rocket_bullet(Point2D pos) {
		super(pos);
		setTextureImage();
	}
	public rocket_bullet(Point2D pos, Point2D speed, int damage, int push,
			Player owner, int bulletSize) {
		super(pos, speed, damage, push, owner, bulletSize);
		setTextureImage();
	}
	protected void setTextureImage(){
		getGraphicShape().setTexturePath("textures/rocket.png");
//		addSprite("textures/rocket1.png");
		setAnimDelay(25);
		getGraphicShape().setTextureAnchor(getGraphicShape());
		getGraphicShape().setImage(true);
	}
}
