package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_active;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Scenario {
	BvsR_Map mapData = new BvsR_Map();
	public Player getPlayer_two() {
		return mapData.getPlayer_two();
	}

	public void setPlayer_two(Player player_two) {
		this.mapData.setPlayer_one(player_two);
	}
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

	public Scenario(Integer width, Integer height, String mapName) {
		setWidth(width);
		setHeight(height);
		
		setBackground(new ArrayList<Entity>());
		setStaticMap(new ArrayList<Entity>());
		setActors(new ArrayList<Actor>());
		setGUI(new ArrayList<Entity>());
		// /////******************** Para probar poner un unico actor y un
		// suelo.
		try {
			setMapData(BvsR_Map.load(mapName));
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		} catch (ClassNotFoundException e) {
			System.out.println("class not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * TODO: 
 * 		- Mejorar el disparo, hacer q se pueda hacer correctamente manteniendo presionado.
 * 		- Delegar el control de estas cosas a otra clase que no sea escenario.
 * 		
 * @param keyCode
 * @param keyChar
 */
	public void pulsedKey(int keyCode, char keyChar) {
	
		if (keyCode == KeyEvent.VK_LEFT)
			getPlayer_two().setLeft(true);
		else if (keyCode == KeyEvent.VK_RIGHT)
			getPlayer_two().setRight(true);
		else if (keyCode == KeyEvent.VK_DOWN)
			getPlayer_two().setDown(true);
		else if (keyCode == KeyEvent.VK_UP)
			getPlayer_two().jump();
		if (keyChar == 'a') {
			getPlayer_one().setLeft(true);
		} else if (keyChar == 'd') {
			getPlayer_one().setRight(true);
		} else if (keyChar == 'w') {
			getPlayer_one().jump();
		} else if (keyChar == 's') {
			getPlayer_one().setDown(true);
		}
		 else if (keyChar == 'j') {
				getActors().add(getPlayer_one().shoot());
		}else if (keyChar == '0') {
			getActors().add(getPlayer_two().shoot());
	}
	}

	public void releasedKey(int keyCode, char keyChar) {
		if (keyCode == KeyEvent.VK_LEFT)
			getPlayer_two().setLeft(false);
		else if (keyCode == KeyEvent.VK_RIGHT)
			getPlayer_two().setRight(false);
		else if (keyCode == KeyEvent.VK_DOWN) {
			getPlayer_two().setDown(false);
			getPlayer_two().setUP(true);
		}
		if (keyChar == 'a') {
			getPlayer_one().setLeft(false);
		} else if (keyChar == 'd') {
			getPlayer_one().setRight(false);
		} else if (keyChar == 'w') {
			// getPlayer_one().setUP(false);
		} else if (keyChar == 's') {
			getPlayer_one().setDown(false);
			getPlayer_one().setUP(true);
		}
	}

	/**
	 * De forma provisional se miran las colisiones aqui, se deberï¿½ crear un
	 * gestor de colisiones mas adelante.
	 * 
	 * @param g
	 */
	/**
	 * TODO implementarlo como objeto contenido en escenario.
	 * 		Uso de lista de objetos pasivos y activos.
	 * 		desplazar la responsabilidad de la actualización a cada objeto.
	 */
	public void update() {
		Physical_passive map;
	//	getPlayer_one().updatePos(null);
	//	playerTwo.updatePos(null);
		for (int i = 0; i < getActors().size(); i++) {
			if (!((Physical_active) getActors().get(i)).updatePos(new PhysicalRectangle(0, 0, 1200, 800))) {				// !!!! NO SE ESTAN GUARDANDO BIEN EL ANCHO Y ALTO DEL MAPA.
				getActors().remove(i);	
			}
			
		}
		for (int i = 0; i < getStaticMap().size(); i++) {
			map = (Physical_passive) (getStaticMap().get(i));
			if (getPlayer_one().collides(map))
				getPlayer_one().repair_collision(map);
			if (getPlayer_two().collides(map))
				getPlayer_two().repair_collision(map);
		}
		
	
		
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
	/*	Physical_passive map = (Physical_passive) (getStaticMap().get(0));
		g2.setColor(Color.GREEN);
		if (getPlayer_one().collides(map))
			g2.draw(getPlayer_one().getCollisionedRectangle(map));
	*/
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
