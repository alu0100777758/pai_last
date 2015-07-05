package es.ull.etsii.pai.practicafinal.metaclass;



import java.awt.Color;
import java.awt.Graphics;

import es.ull.etsii.pai.practicafinal.physics.MovementEquation;
import es.ull.etsii.pai.practicafinal.physics.RectilinearLocomotion;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public abstract class CreditText {
	private String text;
	private Point2D pos;
	private Point2D speed;
	private MovementEquation movementEquation;
	private Color color;
	
	public CreditText(Point2D pos, Point2D speed, String text) {
		setPos(pos);
		setSpeed(speed);
		setText(text);
		setMovementEquation(new RectilinearLocomotion());
		setColor(Color.WHITE);
	}
	
	public CreditText(Point2D pos, Point2D speed, String text, Color color) {
		this(pos, speed, text);
		
		setColor(color);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void update() {
		setPos(getMovementEquation().getNewpos(getSpeed(), getPos()));
	}
	/**
	 * De esta forma se pueden pintar titulos y otras cosas de distintas formas facilmente.
	 * @param g
	 */
	public abstract void paint(Graphics g);
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Point2D getPos() {
		return pos;
	}

	public void setPos(Point2D pos) {
		this.pos = pos;
	}

	public Point2D getSpeed() {
		return speed;
	}

	public void setSpeed(Point2D speed) {
		this.speed = speed;
	}

	public MovementEquation getMovementEquation() {
		return movementEquation;
	}

	public void setMovementEquation(MovementEquation movementEquation) {
		this.movementEquation = movementEquation;
	}
	
}
