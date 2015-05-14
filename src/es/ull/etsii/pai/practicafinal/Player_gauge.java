package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;

public class Player_gauge extends Entity implements Drawable {
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
		setCurrentStatus(new GraphicRectangle(x_pos, y_pos, WIDTH, HEIGHT));
		getCurrentStatus().setPaint(Color.RED);
		getGraphicShapes().add(getCurrentStatus());
		setFront();
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
		// TODO Auto-generated method stub

	}

	private void setBackgroundGauge() {
		GraphicRectangle background = new GraphicRectangle(x_pos, y_pos, WIDTH,
				HEIGHT);
		background.setPaint(Color.BLACK);
		getGraphicShapes().add(background);
	}

	private void setFront() {
		GraphicRectangle grct = new GraphicRectangle(x_pos, y_pos, WIDTH,
				HEIGHT);
		grct.setTexturePath("textures/" + "front_gauge.png");
		grct.setTextureAnchor(grct);
		grct.setTexturized(true);
		getGraphicShapes().add(grct);
	}

	@Override
	public RectangularShape getShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub

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

}
