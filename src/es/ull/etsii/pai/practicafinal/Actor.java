package es.ull.etsii.pai.practicafinal;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import javax.swing.JEditorPane;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.prct9.geometry.Point2D;
/**
 * 
 * @author Sabato Ceruso, Javier Martin Hernandez.
 *
 */
public class Actor extends Entity {
	private Point2D position;
	private PhysicalRectangle physicalShape ;
	private ArrayList<GraphicRectangle> graphicShapes;
	
	public Actor(Point2D position) {
		physicalShape = new PhysicalRectangle((int)position.x(), (int)position.y(), 10, 20); // cambiar!
		setPosition(position);
		setGraphicShapes(new ArrayList<GraphicRectangle>());
	}
	
	public void die(){
		
	}
	@Override
	public void reproduce() {
		// TODO Auto-generated method stub

	}
	/**
	 * Pinta el actor en las coordenadas en las que est�.
	 * @param g
	 */
	public void paint(Graphics g) {
		
	}
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
	public void addXPosition(double xposition) {
		this.position.setX(this.position.x() + xposition);
		this.physicalShape.setLocation((int)position.x(), (int)position.y());
	}
	public void addYPosition(double yposition) {
		this.position.setY(this.position.y() + yposition);
		this.physicalShape.setLocation((int)position.x(), (int)position.y());
	}
	public PhysicalRectangle getPhysicalShape() {
		return physicalShape;
	}
	public void setPhysicalShape(PhysicalRectangle physicalShape) {
		this.physicalShape = physicalShape;
	}
	public void addPosition(Point2D point){
		setPosition(getPosition().add(point));
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
