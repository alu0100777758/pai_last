package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.main.SceneManager;

public abstract class ScenarioPanel extends JPanel {
	private SceneManager sceneManager;
	
	public SceneManager getSceneManager() {
		return sceneManager;
	}

	public void setSceneManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	public void pulsedKey(int keyCode, char keyChar) {
		
	}
	
	public void releasedKey(int keyCode, char keyChar) {
		
	}

	public abstract void sizeUpdate();
	
}
