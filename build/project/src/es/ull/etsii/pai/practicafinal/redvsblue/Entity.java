package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

public abstract class Entity implements Serializable {

	/**
	 * Obtiene la forma de la entidad.
	 * @return
	 */
	public abstract RectangularShape getShape();	
	/**
	 * Cambia la posicion de la entidad.
	 * @param x
	 * @param y
	 */
	public abstract void setLocation(int x, int y);	
	/**
	 * Obtiene la coordenada x de la posicion.
	 * @return
	 */
	public abstract int getX();
	/**
	 * Obtiene la coordenada y de la posicion.
	 * @return
	 */
	public abstract int gety();
	/**
	 * Modifica el tama�o de la entidad.
	 * @param width
	 * @param height
	 */
	public abstract void setSize(int width, int height);
	/**
	 * Devuelve una copia de la entidad.
	 */
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
