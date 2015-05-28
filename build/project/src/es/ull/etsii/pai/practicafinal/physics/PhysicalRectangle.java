package es.ull.etsii.pai.practicafinal.physics;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
	
import java.awt.Rectangle;
import java.util.ArrayList;

import es.ull.etsii.pai.prct9.geometry.Segment;

public class PhysicalRectangle extends Rectangle implements Physical_passive {
	public PhysicalRectangle(int x1, int y1, int width, int height) {
		super(x1, y1, width, height);
	}
	@Override
	public boolean collides(Physical_passive actor) {
		return this.intersects(actor.getPhysicalRectangle());
	}

	public ArrayList<Segment> getSegmentList() {
//		ArrayList<Segment> res = new ArrayList<Segment>();
//		res.add(getTop_segment());
//		res.add(getBottom_segment());
//		res.add(getLeft_segment());
//		res.add(getRight_segment());
//		return res;
		return null;														// vacio EMPTY NAAAAA
	}
 
	@Override
	public PhysicalRectangle getPhysicalRectangle() {
		
		return this;
	}
	@Override
	public Rectangle getCollisionedRectangle(Physical_passive actor) {
		return intersection(actor.getPhysicalRectangle());
	}
	@Override
	public boolean hasToDie() {
		// TODO Auto-generated method stub
		return false;
	}


}
