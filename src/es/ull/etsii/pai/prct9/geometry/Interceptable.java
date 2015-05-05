package es.ull.etsii.pai.prct9.geometry;

/**
 * @author Javier Mart�n Hern�ndez
 * Interfaz que permite discernir si un objeto intercepta o es interceptado por un punto o segmento.
 */
public interface Interceptable extends Positionable{
	Rectangle interception(Rectangle target);
	void intercept(Rectangle r1, Rectangle r2, Rectangle res );
	boolean belongs(Point2D point);
	boolean intercepts(Segment segment);
	double interceptionDistance(Point2D point);
	double interceptionDistance(Segment segment);
	Point2D getCentroid();
}
