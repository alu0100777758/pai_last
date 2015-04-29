package es.ull.etsii.pai.practicafinal;

import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class ScenarioPanel extends JPanel{
	private Scenario scenario;
	
	public ScenarioPanel() {
		setScenario(new Scenario(getWidth(), getHeight()));
		this.addComponentListener(new SizeChangeListener());
		this.requestFocus();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g.create());
		getScenario().paint(g.create());
	}
	
	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	class SizeChangeListener extends ComponentAdapter {

		@Override
		public void componentResized(ComponentEvent e) {
			setScenario(new Scenario(getWidth(), getHeight()));				// Solo para probar, desde que funcione que se modifique con getters y setters.
		}

		
	}
}
