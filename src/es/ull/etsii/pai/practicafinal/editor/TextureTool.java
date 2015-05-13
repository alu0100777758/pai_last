package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.nio.channels.SelectionKey;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.Actor;
import es.ull.etsii.pai.practicafinal.StaticPlatform;

public class TextureTool extends DefaultTool {
	private boolean drawing = false;
	private Point begin;
	private Point lastVisited;
	private String Name = "firstGrasssTextureTestDontJudgeMe.png";
	private Rectangle anchorRectangle;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Rectangle getAnchorRectangle() {
		return anchorRectangle;
	}

	public void setAnchorRectangle(Rectangle anchorRectangle) {
		this.anchorRectangle = anchorRectangle;
	}

	public boolean isDrawing() {
		return drawing;
	}

	public void setDrawing(boolean drawing) {
		this.drawing = drawing;
	}

	public Point getBegin() {
		return begin;
	}

	public void setBegin(Point begin) {
		this.begin = begin;
	}

	public Point getLastVisited() {
		return lastVisited;
	}

	public void setLastVisited(Point lastVisited) {
		this.lastVisited = lastVisited;
	}

	public TextureTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource(
				"/icons/textureTool.png"))));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (getSelectedActor() != null)
				SetTexture();
		}
		setModified(true);
	}

	private void SetTexture() {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (getSelectedActor() == null
				&& arg0.getButton() == MouseEvent.BUTTON1)
			setSelectedActor(getFirstFor(arg0.getPoint()));
		else {
			if (!isDrawing()) {
				setDrawing(true);
				setBegin(arg0.getPoint());
				setLastVisited(getBegin());
			} else {
				setDrawing(false);
			}
		}
		setModified(true);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (getSelectedActor() != null) {
			g2d.setColor(Color.YELLOW);
			g2d.draw(getSelectedActor().getShape());
		}
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
	public void mouseReleased(MouseEvent arg0) {
		if (isDrawing()) {
			setAnchorRectangle(new Rectangle(
					(int) Math.min(begin.getX(), getLastVisited().getX()),
					(int) Math.min(begin.getY(), getLastVisited().getY()),
					(int) ((int) Math.max(begin.getX(), getLastVisited().getX()) - (int) Math
							.min(begin.getX(), getLastVisited().getX())),
					(int) ((int) Math.max(begin.getY(), getLastVisited().getY()) - (int) Math
							.min(begin.getY(), getLastVisited().getY()))));
			setTexture();
			setDrawing(false);
		}
		// TODO Auto-generated method stub
		setModified(true);
	}

	private void setTexture() {
		switch (getFoundInplane()) {
		case DefaultTool.PLANE_ACTORS:
			break;
		case DefaultTool.PLANE_MAP:
			StaticPlatform platform = (StaticPlatform)getSelectedActor();
			platform.getGraphicRectangle().setTexturePath("textures/" + getName());
			platform.getGraphicRectangle().setTextureAnchor(getAnchorRectangle());
			platform.getGraphicRectangle().setTexturized(true);
			break;
		default:
			break;
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			setSelectedActor(null);
			break;
		default:
			break;
		}
		setModified(true);

	}
	@Override
	public void mouseDragged(MouseEvent e) {
		setLastVisited(e.getPoint());
		setModified(true);
		// TODO Auto-generated method stub

	}

}
