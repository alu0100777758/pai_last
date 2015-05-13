package es.ull.etsii.pai.practicafinal.editor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.Actor;
import es.ull.etsii.pai.practicafinal.Entity;

public class DefaultTool extends EditorTool {
	public static final int PLANE_ACTORS = 0;
	public static final int PLANE_MAP = 1;
	public static final int PLANE_BACKGROUND = 2;
	private static Entity selectedEntity = null;
	private int xOffset = 0;
	private int yOffset = 0;
	private static int foundInplane = 0;

	public int getFoundInplane() {
		return foundInplane;
	}

	public void setFoundInplane(int foundInplane) {
		DefaultTool.foundInplane = foundInplane;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public Entity getSelectedActor() {
		return selectedEntity;
	}

	public void setSelectedActor(Entity selectedActor) {
		DefaultTool.selectedEntity = selectedActor;
	}

	public DefaultTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource("/icons/DefaultTool.png"))));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		setModified(true);
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		setSelectedActor(getFirstFor(e.getPoint()));
		if (getSelectedActor() != null) {
			setxOffset(e.getX() - getSelectedActor().getX());
			setyOffset(e.getY() - getSelectedActor().gety());
		}
	}

	protected Entity getFirstFor(Point p) {
		for (Actor actor : getMap().getActors()) {
			setFoundInplane(PLANE_ACTORS);
			if (actor.getPhysicalShape().contains(p))
				return actor;
		}
		for (Entity actor : getMap().getStaticMap()) {
			setFoundInplane(PLANE_MAP);
			if (actor.getShape().contains(p))
				return actor;
		}
		for (Entity actor : getMap().getBackground()) {
			setFoundInplane(PLANE_BACKGROUND);
			if (actor.getShape().contains(p))
				return actor;
		}
		setFoundInplane(-1);
		return null;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (getSelectedActor() != null) {
			selectedEntity.setLocation(e.getX() - getxOffset(), e.getY()
					- getyOffset());
			setModified(true);
		}
		// TODO Auto-generated method stub

	}
	public void moveAdd(Point point){
		if (getSelectedActor() != null) {
			selectedEntity.setLocation((int)(selectedEntity.getX()+point.getX()),(int)( selectedEntity.gety()+point.getY()));
			setModified(true);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		if (getSelectedActor() != null) {
			g2d.setColor(Color.YELLOW);
			g2d.draw(getSelectedActor().getShape());
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_DELETE:
		case KeyEvent.VK_BACK_SPACE:
			deleteSelected();
			break;
		case KeyEvent.VK_DOWN:
			moveAdd(new Point(0,1));
			break;
		case KeyEvent.VK_UP:
			moveAdd(new Point(0,-1));
			break;
		case KeyEvent.VK_LEFT:
			moveAdd(new Point(-1,0));
			break;
		case KeyEvent.VK_RIGHT:
			moveAdd(new Point(1,0));
			break;
		default:
			break;
		}
		setModified(true);

	}

	private void deleteSelected() {
		switch (getFoundInplane()) {
		case PLANE_ACTORS:
			getMap().getActors().remove(getSelectedActor());
			if (getSelectedActor().equals((Actor) getMap().getPlayer_one())) {
				getMap().setPlayer_one(null);
			}
			else if (getSelectedActor().equals((Actor) getMap().getPlayer_two())) {
				getMap().setPlayer_two(null);
			}
			break;
		case PLANE_MAP:
			getMap().getStaticMap().remove(getSelectedActor());
			break;
		case PLANE_BACKGROUND:
			getMap().getBackground().remove(getSelectedActor());
			break;
		default:
			break;
		}
		setSelectedActor(null);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
