package es.ull.etsii.pai.practicafinal.physics;

import es.ull.etsii.pai.prct9.geometry.Point2D;

public interface Physical_active extends Physical_passive{
	boolean repair_collisionY(Point2D point);
	boolean repair_collisionX(Point2D point);
	boolean repair_collision(Physical_passive actor);
	/**
	 * Devuelve false si el objeto no esta dentro del escenario :D
	 * @param map	Rectangulo que contiene al escenario completo.
	 * @return
	 */
	boolean updatePos(Physical_passive map);
}
