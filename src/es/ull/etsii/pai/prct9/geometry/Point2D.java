package es.ull.etsii.pai.prct9.geometry;

public class Point2D {
	private double x = 0;
	private double y = 0;

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double x() {
		return this.x;
	}

	public double y() {
		return this.y;
	}

	public void setX(double val) {
		this.x = val;
	}

	public void setY(double val) {
		this.y = val;
	}

	public Point2D add(Point2D other) {
		return new Point2D(this.x() + other.x(), this.y() + other.y());
	}

	public Point2D add(double x, double y) {
		return this.add(new Point2D(x, y));
	}
	
	public Point2D substract(Point2D other) {
		return new Point2D(this.x() - other.x(), this.y() - other.y());
	}
	
	public Point2D substract(double x, double y) {
		return this.substract(new Point2D(x, y));
	}

	public Point2D scalarProduct(double scalar) {
		return new Point2D(this.x() * scalar, this.y() * scalar);
	}

	public boolean equals(Point2D other) {
		return (this.x() == other.x()) && (this.y() == other.y());
	}
}
