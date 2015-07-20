package es.ull.etsii.pai.practicafinal.physics;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Rectangle;
import java.util.ArrayList;

import es.ull.etsii.pai.prct9.geometry.Segment;

public interface Physical_passive{
	boolean hasToDie();
	boolean collides(Physical_passive actor);
	ArrayList<Segment> getSegmentList();
	PhysicalRectangle getPhysicalRectangle();
	Rectangle getCollisionedRectangle(Physical_passive actor);
}

