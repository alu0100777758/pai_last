package es.ull.etsii.pai.practicafinal;
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
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.metaclass.Weapon;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.Knife;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.RocketLauncher;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_active;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class Player extends Actor implements Physical_active {
	private static final long serialVersionUID = -3033119409170313204L;

	private Point2D speed; 									// Vector velocidad.
	private Point2D push;									// Vector de empuje.

	private int hp;											// Cantidad de vida actual.
	private int maxHp;										// Cantidad de vida maxima.	
	private int maxJumpTTL = 20;							// Numero maximo de frames de duracion de un salto.
	private double climbPertTick = 1;						// Cantidad de desplazamiento en el eje y por frame durante el salto.
	private Side lookingAt;									// Lado al que se esta mirando.
	private Weapon weapon;									// Arma actual.
	private BvsR_Map map;									// Mapa en que se encuentra el jugador.
	private boolean dead = false;							// True si esta muerto.
	private boolean block_up = false;						// True si no se puede mover hacia arriba.
	private boolean block_down = false;						// True si no se puede mover hacia abajo.
	private boolean block_left = false;						// True si no se puede mover a la izquierda.
	private boolean block_right = false;					// True si no se puede mover a la derecha.
	private int jumpTTL = 0;								// Numero de frames que puede seguir saltando.
	private boolean move_up = false;						// True si esta saltando.
	private boolean move_down = false;						// True si se esta agachando.
	private boolean move_left = false;						// True si se esta moviendo a la izquierda. 
	private boolean move_right = false;						// True si se esta moviendo a la derecha.
	private boolean crounched = false;						// True si esta agachado.
	private boolean shooting = false;						// True si esta disparando.
	public static final int WIDTH = 20;						// Ancho del personaje.
	public int HEIGHT = 40;									// Alto del personaje.
	public static final int SPEED = 5;						// Velocidad de movimiento por frame.
	public static final double TIME = 1.0;					// 
	public static double GRAVITY = -5.0;					// Gravedad que se le aplica al jugador.
	public static final int BODY = 0;						// Indice donde se guarda el grafico del cuerpo.
	public static final int WEAPON = 1;						// Indice donde se guarda el grafico del arma.
	public static final int DEFAULT_MAX_HP = 150;			// Vida maxima por defecto.
	public static final int PUSH_RESIST = 2;				// Resistencia al empuje por frame.
	private Color color = Color.BLUE; 
	private String [] hitSounds = {"playerhit01.wav","playerhit02.wav","playerhit03.wav",}; // Sonidos que emite al ser golpeado. 

	/**
	 * Crea un jugador en la posicion dada en el mapa dado.
	 * @param position
	 * @param map
	 */
	public Player(Point2D position, BvsR_Map map) {
		super(position);
		setHp(DEFAULT_MAX_HP);
		setMaxHp(DEFAULT_MAX_HP);
		setMap(map);
		setSpeed(new Point2D(0, 0));
		setPhysicalShape(new PhysicalRectangle((int) getPosition().x(),
				(int) getPosition().y(), WIDTH, HEIGHT));
		setLookingAt(Side.RIGHT);
		setPush(new Point2D(0, 0));
		setJump(100, 0.33);
		getGraphicShapes().add(
				new GraphicRectangle((int) getPosition().x(),
						(int) getPosition().y(), WIDTH, HEIGHT));
		getGraphicShapes().get(BODY).setPaint(Color.BLUE);
		getGraphicShapes().add(null);
		
//		setWeapon(new RocketLauncher(this));
		setWeapon(new Knife(this));
	}

	@Override
	public void paint(Graphics g) {
		for (int i = 0; i < getGraphicShapes().size(); i++)
			getGraphicShapes().get(i).paint(g.create());
	}

	@Override
	public boolean hasToDie() {
		return isDead();
	}
	
	/**
	 * Mueve el jugador a la izquierda.
	 * @return
	 */
	private boolean moveLeft() {
		getSpeed().setX(-SPEED);
		setBlock_right(false);
		return true;
	}
	
	/**
	 * Mueve el jugador a la derecha.
	 * @return
	 */
	private boolean moveRight() {
		getSpeed().setX(SPEED);
		setBlock_left(false);
		return true;
	}
	
	/**
	 * Levanta al jugador.
	 * @return
	 */
	private boolean moveUP() {
		HEIGHT = 40;
		getPosition().setY(getPosition().y() - HEIGHT / 2);

		getGraphicShapes().get(BODY).setLocation(
				new Point((int) getPosition().x(), (int) getPosition().y()));
		getGraphicShapes().get(BODY).setSize(WIDTH, HEIGHT);
		setPhysicalShape(new PhysicalRectangle((int) getPosition().x(),
				(int) getPosition().y(), WIDTH, HEIGHT));
		setUP(false);
		setCrounched(false);
		return true;
	}
	
	/**
	 * Hace que el jugador se agache.
	 * @return
	 */
	private boolean moveDown() {
		getPosition().setY(getPosition().y() + HEIGHT / 2);
		HEIGHT = 20;
		getGraphicShapes().get(BODY).setLocation(
				new Point((int) getPosition().x(), (int) getPosition().y()));
		getGraphicShapes().get(BODY).setSize(WIDTH, HEIGHT);
		setPhysicalShape(new PhysicalRectangle((int) getPosition().x(),
				(int) getPosition().y(), WIDTH, HEIGHT));
		setCrounched(true);
		return true;
	}
	
	/**
	 * Hace que el jugador dispare.
	 */
	public void shoot() {
		getWeapon().triggerMain();
		setShooting(true);
	}
	
	/**
	 * Hace que deje de disparar.
	 */
	public void stopShooting() {
		getWeapon().releaseMain();
		setShooting(false);
	}

	@Override
	public boolean repair_collisionY(Point2D point) {
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

	@Override
	public void setLocation(int x, int y) {
		super.setPosition(new Point2D(x, y));
		for (GraphicRectangle graphics : getGraphicShapes())
			graphics.setLocation(new Point((int) getPosition().x(),
					(int) getPosition().y()));
	};

	/**
	 * Afinar esto.
	 */
	@Override
	public boolean repair_collision(Physical_passive actor) {
		Rectangle intersection = actor.getCollisionedRectangle(this
				.getPhysicalRectangle());
		boolean repaired = false;
		if (intersection.isEmpty())
			return true;
		// Resolvemos colisiones primero en Y mejor.

		// Miramos si colisiona con la cabeza o los pies:

		// Si alguno es true colisiona con la cabeza.
		if ((actor.getPhysicalRectangle().contains(
				new Point((int) getPhysicalRectangle().getMinX(),
						(int) getPhysicalRectangle().getMinY())) || actor
				.getPhysicalRectangle().contains(
						new Point((int) getPhysicalRectangle().getMaxX(),
								(int) getPhysicalRectangle().getMinY())))
				&& !isBlock_down()) {
			if (Math.abs(getSpeed().y()) >= intersection.getHeight()) {
				this.setPosition(getPosition().add(
						new Point2D(0, intersection.getHeight()))); // Tocado
																	// con la
																	// cabeza
				repaired = true;
				setJumpTTL(0);
				setBlock_up(true);
			}
		}
		// Si alguno es true colisiona con los pies.
		else if ((actor.getPhysicalRectangle().contains(
				new Point((int) getPhysicalRectangle().getMinX(),
						(int) getPhysicalRectangle().getMaxY())) || actor
				.getPhysicalRectangle().contains(
						new Point((int) getPhysicalRectangle().getMaxX(),
								(int) getPhysicalRectangle().getMaxY())))
				&& !isBlock_up()) {
			if (Math.abs(getSpeed().y()) >= intersection.getHeight()) {
				this.setPosition(getPosition().add(
						new Point2D(0, -intersection.getHeight())));// Tocado
																	// con los
																	// pies.
				repaired = true;
				setBlock_down(true);
			}
		}
		if (!repaired) {
			if (Math.abs(2 * getSpeed().add(getPush()).x()) >= intersection
					.getWidth()) { // Comentar esto, buscar solucion mejor que
									// multiplicar por 2.
				if (getSpeed().add(getPush()).x() > 0) {
					this.setPosition(getPosition().substract(
							intersection.getWidth(), 0));
					setBlock_right(true);
				} else {
					this.setPosition(getPosition().add(intersection.getWidth(),
							0));
					setBlock_left(true);
				}

			}
		}
		// System.out.println("Vel: " + getSpeed().x() + " ancho: " +
		// intersection.getWidth() + " alto: " + intersection.getHeight()); //
		return false;
	}

	/**
	 * Realiza las operaciones necesarias cuando es golpeado por una bala.
	 * @param bullet
	 */
	public void gotHit(Bullet bullet) {
		if (bullet.getOwner() != this) {
			AudioManager.startAudio(bullet.getSoundName());
			if(!isDead())
				AudioManager.startAudio(getSoundName());
			setHp(getHp() - bullet.getDamage());
			if (bullet.getSpeed().x() > 0)
				getPush().setX(getPush().x() + bullet.getPush());
			else
				getPush().setX(getPush().x() - bullet.getPush());
			if (getHp() <= 0 && !isDead())
				die();
		}
	}



	/**
	 * Realiza las operaciones necesarias para que el jugador muera.
	 */
	public void die() {
		setDead(true);
		setHp(0);
		getGraphicShapes().get(BODY).setLocation((int) getPosition().x(),
				(int) getPosition().y() + HEIGHT - WIDTH);
		getGraphicShapes().get(BODY).setSize(HEIGHT, WIDTH);
		getPhysicalRectangle().setLocation((int) getPosition().x(),
				(int) getPosition().y() + HEIGHT - WIDTH);
		getPhysicalRectangle().setSize(HEIGHT, WIDTH);
		getGraphicShapes().remove(WEAPON);
	}

	/**
	 * Resuelve los movimientos que ha de realizar el jugador en el proximo frame.
	 */
	void ResolveUnreleasedMovements() {
		if (isMove_down() && !isCrounched())
			moveDown();
		if (isMove_left())
			moveLeft();
		if (isMove_right())
			moveRight();
		if (isMove_up())
			moveUP();
		setBlock_down(false);
		if (!isMove_left() && !isMove_right())
			getSpeed().setX(0);
	}

	/**
	 * Actualiza el empuje.
	 */
	private void updatePush() {
		if ((int) getPush().x() == 0 || Math.abs(getPush().x()) < PUSH_RESIST)
			getPush().setX(0);
		else if (getPush().x() > 0)
			getPush().setX(getPush().x() - PUSH_RESIST);
		else
			getPush().setX(getPush().x() + PUSH_RESIST);
	}

	/**
	 * Mueve el jugador segun marca la velocidad y actualiza el disparo del
	 * arma .
	 */
	@Override
	public boolean updatePos(Physical_passive map) {
		if (!map.getPhysicalRectangle().contains(getPhysicalShape()))
				return false;
		if (!isDead()) {
			ResolveUnreleasedMovements();
			getWeapon().update();
			updatePush();
			if (!isBlock_down()) { // Por lo visto esto controla el salto
				if (getJumpTTL() != 0) {
					moveJump();
				} else
					// Y este 3 es la gravedad., lo paso a un metodo de actor
					// para decirle q empiece a caer
					fall();
			}
			// Aqui es donde realmente cambiamos la posicion una vez calculado
			// donde va a ir.
			getGraphicShapes().get(BODY)
					.setLocation(
							new Point((int) getPosition().x(),
									(int) getPosition().y()));
			setPosition(getPosition().add(getSpeed().add(getPush()))); // CAmbiado;
		}
		return true;
	}

	/**
	 * Ejecuta la accion de saltar poniendo libre la direccion hacia abajo y
	 * dando valor al jumpTTL
	 */
	public void jump() {
		if (isBlock_down()) {
			setJumpTTL(getMaxJumpTTL());
			setBlock_down(false);
		}
	}

	/**
	 * Mueve el personaje hacia arriba reduciendo el tiempo que le queda por
	 * saltar.
	 */
	public void moveJump() {
		getSpeed().setY(-getClimbPertTick());
		setJumpTTL(getJumpTTL() - 1);

	}

	/**
	 * Hace caer al personaje segun marca la gravedad.
	 */
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
		return getPhysicalRectangle().getCollisionedRectangle(
				actor.getPhysicalRectangle());
	}

	@Override
	public ArrayList<Segment> getSegmentList() {
		return getPhysicalShape().getSegmentList();
	}

	/**
	 * Getters y Setters.
	 * 
	 */
	public void setLeft(boolean b) {
		setMove_left(b);
	}

	public void setRight(boolean b) {
		setMove_right(b);
	}

	public void setUP(boolean b) {
		setMove_up(b);
	}

	public void setDown(boolean b) {
		setMove_down(b);
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
		if (lookingAt != getLookingAt()) {
			this.lookingAt = lookingAt;
			switchFlip();
		}
	}

	private void switchFlip() {
		for (GraphicRectangle rect : getGraphicShapes())
			rect.setFlipImage(!rect.isFlipImage());
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		getGraphicShapes().set(WEAPON, weapon.getGraphicShape());
	}

	public BvsR_Map getMap() {
		return map;
	}

	private void setMap(BvsR_Map map) {
		this.map = map;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Point2D getPush() {
		return push;
	}

	public void setPush(Point2D push) {
		this.push = push;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public void setJump(int height, double timeSeconds) {
		setMaxJumpTTL((int) (60 * timeSeconds));
		setClimbPertTick((double) height / getMaxJumpTTL());
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
	private String getSoundName() {
		return hitSounds[ResourceManager.getInstance().getRandGen().nextInt(hitSounds.length)];
	}
}
