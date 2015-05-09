package es.ull.etsii.pai.practicafinal;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BvsR_Map implements Serializable{
	private static final long serialVersionUID = 8439640060806465321L;
	private ArrayList<Entity> background = new ArrayList<Entity>();
	private ArrayList<Entity> staticMap = new ArrayList<Entity>();
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	private ArrayList<Entity> GUI = new ArrayList<Entity>(); 
	private int width;
	private int height;
	private Player player_one;

	public BvsR_Map(Player player_one) {
		this.player_one = player_one;
	}

	public BvsR_Map() {
		// TODO Auto-generated constructor stub
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

	public ArrayList<Entity> getGUI() {
		return GUI;
	}

	public void setGUI(ArrayList<Entity> gUI) {
		GUI = gUI;
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

	public void addActor(StaticPlatform staticPlatform, int i) {
		switch(i){
		case 1 :
			staticMap.add(staticPlatform);
		}
		
	}

	public static BvsR_Map load(String string) throws FileNotFoundException, IOException, ClassNotFoundException {
		BvsR_Map map = null;
		ObjectInputStream save = new ObjectInputStream(new FileInputStream(string));
		map = (BvsR_Map) save.readObject();
		save.close();
	return map;
	}
}