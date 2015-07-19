package es.ull.etsii.pai.practicafinal.metaclass.weapons;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import es.ull.etsii.pai.practicafinal.metaclass.Weapon;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.bullets.Knife_bullet;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.bullets.Slipper_bullet;
import es.ull.etsii.pai.practicafinal.redvsblue.Bullet;
import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.practicafinal.redvsblue.Side;
import es.ull.etsii.pai.prct9.geometry.Point2D;


public class Slipper extends Weapon {
	private static final long serialVersionUID = -3540229204679818955L;

	public static final double FIRE_RATE = 1.0;
	public static final int CLIP_SIZE = 1;
	public static final int RELOADING_TIME = 0;
	public static final int DAMAGE = 10;
	public static final int SPEED = 20;
	public static final int PUSH = 15;
	public static final int RANGE = 1500;
	private static final int BULLET_SIZE = 20;
	private static final int UP_THROW_SPEED = 20;
	
	public Slipper(Player owner) {
		super(owner);
		initializeWeapon();
	}	

	public Slipper(int x, int y) {
		super(x, y);
		initializeWeapon();
	}
	private void initializeWeapon() {
		setFireRate(FIRE_RATE);
		setMainClipSize(CLIP_SIZE);
		setMainAmmo(CLIP_SIZE);
		setSpeed(SPEED);
		setHeight(15);
		setX_offset(-15);
		setY_offset(10);
		setMainReloadingTime(RELOADING_TIME);
		getGraphicShape().setTexturePath("textures/slipper.png");
		getGraphicShape().setTextureAnchor(getGraphicShape());
		getGraphicShape().setImage(true);
		setReload_able(false);
	}
	
	protected void shootSecondary() {}

	protected void shootMain() {
		int side = getOwner().getLookingAt() == Side.LEFT? -getSpeed() : getSpeed();
		int addy = getOwner().isCrounched()? getY_offset() /2 : getY_offset();
		Point2D speed = new Point2D (side, -UP_THROW_SPEED);
		Point2D position = getOwner().getPosition();
		position = position.add(0, addy);
		Bullet breechBullet = new Slipper_bullet(position, speed, DAMAGE, PUSH,getOwner(), BULLET_SIZE);
		breechBullet.setMaxDistance(RANGE);
		breechBullet.setSoundName("rocketBang.wav");
		addBullet(breechBullet);
//		AudioManager.startAudio("KnifeShot.wav");
	}

}
