package es.ull.etsii.pai.practicafinal.redvsblue;

import java.awt.Graphics;

import es.ull.etsii.pai.practicafinal.editor.MapPainter;
import es.ull.etsii.pai.practicafinal.graphics.Drawable;

public class RvsB_World implements Drawable {
	BvsR_Map mapdata;
	public RvsB_World(BvsR_Map map) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void paint(Graphics g) {
		MapPainter.paint(g,getMapdata());
	}
	public BvsR_Map getMapdata() {
		return mapdata;
	}
	public void setMapdata(BvsR_Map mapdata) {
		this.mapdata = mapdata;
	}
	
}
