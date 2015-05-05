package es.ull.etsii.pai.practicafinal;

import java.awt.Graphics;

import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Actor extends Entity {
	private Point2D position;
	private PhysicalRectangle physicalShape;
	
	public Actor(Point2D position) {
		setPosition(position);
	}
	@Override
	public void reproduce() {
		// TODO Auto-generated method stub

	}
	/**
	 * Pinta el actor en las coordenadas en las que está.
	 * @param g
	 */
	public void paint(Graphics g) {
		
	}
	public Point2D getPosition() {
		return position;
	}
	public void setPosition(Point2D position) {
		this.position = position;
	}
	public void setXPosition(double xposition) {
		this.position.setX(xposition);
	}
	public void setYPosition(double yposition) {
		this.position.setY(yposition);
	}
	public void addXPosition(double xposition) {
		this.position.setX(this.position.x() + xposition);
	}
	public void addYPosition(double yposition) {
		this.position.setY(this.position.y() + yposition);
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
}
