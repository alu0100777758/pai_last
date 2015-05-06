package es.ull.etsii.pai.practicafinal.physics;

import java.awt.Rectangle;
import java.util.ArrayList;

import es.ull.etsii.pai.prct9.geometry.Segment;

public interface Physical_passive{
	boolean collides(Physical_passive actor);
	ArrayList<Segment> getSegmentList();
	PhysicalRectangle getPhysicalRectangle();
	Rectangle getCollisionedRectangle(Physical_passive actor);
}

