package es.ull.etsii.pai.practicafinal.physics;

import es.ull.etsii.pai.prct9.geometry.Point2D;

public interface Physical_active extends Physical_passive{
	boolean repair_collisionY(Point2D point);
	boolean repair_collisionX(Point2D point);
	boolean repair_collision(Physical_passive actor);
	void updatePos(Physical_passive map);
}
