package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.metaclass.Weapon;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_active;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class Player extends Actor implements Physical_active {

	private Point2D speed; 		// Vector velocidad.

	private int maxJumpTTL = 20;
	private double climbPertTick = 1;
	private Side lookingAt;
	private Weapon weapon;
	private boolean block_up = false;
	private boolean block_down = false;
	private boolean block_left = false;
	private boolean block_right = false;
	private int jumpTTL = 0;
	private boolean move_up = false;
	private boolean move_down = false;
	private boolean move_left = false;
	private boolean move_right = false;
	private boolean crounched = false;
	private boolean shooting = false;
	public static final int WIDTH = 10;
	public int HEIGHT = 20;
	public static final int SPEED = 5;
	public static final double TIME = 1.0;
	public static double GRAVITY = -5.0;
	private	Color color = Color.BLUE; // error, usar rectangulo gráfico
	
	
	public Player(Point2D position) {
		super(position);
		setSpeed(new Point2D(0, 0));
		setPhysicalShape(new PhysicalRectangle((int)getPosition().x(), (int)getPosition().y(), WIDTH, HEIGHT));
		setLookingAt(Side.RIGHT);
		//setWeapon(new Pistol());
	}
	
	public void setJump(int height , double timeSeconds ){
		setMaxJumpTTL((int)(60*timeSeconds));
		setClimbPertTick((double)height/getMaxJumpTTL());
	}
	public Color getColor() {
		return color;
	}
	
	public double getClimbPertTick() {
		return climbPertTick;
	}
	public void setClimbPertTick(double climbPertTick) {
		this.climbPertTick = climbPertTick;
	}
	public void setColor(Color color) {
		this.color = color;
	}

<<<<<<< HEAD
=======
	public Player(Point2D position) {
		super(position);
		setSpeed(new Point2D(0, 0));
		setPhysicalShape(new PhysicalRectangle((int)getPosition().x(), (int)getPosition().y(), WIDTH, HEIGHT));
		setLookingAt(Side.RIGHT);
		setJump(100, 0.333333);
	}
	
>>>>>>> 0a7adf517c469eaae20bd177708e7d4d37817217
	@Override
	public void paint(Graphics g) {
		g.setColor(getColor());
		g.fillRect((int) getPosition().x(), (int) getPosition().y(),
				(int) WIDTH, (int) HEIGHT);
	}

	public boolean moveLeft() {
		getSpeed().setX(-SPEED);
		setBlock_right(false);
		//setLookingAt(Side.LEFT);
		return true;
	}

	public boolean moveRight() {
		getSpeed().setX(SPEED);
		setBlock_left(false);
		//setLookingAt(Side.RIGHT);
		return true;
	}

	public boolean moveUP() {
		getPosition().setY(getPosition().y() - 10);
		HEIGHT = 20;
		setPhysicalShape(new PhysicalRectangle((int)getPosition().x(), (int)getPosition().y(), WIDTH, HEIGHT));
		setUP(false);
		setCrounched(false);
		return true;
	}

	public boolean moveDown() {
		getPosition().setY(getPosition().y() + 10);
		HEIGHT = 10;
		setPhysicalShape(new PhysicalRectangle((int)getPosition().x(), (int)getPosition().y(), WIDTH, HEIGHT));
		setCrounched(true);
		return true;
	}
	public Bullet shoot ()  {
		int side = getLookingAt() == Side.LEFT? -20 : 20;
		return new Bullet(getPosition(), new Point2D (side, 0));
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
		if ((actor.getPhysicalRectangle().contains(new Point((int)getPhysicalRectangle().getMinX(), (int)getPhysicalRectangle().getMinY()))  || 
				actor.getPhysicalRectangle().contains(new Point((int)getPhysicalRectangle().getMaxX(), (int)getPhysicalRectangle().getMinY())) ) && !isBlock_down()) {
			if (Math.abs(getSpeed().y()) >= intersection.getHeight()) {
				this.setPosition(getPosition().add(new Point2D(0, intersection.getHeight()))); // Tocado con la cabeza
				repaired = true;
				setJumpTTL(0);
				setBlock_up(true);
			}
		}
		// Si alguno es true colisiona con los pies.
		else if ((actor.getPhysicalRectangle().contains(new Point((int)getPhysicalRectangle().getMinX(), (int)getPhysicalRectangle().getMaxY()))  || 
				actor.getPhysicalRectangle().contains(new Point((int)getPhysicalRectangle().getMaxX(), (int)getPhysicalRectangle().getMaxY()))) && !isBlock_up()) {
			if (Math.abs(getSpeed().y()) >= intersection.getHeight()) {	
				this.setPosition(getPosition().add(new Point2D(0, -intersection.getHeight())));// Tocado con los pies.
				repaired = true;
				setBlock_down(true);
			}
		}
		if (!repaired) {
			if (Math.abs(2 * getSpeed().x()) >= intersection.getWidth()) {							// Comentar esto, buscar solucion mejor que multiplicar por 2.
				if (getSpeed().x() > 0) {
					this.setPosition(getPosition().substract(intersection.getWidth(), 0));
					setBlock_right(true);
				}
				else {
					this.setPosition(getPosition().add(intersection.getWidth(), 0));
					setBlock_left(true);
				}
				
			}
		}
		//System.out.println("Vel: " + getSpeed().x() + " ancho: " + intersection.getWidth() + " alto: " + intersection.getHeight());			//
		return false;
	}

	void ResolveUnreleasedMovements(){
		if(isMove_down() && !isCrounched())
			moveDown();
		if(isMove_left())
			moveLeft();
		if(isMove_right())
			moveRight();
		if(isMove_up())
			moveUP();
		setBlock_down(false);
		if (!isMove_left() && !isMove_right())
			getSpeed().setX(0);
	}
	/**
	 * Mueve el jugador seg�n marca la velocidad.
	 */
	@Override
	public boolean updatePos(Physical_passive map) {
		ResolveUnreleasedMovements();
		if (!isBlock_down()) {													// Por lo visto esto controla el salto
			if (getJumpTTL() != 0) {
				moveJump();
			} else																// Y este 3 es la gravedad., lo paso a un metodo de actor para decirle q empiece a caer
				fall();
		}
		setPosition(getPosition().add(getSpeed()));								// Aqui es donde realmente cambiamos la posicion una vez calculado donde va a ir.
		return true;
	}


	public void jump() {
		if(isBlock_down()){
			setJumpTTL(getMaxJumpTTL());
			setBlock_down(false);
		}	
	}

	public void moveJump() {
		getSpeed().setY(-getClimbPertTick());
		setJumpTTL(getJumpTTL() - 1);
		
	}

	public void fall() {
		getSpeed().setY(-GRAVITY);
		setBlock_up(false);
	}
	
	@Override
	public PhysicalRectangle getPhysicalRectangle() {
		return getPhysicalShape();
		
	}

	@Override
	public Rectangle getCollisionedRectangle(Physical_passive actor) {
		return getPhysicalRectangle().getCollisionedRectangle(actor.getPhysicalRectangle());
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
	public Point2D getSpeed() {
		return speed;
	}

	public void setSpeed(Point2D speed) {
		this.speed = speed;
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

	public int getMaxJumpTTL() {
		return maxJumpTTL;
	}

	public void setMaxJumpTTL(int velY) {
		this.maxJumpTTL = velY;
	}

	public boolean isCrounched() {
		return crounched;
	}

	public void setCrounched(boolean crounched) {
		this.crounched = crounched;
	}

	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public Side getLookingAt() {
		return lookingAt;
	}

	public void setLookingAt(Side lookingAt) {
		this.lookingAt = lookingAt;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
}
