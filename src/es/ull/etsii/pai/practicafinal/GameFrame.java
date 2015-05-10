package es.ull.etsii.pai.practicafinal;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements ActionListener{
	private ScenarioPanel scenarioPanel;
	public GameFrame(String mapName) {
		setScenarioPanel(new ScenarioPanel(mapName));
		this.add(getScenarioPanel());
		this.addKeyListener(new KeyHandler());
		GameLoop.setDisplayer(scenarioPanel);
		GameLoop.setUpdater(scenarioPanel.getScenario());
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
			getScenarioPanel().getScenario().pulsedKey(e.getKeyCode(), e.getKeyChar());
//			getScenarioPanel().repaint();
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			System.out.println(arg0.getKeyCode());
			
			getScenarioPanel().getScenario().releasedKey(arg0.getKeyCode(), arg0.getKeyChar());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			//System.out.println("typed");
			
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		 getScenarioPanel().getScenario().update();
	        getScenarioPanel().repaint();
	        Toolkit.getDefaultToolkit().sync();
	      //  System.out.println("updating");
		// TODO Auto-generated method stub
		
	}
}
