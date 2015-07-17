package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.graphics.AnimatedGraphicRectangle;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.bullets.UZI_bullet;
import es.ull.etsii.pai.practicafinal.physics.MovementEquation;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_active;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.practicafinal.physics.RectilinearLocomotion;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class Bullet extends Actor implements Physical_active{
	private AnimatedGraphicRectangle graphicShape;							// Forma grafica de la bala.
	private Point2D speed;											// Vector de velocidad.
	private int damage = 0;											// Da�o de la bala.
	private int push = 0; 											// Empuje.
	private int maxDistance = 1000; 								// distancia maxima que puede recorrer la bala.
//	private MovementEquation motion = new ParabolicLocomotion(9); // pruebame , si quieres dale un poco de velocidad inicial hacia arriba
	private MovementEquation motion = new RectilinearLocomotion();	// Funcion de movimiento de la bala.
	private Player owner;											// Jugador que dispara la bala.
	private int bulletSize = 7;										// Tama�o de la bala.
	private String soundName = "rocketBang.wav";					// Sonido por defecto de la bala.
	private boolean dead = false;
	/**
	 * Crea una bala en la posicion indicada.
	 * @param pos
	 */
	public Bullet (Point2D pos) {
		super(pos);
		setPhysicalShape(new PhysicalRectangle((int) pos.x(), (int)pos.y(), bulletSize, bulletSize));
		setGraphicShape(new AnimatedGraphicRectangle((int) pos.x(), (int)pos.y(), bulletSize, bulletSize));
		getGraphicShapes().add(getGraphicShape());
		setSpeed(new Point2D (0, 0));
		getGraphicShape().setPaint(Color.BLACK); 
	}
	/**
	 * Crea una bala con la posicion y velocidad indicada por el propietario indicado.
	 * @param pos
	 * @param speed
	 * @param owner
	 */
	public Bullet(Point2D pos, Point2D speed, Player owner) {
		this(pos);
		setSpeed(speed);
		setOwner(owner);
		getGraphicShape().setFlipImage(getOwner().getGraphicShapes().get(0).isFlipImage());
		getGraphicShapes().add(getGraphicShape());
	}
	/**
	 * Crea una bala con la posicion, velocidad, da�o y empuje indicados por el propietario indicado.
	 * @param pos
	 * @param speed
	 * @param damage
	 * @param Push
	 * @param owner
	 */
	public Bullet(Point2D pos, Point2D speed, int damage, int Push,Player owner) {
		this(pos, speed, owner);
		setDamage(damage);
		setPush(Push);
	}
	/**
	 * Crea una bala con la posicion, velocidad, da�o, tama�o yempuje indicados por el propietario indicado.
	 * @param pos
	 * @param speed
	 * @param damage
	 * @param Push
	 * @param owner
	 * @param size
	 */

	public Bullet(Point2D pos, Point2D speed, int damage, int Push,Player owner, int size) {
		this(pos, speed, damage, Push, owner);
		setBULLET_SIZE(size);
	}
	
	@Override
	public boolean hasToDie() {
		return isDead();
	}
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	/**
	 * Pinta la bala.
	 * 
	 */
	public void paint(Graphics g) {
		for(GraphicRectangle graphic : getGraphicShapes())
			graphic.paint(g);
	}	
	@Override
	public boolean updatePos(Physical_passive map) {
		int init = (int)getPosition().x();
		updatePos();
		updateDistance(init);
		if(getMaxDistance() <= 0)
			actionAtMaxDistance();
		updateGraphics();
		if (!map.getPhysicalRectangle().contains(getPhysicalRectangle()))
			return false;
		return true;
	}
	public void updateGraphics() {
		getGraphicShape().setLocation(new Point((int)getPosition().x(), (int)getPosition().y()));
	}
	private void updateDistance(int init) {
		setMaxDistance((int)(getMaxDistance()-(Math.max(init, getPosition().x())-Math.min(init, getPosition().x()))));
	}
	protected void actionAtMaxDistance() {
		setDead(true);
	}

	public void updatePos(){
		setPosition(getMotion().getNewpos(getSpeed(), getPosition())); 
	}
	@Override
	public boolean repair_collisionY(Point2D point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean repair_collisionX(Point2D point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean repair_collision(Physical_passive actor) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Getters y Setters.
	 * @return
	 */
	
	public int getDamage() {
		return damage;
	}

	public MovementEquation getMotion() {
		return motion;
	}
	public void setMotion(MovementEquation motion) {
		this.motion = motion;
	}
	private void setDamage(int damage) {
		this.damage = damage;
	}

	public Point2D getSpeed() {
		return speed;
	}

	public void setSpeed(Point2D speed) {
		this.speed = speed;
	}

	public GraphicRectangle getGraphicShape() {
		return graphicShape;
	}
	public void addSprite(String path){
		graphicShape.addTexturePath(path);
	}
	public void setAnimDelay(int value){
		graphicShape.setMaxDelay(value);
	}
	public void setGraphicShape(AnimatedGraphicRectangle graphicShape) {
		this.graphicShape = graphicShape;
	}


	@Override
	public boolean collides(Physical_passive actor) {
		
		if(isLastCollision(actor) || isDead()){
			return true;
		}
		return false;
	}
	public boolean isLastCollision(Physical_passive actor){
		if(actor.getPhysicalRectangle().collides(getPhysicalRectangle())){
			if(actor instanceof Player){
				Player player = (Player)actor;
				if(actor == getOwner())
					return collidesWithFriend((Player)actor);
				else
					return collidesWithEnemy((Player)actor);
			}
			return collidesWithOther(actor);
		}
		return false;
	}
	public boolean collidesWithOther(Physical_passive actor) {
		setDead(true);
		return true;
	}
	public boolean collidesWithFriend(Player actor) {
		return false;
	}
	public boolean collidesWithEnemy(Player actor) {
		actor.gotHit(this);
		setDead(true);
		return true;
	}
	public String getSoundName() {
		return soundName;
	}

	public void setSoundName(String soundName) {
		this.soundName = soundName;
	}

	@Override
	public ArrayList<Segment> getSegmentList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhysicalRectangle getPhysicalRectangle() {
		// TODO Auto-generated method stub
		return getPhysicalShape();
	}

	@Override
	public Rectangle getCollisionedRectangle(Physical_passive actor) {
		// TODO Auto-generated method stub
		return null;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getPush() {
		return push;
	}

	public void setPush(int push) {
		this.push = push;
	}

	public int getBULLET_SIZE() {
		return bulletSize;
	}
	
	public int getMaxDistance() {
		return maxDistance;
	}
	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}
	public void setBULLET_SIZE(int bULLET_SIZE) {
		bulletSize = bULLET_SIZE;
		getGraphicShape().setSize(getBULLET_SIZE(), getBULLET_SIZE());
		getPhysicalShape().setSize(getBULLET_SIZE(), getBULLET_SIZE());
	
	}	
	
}
