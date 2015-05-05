package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_active;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.EcuacionesMovimientoParabolico;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class Player extends Actor implements Physical_active {

	private Point2D speed; // Vector velocidad.
	private Point2D movement; // Vector que indica el angulo y cantidad que se
								// movera.
	private int velX = 0;
	private int velY = 0;
	private boolean block_up = false;
	private boolean block_down = false;
	private boolean block_left = false;
	private boolean block_right = false;

	private boolean move_up = false;
	private boolean move_down = false;
	private boolean move_left = false;
	private boolean move_right = false;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 20;
	public static final int SPEED = 1;
	public static final double TIME = 1.0;
	public static double GRAVITY = -0.0;

	public boolean isMove_up() {
		return move_up;
	}

	public void setMove_up(boolean move_up) {
		this.move_up = move_up;
	}

	public boolean isMove_down() {
		return move_down;
	}

	public void setMove_down(boolean move_down) {
		this.move_down = move_down;
	}

	public boolean isMove_left() {
		return move_left;
	}

	public void setMove_left(boolean move_left) {
		this.move_left = move_left;
	}

	public boolean isMove_right() {
		return move_right;
	}

	public void setMove_right(boolean move_right) {
		this.move_right = move_right;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public Player(Point2D position) {
		super(position);
		setMovement(new Point2D(0, 0));
		setSpeed(new Point2D(0, 0));
		setPhysicalShape(new PhysicalRectangle((int)getPosition().x(), (int)getPosition().y(), WIDTH, HEIGHT));
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) getPosition().x(), (int) getPosition().y(),
				(int) WIDTH, (int) HEIGHT);
	}

//	public void updatePosition() {
//		// setPosition(EcuacionesMovimientoParabolico.posicion(getPosition(),
//		// getSpeed(), TIME, GRAVITY));
//		// setPosition(getPosition().add(getMovement()));
//		// getPhysicalShape().setPos(getPosition());
//		// if (onPlatform)
//		// GRAVITY = 0;
//		// setSpeed(EcuacionesMovimientoParabolico.velocidadInstantanea(getSpeed(),
//		// TIME, GRAVITY));
//
//	}

	public Point2D updateMovement() {
		Point2D newmove = EcuacionesMovimientoParabolico.posicion(
				getPosition(), getSpeed(), TIME, GRAVITY);
		getPhysicalShape().setLocation(new Point((int)newmove.x(),(int)newmove.y()));
		setMovement(newmove.substract(getPosition()));
		return getMovement();
	}

	public boolean moveLeft() {
		addXPosition(-SPEED);
		return true;
	}

	public boolean moveRight() {
		// getSpeed().setX(SPEED);
		addXPosition(SPEED);
		return true;
	}

	public boolean moveUP() {
		// getSpeed().setX(SPEED);
		addYPosition(-SPEED);
		return true;
	}

	public boolean moveDown() {
		// getSpeed().setX(SPEED);
		addYPosition(SPEED);
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

	
	@Override
	public boolean repair_collisionY(Point2D point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean repair_collisionX(Point2D point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collides(Physical_passive actor) {
		return this.getPhysicalShape().collides(actor);
	}

	@Override
	public boolean repair_collision(Physical_passive actor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Segment> getSegmentList() {
		return getPhysicalShape().getSegmentList();
	}
	public void setLeft(boolean b) {
		setMove_left(b);
		ResolveUnreleasedMovements();
	}

	public void setRight(boolean b) {
		setMove_right(b);
		ResolveUnreleasedMovements();
	}

	public void setUP(boolean b) {
		setMove_up(b);
		ResolveUnreleasedMovements();
	}

	public void setDown(boolean b) {
		setMove_down(b);
		ResolveUnreleasedMovements();
	}
	void ResolveUnreleasedMovements(){
		if(isMove_down())
			moveDown();
		if(isMove_left())
			moveLeft();
		if(isMove_right())
			moveRight();
		if(isMove_up())
			moveUP();
	}
	@Override
	public void updatePos() {
		ResolveUnreleasedMovements();
//		System.out.println(getSpeed());
//		addPosition(getSpeed());
//		System.out.println(getPosition());
	}

	@Override
	public PhysicalRectangle getPhysicalRectangle() {
		return getPhysicalShape();
		
	}

}
