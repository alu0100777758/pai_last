package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Color;



import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;

public class BackgroundTool extends RectangleTool{
	public BackgroundTool(){
		setButton(new JButton(new ImageIcon(getClass().getResource(
				"/icons/BackgroundTool.png"))));
	}
	protected void addRectangle(int i, int j, int i2, int j2) {
		int beginX = Math.min(i, i2);
		int beginY = Math.min(j, j2);
		int endX = Math.max(i, i2);
		int endY = Math.max(j, j2);
		GraphicRectangle rect = new GraphicRectangle(beginX, beginY, endX - beginX,
				endY - beginY);
		rect.setPaint(Color.BLUE);
		getMap().addBackground(rect);
	}
}
