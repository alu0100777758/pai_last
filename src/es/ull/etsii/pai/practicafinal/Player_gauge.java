package es.ull.etsii.pai.practicafinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;

public class Player_gauge extends Entity implements Drawable {
	public static final int X_POS = 0;
	public static final int Y_POS = 0;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 50;
	private ArrayList<GraphicRectangle> graphicShapes = new ArrayList<GraphicRectangle>();
	private Player currentPlayer;
	private GraphicRectangle currentStatus ;
	
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
	public Player_gauge(Player player){
		setBackgroundGauge();
		setCurrentPlayer(player);
		setCurrentStatus(new GraphicRectangle(X_POS, Y_POS, WIDTH, HEIGHT));
		getCurrentStatus().setPaint(Color.RED);
		getGraphicShapes().add(getCurrentStatus());
		setFront();
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
	private void setBackgroundGauge(){
		GraphicRectangle background = new GraphicRectangle(X_POS, Y_POS, WIDTH, HEIGHT);
		background.setPaint(Color.BLACK);
		getGraphicShapes().add(background);
	}
	private void setFront(){
		GraphicRectangle grct = new GraphicRectangle(X_POS, Y_POS, WIDTH, HEIGHT);
		grct.setTexturePath(
				"textures/" + "front_gauge.png");
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
		for(GraphicRectangle rect : getGraphicShapes())
			rect.paint(g);	
	}
	private void updateCurrent() {
		System.out.println("max : " + getCurrentPlayer().getMaxHp() + " actual: " + getCurrentPlayer().getHp());
		getCurrentStatus().setFrame(X_POS, Y_POS,(int)( WIDTH*((double)getCurrentPlayer().getHp()/getCurrentPlayer().getMaxHp())), HEIGHT);
		
	}

}
