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
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Actor extends Entity {
	private Point2D position;							// Posicion del actor.
	private PhysicalRectangle physicalShape ;			// Forma fisica.
	private ArrayList<GraphicRectangle> graphicShapes;	// Lista de formas graficas que representan al actor.
	
	/**
	 * Crea un actor en la posicion determinada.
	 * @param position
	 */
	public Actor(Point2D position) {
		physicalShape = new PhysicalRectangle((int)position.x(), (int)position.y(), 10, 20); // cambiar!
		setPosition(position);
		setGraphicShapes(new ArrayList<GraphicRectangle>());
	}
	/**
	 * Funcion de morir. 
	 * Se invoca cada vez que un actor o derivados debe ejecutar una serie de acciones antes de ser eliminado.
	 */
	public void die(){}
	/**
	 * Pinta el actor en las coordenadas en las que est�.
	 * @param g
	 */
	public void paint(Graphics g) {
		
	}
	/**
	 * Mueve la posicion del actor en el eje X tanto como indica el parametro.
	 * @param xposition
	 */
	public void addXPosition(double xposition) {
		this.position.setX(this.position.x() + xposition);
		this.physicalShape.setLocation((int)position.x(), (int)position.y());
	}
	/**
	 * Mueve la posicion del actor en el eje Y tanto como indica el parametro.
	 * @param xposition
	 */
	public void addYPosition(double yposition) {
		this.position.setY(this.position.y() + yposition);
		this.physicalShape.setLocation((int)position.x(), (int)position.y());
	}
	/**
	 * Mueve la posicion del actor en ambos ejes tanto como indica el parametro.
	 * @param xposition
	 */
	public void addPosition(Point2D point){
		setPosition(getPosition().add(point));
	}
	
	/**
	 * Getters y Setters.
	 * @return
	 */
	public Point2D getPosition() {
		return position;
	}
	public void setPosition(Point2D position) {
		this.position = position;
		this.physicalShape.setLocation((int)position.x(), (int)position.y());
	}
	public void setXPosition(double xposition) {
		this.position.setX(xposition);
		this.physicalShape.setLocation((int)position.x(), (int)position.y());
	}
	public void setYPosition(double yposition) {
		this.position.setY(yposition);
		this.physicalShape.setLocation((int)position.x(), (int)position.y());
	}
	public PhysicalRectangle getPhysicalShape() {
		return physicalShape;
	}
	public void setPhysicalShape(PhysicalRectangle physicalShape) {
		this.physicalShape = physicalShape;
	}
	@Override
	public RectangularShape getShape() {
		return getPhysicalShape();
	}
	@Override
	public void setLocation(int x, int y) {
		setPosition(new Point2D(x, y));
	}
	@Override
	public int getX() {
		return (int)getPosition().x();
	}
	@Override
	public int gety() {
		return (int)getPosition().y();
	}
	public ArrayList<GraphicRectangle> getGraphicShapes() {
		return graphicShapes;
	}
	public void setGraphicShapes(ArrayList<GraphicRectangle> graphicShapes) {
		this.graphicShapes = graphicShapes;
	}

	public void setSize(int width, int height) {
		for(Rectangle rect : getGraphicShapes())
			rect.setSize(new Dimension(width,height));
		getPhysicalShape().setSize(new Dimension(width,height));
	}
	
}
