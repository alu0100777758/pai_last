package es.ull.etsii.pai.prct9;

import java.awt.Graphics;

import es.ull.etsii.pai.prct9.geometry.Positionable;

public interface Drawable extends Positionable {
	public void paint(Graphics g);
}
