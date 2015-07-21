package es.ull.etsii.pai.practicafinal.physics;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Point;

import es.ull.etsii.pai.prct9.geometry.Point2D;

public class RectilinearLocomotion implements MovementEquation {

	@Override
	public Point2D getNewpos(Point2D vector, Point2D oldPos) {
		Point2D res =  new Point2D(0,0).add(oldPos);
		return res.add(vector);
	}

	@Override
	public int getNewX(Point2D vector, Point2D oldPos) {
		return (int) (oldPos.x() + vector.x());
	}

	@Override
	public int getNewY(Point2D vector, Point2D oldPos) {
		return (int) (oldPos.y() + vector.y());
	}



}
