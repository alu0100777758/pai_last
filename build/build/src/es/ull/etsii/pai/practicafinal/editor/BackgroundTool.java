package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
/**
 * Herramienta analoga a RectangleTool , con la diferencia de esta forma parte del fondo del mapa (carecen de fisicas, y se dibujan en primer lugar)
 *
 */
public class BackgroundTool extends RectangleTool{
	public static final String T_BACKGROUND_ICON = "/icons/BackgroundTool.png";		//	ruta del icono
	public BackgroundTool(){
		setButton(new JButton(new ImageIcon(getClass().getResource(
				T_BACKGROUND_ICON))));
	}
	protected void addRectangle(int i, int j, int i2, int j2) {
		int beginX = Math.min(i, i2);
		int beginY = Math.min(j, j2);
		int endX = Math.max(i, i2);
		int endY = Math.max(j, j2);
		GraphicRectangle rect = new GraphicRectangle(beginX, beginY, endX - beginX,
				endY - beginY);
		rect.setPaint(Color.BLUE);
		addRectangle(rect);
	}
	protected void addRectangle(GraphicRectangle rect) {
		getMap().addBackground(rect);
	}
}
