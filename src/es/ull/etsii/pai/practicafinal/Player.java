package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;

import es.ull.etsii.pai.prct9.geometry.EcuacionesMovimientoParabolico;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Player extends Actor {

	private Point2D speed;							// Vector velocidad.
	
	public static final int WIDTH = 10;
	public static final int HEIGHT = 20;
	public static final int SPEED = 5;
	public static final double TIME = 0.5;
	
	public Player(Point2D position) {
		super(position);
		setSpeed(new Point2D(0,0));
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		updatePosition();
		g.fillRect((int)getPosition().x(), (int)getPosition().y(), 10, 20);
	}
	public void updatePosition() {
		setPosition(EcuacionesMovimientoParabolico.posicion(getPosition(), getSpeed(), TIME, -9.0));
		setSpeed(EcuacionesMovimientoParabolico.velocidadInstantanea(getSpeed(), TIME, -9.0));
	}
	public boolean moveLeft() {
		getSpeed().setX(-SPEED);
		return true;
	}
	public boolean moveRight() {
		getSpeed().setX(SPEED);
		return true;
	}
	public Point2D getSpeed() {
		return speed;
	}
	public void setSpeed(Point2D speed) {
		this.speed = speed;
	}
	
	
}
