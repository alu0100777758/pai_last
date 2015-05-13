package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.StaticPlatform;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.graphics.Texture;

public class RectangleTool extends EditorTool {
	private boolean drawing = false;
	private Point begin;
	private Point lastVisited;

	public Point getLastVisited() {
		return lastVisited;
	}

	public void setLastVisited(Point lastVisited) {
		this.lastVisited = lastVisited;
	}

	public Point getBegin() {
		return begin;
	}

	public void setBegin(Point begin) {
		this.begin = begin;
	}

	public boolean isDrawing() {
		return drawing;
	}

	public void setDrawing(boolean drawing) {
		this.drawing = drawing;
	}

	public RectangleTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource(
				"/icons/rectangleTool.png"))));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	private void addRectangle(int i, int j, int i2, int j2) {
		System.out.println("añadiendo");
		int beginX = Math.min(i, i2);
		int beginY = Math.min(j, j2);
		int endX = Math.max(i, i2);
		int endY = Math.max(j, j2);
		// firstGrasssTextureTestDontJudgeMe.png
		StaticPlatform rect = new StaticPlatform(beginX, beginY, endX - beginX,
				endY - beginY);
		//			rect.setPaint(new Texture("textures/firstGrasssTextureTestDontJudgeMe.png", rect.getPhysicalRectangle()));
		rect.getGraphicRectangle().setTexturePath("textures/firstGrasssTextureTestDontJudgeMe.png");
		rect.getGraphicRectangle().setTexturized(true);
		getMap().addStaticMap(rect);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (!isDrawing()) {
			setDrawing(true);
			setBegin(arg0.getPoint());
			setLastVisited(getBegin());
		} else {
			addRectangle((int) begin.getX(), (int) begin.getY(), arg0.getX(),
					arg0.getY());
			setDrawing(false);
		}
		setModified(true);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (isDrawing()) {
			addRectangle((int) begin.getX(), (int) begin.getY(), arg0.getX(),
					arg0.getY());
			setDrawing(false);
		}
		// TODO Auto-generated method stub
		setModified(true);
	}

	@Override
	public void paint(Graphics g) {
		if (isDrawing()) {
			g.drawRect(
					(int) Math.min(begin.getX(), getLastVisited().getX()),
					(int) Math.min(begin.getY(), getLastVisited().getY()),
					(int) ((int) Math.max(begin.getX(), getLastVisited().getX()) - (int) Math
							.min(begin.getX(), getLastVisited().getX())),
					(int) ((int) Math.max(begin.getY(), getLastVisited().getY()) - (int) Math
							.min(begin.getY(), getLastVisited().getY())));

		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		setLastVisited(e.getPoint());
		setModified(true);
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
