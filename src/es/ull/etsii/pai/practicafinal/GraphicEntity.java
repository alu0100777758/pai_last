package es.ull.etsii.pai.practicafinal;

import java.awt.Dimension;
import java.awt.geom.RectangularShape;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;

public class GraphicEntity extends Entity {
	GraphicRectangle graphic = null;
	
	public GraphicRectangle getGraphic() {
		return graphic;
	}

	public void setGraphic(GraphicRectangle graphic) {
		this.graphic = graphic;
	}
	public GraphicEntity(GraphicRectangle rect) {
		super();
		setGraphic(rect);
	}
	@Override
	public void reproduce() {
		// TODO Auto-generated method stub

	}

	@Override
	public RectangularShape getShape() {
		return getGraphic();
	}

	@Override
	public void setLocation(int x, int y) {
		getGraphic().setLocation(x, y);
	}

	@Override
	public int getX() {
		return (int)getGraphic().getX();
	}

	@Override
	public int gety() {
		return (int)getGraphic().getY();
	}

	@Override
	public void setSize(int width, int height) {
		getGraphic().setSize(new Dimension(width,height));
	}
}
