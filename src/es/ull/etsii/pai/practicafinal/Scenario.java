package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Scenario {
	BvsR_Map mapData = new BvsR_Map(new Player(new Point2D(200, 200)));

	public Player getPlayer_one() {
		return mapData.getPlayer_one();
	}

	public void setPlayer_one(Player player_one) {
		this.mapData.setPlayer_one(player_one);
	}
	
	
	public BvsR_Map getMapData() {
		return mapData;
	}

	public void setMapData(BvsR_Map mapData) {
		this.mapData = mapData;
	}

	public Scenario(Integer width, Integer height) {
		setWidth(width);
		setHeight(height);
		
		setBackground(new ArrayList<Entity>());
		setStaticMap(new ArrayList<Entity>());
		setActors(new ArrayList<Actor>());
		setGUI(new ArrayList<Entity>());
		// /////******************** Para probar poner un unico actor y un
		// suelo.
//		setMapData(BvsR_Map.load("default"));
		getActors().add(getPlayer_one());
		getStaticMap().add(new StaticPlatform(50, 600, 550, 50));

	}
	// TODO cuando se implemente en su propia clase recordar resolver el trato de mayusculas 
	// según la lógica del scenario EJ: al moverse pulsar "d" y soltar "D" puede ser conflictivo
	public void processKey(int keyCode, char keyChar) {
		if (keyCode == KeyEvent.VK_LEFT)
			getPlayer_one().moveLeft();
		if (keyCode == KeyEvent.VK_RIGHT)
			getPlayer_one().moveRight();
		if (keyChar == 'a') {
			getPlayer_one().moveLeft();
		}
		if (keyChar == 'd') {
			getPlayer_one().moveRight();
		}
		if (keyChar == 'w') {
			getPlayer_one().moveUP();
		}
		if (keyChar == 's') {
			getPlayer_one().moveDown();
		}
	}

	public void pulsedKey(int keyCode, char keyChar) {
	
		if (keyCode == KeyEvent.VK_LEFT)
			getPlayer_one().setLeft(true);
		if (keyCode == KeyEvent.VK_RIGHT)
			getPlayer_one().setRight(true);
		else if (keyChar == 'a') {
			getPlayer_one().setLeft(true);
		} else if (keyChar == 'd') {
			getPlayer_one().setRight(true);
		} else if (keyChar == 'w') {
			getPlayer_one().jump();
		} else if (keyChar == 's') {
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
		} else if (keyChar == 'd') {
			getPlayer_one().setRight(false);
		} else if (keyChar == 'w') {
			// getPlayer_one().setUP(false);
		} else if (keyChar == 's') {
			getPlayer_one().setDown(false);
		}
	}

	/**
	 * De forma provisional se miran las colisiones aqui, se deber� crear un
	 * gestor de colisiones mas adelante.
	 * 
	 * @param g
	 */
	/**
	 * TODO implementarlo como objeto contenido en escenario.
	 * 		Uso de lista de objetos pasivos y activos.
	 * 		desplazar la responsabilidad de la actualizaci�n a cada objeto.
	 */
	public void update() {
		Physical_passive map = (Physical_passive) (getStaticMap().get(0));
		getPlayer_one().updatePos(map);
	}

	public void paint(Graphics g) {
		g.fillRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g.create();
		for (int i = 0; i < getStaticMap().size(); i++) {
			((StaticPlatform) getStaticMap().get(i)).paint(g.create());
		}
		for (int i = 0; i < getActors().size(); i++) {
			getActors().get(i).paint(g.create());
		}
		Physical_passive map = (Physical_passive) (getStaticMap().get(0));
		g2.setColor(Color.GREEN);
		if (getPlayer_one().collides(map))
			g2.draw(getPlayer_one().getCollisionedRectangle(map));
	}

	/**
	 * Getters y Setters**************
	 */
	public Integer getWidth() {
		return mapData.getWidth();
	}

	public void setWidth(Integer width) {
		this.mapData.setWidth(width);
	}

	public Integer getHeight() {
		return mapData.getHeight();
	}

	public void setHeight(Integer height) {
		this.mapData.setHeight(height);
	}

	public ArrayList<Entity> getBackground() {
		return mapData.getBackground();
	}

	public void setBackground(ArrayList<Entity> background) {
		this.mapData.setBackground(background);
	}

	public ArrayList<Entity> getStaticMap() {
		return mapData.getStaticMap();
	}

	public void setStaticMap(ArrayList<Entity> staticMap) {
		this.mapData.setStaticMap(staticMap);
	}

	public ArrayList<Actor> getActors() {
		return mapData.getActors();
	}

	public void setActors(ArrayList<Actor> actors) {
		this.mapData.setActors(actors);
	}

	public ArrayList<Entity> getGUI() {
		return mapData.getGUI();
	}

	public void setGUI(ArrayList<Entity> gUI) {
		mapData.setGUI(gUI);
	}

}
