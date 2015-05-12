package es.ull.etsii.pai.practicafinal.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.io.IOException;

import es.ull.etsii.pai.practicafinal.Drawable;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class GraphicRectangle extends Rectangle implements Drawable {
	// private Color color = Color.RED;
	private Paint paint = Color.RED;

	public GraphicRectangle(int x1, int y1, int width, int height) {
		super(x1, y1, width, height);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(getPaint());
		g2.fill(this);
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint color) {
		this.paint = color;
	}

}
