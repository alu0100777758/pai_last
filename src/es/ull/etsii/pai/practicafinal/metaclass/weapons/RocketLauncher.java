package es.ull.etsii.pai.practicafinal.metaclass.weapons;

import es.ull.etsii.pai.practicafinal.AudioManager;
import es.ull.etsii.pai.practicafinal.Bullet;
import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.practicafinal.Side;
import es.ull.etsii.pai.practicafinal.metaclass.Weapon;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.bullets.rocket_bullet;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class RocketLauncher extends Weapon{
	public static final double FIRE_RATE = 1.0;
	public static final int CLIP_SIZE = 3;
	public static final int RELOADING_TIME = 90;
	public static final int DAMAGE = 15;
	public static final int SPEED = 20;
	public static final int PUSH = 30;
	public static final int BULLET_SIZE = 15;
	
	public RocketLauncher(Player owner) {
		super(owner);
		initializeWeapon();
	}	
	
	public RocketLauncher(int x, int y) {
		super(x, y);
		initializeWeapon();
	}
	
	private void initializeWeapon() {
		setFireRate(FIRE_RATE);
		setMainClipSize(CLIP_SIZE);
		setMainAmmo(CLIP_SIZE);
		setSpeed(SPEED);
		setMainReloadingTime(RELOADING_TIME);
		setWidth(40);
		setHeight(15);
		setX_offset(-30);
		getGraphicShape().setTexturePath("textures/rocketlauncher.png");
		getGraphicShape().setTextureAnchor(getGraphicShape());
		getGraphicShape().setImage(true);
	}
	protected void shootSecondary() {}

	protected void shootMain() {
		int side = getOwner().getLookingAt() == Side.LEFT? -getSpeed() : getSpeed();

		int addy = getOwner().isCrounched()? getY_offset() /2 : getY_offset();
		Point2D speed = new Point2D (side, 0);
		Point2D position = getOwner().getPosition();
		position = position.add(0, addy);
		Bullet breechBullet = new rocket_bullet(position, speed, DAMAGE, PUSH,getOwner(), BULLET_SIZE);
		breechBullet.setSoundName("rocketBang.wav");
		getOwner().getMap().getActors().add(breechBullet);
		AudioManager.startAudio("rocketShot.wav");
	}
}
