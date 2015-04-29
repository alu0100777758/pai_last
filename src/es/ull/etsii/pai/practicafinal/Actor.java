package es.ull.etsii.pai.practicafinal;

import java.awt.Graphics;

import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Actor extends Entity {
	private Point2D position;
	
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
	
	
}
