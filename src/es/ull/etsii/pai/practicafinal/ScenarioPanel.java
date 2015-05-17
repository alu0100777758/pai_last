package es.ull.etsii.pai.practicafinal;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class ScenarioPanel extends JPanel{
	private Scenario scenario;				// Escenario que se esta mostrando actualmente.
	
	/**
	 * Crea un panel con un escenario con un determinado mapa.
	 * @param mapName
	 */
	public ScenarioPanel(String mapName) {
		setScenario(new Scenario(getWidth(), getHeight(),mapName));
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

}
