package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
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
	private int jumpSpeed = 5;
	private boolean block_up = false;
	private boolean block_down = false;
	private boolean block_left = false;
	private boolean block_right = false;
	private int jumpTTL = 0;
	private boolean move_up = false;
	private boolean move_down = false;
	private boolean move_left = false;
	private boolean move_right = false;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 20;
	public static final int SPEED = 2;
	public static final double TIME = 1.0;
	public static double GRAVITY = -5.0;

	
	public Player(Point2D position) {
		super(position);
		setMovement(new Point2D(0, 0));
		setSpeed(new Point2D(0, 0));
		setPhysicalShape(new PhysicalRectangle((int)getPosition().x(), (int)getPosition().y(), WIDTH, HEIGHT));
	}
	
	public int getJumpTTL() {
		return jumpTTL;
	}

	public void setJumpTTL(int jumpTTL) {
		this.jumpTTL = jumpTTL;
	}

	public boolean isBlock_up() {
		return block_up;
	}

	public void setBlock_up(boolean block_up) {
		this.block_up = block_up;
	}

	public boolean isBlock_down() {
		return block_down;
	}

	public void setBlock_down(boolean block_down) {
		this.block_down = block_down;
	}

	public boolean isBlock_left() {
		return block_left;
	}

	public void setBlock_left(boolean block_left) {
		this.block_left = block_left;
	}

	public boolean isBlock_right() {
		return block_right;
	}

	public void setBlock_right(boolean block_right) {
		this.block_right = block_right;
	}

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

	public int getJumpVelY() {
		return jumpSpeed;
	}

	public void setVelY(int velY) {
		this.jumpSpeed = velY;
	}



	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) getPosition().x(), (int) getPosition().y(),
				(int) WIDTH, (int) HEIGHT);
	}


	public boolean moveLeft() {
		addXPosition(-SPEED);
		setVelX(-SPEED);
		return true;
	}

	public boolean moveRight() {
		addXPosition(SPEED);
		setVelX(SPEED);
		return true;
	}

	public boolean moveUP() {
		addYPosition(-SPEED);
		return true;
	}

	public boolean moveDown() {
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
		
		
		return false;
	}

	@Override
	public boolean collides(Physical_passive actor) {
		return this.getPhysicalShape().collides(actor);
	}

	/**
	 * Afinar esto.
	 */
	@Override
	public boolean repair_collision(Physical_passive actor) {
		Rectangle intersection = actor.getCollisionedRectangle(this.getPhysicalRectangle());
		boolean repaired = false;
		// Resolvemos colisiones primero en Y mejor.
		
		// Miramos si colisiona con la cabeza o los pies:
		
		// Si alguno es true colisiona con la cabeza. 
		if (actor.getPhysicalRectangle().contains(new Point((int)getPhysicalRectangle().getMinX(), (int)getPhysicalRectangle().getMinY()))  || 
				actor.getPhysicalRectangle().contains(new Point((int)getPhysicalRectangle().getMaxX(), (int)getPhysicalRectangle().getMinY())) ) {
			if (getJumpVelY() >= intersection.getHeight()) {
				this.setPosition(getPosition().add(new Point2D(0, intersection.getHeight()))); // Tocado con la cabeza
				repaired = true;
			}
		}
		// Si alguno es true colisiona con los pies.
		else if (actor.getPhysicalRectangle().contains(new Point((int)getPhysicalRectangle().getMinX(), (int)getPhysicalRectangle().getMaxY()))  || 
				actor.getPhysicalRectangle().contains(new Point((int)getPhysicalRectangle().getMaxX(), (int)getPhysicalRectangle().getMaxY())) ) {
			if (-GRAVITY >= intersection.getHeight()) {
				this.setPosition(getPosition().add(new Point2D(0, -intersection.getHeight())));;// Tocado con los pies.
				repaired = true;
			}
		}
			
		
		
		if (!repaired) {
			if (Math.abs(2 * getVelX()) >= intersection.getWidth()) {							// Comentar esto, buscar solucion mejor que multiplicar por 2.
				if (getVelX() > 0)
					this.setPosition(getPosition().substract(intersection.getWidth(), 0));
				else
					this.setPosition(getPosition().add(intersection.getWidth(), 0));
				
			}
		}
		System.out.println("Vel: " + getVelX() + " ancho: " + intersection.getWidth() + " alto: " + intersection.getHeight());
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
		setBlock_down(false);
		if (!isMove_left() && !isMove_right())
			setVelX(0);
	}
	@Override
	public void updatePos(Physical_passive map) {
		ResolveUnreleasedMovements();
		if (!isBlock_down()) {													// Por lo visto esto controla el salto
			if (getJumpTTL() != 0) {
				moveJump();
			} else																// Y este 3 es la gravedad., lo paso a un metodo de actor para decirle q empiece a caer
				fall();
		}
		if (collides(map)) {
			repair_collision(map);
			setBlock_down(true);
			System.out.println("collision");
		}
	}

	@Override
	public PhysicalRectangle getPhysicalRectangle() {
		return getPhysicalShape();
		
	}

	@Override
	public Rectangle getCollisionedRectangle(Physical_passive actor) {
		return getPhysicalRectangle().getCollisionedRectangle(actor.getPhysicalRectangle());
	}

	public void jump() {
		if(isBlock_down()){
			jumpTTL = 20;
			setBlock_down(false);
		}
		
	}

	public void moveJump() {
		addYPosition(-getJumpVelY());
		setJumpTTL(getJumpTTL() - 1);
		
	}

	public void fall() {
		addYPosition(- GRAVITY);
	}
}
