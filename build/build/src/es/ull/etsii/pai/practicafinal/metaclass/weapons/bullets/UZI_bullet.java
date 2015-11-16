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


public class UZI_bullet extends Bullet {
	private static final long serialVersionUID = 5449532999166945335L;
	public UZI_bullet(Point2D pos) {
		super(pos);
		setTextureImage();
	}
	public UZI_bullet(Point2D pos, Point2D speed, int damage, int push,
			Player owner, int bulletSize) {
		super(pos, speed, damage, push, owner, bulletSize);
		setTextureImage();
		setMaxDistance(500);
	}
	protected void setTextureImage(){
		getGraphicShape().setTexturePath("textures/uziBullet.png");
		getGraphicShape().setTextureAnchor(getGraphicShape());
		getGraphicShape().setImage(true);
	}
}
