package es.ull.etsii.pai.practicafinal.metaclass.weapons;

import es.ull.etsii.pai.practicafinal.AudioManager;
import es.ull.etsii.pai.practicafinal.Bullet;
import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.practicafinal.Side;
import es.ull.etsii.pai.practicafinal.metaclass.Weapon;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.bullets.UZI_bullet;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class UZI extends Weapon{
	public static final double FIRE_RATE = 5.0;
	public static final int CLIP_SIZE = 20;
	public static final int RELOADING_TIME = 90;
	public static final int DAMAGE = 1;
	public static final int SPEED = 20;
	public static final int PUSH = 6;
	
	public UZI(Player owner) {
		super(owner);
		initializeWeapon();
	}	

	public UZI(int x, int y) {
		super(x, y);
		initializeWeapon();
	}
	private void initializeWeapon() {
		setFireRate(FIRE_RATE);
		setMainClipSize(CLIP_SIZE);
		setMainAmmo(CLIP_SIZE);
		setSpeed(SPEED);
		setHeight(15);
		setX_offset(-10);
		setMainReloadingTime(RELOADING_TIME);
		getGraphicShape().setTexturePath("textures/uzi.png");
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
		getOwner().getMap().getActors().add(new UZI_bullet(position, speed, DAMAGE, PUSH,getOwner(), 10));
		AudioManager.startAudio("uziShot.wav");
	}
}
