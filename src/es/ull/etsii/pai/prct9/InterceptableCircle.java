package es.ull.etsii.pai.prct9;

import es.ull.etsii.pai.prct9.geometry.Circle;
import es.ull.etsii.pai.prct9.geometry.Interceptable;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Rectangle;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class InterceptableCircle extends Circle implements Interceptable {

	public InterceptableCircle(double radius, Point2D center) {
		super(radius, center);
	}

	public double centerToPoint(Point2D point) {
		return (new Segment(getCenter(), point)).lenght();
	}

	public double centerToSegment(Segment segment) {
		return segment.segmentToPoint(getCenter());
	}

	public boolean belongs(Point2D point) {
		return interceptionDistance(point) < 0;
	}

	@Override
	public boolean intercepts(Segment segment) {
		return interceptionDistance(segment) <= 0;
	}

	@Override
	public double interceptionDistance(Point2D point) {
		return centerToPoint(point) - getRadius();
	}

	@Override
	public double interceptionDistance(Segment segment) {
		return centerToSegment(segment) - getRadius();
	}

	@Override
	public Point2D getCentroid() {
		return getCenter();
	}

	@Override
	public Point2D getPos() {
		return getCenter();
	}

	@Override
	public void setPos(Point2D newpos) {
		setCenter(newpos);
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
