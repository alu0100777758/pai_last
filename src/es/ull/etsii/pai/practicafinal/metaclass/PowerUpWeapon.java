package es.ull.etsii.pai.practicafinal.metaclass;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.practicafinal.StaticPlatform;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Segment;

/**
 * 
 * @author Sabato Ceruso, Javier Martin Hernandez.
 *
 */
public class PowerUpWeapon extends StaticPlatform{
	private static final long serialVersionUID = 674995040178479648L;
	private Weapon weapon;
	private boolean alive;
	
	
	public PowerUpWeapon(GraphicRectangle graphic, PhysicalRectangle physic, Weapon weapon) {
		super(graphic, physic);
		setWeapon(weapon);
	}
	public PowerUpWeapon(int x1, int y1, int width, int height, Weapon weapon) {
		super(x1, y1, width, height);
		setWeapon(weapon);
	}
	@Override
	public boolean collides(Physical_passive actor) {
		if (actor.getPhysicalRectangle().intersects(getPhysicalRectangle())) {
			if (actor instanceof Player) {
				((Player)actor).setWeapon(getWeapon());
				setPhysicalRectangle(new PhysicalRectangle(0, 0, 0, 0));
				setGraphicRectangle(new GraphicRectangle(0, 0, 0, 0));
				setAlive(false);
				return true;
			}
		}
		return false;
	}
	@Override
	public ArrayList<Segment> getSegmentList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean hasToDie() {
		return !isAlive();
	}
	/**
	 * Getters y Setters.
	 * @return
	 */
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
}
