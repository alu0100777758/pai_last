package es.ull.etsii.pai.practicafinal.metaclass;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import es.ull.etsii.pai.prct9.geometry.Point2D;

public class CollaboratorCreditText extends CreditText {
	public static final String FONT = "SanSerif";
	
	public CollaboratorCreditText(Point2D pos, Point2D speed, String text) {
		super(pos, speed, text);
		// TODO Auto-generated constructor stub
	}
	public CollaboratorCreditText(Point2D pos, Point2D speed, String text, Color color) {
		super(pos, speed, text, color);
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(getColor());
		g.setFont(new Font(FONT, Font.ITALIC, 48));
		g.drawString(getText(), (int)getPos().x(), (int)getPos().y());

	}

}
