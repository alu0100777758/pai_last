package es.ull.etsii.pai.practicafinal.graphics;

import java.awt.Color;
import java.awt.Graphics;

import es.ull.etsii.pai.prct9.Drawable;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Rectangle;

public class GraphicRectangle extends Rectangle implements Drawable{
	private Color color;
	
	public GraphicRectangle(Point2D start, Point2D end) {
		super(start, end);
		setColor(Color.RED);
	}

	@Override
	public Point2D getPos() {
		// TODO Auto-generated method stub
		return getStart();
	}

	@Override
	public void setPos(Point2D newpos) {
		setStart(newpos);
		
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(getColor());
		
		g.fillRect((int)getStart().x(), (int)getStart().y(), (int)getWidth(), (int)getHeight());
		
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
}
