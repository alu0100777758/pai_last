package es.ull.etsii.pai.prct9;

import java.awt.Color;
import java.awt.Graphics;

import es.ull.etsii.pai.prct9.geometry.Circle;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class DrawableCircle extends Circle implements Drawable {
	private Color color = Color.RED;
	private boolean filled = false;
	DrawableCircle(double radius, Point2D center) {
		super(radius, center);
	}
	DrawableCircle(double radius, Point2D center, boolean filled) {
		super(radius, center);
		this.filled = filled;
	}
	public void setColor(Color color){
		this.color = color;
	}
	public void paint(Graphics g) {
		 int xRectangle = (int)(getCenter().x()-(getRadius()));
		 int yRectangle = (int)(getCenter().y()-(getRadius()));
		g.setColor(color);
		if(filled){
			g.fillOval(xRectangle, yRectangle, (int)getRadius()*2, (int)getRadius()*2);
		}
		else
			g.drawOval(xRectangle, yRectangle, (int)getRadius()*2, (int)getRadius()*2);
	}
	public Point2D getPos() {
		return getCenter();
	}
	public void setPos(Point2D newpos) {
		setCenter(newpos);
//		System.out.println("pos: (" + getCenter().x() + "," + getCenter().y()+")");
	}

}
