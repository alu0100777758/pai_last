package es.ull.etsii.pai.practicafinal.metaclass.weapons;

import es.ull.etsii.pai.practicafinal.AudioManager;
import es.ull.etsii.pai.practicafinal.Bullet;
import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.practicafinal.Side;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.metaclass.Weapon;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.bullets.rocket_bullet;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class RocketLauncher extends Weapon{
	public static final double FIRE_RATE = 1.0;
	public static final int CLIP_SIZE = 1;
	public static final int DAMAGE = 15;
	public static final int SPEED = 20;
	public static final int PUSH = 30;
	public static final int BULLET_SIZE = 15;
	public static final AudioManager audioManager = new AudioManager();
	
	public RocketLauncher(Player owner) {
		super(owner);
		setFireRate(FIRE_RATE);
		setMainClipSize(CLIP_SIZE);
		setWidth(40);
		setHeight(15);
		setX_offset(-30);
		setGraphicShape();
		getGraphicShape().setTexturePath("textures/rocketlauncher.png");
		getGraphicShape().setTextureAnchor(getGraphicShape());
		getGraphicShape().setImage(true);
	}	

	protected void shootSecondary() {}

	protected void shootMain() {
		int side = getOwner().getLookingAt() == Side.LEFT? -SPEED : SPEED;
		int addy = getOwner().isCrounched()? getY_offset() /2 : getY_offset();
		Point2D speed = new Point2D (side, 0);
		Point2D position = getOwner().getPosition();
		position = position.add(0, addy);
		getOwner().getMap().getActors().add(new rocket_bullet(position, speed, DAMAGE, PUSH,getOwner(), BULLET_SIZE));
		audioManager.startAudio();
	}
}
