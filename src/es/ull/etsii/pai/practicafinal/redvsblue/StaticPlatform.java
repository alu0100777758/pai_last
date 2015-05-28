package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import es.ull.etsii.pai.practicafinal.graphics.Drawable;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class StaticPlatform extends Entity implements Physical_passive, Drawable {
	private GraphicRectangle graphicRectangle;						// Forma grafica de la plataforma.
	private PhysicalRectangle physicalRectangle;					// Forma fisica de la plataforma.
	
	/**
	 * Crea una plataforma en las coordenadadas indicadas con el alto y ancho indicados.
	 * @param x1
	 * @param y1
	 * @param width
	 * @param height
	 */
	public StaticPlatform(int x1, int y1, int width, int height) {
		setGraphicRectangle(new GraphicRectangle(x1, y1, width, height));
		getGraphicRectangle().setPaint(Color.GREEN);
		setPhysicalRectangle(new PhysicalRectangle(x1, y1, width, height));
	}
	/**
	 * Crea una plataforma a partir de los rectangulos grafico y fisico indicados.
	 * @param graphic
	 * @param physic
	 */
	public StaticPlatform(GraphicRectangle graphic, PhysicalRectangle physic) {
		setPhysicalRectangle((PhysicalRectangle) physic.clone());
		setGraphicRectangle((GraphicRectangle)graphic.clone());
	}
	
	
	public void paint(Graphics g) {
		getGraphicRectangle().paint(g);
	}
	@Override
	public boolean hasToDie() {
		return false;
	}

	@Override
	public boolean collides(Physical_passive actor) {
		
		return getPhysicalRectangle().collides(actor);
	}

	/**
	 * Getters y Setters.
	 * @return
	 */
	public GraphicRectangle getGraphicRectangle() {
		return graphicRectangle;
	}
	public void setGraphicRectangle(GraphicRectangle graphicRectangle) {
		this.graphicRectangle = graphicRectangle;
	}
	@Override
	public PhysicalRectangle getPhysicalRectangle() {
		return physicalRectangle;
	}
	public void setPhysicalRectangle(PhysicalRectangle pyhsicalRectangle) {
		this.physicalRectangle = pyhsicalRectangle;
	}

	@Override
	public ArrayList<Segment> getSegmentList() {
		return getPhysicalRectangle().getSegmentList();
	}

	@Override
	public Rectangle getCollisionedRectangle(Physical_passive actor) {
		return getPhysicalRectangle().getCollisionedRectangle(actor.getPhysicalRectangle());
	}

	@Override
	public RectangularShape getShape() {
		return getPhysicalRectangle();
	}

	@Override
	public void setLocation(int x, int y) {
		translatePhysicalShape(x, y);
		translateGraphicalShape(x,y);
	}

	private void translateGraphicalShape(int x, int y) {
		getGraphicRectangle().setLocation(x, y);
	}

	private void translatePhysicalShape(int x, int y) {
		getPhysicalRectangle().setLocation(x,y);
	}

	@Override
	public int getX() {
		return (int)getPhysicalRectangle().getLocation().getX();
	}

	@Override
	public int gety() {
		return (int)getPhysicalRectangle().getLocation().getY();
	}
	public void setSize(int width, int height) {
		getGraphicRectangle().setSize(new Dimension(width,height));
		getPhysicalRectangle().setSize(new Dimension(width,height));
	}
	public void setPaint(Paint paint){
		getGraphicRectangle().setPaint(paint);
	}
	@Override
	public Object clone(){
		Object newobject = new StaticPlatform(getGraphicRectangle(),getPhysicalRectangle());
		
		return newobject; 
	}

}
