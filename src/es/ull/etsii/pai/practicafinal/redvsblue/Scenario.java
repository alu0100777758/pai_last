package es.ull.etsii.pai.practicafinal.redvsblue;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.RepaintManager;

import es.ull.etsii.pai.practicafinal.main.MapSelector;
import es.ull.etsii.pai.practicafinal.main.SceneManager;
import es.ull.etsii.pai.practicafinal.metaclass.gamemodeclasses.DefaultModeScoring;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.practicafinal.physics.PhysicsEngine;

public class Scenario {
	public static final String PAUSE_TEXTURE = "textures/pause.png";
	RvsBKeyController keyController = new RvsBKeyController(); // Controlador de
																// teclas.
	private SceneManager scenemanager = null;
	private boolean ended;
	private boolean redWins;
	private boolean blueWins;
	private boolean paused = false;
	private RvsB_World world;
	private PhysicsEngine physicEngine;
	public static final String[] dieSounds = { "Idie01.wav", "Idie02.wav",
			"Idie03.wav" };

	/**
	 * Crea un escenario de alto y ancho definidos con un mapa determinado.
	 * 
	 * @param width
	 * @param height
	 * @param mapName
	 * @param sceneManager 
	 */
	public Scenario(Integer width, Integer height, String mapName, SceneManager sceneManager) {
//		setWidth(width);
//		setHeight(height);
		setScenemanager(sceneManager);
//		System.out.println(sceneManager);
//		AudioManager.reproduceAudio("Fall_Walk_Run_-_Do_or_Die.wav");
		try {
			setWorld(new RvsB_World(BvsR_Map.load(mapName)));
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		} catch (ClassNotFoundException e) {
			System.out.println("class not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setPhysicEngine(new PhysicsEngine(getWorld()));
	}

	/**
	 * Actualiza el estado del escenario.
	 */
	public void update() {
		if (!isPaused()) {
			getPhysicEngine().update();
			/**
			 * Verifica si alguien tiene que morir.
			 */
			for (int i = 0; i < getStaticMap().size(); i++)
				if (((Physical_passive) getStaticMap().get(i)).hasToDie())
					getStaticMap().remove(i);
			if (getPlayer_one().hasToDie()) {
				setEnded(true);
				setRedWins(true);
				DefaultModeScoring.addWinningScore(getPlayer_two());
			}
			if (getPlayer_two().hasToDie()) {
				setEnded(true);
				setBlueWins(true);
				DefaultModeScoring.addWinningScore(getPlayer_one());
			}
			if (isEnded()) {
				AudioManager.stopAll();
				AudioManager.startAudio(dieSounds[ResourceManager.getInstance()
						.getRandGen().nextInt(dieSounds.length)]);
				GameLoop.stepTimer.stop();
			}
		}

	}

	/**
	 * Pinta el escenario.
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		getWorld().paint(g);
		if (isPaused()) {
			ScreenManager sm = ScreenManager.getInstance();
			g.drawImage(
					ResourceManager.getInstance().getBufferedImage(
							PAUSE_TEXTURE), 0, 0,
					(int) (sm.getWindWidth() * sm.getRate_x()),
					(int) (sm.getWindHeight() * sm.getRate_y()), null);
		}
	}

	/**
	 * Getters y Setters**************
	 */
	public Integer getWidth() {
		return getWorld().getMapData().getWidth();
	}

	public void setWidth(Integer width) {
		getWorld().getMapData().setWidth(width);
	}

	public Integer getHeight() {
		return getWorld().getMapData().getHeight();
	}

	public void setHeight(Integer height) {
		getWorld().getMapData().setHeight(height);
	}

	public ArrayList<Entity> getBackground() {
		return getWorld().getMapData().getBackground();
	}

	public void setBackground(ArrayList<Entity> background) {
		getWorld().getMapData().setBackground(background);
	}

	public ArrayList<Entity> getStaticMap() {
		return getWorld().getMapData().getStaticMap();
	}

	public void setStaticMap(ArrayList<Entity> staticMap) {
		getWorld().getMapData().setStaticMap(staticMap);
	}

	public ArrayList<Actor> getActors() {
		return getWorld().getMapData().getActors();
	}

	public void setActors(ArrayList<Actor> actors) {
		getWorld().getMapData().setActors(actors);
	}

	public RvsBKeyController getKeyController() {
		return keyController;
	}

	public void setKeyController(RvsBKeyController keyController) {
		this.keyController = keyController;
	}

	public Player getPlayer_two() {
		return getWorld().getMapData().getPlayer_two();
	}

	public void setPlayer_two(Player player_two) {
		getWorld().getMapData().setPlayer_one(player_two);
	}

	public Player getPlayer_one() {
		return getWorld().getMapData().getPlayer_one();
	}

	public void setPlayer_one(Player player_one) {
		getWorld().getMapData().setPlayer_one(player_one);
	}

	public BvsR_Map getMapData() {
		return getWorld().getMapData();
	}

	public void setMapData(BvsR_Map mapData) {
		getWorld().setMapData(mapData);
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public boolean isRedWins() {
		return redWins;
	}

	public void setRedWins(boolean redWins) {
		this.redWins = redWins;
	}

	public boolean isBlueWins() {
		return blueWins;
	}

	public void setBlueWins(boolean blueWins) {
		this.blueWins = blueWins;
	}

	/**
	 * 
	 * @author Sabato Ceruso.
	 * @author Javier Martin Hernandez.
	 *
	 */
	class RvsBKeyController extends KeyController {

		/**
		 * Acciones a tomar cuando se pulsa una tecla.
		 * 
		 * @param keyCode
		 * @param keyChar
		 */
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
			} else if (keyCode == getKeyMap().get(KeyActions.P1SHOOTLEFT)) {
				getPlayer_one().setLookingAt(Side.LEFT);
				// getActors().add(getPlayer_one().shoot());
				getPlayer_one().shoot();
			} else if (keyCode == getKeyMap().get(KeyActions.P2SHOOTLEFT)) {
				getPlayer_two().setLookingAt(Side.LEFT);
				// getActors().add(getPlayer_two().shoot());
				getPlayer_two().shoot();
			} else if (keyCode == getKeyMap().get(KeyActions.P1SHOOTRIGHT)) {
				getPlayer_one().setLookingAt(Side.RIGHT);
				// getActors().add(getPlayer_one().shoot());
				getPlayer_one().shoot();
			} else if (keyCode == getKeyMap().get(KeyActions.P2SHOOTRIGHT)) {
				getPlayer_two().setLookingAt(Side.RIGHT);
				// getActors().add(getPlayer_two().shoot());
				getPlayer_two().shoot();
			} else if (keyCode == getKeyMap().get(KeyActions.PAUSE)) {
				pause();
			} else if (keyCode == getKeyMap().get(KeyActions.MENU)) {
				menu();
			}
		}

		private void menu() {
			GameLoop.stepTimer.stop();
			getScenemanager().switchScenario(new MapSelector());
			
		}

		private void pause() {
			setPaused(!isPaused());
		}

		/**
		 * Acciones a tomar cuando se deja de pulsar una tecla.
		 * 
		 * @param keyCode
		 * @param keyChar
		 */
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
			} else if (keyCode == getKeyMap().get(KeyActions.P1SHOOTLEFT)) {
				getPlayer_one().stopShooting();
			} else if (keyCode == getKeyMap().get(KeyActions.P2SHOOTLEFT)) {
				getPlayer_two().stopShooting();
			} else if (keyCode == getKeyMap().get(KeyActions.P1SHOOTRIGHT)) {
				getPlayer_one().stopShooting();
			} else if (keyCode == getKeyMap().get(KeyActions.P2SHOOTRIGHT)) {
				getPlayer_two().stopShooting();
			}
		}
	}

	public RvsB_World getWorld() {
		return world;
	}

	public void setWorld(RvsB_World world) {
		this.world = world;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public PhysicsEngine getPhysicEngine() {
		return physicEngine;
	}

	public void setPhysicEngine(PhysicsEngine physicEngine) {
		this.physicEngine = physicEngine;
	}

	public SceneManager getScenemanager() {
		return scenemanager;
	}

	public void setScenemanager(SceneManager scenemanager) {
		this.scenemanager = scenemanager;
	}

}