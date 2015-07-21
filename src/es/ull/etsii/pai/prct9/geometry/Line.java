package es.ull.etsii.pai.prct9.geometry;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
public class Line {
	private Point2D firstPoint;
	private Point2D secondPoint;
	private Double gradient;

	protected Point2D getFirstPoint() {
		return firstPoint;
	}

	protected void setFirstPoint(Point2D firstPoint) {
		this.firstPoint = firstPoint;
	}

	protected Point2D getSecondPoint() {
		return secondPoint;
	}

	protected void setSecondPoint(Point2D secondPoint) {
		this.secondPoint = secondPoint;
	}

	protected Double getGradient() {
		return gradient;
	}

	protected void setGradient(Double gradient) {
		this.gradient = gradient;
	}
	Line(int x1, int y1, int x2, int y2) {
		this(new Point2D(new Double(x1), new Double(y1)),new Point2D(new Double(x2), new Double(y2)));
	}
	Line(Point2D first, Point2D second) {
		setFirstPoint(first);
		setSecondPoint(second);
		
		if (firstPoint.x() == secondPoint.x())
			gradient = Double.MAX_VALUE;
		else if (firstPoint.y() == secondPoint.y())
			gradient = 0.0;
		else
			gradient = (secondPoint.y() - firstPoint.y())
					/ (secondPoint.x() - firstPoint.x());

	}

	public double lineToPoint(Point2D point) {
		double numerator = Math.abs((secondPoint.y() - firstPoint.y())
				* point.x() - (secondPoint.x() - firstPoint.x()) * point.y()
				+ secondPoint.x() * firstPoint.y() - secondPoint.y()
				* firstPoint.x());
		double denominator = Math.sqrt(Math.pow(
				secondPoint.y() - firstPoint.y(), 2)
				+ Math.pow(secondPoint.x() - firstPoint.x(), 2));
		return numerator / denominator;
	}

	public boolean belongs(int x, int y) {
		if (gradient.equals(Double.MAX_VALUE))// misma columna
			return secondPoint.x() == (new Double(x));
		else if (gradient == 0.0)// misma fila
			return secondPoint.y() == (new Double(y));
		else
			return (y == equation(x));
	}

	private double equation(int x) {
		return (gradient * (x - firstPoint.x())) + firstPoint.y();
	}
}
