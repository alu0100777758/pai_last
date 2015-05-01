package es.ull.etsii.pai.practicafinal;

import java.awt.Graphics;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.prct9.geometry.Interceptable;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class StaticPlatform extends Entity {
	private GraphicRectangle graphicRectangle;
	private PhysicalRectangle pyhsicalRectangle;
	
	public StaticPlatform(Point2D start, Point2D end) {
		setGraphicRectangle(new GraphicRectangle(start, end));
		setPyhsicalRectangle(new PhysicalRectangle(start, end));
	}
	
	public void paint(Graphics g) {
		getGraphicRectangle().paint(g.create());
	}
	public boolean collides(Actor actor) {
		PhysicalRectangle actorShape = actor.getPhysicalShape(); 
		
		
		System.out.println(getPyhsicalRectangle().intercepts(actorShape.getBottom_segment()));
		return getPyhsicalRectangle().intercepts(actorShape.getBottom_segment());
	}
	
	/**
	 * Distancia de colicion con los pies del jugador
	 * @param actor
	 * @return
	 */
	public double collisionDistance(Actor actor) {
		PhysicalRectangle actorShape = actor.getPhysicalShape(); 
		
		return getPyhsicalRectangle().interceptionDistance(actorShape.getBottom_segment());
	}
	@Override
	public void reproduce() {
		// TODO Auto-generated method stub
	}
	public GraphicRectangle getGraphicRectangle() {
		return graphicRectangle;
	}
	public void setGraphicRectangle(GraphicRectangle graphicRectangle) {
		this.graphicRectangle = graphicRectangle;
	}
	public PhysicalRectangle getPyhsicalRectangle() {
		return pyhsicalRectangle;
	}
	public void setPyhsicalRectangle(PhysicalRectangle pyhsicalRectangle) {
		this.pyhsicalRectangle = pyhsicalRectangle;
	}	
	

}
