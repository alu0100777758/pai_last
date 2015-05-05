package es.ull.etsii.pai.prct9;

import java.awt.Graphics;
import java.util.Collection;

import es.ull.etsii.pai.prct9.geometry.Interceptable;
import es.ull.etsii.pai.prct9.geometry.Point2D;
import es.ull.etsii.pai.prct9.geometry.Segment;

public abstract class Actor implements Drawable, Interceptable {
	public static final short UP_DIR = 1;
	public static final short LEFT_DIR = 2;
	public static final short RIGHT_DIR = -2;
	public static final short DOWN_DIR = -1;

	private Collection<Drawable> graphicGeometry;
	private Collection<Interceptable> physicalGeometry;
	
	public Actor(Collection<Drawable> collectionGraphics, Collection<Interceptable> collectionPhysical) {
		graphicGeometry = collectionGraphics;
		physicalGeometry = collectionPhysical;
	}
	public void paint(Graphics g) {
		for (Drawable geometry : graphicGeometry) {
			geometry.paint(g);
		}
	};
	void addGeometry(Interceptable newObject){
		physicalGeometry.add(newObject);
	}
	void addGraphic(Drawable newObject){
		graphicGeometry.add(newObject);
	}
	public void move(double steps, short direction) {
		for (Drawable graphic : graphicGeometry) {
			switch (direction) {
			case (Actor.LEFT_DIR):
				graphic.setPos(graphic.getPos().substract(steps, 0));
				break;
			case (Actor.RIGHT_DIR):
				graphic.setPos(graphic.getPos().add(steps, 0));
				break;
			case (Actor.UP_DIR):
				graphic.setPos(graphic.getPos().substract(0, steps));
				break;
			case (Actor.DOWN_DIR):
				graphic.setPos(graphic.getPos().add(0, steps));
				break;
			}
//			System.out.println("desplazado a: (" + graphic.getPos().x() + ", " + graphic.getPos().y() + ")");
		}
		for (Interceptable geometry : physicalGeometry) {
			switch (direction) {
			case (Actor.LEFT_DIR):
				geometry.setPos(geometry.getPos().substract(steps, 0));
				break;
			case (Actor.RIGHT_DIR):
				geometry.setPos(geometry.getPos().add(steps, 0));
				break;
			case (Actor.UP_DIR):
				geometry.setPos(geometry.getPos().substract(0, steps));
				break;
			case (Actor.DOWN_DIR):
				geometry.setPos(geometry.getPos().add(0, steps));
				break;
			}
		}
	}
	@Override
	public boolean belongs(Point2D point) {
		boolean res = true;
		for(Interceptable object : physicalGeometry){
			res &= object.belongs(point);
			if(!res)
				return res;
		}
		return res;
	}
	@Override
	public boolean intercepts(Segment segment) {
		boolean res = true;
		for(Interceptable object : physicalGeometry){
			res &= object.intercepts(segment);
			if(!res)
				return res;
		}
		return res;
	}
}
