package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Graphics;

import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.Entity;
import es.ull.etsii.pai.practicafinal.GraphicEntity;
import es.ull.etsii.pai.practicafinal.ResourceManager;
import es.ull.etsii.pai.practicafinal.ScreenManager;
import es.ull.etsii.pai.practicafinal.StaticPlatform;
import es.ull.etsii.pai.practicafinal.graphics.Drawable;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
public class MapPainter extends JPanel {
	private static final long serialVersionUID = -4939012327823614215L;
	BvsR_Map map = null;
	boolean guiActive = true;
	public MapPainter(BvsR_Map map) {
		setMap(map);
	}
	public boolean isGuiActive() {
		return guiActive;
	}

	public void setGuiActive(boolean guiActive) {
		this.guiActive = guiActive;
	}

	public BvsR_Map getMap() {
		return map;
	}

	public void setMap(BvsR_Map map) {
		this.map = map;
	}

	
	public static void paintBackground(Graphics g, BvsR_Map map){
		g.fillRect(0, 0, ScreenManager.getInstance().getWindWidth(), ScreenManager.getInstance().getWindHeight());
		for(Entity ent: map.getBackground()){
			GraphicEntity entg = (GraphicEntity) ent;
			entg.getGraphic().paint(g);
		}
	}
	public static void paintStaticMap(Graphics g, BvsR_Map map){
		for (int i = 0; i < map.getStaticMap().size(); i++) {
			((StaticPlatform) map.getStaticMap().get(i)).paint(g.create());
		}
	}
	public static void paintActors(Graphics g, BvsR_Map map ){
		for (int i = 0; i < map.getActors().size(); i++) {
			map.getActors().get(i).paint(g.create());
		}
	}
	public static void paintGUI(Graphics g, BvsR_Map map){
		for (int i = 0; i < map.getGUI().size(); i++) {
			Drawable gui = (Drawable) (map.getGUI().get(i));
			gui.paint(g.create());
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g,getMap());
		paintStaticMap(g,getMap());
		paintActors(g,getMap());
		if (isGuiActive()) {
			paintGUI(g,getMap());
		}
	}
	public static void paint(Graphics g,BvsR_Map map){
		paintBackground(g,map);
		paintStaticMap(g,map);
		paintActors(g,map);
			paintGUI(g,map);
	}

}
