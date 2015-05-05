package es.ull.etsii.pai.practicafinal;

import java.awt.Graphics;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Segment;

public class StaticPlatform extends Entity implements Physical_passive {
	private GraphicRectangle graphicRectangle;
	private PhysicalRectangle physicalRectangle;
	
	public StaticPlatform(int x1, int y1, int width, int height) {
		setGraphicRectangle(new GraphicRectangle(x1, y1, width, height));
		setPhysicalRectangle(new PhysicalRectangle(x1, y1, width, height));
	}
	
	public void paint(Graphics g) {
		getGraphicRectangle().paint(g.create());
	}
	/**
	 *  TODO
	 */
	public boolean collides(Actor actor) {
//		PhysicalRectangle actorShape = actor.getPhysicalShape(); 
//		
//		
//		System.out.println(getPhysicalRectangle().intercepts(actorShape.getBottom_segment()));
//		return getPhysicalRectangle().intercepts(actorShape.getBo);
		return false;
	}
	
	/**
	 * Distancia de colicion con los pies del jugador
	 * @param actor
	 * @return
	 */
	public double collisionDistance(Actor actor) {
//		PhysicalRectangle actorShape = actor.getPhysicalShape(); 
//		
//		return getPhysicalRectangle().interceptionDistance(actorShape.getBottom_segment());
		return 0;
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
	@Override
	public PhysicalRectangle getPhysicalRectangle() {
		return physicalRectangle;
	}
	public void setPhysicalRectangle(PhysicalRectangle pyhsicalRectangle) {
		this.physicalRectangle = pyhsicalRectangle;
	}

	
	@Override
	public boolean collides(Physical_passive actor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Segment> getSegmentList() {
		return getPhysicalRectangle().getSegmentList();
	}

}
