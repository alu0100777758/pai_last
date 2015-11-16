package es.ull.etsii.pai.practicafinal.physics;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.redvsblue.Actor;
import es.ull.etsii.pai.practicafinal.redvsblue.Bullet;
import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.Entity;
import es.ull.etsii.pai.practicafinal.redvsblue.RvsB_World;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class PhysicsEngine {
	RvsB_World world;
	public static final int WINDOW_TOLERANCE = 200; // Numero de pixeles que se
	// pueden salir los
	// jugadores de la pantalla
	// antes de morir.

	
	public PhysicsEngine(RvsB_World world) {
		this.world = world;
	}

	public RvsB_World getWorld() {
		return world;
	}

	public void setWorld(RvsB_World world) {
		this.world = world;
	}
	public void update() {
		Physical_passive map;

		for (int i = 0; i < getActors().size(); i++) {
			if (!((Physical_active) getActors().get(i))
					.updatePos(new PhysicalRectangle(-WINDOW_TOLERANCE / 2,
							-WINDOW_TOLERANCE / 2, ScreenManager.getInstance()
									.getWindWidth() + WINDOW_TOLERANCE,
							ScreenManager.getInstance().getWindHeight()
									+ WINDOW_TOLERANCE))) {
				getActors().get(i).die();
				getActors().remove(i);
			}

		}
		for (int i = 0; i < getMapData().getBullets().size(); i++) {
			Bullet bullet = getMapData().getBullets().get(i);
			if (!bullet.updatePos(new PhysicalRectangle(-WINDOW_TOLERANCE / 2,
					-WINDOW_TOLERANCE / 2, ScreenManager.getInstance()
							.getWindWidth() + WINDOW_TOLERANCE, ScreenManager
							.getInstance().getWindHeight() + WINDOW_TOLERANCE))) {
				bullet.setDead(true);
			}
			if (!bullet.isDead()) {
				for (int j = 0; j < getActors().size(); j++) {
					if ( getActors().get(j) instanceof Physical_passive) {
						if (getActors().get(j) != bullet && bullet.collides((Physical_passive) getActors().get(
								j)))
							bullet.setDead(true);
					}
				}
				for(int l = 0; l < getStaticMap().size(); l++){
					if(getStaticMap().get(l) instanceof Physical_passive){
						if(bullet.collides((Physical_passive)getMapData().getStaticMap().get(l)))
							bullet.setDead(true);
					}
				}
			}
			if(bullet.isDead())
				getMapData().getBullets().remove(bullet);

		}
		/**
		 * Aqui es donde se comprueban colisiones.
		 */
		for (int i = 0; i < getStaticMap().size(); i++) {
			map = (Physical_passive) (getStaticMap().get(i));
			if (map.collides(getMapData().getPlayer_one())/* )getPlayer_one().collides(map) */)
				getMapData().getPlayer_one().repair_collision(map);
			if (map.collides(getMapData().getPlayer_two())/* )getPlayer_one().collides(map) */)
				getMapData().getPlayer_two().repair_collision(map);
			for (int j = 0; j < getActors().size(); j++) {
				if (getActors().get(j) instanceof Bullet) {
					if (((Bullet) getActors().get(j)).collides(map))
						getActors().remove(j);
				}
			}
		}
		// /**
		// * Verifica si le pego a algun jugador
		// */
		//
		// for (int i = 0; i < getActors().size(); i++) {
		// if (getActors().get(i) instanceof Bullet) {
		// if (getPlayer_one().collides(
		// getActors().get(i).getPhysicalShape())) {
		// getPlayer_one().gotHit((Bullet) getActors().get(i));
		// getActors().remove(getActors().get(i));
		// } else if (getPlayer_two().collides(
		// getActors().get(i).getPhysicalShape())) {
		// getPlayer_two().gotHit((Bullet) getActors().get(i));
		// getActors().remove(getActors().get(i));
		// }
		// }
		// }


	}

	private ArrayList<Entity> getStaticMap() {
		return getWorld().getMapData().getStaticMap();
	}

	private  RvsB_World getMapData() {
		return getWorld();
	}

	private  ArrayList<Actor> getActors() {
		return getWorld().getMapData().getActors();
	}
	

}
