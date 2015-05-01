package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;

import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.prct9.geometry.EcuacionesMovimientoParabolico;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Player extends Actor {

	private Point2D speed;							// Vector velocidad.
	private Point2D movement;						// Vector que indica el angulo y cantidad que se movera.
	private boolean onPlatform;
	public static final double WIDTH = 10;
	public static final double HEIGHT = 20;
	public static final int SPEED = 45;
	public static final double TIME = 0.5;
	public static  double GRAVITY = -9.0;
	
	public Player(Point2D position) {
		super(position);
		setMovement(new Point2D(0,0));
		setSpeed(new Point2D(0,0));
		setPhysicalShape(new PhysicalRectangle(getPosition(), new Point2D((double)(getPosition().x() + WIDTH), (double)(getPosition().y() + HEIGHT))));
		setOnPlatform(false);
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		updatePosition();
		g.fillRect((int)getPosition().x(), (int)getPosition().y(), (int)WIDTH, (int)HEIGHT);
	}
	public void updatePosition() {
		//setPosition(EcuacionesMovimientoParabolico.posicion(getPosition(), getSpeed(), TIME, GRAVITY));
		setPosition(getPosition().add(getMovement()));
		getPhysicalShape().setPos(getPosition());
		if (onPlatform)
			GRAVITY = 0;
		setSpeed(EcuacionesMovimientoParabolico.velocidadInstantanea(getSpeed(), TIME, GRAVITY));
	}
	public Point2D updateMovement(){
		Point2D newmove = EcuacionesMovimientoParabolico.posicion(getPosition(), getSpeed(), TIME, GRAVITY);
		getPhysicalShape().setPos(newmove);
		setMovement(newmove.substract(getPosition()));
		return getMovement();
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
	public Point2D getMovement() {
		return movement;
	}
	public void setMovement(Point2D movement) {
		this.movement = movement;
	}
	public boolean isOnPlatform() {
		return onPlatform;
	}
	public void setOnPlatform(boolean onPlatform) {
		this.onPlatform = onPlatform;
	}

	
	
}
