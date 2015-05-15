package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.Actor;
import es.ull.etsii.pai.practicafinal.Entity;

public class DefaultTool extends EditorTool {
	public static final int Y_AXIS = 0;
	public static final int X_AXIS = 3;
	public static final int PLANE_ACTORS = 0;
	public static final int PLANE_MAP = 1;
	public static final int PLANE_BACKGROUND = 2;
	private static ArrayList<Entity> selectedEntity = new ArrayList<Entity>();
	private static ArrayList<Integer> foundInplane = new ArrayList<Integer>();
	private boolean stretchingMode;
	private boolean addingMode = false;
	private boolean removeMode = false;
	private Rectangle shape = null;
	public static final Color STRETCH_COLOR = Color.RED;
	public static final Color TRANSLATE_COLOR = Color.YELLOW;
	private Color selectionColor = TRANSLATE_COLOR;
	private Point lastPoint = null;
	

	public Point getLastPoint() {
		return lastPoint;
	}

	public void setLastPoint(Point lastPoint) {
		this.lastPoint = lastPoint;
	}

	public boolean isRemoveMode() {
		return removeMode;
	}

	public void setRemoveMode(boolean removeMode) {
		this.removeMode = removeMode;
	}

	public Color getSelectionColor() {
		return selectionColor;
	}

	public void setSelectionColor(Color selectionColor) {
		this.selectionColor = selectionColor;
	}

	public Rectangle getShape() {
		return shape;
	}

	public void setShape(Rectangle shape) {
		this.shape = shape;
	}

	public boolean isAddingMode() {
		return addingMode;
	}

	public void setAddingMode(boolean isCtrlPulsed) {
		this.addingMode = isCtrlPulsed;
	}

	public boolean isStetchingMode() {
		return stretchingMode;
	}

	public void setStetchingMode(boolean stetchingMode) {
		this.stretchingMode = stetchingMode;
	}


	public ArrayList<Integer> getFoundInplane() {
		return foundInplane;
	}

	public void setFoundInplane(ArrayList<Integer> foundInplane) {
		this.foundInplane = foundInplane;
	}

	public ArrayList<Entity> getSelectedActor() {
		return selectedEntity;
	}

	public void setSelectedActor(ArrayList<Entity> selectedActor) {
		DefaultTool.selectedEntity = selectedActor;
	}

	public DefaultTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource(
				"/icons/DefaultTool.png"))));
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
		setLastPoint(e.getPoint());
		if(!isAddingMode() && !isRemoveMode() && (getSelectedActor().size() <2 || !getShape().contains(e.getPoint())))
			clearAll();
		Entity entity = getFirstFor(e.getPoint());
		if (isRemoveMode()) {
			getSelectedActor().remove(entity);
		} else if(entity != null && !getSelectedActor().contains(entity)){
			getSelectedActor().add(entity);
			Rectangle shape = getShape(getSelectedActor());
			setShape(shape);
		}
	}

	private void clearAll() {
		getSelectedActor().clear();
		getFoundInplane().clear();
	}

	protected Entity getFirstFor(Point p) {
		for (Actor actor : getMap().getActors()) {
			getFoundInplane().add(PLANE_ACTORS);
			if (actor.getPhysicalShape().contains(p))
				return actor;
		}
		for (Entity actor : getMap().getStaticMap()) {
			getFoundInplane().add(PLANE_MAP);
			if (actor.getShape().contains(p))
				return actor;
		}
		for (Entity actor : getMap().getBackground()) {
			getFoundInplane().add(PLANE_BACKGROUND);
			if (actor.getShape().contains(p))
				return actor;
		}
		getFoundInplane().add(-1);
		return null;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!getSelectedActor().isEmpty()) {
			Point point = new Point(e.getX()-(int)getLastPoint().getX(),e.getY()-(int)getLastPoint().getY());
				moveAdd(point);
			setLastPoint(e.getPoint());
			setModified(true);
		}

	}

	public void moveAdd(Point point) {
		if (!getSelectedActor().isEmpty()) {
			for (Entity selectEntity : getSelectedActor()) {
				selectEntity.setLocation(
						(int) (selectEntity.getX() + point.getX()),
						(int) (selectEntity.gety() + point.getY()));
			}
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
		if (!getSelectedActor().isEmpty()) {
			g2d.setColor(getSelectionColor());
			setShape(getShape(getSelectedActor()));
			g2d.draw(getShape());
		}
	}

	protected Rectangle getShape(ArrayList<Entity> selectedActor) {
		if(selectedActor.isEmpty())
			return null;
		int minx = Integer.MAX_VALUE;
		int miny = Integer.MAX_VALUE;
		int mostRight = Integer.MIN_VALUE;
		int mostDown = Integer.MIN_VALUE;
		for (Entity entity : getSelectedActor()) {
			minx = (int) Math.min(entity.getX(), minx);
			miny = (int) Math.min(entity.gety(), miny);
			mostRight = (int) (Math.max(entity.getX()
					+ entity.getShape().getWidth(), mostRight));
			mostDown = (int) (Math.max(entity.gety()
					+ entity.getShape().getHeight(), mostDown));
		}

		// System.out.println("minx:" + minx + " min y: "+ miny +" maxx "+maxx+
		// " maxy "+maxy);
		return new Rectangle(minx, miny, mostRight - minx, mostDown - miny);
	}

	public void enlarge(int size, int direction) {
		int i = 0;
		for (Entity entity : getSelectedActor()) {
			if (getFoundInplane().get(i) == PLANE_MAP) {
				int x = (int) entity.getX();
				int y = (int) entity.gety();
				int width = (int) entity.getShape().getWidth();
				int height = (int) entity.getShape().getHeight();
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
				entity.setLocation(x, y);
				entity.setSize(width, height);
			}
			i++;
		}
		setModified(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_DELETE:
		case KeyEvent.VK_BACK_SPACE:
			deleteSelected();
			break;
		case KeyEvent.VK_DOWN:
			if (isStetchingMode())
				enlarge(1, Y_AXIS);
			else
				moveAdd(new Point(0, 1));
			break;
		case KeyEvent.VK_UP:
			if (isStetchingMode())
				enlarge(-1, Y_AXIS);
			else
				moveAdd(new Point(0, -1));
			break;
		case KeyEvent.VK_LEFT:
			if (isStetchingMode())
				enlarge(-1, X_AXIS);
			else
				moveAdd(new Point(-1, 0));
			break;
		case KeyEvent.VK_RIGHT:
			if (isStetchingMode())
				enlarge(1, X_AXIS);
			else
				moveAdd(new Point(1, 0));
			break;
		case KeyEvent.VK_M:
			setStetchingMode(!isStetchingMode());
			if (isStetchingMode())
				setSelectionColor(STRETCH_COLOR);
			else
				setSelectionColor(TRANSLATE_COLOR);
			setModified(true);
			break;
		case KeyEvent.VK_CONTROL:
			setRemoveMode(true);
			break;
		case KeyEvent.VK_SHIFT:
			setAddingMode(true);
			break;
		default:
			break;
		}

	}

	private void deleteSelected() {
		for (int i = 0; i < getSelectedActor().size(); i++) {
			switch (getFoundInplane().get(i)) {
			case PLANE_ACTORS:
				getMap().getActors().remove(getSelectedActor());
				if (getSelectedActor().equals((Actor) getMap().getPlayer_one())) {
					getMap().setPlayer_one(null);
				} else if (getSelectedActor().equals(
						(Actor) getMap().getPlayer_two())) {
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
		}
		setSelectedActor(null);
		setModified(true);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_CONTROL:
			setRemoveMode(false);
			break;
		case KeyEvent.VK_SHIFT:
			setAddingMode(false);
			break;
		default:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
