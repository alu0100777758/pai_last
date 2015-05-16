package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.editor.MapPainter;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_active;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Scenario {
	BvsR_Map mapData = new BvsR_Map();
	RvsBKeyController keyController = new RvsBKeyController();
	public static final int WINDOW_TOLERANCE = 100;
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
		Physical_passive map;

		for (int i = 0; i < getActors().size(); i++) {
			if (!((Physical_active) getActors().get(i)).updatePos(new PhysicalRectangle(0, 0, ResourceManager.getInstance().getWindWidth() + WINDOW_TOLERANCE, ResourceManager.getInstance().getWindHeight() + WINDOW_TOLERANCE))) {				// !!!! NO SE ESTAN GUARDANDO BIEN EL ANCHO Y ALTO DEL MAPA.
				getActors().get(i).die();
				getActors().remove(i);	
			}
			
		}
		/**
		 * Aqui es donde se comprueban colisiones.
		 */
		for (int i = 0; i < getStaticMap().size(); i++) {
			map = (Physical_passive) (getStaticMap().get(i));
			if (getPlayer_one().collides(map))
				getPlayer_one().repair_collision(map);
			if (getPlayer_two().collides(map))
				getPlayer_two().repair_collision(map);
			for (int j = 0; j < getActors().size(); j++) {
				if (getActors().get(j) instanceof Bullet) {
					if (((Bullet) getActors().get(j)).collides(map)) 
						getActors().remove(j);
				}
			}
		}
		/**
		 * Verifica si le pego a algun jugador
		 */
		
		for (int i = 0; i < getActors().size(); i++) {
			if (getActors().get(i) instanceof Bullet) {
				if (getPlayer_one().collides(getActors().get(i).getPhysicalShape())) {
					getPlayer_one().gotHit((Bullet) getActors().get(i));
					getActors().remove(getActors().get(i));
				}
				else if (getPlayer_two().collides(getActors().get(i).getPhysicalShape())) {
					getPlayer_two().gotHit((Bullet) getActors().get(i));
					getActors().remove(getActors().get(i));
				}
			}
		}		
	}

	public void paint(Graphics g) {
		MapPainter.paint(g, getMapData());
//		g.fillRect(0, 0, getWidth(), getHeight());
//		Graphics2D g2 = (Graphics2D) g.create();
//		for (int i = 0; i < getStaticMap().size(); i++) {
//			((StaticPlatform) getStaticMap().get(i)).paint(g.create());
//		}
//		for (int i = 0; i < getActors().size(); i++) {
//			getActors().get(i).paint(g.create());
//		}
//		for (int i = 0; i < getMapData().getGUI().size(); i++) {
//			Drawable gui = (Drawable)(getMapData().getGUI().get(i));
//			gui.paint(g.create());
//		}
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
	
	public RvsBKeyController getKeyController() {
		return keyController;
	}

	public void setKeyController(RvsBKeyController keyController) {
		this.keyController = keyController;
	}

	class RvsBKeyController extends KeyController{

		public void pulsedKey(int keyCode, char keyChar) {
			
			if (keyCode == getKeyMap().get(KeyActions.P2LEFT))
				getPlayer_two().setLeft(true);
			else if (keyCode == getKeyMap().get(KeyActions.P2RIGHT))
				getPlayer_two().setRight(true);
			else if (keyCode == getKeyMap().get(KeyActions.P2DOWN))
				getPlayer_two().setDown(true);
			else if (keyCode == getKeyMap().get(KeyActions.P2UP))
				getPlayer_two().jump();
			if (keyCode == getKeyMap().get(KeyActions.P1LEFT)) {
				getPlayer_one().setLeft(true);
			} else if (keyCode == getKeyMap().get(KeyActions.P1RIGHT)) {
				getPlayer_one().setRight(true);
			} else if (keyCode == getKeyMap().get(KeyActions.P1UP)) {
				getPlayer_one().jump();
			} else if (keyCode == getKeyMap().get(KeyActions.P1DOWN)) {
				getPlayer_one().setDown(true);
			}
			else if (keyCode == getKeyMap().get(KeyActions.P1SHOOTLEFT)) {
				 	getPlayer_one().setLookingAt(Side.LEFT);
					//getActors().add(getPlayer_one().shoot());
				 	getPlayer_one().shoot();
			}
			else if (keyCode == getKeyMap().get(KeyActions.P2SHOOTLEFT)) {
				getPlayer_two().setLookingAt(Side.LEFT);
				//getActors().add(getPlayer_two().shoot());
				getPlayer_two().shoot();
			}
			else if (keyCode == getKeyMap().get(KeyActions.P1SHOOTRIGHT)) {
			 	getPlayer_one().setLookingAt(Side.RIGHT);
				//getActors().add(getPlayer_one().shoot());
			 	getPlayer_one().shoot();
			}	
			else if (keyCode == getKeyMap().get(KeyActions.P2SHOOTRIGHT)) {
				getPlayer_two().setLookingAt(Side.RIGHT);
				//getActors().add(getPlayer_two().shoot());
				getPlayer_two().shoot();
			}
		}

		public void releasedKey(int keyCode, char keyChar) {
			if (keyCode == getKeyMap().get(KeyActions.P2LEFT))
				getPlayer_two().setLeft(false);
			else if ((keyCode == getKeyMap().get(KeyActions.P2RIGHT)))
				getPlayer_two().setRight(false);
			else if (keyCode == getKeyMap().get(KeyActions.P2DOWN)) {
				getPlayer_two().setDown(false);
				getPlayer_two().setUP(true);
			}
			if (keyCode == getKeyMap().get(KeyActions.P1LEFT)) {
				getPlayer_one().setLeft(false);
			} else if (keyCode == getKeyMap().get(KeyActions.P1RIGHT)) {
				getPlayer_one().setRight(false);
			} else if (keyChar == 'w') {
				// getPlayer_one().setUP(false);
			} else if (keyCode == getKeyMap().get(KeyActions.P1DOWN)) {
				getPlayer_one().setDown(false);
				getPlayer_one().setUP(true);
			}
			else if (keyCode == getKeyMap().get(KeyActions.P1SHOOTLEFT)) {
			 	getPlayer_one().stopShooting();
			}
			else if (keyCode == getKeyMap().get(KeyActions.P2SHOOTLEFT)) {
				getPlayer_two().stopShooting();
			}
			else if (keyCode == getKeyMap().get(KeyActions.P1SHOOTRIGHT)) {
				getPlayer_one().stopShooting();
			}	
			else if (keyCode == getKeyMap().get(KeyActions.P2SHOOTRIGHT)) {
				getPlayer_two().stopShooting();
			}
		}
	}
}