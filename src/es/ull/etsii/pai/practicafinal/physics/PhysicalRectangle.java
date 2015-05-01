package es.ull.etsii.pai.practicafinal.physics;

import es.ull.etsii.pai.prct9.geometry.Interceptable;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Rectangle;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class PhysicalRectangle extends Rectangle implements Interceptable{
	
	public PhysicalRectangle(Point2D start, Point2D end) {
		super(start, end);
	}

	/**
	 * Devuelve la posicion de la esquina superior izquierda.
	 */
	@Override
	public Point2D getPos() {
		// TODO Auto-generated method stub
		return getStart();
	}
	/**
	 * Cambia la posicion de la esquina superior izquierda.
	 */
	@Override
	public void setPos(Point2D newpos) {
		double width = getWidth();
		double height = getHeight();
		setStart(newpos);
		setEnd(new Point2D(newpos.x() + width, newpos.y() + height));
	}

	@Override
	public boolean intercepts(Point2D point) {
		return getStart().x() <= point.x() && getEnd().x() >= point.x() && getStart().y() <= point.y() && getEnd().y() >= point.y();
		//return interceptionDistance(point) < 0;
	}

	@Override
	public boolean intercepts(Segment segment) {

		return intercepts(segment.start()) || intercepts(segment.end());
		//return interceptionDistance(segment) < 0;
	}

	@Override
	public double interceptionDistance(Point2D point) {
		double top =  getTop_segment().segmentToPoint(point);
		double bot = getBottom_segment().segmentToPoint(point);
		double left = getLeft_segment().segmentToPoint(point);
		double right = getRight_segment().segmentToPoint(point);
		
		return Math.min(Math.min(Math.min(top, bot), left), right);
	}

	@Override
	public double interceptionDistance(Segment segment) {
		double top = getTop_segment().segmentToSegment(segment);
		double bot = getBottom_segment().segmentToSegment(segment);
		double left = getLeft_segment().segmentToSegment(segment);
		double right = getRight_segment().segmentToSegment(segment);
		
		return Math.min(Math.min(Math.min(top, bot), left), right);
	}

	/**
	 * A rellenar luego.
	 */
	@Override
	public Point2D getCentroid() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
