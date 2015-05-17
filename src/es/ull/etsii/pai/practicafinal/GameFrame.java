package es.ull.etsii.pai.practicafinal;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements ActionListener{
	private ScenarioPanel scenarioPanel;				// Panel con el escenario.
	
	/**
	 * Inicia una ventana con un mapa cargado identificado con el parametro.
	 * @param mapName
	 */
	public GameFrame(String mapName) {
		setScenarioPanel(new ScenarioPanel(mapName));
		this.add(getScenarioPanel());
		this.addKeyListener(new KeyHandler());
		GameLoop.setDisplayer(scenarioPanel);
		GameLoop.setUpdater(scenarioPanel.getScenario());
	}
	/**
	 * Getters y Setters.
	 * @return
	 */
	public ScenarioPanel getScenarioPanel() {
		return scenarioPanel;
	}

	public void setScenarioPanel(ScenarioPanel scenarioPanel) {
		this.scenarioPanel = scenarioPanel;
	}
	
	/**
	 * Manejador de teclas.
	 * @author Sabato Ceruso.
	 * @author Javier Martin Hernandez.
	 *
	 */
	class KeyHandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			getScenarioPanel().getScenario().getKeyController().pulsedKey(e.getKeyCode(), e.getKeyChar());	
		}

		@Override
		public void keyReleased(KeyEvent arg0) {;		
			getScenarioPanel().getScenario().getKeyController().releasedKey(arg0.getKeyCode(), arg0.getKeyChar());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		 getScenarioPanel().getScenario().update();
	        getScenarioPanel().repaint();
	        Toolkit.getDefaultToolkit().sync();
	}
}
