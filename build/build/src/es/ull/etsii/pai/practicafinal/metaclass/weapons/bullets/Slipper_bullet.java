package es.ull.etsii.pai.practicafinal.metaclass.weapons.bullets;

import es.ull.etsii.pai.practicafinal.physics.ParabolicLocomotion;
import es.ull.etsii.pai.practicafinal.redvsblue.Bullet;
import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Slipper_bullet extends Bullet {
	public static final int GRAVITY = 2;
	public Slipper_bullet(Point2D pos) {
		super(pos);
		setTextureImage();
		setMotion(new ParabolicLocomotion(GRAVITY));
	}
	public Slipper_bullet(Point2D pos, Point2D speed, int damage, int push,
			Player owner, int bulletSize) {
		super(pos, speed, damage, push, owner, bulletSize);
		setTextureImage();
		setMotion(new ParabolicLocomotion(GRAVITY));
	}
	protected void setTextureImage(){
		getGraphicShape().setTexturePath("textures/slipper_bullet.png");
		getGraphicShape().setTextureAnchor(getGraphicShape());
		getGraphicShape().setImage(true);
	}
}
