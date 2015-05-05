package es.ull.etsii.pai.practicafinal.physics;

import java.awt.Rectangle;
import java.util.ArrayList;

import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class PhysicalRectangle extends Rectangle implements Physical_passive {

//	public PhysicalRectangle(int x1, int y1, int width, int height) {
//		super(x1, end);
//	}

	/**
	 * Devuelve la posicion de la esquina superior izquierda.
	 */
//	@Override
//	public Point2D getPos() {
//		return getStart();
//	}


	@Override
	public boolean collides(Physical_passive actor) {

	}

	@Override
	public ArrayList<Segment> getSegmentList() {
//		ArrayList<Segment> res = new ArrayList<Segment>();
//		res.add(getTop_segment());
//		res.add(getBottom_segment());
//		res.add(getLeft_segment());
//		res.add(getRight_segment());
//		return res;
		return null;														// vacio EMPTY NAAAAA
	}

//	@Override
//	public Rectangle interception(Rectangle target) {
//		Rectangle res = new Rectangle(, Point);
//		intercept(this, target, res);
//		return res;
//	}

//	@Override
//	public void intercept(Rectangle r1, Rectangle r2, Rectangle res) {
//		double x1 = Math.max(r1.getMinX(), r2.getMinX());
//		double y1 = Math.max(r1.getMinY(), r2.getMinY());
//		double x2 = Math.min(r1.getMaxX(), r2.getMaxX());
//		double y2 = Math.min(r1.getMaxY(), r2.getMaxY());
//		res.setStart(new Point2D(x1, y1));
//		res.setEnd(new Point2D(x2 - x1, y2 - y1));
//
//	}

	// public boolean belongs(Point2D point) {
	// return((point.x() > getStart().x() && point.x() < getEnd().x()) &&
	// (point.y() > getStart().y() && point.y() < getEnd().y()) );
	// }

}
