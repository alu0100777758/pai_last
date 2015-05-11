package es.ull.etsii.pai.practicafinal.physics;

import java.awt.Point;

import es.ull.etsii.pai.prct9.geometry.Point2D;

public interface MovementEquation {
	public Point2D getNewpos(Point2D vector, Point2D oldPos);
	public int getNewX(Point2D vector, Point2D oldPos);
	public int getNewY(Point2D vector, Point2D oldPos);
}
