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
	Entity selectedEntity = null;
	int xOffset = 0;
	int yOffset = 0;
	private int foundInplane = 0;

	public int getFoundInplane() {
		return foundInplane;
	}

	public void setFoundInplane(int foundInplane) {
		this.foundInplane = foundInplane;
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
		this.selectedEntity = selectedActor;
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
		addRectangle(e.getX(), e.getY());
		System.out.println("default clicked");
		setModified(true);
		// TODO Auto-generated method stub

	}

	private void addRectangle(int i, int j) {
		// System.out.println("añadiendo");
		// map.addActor(new StaticPlatform(i,j, 100, 30),1);
		// repaint();
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

	private Entity getFirstFor(Point p) {
		for (Actor actor : getMap().getActors()) {
			setFoundInplane(0);
			if (actor.getPhysicalShape().contains(p))
				return actor;
		}
		for (Entity actor : getMap().getStaticMap()) {
			setFoundInplane(1);
			if (actor.getShape().contains(p))
				return actor;
		}
		for (Entity actor : getMap().getBackground()) {
			setFoundInplane(2);
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

		default:
			break;
		}
		setModified(true);

	}

	private void deleteSelected() {
		switch (getFoundInplane()) {
		case 0:
			getMap().getActors().remove(getSelectedActor());
			if (getSelectedActor().equals((Actor) getMap().getPlayer_one())) {
				getMap().setPlayer_one(null);
			}
			else if (getSelectedActor().equals((Actor) getMap().getPlayer_two())) {
				getMap().setPlayer_two(null);
			}
			break;
		case 1:
			getMap().getStaticMap().remove(getSelectedActor());
			break;
		case 2:
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
