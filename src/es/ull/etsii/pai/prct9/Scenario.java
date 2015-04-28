package es.ull.etsii.pai.prct9;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import es.ull.etsii.pai.prct9.geometry.Point2D;

public class Scenario extends JPanel {
	private static final long serialVersionUID = 5612241991133512463L;
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	private EnvelopRectangle geometryLimits;

	public Scenario() {
		setBackground(Color.CYAN);
		geometryLimits = new EnvelopRectangle(new Point2D(0, 0), new Point2D(0,
				0));
	}

	public void setLimits(Point2D start, Point2D end) {
		geometryLimits = new EnvelopRectangle(start, end);
	}

	public void setLimits() {
		geometryLimits.setHeight(getHeight());
		geometryLimits.setWidth(getWidth());
	}

	void addActor(Actor actor) {
		actors.add(actor);

	}

	public void paint(Graphics g) {
		super.paint(g);
//		 compensateRedim();
		for (Actor actor : actors) {
			actor.paint(g);
		}
		setLimits();
	}

	public boolean isIn(Actor actor) {
		return geometryLimits.envelops(actor);
	}
	public void reemplaceBall(Actor actor) {
		if(!actor.intercepts(geometryLimits.getTop_segment()) && !isIn(actor)){
			actor.move(getHeight(), Actor.DOWN_DIR);
		}
		else if(!actor.intercepts(geometryLimits.getBottom_segment()) && !isIn(actor)){
			actor.move(getHeight(), Actor.UP_DIR);
		}
		else if(!actor.intercepts(geometryLimits.getLeft_segment()) && !isIn(actor)){
			actor.move(getWidth(), Actor.RIGHT_DIR);
		}
		else if(!actor.intercepts(geometryLimits.getRight_segment()) && !isIn(actor)){
			actor.move(getWidth(), Actor.LEFT_DIR);
		}
	}
	public boolean intercepts(Actor actor) {
		return (actor.intercepts(geometryLimits.getTop_segment()) || actor.intercepts(geometryLimits.getBottom_segment()) || actor.intercepts(geometryLimits.getLeft_segment()) ||actor.intercepts(geometryLimits.getRight_segment()));
	}

	public double lenghtToLimit(Actor actor) {
		// Segment top = geometryLimits.getTop_segment();
		// Segment left = geometryLimits.getLeft_segment();
		// Segment right = geometryLimits.getRight_segment();
		// Segment bottom = geometryLimits.getBottom_segment();
		double min = Math.min(
				actor.interceptionDistance(geometryLimits.getTop_segment()),
				actor.interceptionDistance(geometryLimits.getLeft_segment()));
		min = Math.min(min,
				actor.interceptionDistance(geometryLimits.getRight_segment()));
		return Math.min(min,
				actor.interceptionDistance(geometryLimits.getBottom_segment()));
	}

	protected void compensateRedim() {
		double xmodifiedProportion = (getWidth()) / geometryLimits.getWidth();
		double ymodifiedProportion = (getHeight()) / geometryLimits.getHeight();
//		System.out.println("proporciones: x: "+ xmodifiedProportion + " Y : "+ymodifiedProportion);
		for (Actor actor : actors) {
			Point2D point = actor.getPos();
			point.setX(point.x() * xmodifiedProportion);
			point.setY(point.y() * ymodifiedProportion);
		}
		setLimits();
	}
}
