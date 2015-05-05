package es.ull.etsii.pai.prct9.geometry;

public class Rectangle extends Geometry implements Resizable{
	private Point2D start;
	private Point2D end;
	
	
	public Point2D getStart() {
		return start;
	}

	public void setStart(Point2D start) {
		this.start = start;
	}

	public Point2D getEnd() {
		return end;
	}

	public void setEnd(Point2D end) {
		this.end = end;
	}

	public Segment getTop_segment() {
		return new Segment(start, (new Point2D(end.x(), start.y())));
	}

	public Segment getLeft_segment() {
		return new Segment(start, (new Point2D(start.x(), end.y())));
	}

	public Segment getRight_segment() {
		return new Segment((new Point2D(end.x(), start.y())), end);
	}

	public Segment getBottom_segment() {
		return new Segment((new Point2D(start.x(), end.y())), end);
	}

	public Rectangle(Point2D start, Point2D end) {
		setStart(start);
		setEnd(end);
	}
	public void setWidth(double width){
		end.setX(start.x()+width);
	}
	public void setHeight(double height){
		end.setY(start.y()+height);
	}
	public double getWidth() {
		return getTop_segment().lenght();
	}

	public double getHeight() {
		return getLeft_segment().lenght();
	}

	public int getMinX() {
		return (int)Math.min(getStart().x(), getEnd().x());
	}

	public int getMinY() {
		return (int)Math.min(getStart().y(), getEnd().y());
	}

	public int getMaxX() {
		return (int)Math.max(getStart().x(), getEnd().x());
	}

	public int getMaxY() {
		return (int)Math.max(getStart().y(), getEnd().y());
	}

}
