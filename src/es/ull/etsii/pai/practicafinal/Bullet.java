package es.ull.etsii.pai.practicafinal;
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

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.MovementEquation;
import es.ull.etsii.pai.practicafinal.physics.ParabolicLocomotion;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_active;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.practicafinal.physics.RectilinearLocomotion;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class Bullet extends Actor implements Physical_active{
	private GraphicRectangle graphicShape;							// Forma grafica de la bala.
	private Point2D speed;											// Vector de velocidad.
	private int damage = 0;											// Daño de la bala.
	private int push = 0; 											// Empuje.
	private int maxDistance = 1000; 								// TODO, distancia maxima que puede recorrer la bala.
//	private MovementEquation motion = new ParabolicLocomotion(9); // pruebame , si quieres dale un poco de velocidad inicial hacia arriba
	private MovementEquation motion = new RectilinearLocomotion();	// Funcion de movimiento de la bala.
	private Player owner;											// Jugador que dispara la bala.
	private int bulletSize = 7;										// Tamaño de la bala.
	private String soundName = "rocketBang.wav";					// Sonido por defecto de la bala.
	
	/**
	 * Crea una bala en la posicion indicada.
	 * @param pos
	 */
	public Bullet (Point2D pos) {
		super(pos);
		setPhysicalShape(new PhysicalRectangle((int) pos.x(), (int)pos.y(), bulletSize, bulletSize));
		setGraphicShape(new GraphicRectangle((int) pos.x(), (int)pos.y(), bulletSize, bulletSize));
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
	}
	/**
	 * Crea una bala con la posicion, velocidad, daño y empuje indicados por el propietario indicado.
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
	 * Crea una bala con la posicion, velocidad, daño, tamaño yempuje indicados por el propietario indicado.
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
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Pinta la bala.
	 * 
	 */
	public void paint(Graphics g) {
		getGraphicShape().paint(g.create());
	}
	@Override
	public boolean updatePos(Physical_passive map) {
		setPosition(motion.getNewpos(getSpeed(), getPosition())); // cambiar a getnewSpeed si se prefiere , tal como estÃ¡ permite aceleracion dentro del motion al modificar y vel
		getGraphicShape().setLocation(new Point((int)getPosition().x(), (int)getPosition().y()));
		if (!map.getPhysicalRectangle().contains(getPhysicalRectangle()))
			return false;
		return true;
	}

	@Override
	public boolean collides(Physical_passive actor) {
		
		if(actor.getPhysicalRectangle().collides(getPhysicalRectangle())){
			AudioManager.startAudio(getSoundName());
			return true;
		}
		return false;
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
	public void setGraphicShape(GraphicRectangle graphicShape) {
		this.graphicShape = graphicShape;
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

	public void setBULLET_SIZE(int bULLET_SIZE) {
		bulletSize = bULLET_SIZE;
		getGraphicShape().setSize(getBULLET_SIZE(), getBULLET_SIZE());
		getPhysicalShape().setSize(getBULLET_SIZE(), getBULLET_SIZE());
	
	}	
	
}
