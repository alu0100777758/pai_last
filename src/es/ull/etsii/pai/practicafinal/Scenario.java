package es.ull.etsii.pai.practicafinal;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Scenario {
	ArrayList<Entity> background;
	ArrayList<Entity> staticMap;
	ArrayList<Actor> actors;
	ArrayList<Entity> GUI;
	private Integer width;
	private Integer height;
	public static final int PLAYER_ONE = 0;
	
	public Scenario(Integer width, Integer height) {
		setWidth(width);
		setHeight(height);
		setBackground(new ArrayList<Entity>());
		setStaticMap(new ArrayList<Entity>());
		setActors(new ArrayList<Actor>());
		setGUI(new ArrayList<Entity>());
		///////******************** Para probar poner un unico actor.
		getActors().add(new Player(new Point2D(200, 200)));
		
	}
	public void processKey(int keyCode, char keyChar) {
		if (keyCode == KeyEvent.VK_LEFT)
			((Player) getActors().get(PLAYER_ONE)).moveLeft();
		if (keyCode == KeyEvent.VK_RIGHT)
			((Player) getActors().get(PLAYER_ONE)).moveRight();
		if (keyChar == 'a') {
			((Player) getActors().get(PLAYER_ONE)).moveLeft();
			System.out.println("Pressed a");
		}
		if (keyChar == 'd') {
			((Player) getActors().get(PLAYER_ONE)).moveRight();
			System.out.println("Pressed d");
		}
			
	}
	public void paint(Graphics g) {
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < getActors().size(); i++) {
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
