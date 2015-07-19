package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.graphics.Drawable;
import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.Entity;
import es.ull.etsii.pai.practicafinal.redvsblue.GraphicEntity;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;


/**
 * Progamacion de aplicaciones interactivas. Universidad de La Laguna.
 * 
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
/**
 * Clase encargada de dibujar un mapa sobre un objeto Graphics
 *
 */
public class MapPainter extends JPanel {
	private static final long serialVersionUID = -4939012327823614215L; // serial de la ultima version
	private BvsR_Map map = null; 										// mapa encargado de dibujar
	private boolean guiActive = true; 									// almacena si la GUI (HUD) esta visible o no
	public static final Color BACKGRAUND_COLOR = new Color(64, 64, 64);	// Color con el que se rellena el fondo
	private boolean foregroundActive = true;
	public MapPainter(BvsR_Map map) {
		setMap(map);
		setMinimumSize(ScreenManager.getInstance().getScreenDimensions());
		setPreferredSize(getMinimumSize());
		setSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
		setBackground(BACKGRAUND_COLOR);
	}

	public static void paintBackground(Graphics g, BvsR_Map map) {
		ScreenManager screen = ScreenManager.getInstance();
		g.setColor(BACKGRAUND_COLOR);
		g.fillRect(0, 0, (int) (screen.getWindWidth() * screen.getRate_x()),
				(int) (screen.getWindHeight() * screen.getRate_y()));
		for (Entity ent : map.getBackground()) {
			GraphicEntity entg = (GraphicEntity) ent;
			entg.getGraphic().paint(g);
		}
	}
	public void paintBackground(Graphics g) {
		for (Entity ent : getMap().getBackground()) {
			GraphicEntity entg = (GraphicEntity) ent;
			entg.getGraphic().paint(g);
		}
	}

	public static void paintStaticMap(Graphics g, BvsR_Map map) {
		for (int i = 0; i < map.getStaticMap().size(); i++) {
			((Drawable) map.getStaticMap().get(i)).paint(g.create());
		}
	}

	public static void paintActors(Graphics g, BvsR_Map map) {
		for (int i = 0; i < map.getActors().size(); i++) {
			map.getActors().get(i).paint(g.create());
		}
	}
//	public static void paintBullets(Graphics g, RvsB_World map) {
//		for (int i = 0; i < map.getBullets().size(); i++) {
//			map.getBullets().get(i).paint(g.create());
//		}
//	}
//	public static void paintGUI(Graphics g, RvsB_World map) {
//		for (int i = 0; i < map.getGUI().size(); i++) {
//			Drawable gui = (Drawable) (map.getGUI().get(i));
//			gui.paint(g.create());
//		}
//	}
	public static void paintForeground(Graphics g, BvsR_Map map){
		for (Entity ent : map.getForeground()) {
			GraphicEntity entg = (GraphicEntity) ent;
			entg.getGraphic().paint(g);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g);
		paintStaticMap(g, getMap());
		paintActors(g, getMap());
		if(isForegroundActive())
			MapPainter.paintForeground(g, getMap());
//		paintBullets(g, getMap());
//		if (isGuiActive()) {
//			paintGUI(g, getMap());
//		}
	}

	public static void paint(Graphics g, BvsR_Map map) {
		paintBackground(g, map);
		paintStaticMap(g, map);
		paintActors(g, map);
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

	public boolean isForegroundActive() {
		return foregroundActive;
	}

	public void setForegroundActive(boolean foregroundActive) {
		this.foregroundActive = foregroundActive;
	}

	public static BufferedImage getPict(BvsR_Map mapa) {
		 BufferedImage image = new BufferedImage(ScreenManager.getInstance().getWindWidth(), ScreenManager.getInstance().getWindHeight(), BufferedImage.TYPE_3BYTE_BGR);
		 Graphics preview = image.getGraphics();
		 paint(preview, mapa);
		 paintForeground(preview, mapa);
		 return image;
	}

}
