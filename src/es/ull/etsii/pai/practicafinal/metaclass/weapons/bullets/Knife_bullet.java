package es.ull.etsii.pai.practicafinal.metaclass.weapons.bullets;

import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.redvsblue.Bullet;
import es.ull.etsii.pai.redvsblue.Player;

public class Knife_bullet extends Bullet {
	public Knife_bullet(Point2D pos) {
		super(pos);
		setTextureImage();
	}
	public Knife_bullet(Point2D pos, Point2D speed, int damage, int push,
			Player owner, int bulletSize) {
		super(pos, speed, damage, push, owner, bulletSize);
		setTextureImage();
	}
	protected void setTextureImage(){
		getGraphicShape().setTexturePath("textures/knife_bullet.png");
		getGraphicShape().setTextureAnchor(getGraphicShape());
		getGraphicShape().setImage(true);
	}
}
