package es.ull.etsii.pai.practicafinal.physics;

import java.awt.geom.Rectangle2D;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import es.ull.etsii.pai.prct9.geometry.Interceptable;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Rectangle;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class PhysicalRectangle extends Rectangle implements Physical_passive {

	public PhysicalRectangle(Point2D start, Point2D end) {
		super(start, end);
	}

	/**
	 * Devuelve la posicion de la esquina superior izquierda.
	 */
	@Override
	public Point2D getPos() {
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
	public boolean belongs(Point2D point) {
		return getStart().x() <= point.x() && getEnd().x() >= point.x()
				&& getStart().y() <= point.y() && getEnd().y() >= point.y();
		// return interceptionDistance(point) < 0;
	}

	@Override
	public boolean intercepts(Segment segment) { // implementar bien

		return belongs(segment.start()) || belongs(segment.end());
		// return interceptionDistance(segment) < 0;
	}

	@Override
	public double interceptionDistance(Point2D point) {
		double top = getTop_segment().segmentToPoint(point);
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

	@Override
	public boolean collides(Physical_passive actor) {
		for (Segment segment : actor.getSegmentList()) {
			if (intercepts(segment))
				return true;
		}
		return false;
	}

	@Override
	public ArrayList<Segment> getSegmentList() {
		ArrayList<Segment> res = new ArrayList<Segment>();
		res.add(getTop_segment());
		res.add(getBottom_segment());
		res.add(getLeft_segment());
		res.add(getRight_segment());
		return res;
	}

	@Override
	public Rectangle interception(Rectangle target) {
		Rectangle res = new Rectangle(Point2D.ORIGIN, Point2D.ORIGIN);
		intercept(this, target, res);
		return res;
	}

	@Override
	public void intercept(Rectangle r1, Rectangle r2, Rectangle res) {
		double x1 = Math.max(r1.getMinX(), r2.getMinX());
		double y1 = Math.max(r1.getMinY(), r2.getMinY());
		double x2 = Math.min(r1.getMaxX(), r2.getMaxX());
		double y2 = Math.min(r1.getMaxY(), r2.getMaxY());
		res.setStart(new Point2D(x1, y1));
		res.setEnd(new Point2D(x2 - x1, y2 - y1));

	}

	// public boolean belongs(Point2D point) {
	// return((point.x() > getStart().x() && point.x() < getEnd().x()) &&
	// (point.y() > getStart().y() && point.y() < getEnd().y()) );
	// }

}
