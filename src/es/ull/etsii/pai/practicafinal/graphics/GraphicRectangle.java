package es.ull.etsii.pai.practicafinal.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import es.ull.etsii.pai.practicafinal.Drawable;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class GraphicRectangle extends Rectangle implements Drawable {
	private Color color = Color.RED;

	public GraphicRectangle(int x1, int y1, int width, int height) {
		super(x1, y1, width, height);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(getColor());
		g2.fill(this);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
