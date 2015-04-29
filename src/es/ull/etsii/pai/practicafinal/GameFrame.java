package es.ull.etsii.pai.practicafinal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	private ScenarioPanel scenarioPanel;

	public GameFrame() {
		setScenarioPanel(new ScenarioPanel());
		this.add(getScenarioPanel());
		this.addKeyListener(new KeyHandler());
	}
	public ScenarioPanel getScenarioPanel() {
		return scenarioPanel;
	}

	public void setScenarioPanel(ScenarioPanel scenarioPanel) {
		this.scenarioPanel = scenarioPanel;
	}

	class KeyHandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			//System.out.println("pressed");
			getScenarioPanel().getScenario().processKey(e.getKeyCode(), e.getKeyChar());
			getScenarioPanel().repaint();
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			System.out.println("Released");
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			//System.out.println("typed");
			
		}
		
	}
}
