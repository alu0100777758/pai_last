package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;



import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
public class BvsR_Map implements Serializable {
	public static final int PLANE_ACTORS = 0;
	public static final int PLANE_MAP = 1;
	public static final int PLANE_BACKGROUND = 2;
	public static final int PLANE_FOREKGROUND = 3;
	private static final long serialVersionUID = 8439640060806465321L;
	private ArrayList<Entity> background = new ArrayList<Entity>();
	private ArrayList<Entity> staticMap = new ArrayList<Entity>();
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	private ArrayList<Entity> foreground = new ArrayList<Entity>();
	private String description;
	private int width;
	private int height;
	private Player player_one = null;
	private Player player_two = null;


	public void addEntity(Entity ent, int plane){
		switch (plane) {
		case PLANE_ACTORS:
			getActors().add((Actor)ent);
			break;
	
		case PLANE_BACKGROUND:
			getBackground().add(ent);
			break;
		case PLANE_MAP:
			getStaticMap().add(ent);
			break;
		case PLANE_FOREKGROUND:
			getForeground().add(ent);
			break;	
		default:
			break;
		}
	}
	
	public void addBackground(GraphicRectangle rect) {
		getBackground().add(new GraphicEntity(rect));
	}

	public Player getPlayer_two() {
		return player_two;
	}

	public void setPlayer_two(Player player_two) {
		this.player_two = player_two;
	}

	public BvsR_Map() {
	}

	public ArrayList<Entity> getBackground() {
		return background;
	}

	public void setBackground(ArrayList<Entity> background) {
		this.background = background;
	}

	public ArrayList<Entity> getStaticMap() {
		return staticMap;
	}

	public void setStaticMap(ArrayList<Entity> staticMap) {
		this.staticMap = staticMap;
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Player getPlayer_one() {
		return player_one;
	}

	public void setPlayer_one(Player player_one) {
		this.player_one = player_one;
	}

	public void addStaticMap(Entity map) {
		staticMap.add(map);
	}

	public void addActor(Actor actor) {
		getActors().add(actor);
	}
	public void markForTexture() {
		for (int i = 0; i < getStaticMap().size(); i++) {
			StaticPlatform actor = (StaticPlatform) (getStaticMap().get(i));
			GraphicRectangle rect = actor.getGraphicRectangle();
			if (rect.getTexturePath() != null) {
				rect.setTexturized(true);
				rect.setPaint(Color.GREEN);

			}

		}
		for (int i = 0; i < getActors().size(); i++) {
			for (GraphicRectangle rect : getActors().get(i).getGraphicShapes()) {
				if (rect.getTexturePath() != null) {
					rect.setTexturized(true);
					rect.setPaint(Color.BLUE);
				}

			}
		}
		for (int i = 0; i < getBackground().size(); i++) {
			GraphicEntity actor = (GraphicEntity) getBackground().get(i);
			GraphicRectangle rect = actor.getGraphic();
			if (rect.getTexturePath() != null) {
				rect.setTexturized(true);
				rect.setPaint(Color.YELLOW);
			}

		}
		for (int i = 0; i < getForeground().size(); i++) {
			GraphicEntity actor = (GraphicEntity) getForeground().get(i);
			GraphicRectangle rect = actor.getGraphic();
			if (rect.getTexturePath() != null) {
				rect.setTexturized(true);
				rect.setPaint(Color.YELLOW);
			}

		}
	}

	public static BvsR_Map load(String string) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		BvsR_Map map = null;
		ObjectInputStream save = new ObjectInputStream(new FileInputStream(
				string));
		map = (BvsR_Map) save.readObject();
		save.close();
		return map;
		
	}

	public ArrayList<Entity> getForeground() {
		return foreground;
	}

	public void setForeground(ArrayList<Entity> foreground) {
		this.foreground = foreground;
	}

	public void addForeground(GraphicRectangle rect) {
		getForeground().add(new GraphicEntity(rect));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}