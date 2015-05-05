package es.ull.etsii.pai.practicafinal;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Scenario {
	ArrayList<Entity> background;
	ArrayList<Entity> staticMap;
	ArrayList<Actor> actors;
	ArrayList<Entity> GUI;
	private Integer width;
	private Integer height;
//	public static final int PLAYER_ONE = 0;
	private Player player_one  = new Player(new Point2D(200, 200));
	
	public Player getPlayer_one() {
		return player_one;
	}
	public void setPlayer_one(Player player_one) {
		this.player_one = player_one;
	}
	public Scenario(Integer width, Integer height) {
		setWidth(width);
		setHeight(height);
		setBackground(new ArrayList<Entity>());
		setStaticMap(new ArrayList<Entity>());
		setActors(new ArrayList<Actor>());
		setGUI(new ArrayList<Entity>());
		///////******************** Para probar poner un unico actor y un suelo.
		getActors().add(getPlayer_one());
		getStaticMap().add(new StaticPlatform(50, 600, 550, 50));
		
	}
	public void processKey(int keyCode, char keyChar) {
		if (keyCode == KeyEvent.VK_LEFT)
			getPlayer_one().moveLeft();
		if (keyCode == KeyEvent.VK_RIGHT)
			getPlayer_one().moveRight();
		if (keyChar == 'a') {
			getPlayer_one().moveLeft();
//			System.out.println("Pressed a");
		}
		if (keyChar == 'd') {
			getPlayer_one().moveRight();
//			System.out.println("Pressed d");
		}
		if (keyChar == 'w') {
			getPlayer_one().moveUP();
		}
		if (keyChar == 's') {
			getPlayer_one().moveDown();
		}
//		System.out.println("pulsed: " + keyChar);
	}
	public void pulsedKey(int keyCode, char keyChar) {
		if (keyCode == KeyEvent.VK_LEFT)
			getPlayer_one().setLeft(true);
		if (keyCode == KeyEvent.VK_RIGHT)
			getPlayer_one().setRight(true);
		else if (keyChar == 'a') {
			getPlayer_one().setLeft(true);
		}
		else if (keyChar == 'd') {
			getPlayer_one().setRight(true);
		}
		else if (keyChar == 'w') {
			getPlayer_one().setUP(true);
		}
		else if (keyChar == 's') {
			getPlayer_one().setDown(true);
		}
	}
	public void releasedKey(int keyCode, char keyChar) {
		if (keyCode == KeyEvent.VK_LEFT)
			getPlayer_one().setLeft(false);
		else if (keyCode == KeyEvent.VK_RIGHT)
			getPlayer_one().setRight(false);
		else if (keyChar == 'a') {
			getPlayer_one().setLeft(false);
		}
		else if (keyChar == 'd') {
			getPlayer_one().setRight(false);
		}
		else if (keyChar == 'w') {
			getPlayer_one().setUP(false);
		}
		else if (keyChar == 's') {
			getPlayer_one().setDown(false);
		}
	}
	/**
	 * De forma provisional se miran las colisiones aqui, se deber� crear
	 * un gestor de colisiones mas adelante.
	 * @param g
	 */
	public void update(){
		Physical_passive map = (Physical_passive)(getStaticMap().get(0));
		getPlayer_one().updatePos();
		if(getPlayer_one().collides(map)){
//			getPlayer_one().repair_collision(map);
			System.out.println("collision");
		}
	}
	public void paint(Graphics g) {
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for (int i = 0; i < getStaticMap().size(); i++) {
			((StaticPlatform) getStaticMap().get(i)).paint(g.create());
		}
		for (int i = 0; i < getActors().size(); i++) {
//			((Player) getActors().get(i)).updateMovement();
//			if (((StaticPlatform) getStaticMap().get(0)).collides(getActors().get(i))) {
//				double distance = ((StaticPlatform) getStaticMap().get(0)).collisionDistance(getActors().get(i));
//				System.out.println(distance);
//				Point2D move = ((Player) getActors().get(i)).getMovement();
//				double angle = move.getAngle();
//				((Player) getActors().get(i)).setMovement(new Point2D(distance*Math.cos(Math.toRadians(angle)), distance * Math.sin(Math.toRadians(angle) - 20)));
//				((Player) getActors().get(i)).setOnPlatform(true);
//			}
				
			getActors().get(i).paint(g.create());
		}
	}
	/**
	 * Getters y Setters**************
	 */
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public ArrayList<Entity> getBackground() {
		return background;
	}

	public void setBackground(ArrayList<Entity> background) {
		this.background = background;
	}

	public ArrayList<Entity> getStaticMap() {
		return staticMap;
	}

	public void setStaticMap(ArrayList<Entity> staticMap) {
		this.staticMap = staticMap;
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}

	public ArrayList<Entity> getGUI() {
		return GUI;
	}

	public void setGUI(ArrayList<Entity> gUI) {
		GUI = gUI;
	}
	
}
