package es.ull.etsii.pai.practicafinal.physics;

import es.ull.etsii.pai.prct9.geometry.Point2D;

public class ParabolicLocomotion implements MovementEquation {
	private int gravity = 3;
	public ParabolicLocomotion(int gravity){
		setGravity(gravity);
	}
	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	@Override
	public Point2D getNewpos(Point2D vector, Point2D oldPos) {
		Point2D res =  new Point2D(0,0).add(oldPos);
		System.out.println("BEFORE -> pos : "+res +" | vel = " + vector);
		vector.setY(vector.y()+getGravity());
		System.out.println("pos : "+res +" | vel = " + vector);
		return res.add(vector);
	}

	@Override
	public int getNewX(Point2D vector, Point2D oldPos) {
		return (int) (oldPos.x() + vector.x());
	}

	@Override
	public int getNewY(Point2D vector, Point2D oldPos) {
		return (int) (oldPos.y() + vector.y() + getGravity());
	}

}
