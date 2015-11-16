package es.ull.etsii.pai.practicafinal.metaclass.weapons.bullets;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.ObjectInputStream.GetField;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.practicafinal.redvsblue.Bullet;
import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Rope_bullet extends Bullet {
	private static final int CHAR_VERTICAL_OFFSET = 20;
	private static final int BULLET_VERTICAL_OFFSET = 7;
	private static final int BULLET_HORIZONTAL_OFFSET = 10;
	private static final long serialVersionUID = 7377268883952126650L;
	private boolean backState = false;
	private Player target = null;
	private GraphicRectangle rope;
	private Point2D pullSpeed = null;

	public Rope_bullet(Point2D pos) {
		super(pos);
		setTextureImage();
	}

	public Rope_bullet(Point2D pos, Point2D speed, int damage, int push,
			Player owner, int bulletSize) {
		super(pos, speed, damage, push, owner, bulletSize);
		setTextureImage();
		setPullSpeed(new Point2D(-speed.x(), -20));
	}

	protected void setTextureImage() {

		getGraphicShape().setTexturePath("textures/hook_head.png");
		getGraphicShape().setTextureAnchor(getGraphicShape());
		getGraphicShape().setImage(true);
	}

	@Override
	public boolean collidesWithEnemy(Player actor) {
		setTarget(actor);
		initBackState();
		return false;
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		Point bulletHeadLocation = getPhysicalRectangle().getLocation();
		Point ownerLocation = getOwner().getPhysicalShape().getLocation();
		double xrate = ScreenManager.getInstance().getRate_x();
		double yrate = ScreenManager.getInstance().getRate_y();
		int x1 = (int) ((bulletHeadLocation.getX() + BULLET_HORIZONTAL_OFFSET) * xrate);
		int y1 = (int) ((bulletHeadLocation.getY() + BULLET_VERTICAL_OFFSET) * yrate);
		int x2 = (int) (ownerLocation.getX() * xrate);
		int y2 = (int) ((ownerLocation.getY() + CHAR_VERTICAL_OFFSET) * yrate);
		g.drawLine(x1, y1, x2, y2);
		super.paint(g);
	};

	@Override
	public boolean collidesWithFriend(Player actor) {
		if (isBackState()) {
			setDead(true);
			return true;
		}
		return false;
	}

	@Override
	public boolean collidesWithOther(Physical_passive actor) {
		if (isBackState() && actor.getClass()==Player.class) {
			setDead(true);
			return true;
		}
		initBackState();
		return false;
	}

	@Override
	public boolean updatePos(Physical_passive map) {
		boolean res = super.updatePos(map);
		if (isBackState()) {
			if (getTarget() != null)
				pullEnemy();
			else if (Math.abs(getOwner().getPosition().x()
					- getPhysicalRectangle().getLocation().getX()) > 20)
				pushOwner();
			else
				setDead(true);
			return true;
		}
		return res;
	};

	private void pushOwner() {
		getOwner().addPosition(getPullSpeed());
		getPullSpeed()
				.setY((getPosition().y() - getOwner().getPhysicalRectangle()
						.getY()) / 5);
		if (getOwner().getPosition().y() <= getPosition().y())
			getPullSpeed().setY(getPullSpeed().y() * 0.2);
	}

	private void pullEnemy() {
		Point location = getPhysicalShape().getLocation();
		getTarget().setLocation((int) location.getX(), (int) location.getY()-5);
	}

	private void initBackState() {
		if (!isBackState()) {
			if (getTarget() != null)
				setSpeed(new Point2D(-getSpeed().x(), getSpeed().y()));
			else {
				double yvel = 0;
				if (getOwner().getPosition().y() > getPosition().y())
					yvel = (getPosition().y() - getOwner()
							.getPhysicalRectangle().getY()) / 5;
				setPullSpeed(new Point2D(getSpeed().x(), yvel));
				setSpeed(new Point2D(0, 0));
			}
			setBackState(true);
		}
		else{
			die();
		}
	}

	public boolean isBackState() {
		return backState;
	}

	public void setBackState(boolean backState) {
		this.backState = backState;
	}

	public Player getTarget() {
		return target;
	}

	public void setTarget(Player target) {
		this.target = target;
	}

	public GraphicRectangle getRope() {
		return rope;
	}

	public void setRope(GraphicRectangle rope) {
		this.rope = rope;
	}

	public Point2D getPullSpeed() {
		return pullSpeed;
	}

	public void setPullSpeed(Point2D pullSpeed) {
		this.pullSpeed = pullSpeed;
	}

}
