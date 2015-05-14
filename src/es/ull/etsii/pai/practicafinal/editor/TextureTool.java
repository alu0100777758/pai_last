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
import es.ull.etsii.pai.practicafinal.StaticPlatform;

public class TextureTool extends DefaultTool {
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	private boolean drawing = false;
	private Point begin;
	private Point lastVisited;
	private String Name = "firstGrasssTextureTestDontJudgeMe.png";
	private Rectangle anchorRectangle;
	private boolean stetchingMode = true;
	
	public boolean isStetchingMode() {
		return stetchingMode;
	}

	public void setStetchingMode(boolean stetchingMode) {
		this.stetchingMode = stetchingMode;
	}

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

	// public void moveFile(String sourcePath, String destPath) {
	// File source = new File(sourcePath);
	// File dest = new File(destPath);
	// try {
	// FileUtils.copyDirectory(source, dest);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

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
		if (arg0.getButton() == MouseEvent.BUTTON1) {
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
			StaticPlatform platform = (StaticPlatform) getSelectedActor();
			platform.getGraphicRectangle().setTexturePath(
					"textures/" + getName());
			platform.getGraphicRectangle().setTextureAnchor(
					getAnchorRectangle());
			platform.getGraphicRectangle().setTexturized(true);
			break;
		default:
			break;
		}
	}
	public void enlarge(int size, int direction){
		int x = (int)getAnchorRectangle().getX();
		int y = (int)getAnchorRectangle().getY();
		int width = (int)getAnchorRectangle().getWidth();
		int height = (int)getAnchorRectangle().getHeight();
		switch (direction) {
		case UP:
			y -= size;
			height += size;
			break;
		case DOWN:
			y += size;
			height += size;
			break;
		case LEFT:
			x -= size;
			width += size;
			break;
		case RIGHT:
			x += size;
			height += size;
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
		if (getSelectedActor() != null) {
			getAnchorRectangle().setLocation(
					(int) (getAnchorRectangle().getX() + point.getX()),
					(int) (getAnchorRectangle().getY() + point.getY()));
			setTexture();
			setModified(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(isStetchingMode()){
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				enlarge(-1,DOWN);
				break;
			case KeyEvent.VK_UP:
				enlarge(1,UP);
				break;
			case KeyEvent.VK_LEFT:
				enlarge(-1,LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				enlarge(1,RIGHT);
				break;
			}
		}
		else{
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			moveAdd(new Point(0, 1));
			break;
		case KeyEvent.VK_UP:
			moveAdd(new Point(0, -1));
			break;
		case KeyEvent.VK_LEFT:
			moveAdd(new Point(-1, 0));
			break;
		case KeyEvent.VK_RIGHT:
			moveAdd(new Point(1, 0));
			break;
		case KeyEvent.VK_ESCAPE:
			setSelectedActor(null);
			break;
		default:
			break;
		}
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
