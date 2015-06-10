package es.ull.etsii.pai.practicafinal.main;

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
