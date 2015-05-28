package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.RectangularShape;

import es.ull.etsii.pai.practicafinal.graphics.Drawable;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;

public class GraphicEntity extends Entity implements Drawable{
	GraphicRectangle graphic = null;		// Rectangulo grafico que representa a la entidad.
	
	/**
	 * Crea una entidad grafica a partir de un rectangulo grafico.
	 * @param rect
	 */
	public GraphicEntity(GraphicRectangle rect) {
		super();
		setGraphic(rect);
	}
	@Override
	public void paint(Graphics g) {
		getGraphic().paint(g);
	}
	/**
	 * Getters y Setters.
	 * @return
	 */
	public GraphicRectangle getGraphic() {
		return graphic;
	}

	public void setGraphic(GraphicRectangle graphic) {
		this.graphic = graphic;
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
	@Override
	public Object clone(){
		Object newobject = new GraphicEntity((GraphicRectangle)getGraphic().clone());
		return newobject; 
	}
	
}
