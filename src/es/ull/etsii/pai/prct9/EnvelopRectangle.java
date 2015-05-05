package es.ull.etsii.pai.prct9;

import es.ull.etsii.pai.prct9.geometry.Envelop;
import es.ull.etsii.pai.prct9.geometry.Interceptable;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Rectangle;

public class EnvelopRectangle extends Rectangle implements Envelop {

	public EnvelopRectangle(Point2D start, Point2D end) {
		super(start, end);
	}

	@Override
	public boolean envelops(Interceptable object) {
		boolean lowerThanTop = !object.intercepts(getTop_segment()) && object.getCentroid().y() > getStart().y();
		boolean rightOfLeft = !object.intercepts(getLeft_segment()) && object.getCentroid().x() > getStart().x();
		boolean ontopTheBottom = !object.intercepts(getBottom_segment()) && object.getCentroid().y() < getEnd().y();
		boolean leftOfRight = !object.intercepts(getRight_segment()) && object.getCentroid().x() < getEnd().x();
		return lowerThanTop && rightOfLeft && ontopTheBottom && leftOfRight;
	}

}
