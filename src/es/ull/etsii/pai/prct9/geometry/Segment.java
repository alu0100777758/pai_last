package es.ull.etsii.pai.prct9.geometry;

public class Segment extends Line {

	public Segment(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
	}
	public Segment(Point2D first, Point2D second) {
		super(first, second);
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
		double min;
		min = Math.min(lineToPoint(point), (new Segment(getFirstPoint(), point)).lenght());
		min = Math.min(min,(new Segment(getSecondPoint(), point)).lenght());
		return min;	
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
