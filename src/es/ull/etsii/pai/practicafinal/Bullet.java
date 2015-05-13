package es.ull.etsii.pai.practicafinal;

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
	private GraphicRectangle graphicShape;
	private Point2D speed;
	private int damage = 0;
	private int push = 0; 	// empuje, por ahora no usar.
	private int maxDistance = 1000; // TODO
//	private MovementEquation motion = new ParabolicLocomotion(9); // pruebame , si quieres dale un poco de velocidad inicial hacia arriba
	private MovementEquation motion = new RectilinearLocomotion();
	private Player owner;
	public static final int BULLET_SIZE = 7;
	
	public Bullet (Point2D pos) {
		super(pos);
		setPhysicalShape(new PhysicalRectangle((int) pos.x(), (int)pos.y(), BULLET_SIZE, BULLET_SIZE));
		setGraphicShape(new GraphicRectangle((int) pos.x(), (int)pos.y(), BULLET_SIZE, BULLET_SIZE));
		setSpeed(new Point2D (0, 0));
		getGraphicShape().setPaint(Color.BLACK); 
	}
	
	public Bullet(Point2D pos, Point2D speed, Player owner) {
		this(pos);
		setSpeed(speed);
		setOwner(owner);
	}
	public Bullet(Point2D pos, Point2D speed, int damage, Player owner) {
		this(pos, speed, owner);
		setDamage(damage);
	}
	
	public void paint(Graphics g) {
		getGraphicShape().paint(g.create());
	}
	@Override
	public boolean updatePos(Physical_passive map) {
		setPosition(motion.getNewpos(getSpeed(), getPosition())); // cambiar a getnewSpeed si se prefiere , tal como está permite aceleracion dentro del motion al modificar y vel
		getGraphicShape().setLocation(new Point((int)getPosition().x(), (int)getPosition().y()));
		if (!map.getPhysicalRectangle().contains(getPhysicalRectangle()))
			return false;
		return true;
	}
	
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

	@Override
	public boolean collides(Physical_passive actor) {
		
		return actor.getPhysicalRectangle().collides(getPhysicalRectangle());
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

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	
	
	
	
}
