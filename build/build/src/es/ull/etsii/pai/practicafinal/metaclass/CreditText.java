package es.ull.etsii.pai.practicafinal.metaclass;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */

import java.awt.Color;
import java.awt.Graphics;

import es.ull.etsii.pai.practicafinal.physics.MovementEquation;
import es.ull.etsii.pai.practicafinal.physics.RectilinearLocomotion;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public abstract class CreditText {
	private String text;							// Texto a mostrar.
	private Point2D pos;							// Posicion del texto.
	private Point2D speed;							// Vector de velocidad hacia donde se mueve.
	private MovementEquation movementEquation;		// Ecuacion de movimiento que rige su movimiento.
	private Color color;							// Color del texto.
	
	/**
	 * Crea el texto con los parametros dados.
	 * @param pos
	 * @param speed
	 * @param text
	 */
	public CreditText(Point2D pos, Point2D speed, String text) {
		setPos(pos);
		setSpeed(speed);
		setText(text);
		setMovementEquation(new RectilinearLocomotion());
		setColor(Color.WHITE);
	}
	/**
	 * Crea el texto con los parametros dados.
	 * @param pos
	 * @param speed
	 * @param text
	 * @param color
	 */
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

	/**
	 * Mueve el texto a la siguiente posicion segun su velocidad.
	 */
	public void update() {
		setPos(getMovementEquation().getNewpos(getSpeed(), getPos()));
	}
	/**
	 * De esta forma se pueden pintar titulos y otras cosas que se comporten distinto de varias formas facilmente.
	 * @param g
	 */
	public abstract void paint(Graphics g);
	
	/**
	 * Setters y Getters.
	 * @return
	 */
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
