package es.ull.etsii.pai.prct9.geometry;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Point;

public class Segment extends Line {

	public Segment(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
	}
	public Segment(Point2D first, Point2D second) {
		super(first, second);
	}
	public Segment(Point first, Point second) {
		super(new Point2D(first.getX(), first.getY()), new Point2D(second.getX(), second.getY()));
	}
	public Point2D start(){
		return super.getFirstPoint();
	}
	public Point2D end(){
		return super.getSecondPoint();
	}
	public double lenght(){
		Point2D cartesianVectorPoint = getSecondPoint().substract(getFirstPoint());
		return Math.sqrt(Math.pow(cartesianVectorPoint.x(),2) + Math.pow(cartesianVectorPoint.y(), 2));
	}
	public double segmentToPoint(Point2D point){
		Segment v = new Segment(new Point2D(0,0), getSecondPoint().substract(getFirstPoint()));
		Segment w = new Segment(new Point2D(0,0), point.substract(getFirstPoint()));
		// TODO añadir dotProduct a dot pasando un Collection o un punto aislado,  pensar en la estructura, ante nada mejor dejar v y w como puntos.
	     double c1 = w.getSecondPoint().x()*point.x() +  w.getSecondPoint().y()*point.y();
	     if ( c1 <= 0 )
	          return new Segment(point, getFirstPoint()).lenght();
	     double c2 = v.getSecondPoint().x()*v.getSecondPoint().x() +  v.getSecondPoint().y()*v.getSecondPoint().y(); // dot(v,v);
	     if ( c2 <= c1 )
	    	 return new Segment(point, getSecondPoint()).lenght();
	     return lineToPoint(point);
	}
	public double segmentToSegment(Segment other){
		double min;
		if(this.getGradient() == other.getGradient()){
			min = Math.min(segmentToPoint(other.getFirstPoint()),segmentToPoint(other.getSecondPoint()));
		}
		else{
			double localEndsToOtherSegment =  Math.min(segmentToPoint(other.getFirstPoint()),segmentToPoint(other.getSecondPoint()));
			double localSegmentToOtherEnds = Math.min(other.segmentToPoint(getFirstPoint()),other.segmentToPoint(getSecondPoint()));
			min = Math.min(localEndsToOtherSegment, localSegmentToOtherEnds);
		}
		return min;
	}
	public boolean equals(Segment other){
		return start().equals(other.start()) && end().equals(other.end());
	}

}
