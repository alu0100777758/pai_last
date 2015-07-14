package es.ull.etsii.pai.practicafinal.redvsblue;

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
