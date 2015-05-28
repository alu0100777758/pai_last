package es.ull.etsii.pai.prct9.geometry;

public class Circle extends Geometry {
	private Point2D center;
	private double radius;

	public Point2D getCenter() {
		return center;
	}

	public void setCenter(Point2D center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Circle(double radius, Point2D center) {
		this.radius = radius;
		this.center = center;
	}
}
