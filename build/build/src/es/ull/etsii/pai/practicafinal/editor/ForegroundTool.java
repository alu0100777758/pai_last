package es.ull.etsii.pai.practicafinal.editor;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;

public class ForegroundTool extends BackgroundTool {
	public static final String T_BACKGROUND_ICON = "/icons/ForegroundTool.png";		//	ruta del icono
	public ForegroundTool(){
		setButton(new JButton(new ImageIcon(getClass().getResource(
				T_BACKGROUND_ICON))));
	}
	@Override
	protected void addRectangle(GraphicRectangle rect) {
		getMap().addForeground(rect);
	}
}
