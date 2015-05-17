package es.ull.etsii.pai.practicafinal;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

public abstract class Entity implements Serializable {

	public abstract void reproduce();
	public abstract RectangularShape getShape();
	public abstract void setLocation(int x, int y);
	public abstract int getX();
	public abstract int gety();
	public abstract void setSize(int width, int height);
	
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
