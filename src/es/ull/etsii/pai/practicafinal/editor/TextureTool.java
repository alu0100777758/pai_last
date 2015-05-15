package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.channels.SelectionKey;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

//import org.apache.commons.io.FileUtils;


import es.ull.etsii.pai.practicafinal.Actor;
import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.Entity;
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
		JFileChooser c = new JFileChooser(System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "src"
				+ System.getProperty("file.separator") + "textures");
		int rVal = c.showOpenDialog(getFrame());
		if (rVal == JFileChooser.APPROVE_OPTION) {
			// moveFile(c.getCurrentDirectory().toString()
			// + System.getProperty("file.separator")
			// + c.getSelectedFile().getName(), "textures/" +
			// c.getSelectedFile());
			setName(c.getSelectedFile().getName());
			System.out.println("name: " + getName());
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
//		if (arg0.getButton() == MouseEvent.BUTTON1) {
//			if (getSelectedActor() == null
//					&& arg0.getButton() == MouseEvent.BUTTON1)
//				getSelectedActor().add(getFirstFor(arg0.getPoint()));
//			else {
				if (!isDrawing()) {
					setDrawing(true);
					setBegin(arg0.getPoint());
					setLastVisited(getBegin());
				} else {
					setDrawing(false);
				}
//			}
//		}
		setModified(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
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
		int i = 0;
		for(Entity entity : getSelectedActor()){
		switch (getFoundInplane().get(i)) {
		case DefaultTool.PLANE_ACTORS:
			break;
		case DefaultTool.PLANE_MAP:
			StaticPlatform platform = (StaticPlatform) entity;
			platform.getGraphicRectangle().setTexturePath(
					"textures/" + getName());
			platform.getGraphicRectangle().setTextureAnchor(
					getAnchorRectangle());
			platform.getGraphicRectangle().setTexturized(true);
			break;
		default:
			break;
		}
		i++;
		}
	}
	public void enlarge(int size, int direction){
		int x = (int)getAnchorRectangle().getX();
		int y = (int)getAnchorRectangle().getY();
		int width = (int)getAnchorRectangle().getWidth();
		int height = (int)getAnchorRectangle().getHeight();
		switch (direction) {
		case Y_AXIS:
			height += size;
			break;
		case X_AXIS:
			width += size;
			break;
		default:
			break;
		}
		getAnchorRectangle().setLocation(x,y);
		getAnchorRectangle().setSize(width, height);
		setTexture();
		setModified(true);
	}
	@Override
	public void moveAdd(Point point) {
		if (!getSelectedActor().isEmpty()) {
			getAnchorRectangle().setLocation(
					(int) (getAnchorRectangle().getX() + point.getX()),
					(int) (getAnchorRectangle().getY() + point.getY()));
			setTexture();
			setModified(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		super.keyPressed(arg0);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		setLastVisited(e.getPoint());
		setModified(true);
		// TODO Auto-generated method stub

	}

}
