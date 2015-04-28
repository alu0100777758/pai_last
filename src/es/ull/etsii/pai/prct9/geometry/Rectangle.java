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
		return new Segment(start, start.add(new Point2D(end.x(), 0)));
	}

	public Segment getLeft_segment() {
		return new Segment(start, start.add(new Point2D(0, end.y())));
	}

	public Segment getRight_segment() {
		return new Segment(start.add(new Point2D(end.x(), 0)), end);
	}

	public Segment getBottom_segment() {
		return new Segment(start.add(new Point2D(0, end.y())), end);
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
}
