package es.ull.etsii.pai.practicafinal.metaclass;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Graphics;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.practicafinal.redvsblue.StaticPlatform;
import es.ull.etsii.pai.prct9.geometry.Segment;


public class PowerUpWeapon extends StaticPlatform{
	private static final long serialVersionUID = 674995040178479648L;
	private Weapon weapon;						// Arma que contiene el power-up.
	private boolean alive = true;				// True si sigue vivo.
	
	/**
	 * Crea un power up con una determinada arma, graficos y fisica.
	 * 
	 * @param graphic
	 * @param physic
	 * @param weapon
	 */
	public PowerUpWeapon(GraphicRectangle graphic, PhysicalRectangle physic, Weapon weapon) {
		super(graphic, physic);
		setWeapon(weapon);
	}
	/**
	 * Crea un power up con una determinada arma.
	 * @param weapon
	 */
	public PowerUpWeapon(Weapon weapon) {
		super(weapon.getGraphicShape(), new PhysicalRectangle((int)weapon.getGraphicShape().getLocation().getX(), (int)weapon.getGraphicShape().getLocation().getY(),
				(int)weapon.getGraphicShape().getWidth(), (int)weapon.getGraphicShape().getHeight()));
		setWeapon(weapon);
	}
	/**
	 * Crea un power up en unas coordenadas con un tama�o determinado con un arma determinada.
	 * @param x1
	 * @param y1
	 * @param width
	 * @param height
	 * @param weapon
	 */
	public PowerUpWeapon(int x1, int y1, int width, int height, Weapon weapon) {
		super(x1, y1, width, height);
		setWeapon(weapon);
	}
	@Override
	public boolean collides(Physical_passive actor) {
		if (actor.getPhysicalRectangle().intersects(getPhysicalRectangle())) {
			if (actor instanceof Player) {
				getWeapon().setOwner((Player)actor);
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
	public void paint(Graphics g) {
		getGraphicRectangle().paint(g);
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
