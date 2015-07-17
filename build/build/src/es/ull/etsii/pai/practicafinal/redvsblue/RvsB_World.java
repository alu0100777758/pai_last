package es.ull.etsii.pai.practicafinal.redvsblue;

import java.awt.Graphics;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.editor.MapPainter;
import es.ull.etsii.pai.practicafinal.graphics.Drawable;

public class RvsB_World implements Drawable {
	BvsR_Map mapData;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Drawable> GUI = new ArrayList<Drawable>();
	public RvsB_World(BvsR_Map map) {
		setMapData(map);
		if (getGUI().isEmpty()) {
			getGUI().add(
					new Player_gauge(getMapData().getPlayer_one(), 0));
			getGUI().add(
					new Player_gauge(getMapData().getPlayer_two(),
							Player_gauge.TOP_RIGHT));
		}
		setPlayers();
	}
	private void setPlayers() {
		getPlayer_one().setMap(this);
		getPlayer_two().setMap(this);
	}
	public void addBullet(Bullet actor) {
		getBullets().add(actor);
	}
	@Override
	public void paint(Graphics g) {
		MapPainter.paint(g,getMapData());
		PaintDynamicElements(g);
		MapPainter.paintForeground(g, getMapData());
	}
	private void PaintDynamicElements(Graphics g) {
		for(Actor dyn : getBullets())
			dyn.paint(g);
		for(Drawable gui : getGUI())
			gui.paint(g);
	}
	public BvsR_Map getMapData() {
		return mapData;
	}
	public void setMapData(BvsR_Map mapData) {
		this.mapData = mapData;
	}
	public ArrayList<Drawable> getGUI() {
		return GUI;
	}

	public void setGUI(ArrayList<Drawable> gUI) {
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

}
