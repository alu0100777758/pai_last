package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.Drawable;
import es.ull.etsii.pai.practicafinal.StaticPlatform;

public class MapPainter extends JPanel {
	private static final long serialVersionUID = -4939012327823614215L;
	BvsR_Map map = null;
	public BvsR_Map getMap() {
		return map;
	}
	public void setMap(BvsR_Map map) {
		this.map = map;
	}
	public MapPainter(BvsR_Map map){
		setMap(map);
	}public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g.create();
		for (int i = 0; i < map.getStaticMap().size(); i++) {
			((StaticPlatform) map.getStaticMap().get(i)).paint(g.create());
		}
		for (int i = 0; i < map.getActors().size(); i++) {
			map.getActors().get(i).paint(g.create());
		}
		for (int i = 0; i < map.getGUI().size(); i++) {
			Drawable gui = (Drawable)(map.getGUI().get(i));
			gui.paint(g.create());
		}
		
	}

}
