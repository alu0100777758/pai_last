package es.ull.etsii.pai.practicafinal.physics;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import es.ull.etsii.pai.prct9.geometry.Point2D;

public interface MovementEquation {
	public Point2D getNewpos(Point2D vector, Point2D oldPos);
	public int getNewX(Point2D vector, Point2D oldPos);
	public int getNewY(Point2D vector, Point2D oldPos);
}
