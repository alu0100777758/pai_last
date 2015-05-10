package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_active;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class Bullet extends Actor implements Physical_active{
	private GraphicRectangle graphicShape;
	private Point2D speed;
	
	public static final int BULLET_SIZE = 5;
	
	public Bullet (Point2D pos) {
		super(pos);
		setPhysicalShape(new PhysicalRectangle((int) pos.x(), (int)pos.y(), BULLET_SIZE, BULLET_SIZE));
		setGraphicShape(new GraphicRectangle((int) pos.x(), (int)pos.y(), BULLET_SIZE, BULLET_SIZE));
		setSpeed(new Point2D (0, 0));
		getGraphicShape().setColor(Color.BLACK); 
	}
	
	public Bullet(Point2D pos, Point2D speed) {
		this(pos);
		setSpeed(speed);
	}
	
	public void paint(Graphics g) {
		getGraphicShape().paint(g.create());
	}
	@Override
	public boolean updatePos(Physical_passive map) {
		addXPosition(getSpeed().x());
		addYPosition(getSpeed().y());
		getGraphicShape().setLocation(new Point((int)getPosition().x(), (int)getPosition().y()));
		if (!map.getPhysicalRectangle().contains(getPhysicalRectangle()))
			return false;
		return true;
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
		// TODO Auto-generated method stub
		return false;
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


	
	
	
}