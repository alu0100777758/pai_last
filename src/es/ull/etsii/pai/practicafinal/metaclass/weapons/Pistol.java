package es.ull.etsii.pai.practicafinal.metaclass.weapons;

import es.ull.etsii.pai.practicafinal.Bullet;
import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.practicafinal.Side;
import es.ull.etsii.pai.practicafinal.metaclass.Weapon;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Pistol extends Weapon{
	public static final double FIRE_RATE = 1.0;
	public static final int CLIP_SIZE = 12;
	public static final int DAMAGE = 2;
	public static final int SPEED = 20;
	public static final int PUSH = 8;
	
	public Pistol(Player owner) {
		super(owner);
		initializeWeapon();
	}	
	public Pistol(int x, int y) {
		super(x, y);
		initializeWeapon();
	}
	private void initializeWeapon() {
		setFireRate(FIRE_RATE);
		setMainClipSize(CLIP_SIZE);
		setSpeed(SPEED);
		
	}

	protected void shootSecondary() {}

	protected void shootMain() {
		int side = getOwner().getLookingAt() == Side.LEFT? -getSpeed()  : getSpeed();
		int addy = getOwner().isCrounched()? getY_offset() / 2 : getY_offset();
		Point2D speed = new Point2D (side, 0);
		Point2D position = getOwner().getPosition();
		position = position.add(0, addy);
		getOwner().getMap().getActors().add(new Bullet(position, speed, DAMAGE, PUSH,getOwner()));
	}
}
