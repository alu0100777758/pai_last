package es.ull.etsii.pai.practicafinal.main;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.redvsblue.GameScenario;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;

public class SceneManager extends JFrame {
	ScenarioPanel	currentScenario ;
	public SceneManager() {
		super();
//		setCurrentScenario(scenario);
//		add(getCurrentScenario());
//		scenario.setSceneManager(this);
	}
	public void switchScenario(ScenarioPanel scenario){
		if(currentScenario != null)
			remove(getCurrentScenario());
		setCurrentScenario(scenario);
		add(scenario);
		scenario.setSceneManager(this);
		validate();
	}
	public ScenarioPanel getCurrentScenario() {
		return currentScenario;
	}
	public void setCurrentScenario(ScenarioPanel currentScenario) {
		this.currentScenario = currentScenario;
	}
	
}
