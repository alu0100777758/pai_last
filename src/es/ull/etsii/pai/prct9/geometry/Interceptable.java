package es.ull.etsii.pai.prct9.geometry;

/**
 * @author Javier Martín Hernández
 * Interfaz que permite discernir si un objeto intercepta o es interceptado por un punto o segmento.
 */
public interface Interceptable extends Positionable{
	boolean belongs(Point2D point);
	boolean intercepts(Segment segment);
	double interceptionDistance(Point2D point);
	double interceptionDistance(Segment segment);
	Point2D getCentroid();
}
