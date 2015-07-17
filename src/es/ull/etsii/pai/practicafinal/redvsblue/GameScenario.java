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
import java.awt.Toolkit;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GameScenario extends ScenarioPanel{
	private Scenario scenario;				// Escenario que se esta mostrando actualmente.
	private GameLoopHandler gameLoopHandler;
	/**
	 * Crea un panel con un escenario con un determinado mapa.
	 * @param mapName
	 */
	public GameScenario(String mapName) {
		setScenario(new Scenario(getWidth(), getHeight(), mapName));
		setGameLoopHandler(new GameLoopHandler());

		GameLoop.init(this.getGameLoopHandler());
		GameLoop.setDisplayer(this);
		GameLoop.setUpdater(this.getScenario());
		
		this.requestFocus();
	} 
	public void paintComponent(Graphics g){
		super.paintComponent(g.create());
		getScenario().paint(g.create());
	}
	/**
	 * Getters y Setters.
	 * @return
	 */
	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public GameLoopHandler getGameLoopHandler() {
		return gameLoopHandler;
	}
	public void setGameLoopHandler(GameLoopHandler gameLoopHandler) {
		this.gameLoopHandler = gameLoopHandler;
	}
	
	@Override
	public void pulsedKey(int keyCode, char keyChar) {
		getScenario().getKeyController().pulsedKey(keyCode, keyChar);		
	}
	@Override
	public void releasedKey(int keyCode, char keyChar) {
		getScenario().getKeyController().releasedKey(keyCode, keyChar);
	}

	class GameLoopHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			getScenario().update();
			repaint();
			Toolkit.getDefaultToolkit().sync();
			
			if (getScenario().isEnded()){
				double xrate = ScreenManager.getInstance().getRate_x();
				double yrate = ScreenManager.getInstance().getRate_y();
				if (getScenario().isBlueWins())
					getSceneManager().switchScenario(new WinnerScene("Blue", (int)(ScreenManager.getInstance().getWindWidth() * xrate), (int)(ScreenManager.getInstance().getWindHeight() * yrate)));		
				else
					getSceneManager().switchScenario(new WinnerScene("Red", (int)(ScreenManager.getInstance().getWindWidth() * xrate), (int)(ScreenManager.getInstance().getWindHeight() * yrate)));
				getSceneManager().getCurrentScenario().repaint();
			}
		}
		
	}

	@Override
	public void sizeUpdate() {
		getScenario().getMapData().markForTexture();
	}
}
