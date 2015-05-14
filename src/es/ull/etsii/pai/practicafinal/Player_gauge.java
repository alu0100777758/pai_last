package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.io.Serializable;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;

public class Player_gauge extends Entity implements Drawable, Serializable {
	public static final int LIFE_FEEDBACK_PORTION = 5;
	public static final int TOP_RIGHT = 1;
	private int x_pos = 0;
	private int y_pos = 0;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 50;
	private ArrayList<GraphicRectangle> graphicShapes = new ArrayList<GraphicRectangle>();
	private Player currentPlayer;
	private GraphicRectangle currentStatus;

	public Player_gauge(Player player, int pos) {
		if(pos == TOP_RIGHT){
			setX_pos(ResourceManager.getInstance().getWindWidth() - WIDTH);
		}
		setBackgroundGauge();
		setCurrentPlayer(player);
		setCurrent();
		setFront();
		setBorder();
	}
	
	public void setCurrent(){
		GraphicRectangle current = new GraphicRectangle(x_pos, y_pos, WIDTH,
				HEIGHT);
		current.setTexturePath("textures/" + "mid_gauge.png");
		current.setTextureAnchor(new Rectangle(x_pos,y_pos,WIDTH,HEIGHT));
		current.setTexturized(true);
		setCurrentStatus(current);
		getGraphicShapes().add(getCurrentStatus());
	}
	public int getX_pos() {
		return x_pos;
	}

	public void setX_pos(int x_pos) {
		this.x_pos = x_pos;
	}

	public int getY_pos() {
		return y_pos;
	}

	public void setY_pos(int y_pos) {
		this.y_pos = y_pos;
	}

	public GraphicRectangle getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(GraphicRectangle currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public ArrayList<GraphicRectangle> getGraphicShapes() {
		return graphicShapes;
	}

	public void setGraphicShapes(ArrayList<GraphicRectangle> graphicShapes) {
		this.graphicShapes = graphicShapes;
	}

	@Override
	public void reproduce() {
	}

	private void setBackgroundGauge() {
		GraphicRectangle background = new GraphicRectangle(x_pos, y_pos, WIDTH,
				HEIGHT);
		background.setTexturePath("textures/" + "background_gauge.png");
		background.setTextureAnchor(new Rectangle(x_pos,y_pos,WIDTH,HEIGHT));
		background.setTexturized(true);
		getGraphicShapes().add(background);
	}

	private void setFront() {
		GraphicRectangle grct = new GraphicRectangle(x_pos, y_pos, WIDTH,
				HEIGHT);
		grct.setTexturePath("textures/" + "front_gauge.png");
		grct.setTextureAnchor(new Rectangle(x_pos,y_pos,(int)(WIDTH*((double)LIFE_FEEDBACK_PORTION/getCurrentPlayer().getMaxHp())),HEIGHT));
		grct.setTexturized(true);
		getGraphicShapes().add(grct);
	}
	private void setBorder() {
		GraphicRectangle grct = new GraphicRectangle(x_pos, y_pos, WIDTH, HEIGHT);
		grct.setTexturePath("textures/" + "border_gauge.png");
		grct.setTextureAnchor(new Rectangle(x_pos,y_pos,WIDTH,HEIGHT));
		grct.setTexturized(true);
		getGraphicShapes().add(grct);
	}

	@Override
	public RectangularShape getShape() {
		return null;
	}

	@Override
	public void setLocation(int x, int y) {
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int gety() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void paint(Graphics g) {
		updateCurrent();
		for (GraphicRectangle rect : getGraphicShapes())
			rect.paint(g);
	}

	private void updateCurrent() {
//		System.out.println("max : " + getCurrentPlayer().getMaxHp()
//				+ " actual: " + getCurrentPlayer().getHp());
		getCurrentStatus()
				.setFrame(
						x_pos,
						y_pos,
						(int) (WIDTH * ((double) getCurrentPlayer().getHp() / getCurrentPlayer()
								.getMaxHp())), HEIGHT);

	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

}
