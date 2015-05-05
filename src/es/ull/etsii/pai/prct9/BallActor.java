package es.ull.etsii.pai.prct9;

import java.util.ArrayList;

import es.ull.etsii.pai.prct9.geometry.Interceptable;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Rectangle;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class BallActor extends Actor {
	public static final int RADIUS = 32;
	private DrawableCircle graphicBall; 
	private InterceptableCircle physicalBall;
	public BallActor() {
		super(new ArrayList<Drawable>(), new ArrayList<Interceptable>());
	}
	public BallActor(double xpos, double ypos) {
		this();
		graphicBall = new DrawableCircle(RADIUS, new Point2D(xpos,ypos),true);
		physicalBall = new InterceptableCircle(RADIUS, new Point2D(xpos,ypos));
		super.addGraphic(graphicBall);
		super.addGeometry(physicalBall);
	}
	
	@Override
	public Point2D getPos() {
		return graphicBall.getPos();
	}

	@Override
	public void setPos(Point2D newpos) {
		graphicBall.setPos(newpos);
	}
	public Point2D getCentroid() {
		return graphicBall.getCenter();
	}
	@Override
	public double interceptionDistance(Point2D point) {
		return physicalBall.centerToPoint(point) - physicalBall.getRadius();
	}
	@Override
	public double interceptionDistance(Segment segment) {
		return physicalBall.centerToSegment(segment) - physicalBall.getRadius();
	}
	@Override
	public Rectangle interception(Rectangle target) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void intercept(Rectangle r1, Rectangle r2, Rectangle res) {
		// TODO Auto-generated method stub
		
	}
}
