package es.ull.etsii.pai.practicafinal.main;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.redvsblue.GameScenario;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;

public class SceneManager extends JFrame {
	ScenarioPanel currentScenario;
	private boolean keyHandlerON = true;

	public SceneManager() {
		super();
		this.addKeyListener(new KeyHandler());
		// setCurrentScenario(scenario);
		// add(getCurrentScenario());
		// scenario.setSceneManager(this);
	}

	public void switchScenario(ScenarioPanel scenario) {
		if (currentScenario != null)
			remove(getCurrentScenario());
		setCurrentScenario(scenario);
		add(scenario);
		scenario.setSceneManager(this);
		validate();
		setKeyHandlerON(true);
	}

	public ScenarioPanel getCurrentScenario() {
		return currentScenario;
	}

	public void setCurrentScenario(ScenarioPanel currentScenario) {
		this.currentScenario = currentScenario;
	}

	/**
	 * Manejador de teclas.
	 * 
	 * @author Sabato Ceruso.
	 * @author Javier Martin Hernandez.
	 *
	 */
	class KeyHandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (isKeyHandlerON())
				getCurrentScenario().pulsedKey(e.getKeyCode(), e.getKeyChar());
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (isKeyHandlerON())
				getCurrentScenario().releasedKey(arg0.getKeyCode(),
						arg0.getKeyChar());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

	}

	public void notify_resize() {
		getCurrentScenario().sizeUpdate();
	}

	public boolean isKeyHandlerON() {
		return keyHandlerON;
	}

	public void setKeyHandlerON(boolean keyHandlerON) {
		this.keyHandlerON = keyHandlerON;
	}

}
