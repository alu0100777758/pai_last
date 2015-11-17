package es.ull.etsii.pai.practicafinal.lwjglImplement.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Graphics;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.editor.MapPainter;
import es.ull.etsii.pai.practicafinal.graphics.Drawable;
import es.ull.etsii.pai.practicafinal.lwjglImplement.graphics.DrawableGL;
import es.ull.etsii.pai.practicafinal.redvsblue.Actor;
import es.ull.etsii.pai.practicafinal.redvsblue.Bullet;
import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.Entity;
import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.practicafinal.redvsblue.Player_gauge;

public class World_lwjgl implements DrawableGL {
	BvsR_Map mapData;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<DrawableGL> GUI = new ArrayList<DrawableGL>();
	public World_lwjgl(BvsR_Map map) {
		setMapData(map);
//		if (getGUI().isEmpty()) {
//			getGUI().add(
//					new Player_gauge(getMapData().getPlayer_one(), 0));
//			getGUI().add(
//					new Player_gauge(getMapData().getPlayer_two(),
//							Player_gauge.TOP_RIGHT));
//		}
		setPlayers();
	}
	private void setPlayers() {
//		getPlayer_one().setMap(this);
//		getPlayer_one().setReloadSound("bluereload.wav");
//		getPlayer_two().setMap(this);
//		getPlayer_two().setReloadSound("redreload.wav");
	}
	public void addBullet(Bullet actor) {
		getBullets().add(actor);
	}

	public void paint(Graphics g) {
		MapPainter.paint(g,getMapData());
		PaintDynamicElements(g);
		MapPainter.paintForeground(g, getMapData());
	}
	private void PaintDynamicElements(Graphics g) {
//		for(Actor dyn : getBullets())
//			dyn.paint(g);
//		for(DrawableGL gui : getGUI())
//			gui.paint(g);
	}
	public BvsR_Map getMapData() {
		return mapData;
	}
	public void setMapData(BvsR_Map mapData) {
		this.mapData = mapData;
	}
	public ArrayList<DrawableGL> getGUI() {
		return GUI;
	}

	public void setGUI(ArrayList<DrawableGL> gUI) {
		GUI = gUI;
	}
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	public  ArrayList<Entity> getStaticMap() {
		return getMapData().getStaticMap();
	}
	public Player getPlayer_one() { 
		// TODO Auto-generated method stub
		return getMapData().getPlayer_one();
	}
	public Player getPlayer_two() {
		// TODO Auto-generated method stub
		return getMapData().getPlayer_two();
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
