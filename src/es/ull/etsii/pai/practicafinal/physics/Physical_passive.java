package es.ull.etsii.pai.practicafinal.physics;

import java.util.ArrayList;

import es.ull.etsii.pai.prct9.geometry.Interceptable;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public interface Physical_passive extends Interceptable{
//	boolean interceptsY(Point2D point);
//	boolean interceptsX(Point2D point);
	boolean collides(Physical_passive actor);
	ArrayList<Segment> getSegmentList();
	PhysicalRectangle getPhysicalRectangle();
}

