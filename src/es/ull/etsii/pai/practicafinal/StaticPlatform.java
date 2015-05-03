package es.ull.etsii.pai.practicafinal;

import java.awt.Graphics;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Interceptable;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class StaticPlatform extends Entity implements Physical_passive {
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

	@Override
	public boolean belongs(Point2D point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intercepts(Segment segment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double interceptionDistance(Point2D point) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double interceptionDistance(Segment segment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point2D getCentroid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point2D getPos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPos(Point2D newpos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean collides(Physical_passive actor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Segment> getSegmentList() {
		return getPyhsicalRectangle().getSegmentList();
	}	
	

}
