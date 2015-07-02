package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.redvsblue.Actor;
import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.Entity;


/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
/**
 * Herramienta que representa el comportamiento de una herramienta "por defecto" (seleccion, desplazamiento...)
 *
 */
public class DefaultTool extends EditorTool {
	public static final String T_DEFAULT_ICON = "/icons/DefaultTool.png";					//	ruta del icono
	public static final int Y_AXIS = 0;														//	constante que define el eje Y
	public static final int X_AXIS = 3;														//	constante que define el eje X
	private static ArrayList<Entity> selectedEntity = new ArrayList<Entity>();				//	ArrayList que contendr� los elementos seleccionados
	private static ArrayList<Integer> foundInplane = new ArrayList<Integer>();				//	ArrayList que contendr� el plano en el que se ha encontrado cada entidad
	private static ArrayList<Entity> clipBoardSelectedEntity = new ArrayList<Entity>();		//	ArrayList que contendr� las entidades copiables
	private static ArrayList<Integer> clipBoardfoundInplane = new ArrayList<Integer>();		//	ArrayList que contendr� el plano en el que se ha encontrado cada entidad copiada
	private boolean stretchingMode;															//	Define si esta activado el modo "alargar"
	private boolean addingMode = false;														//	Define si esta activado el modo "agregar a la seleccion"
	private boolean removeMode = false;														//	Define si esta activado el modo "eliminar de la seleccion"
	private Rectangle shape = null;															//  Define el rectangulo en el que se encuadra la seleccion ("feedback" visual)
	public static final Color STRETCH_COLOR = Color.RED;									//	Color de la seleccion en modo "alargar"
	public static final Color TRANSLATE_COLOR = Color.YELLOW;								//	Color de la seleccion en modo "desplazar"
	private Color selectionColor = TRANSLATE_COLOR;											//	Color por defecto
	private Point lastPoint = null;															//	Ultimo punto en el que se ha detectado el raton durante el arrastre
	
	public DefaultTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource(
				T_DEFAULT_ICON))));
	}
	/**
	 * @param size cantidad de unidades que se va alargar el objeto.
	 * @param direction	eje en el que se va a alargar
	 * Metodo encargado de "estirar" los objetos (solo los conceptualmente redimensionables)
	 */
	public void enlarge(int size, int direction) {
		int i = 0;
		for (Entity entity : getSelectedActor()) {
			if (getFoundInplane().get(i) != BvsR_Map.PLANE_ACTORS) {
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
	/**
	 * Metodo encargado de borrar la seleccion
	 */
	private void clearAll() {
		getSelectedActor().clear();
		getFoundInplane().clear();
	}
	/**
	 * @param p punto para el que se quiere encontrar el primer objeto
	 * @return	entidad inmediatamente bajo ese punto (priorizando desde el ultimo objeto dibujado)
	 */
	protected Entity getFirstFor(Point p) {
		if (getMap().getForeground().size() != 0) {
			for (int i = getMap().getForeground().size() - 1; i >= 0; i--) {
				Entity actor = getMap().getForeground().get(i);
				if (actor.getShape().contains(p)) {
					getFoundInplane().add(BvsR_Map.PLANE_FOREKGROUND);
					return actor;
				}
			}
		}
		if (getMap().getActors().size() != 0) {
			for (int i = getMap().getActors().size() - 1; i >= 0; i--) {
				Actor actor = getMap().getActors().get(i);
				if (actor.getPhysicalShape().contains(p)) {
					getFoundInplane().add(BvsR_Map.PLANE_ACTORS);
					return actor;
				}
			}
		}
		if (getMap().getStaticMap().size() != 0) {
			for (int i = getMap().getStaticMap().size() - 1; i >= 0; i--) {
				Entity actor = getMap().getStaticMap().get(i);
				if (actor.getShape().contains(p)) {
					getFoundInplane().add(BvsR_Map.PLANE_MAP);
					return actor;
				}
			}
		}
		if (getMap().getBackground().size() != 0) {
			for (int i = getMap().getBackground().size() - 1; i >= 0; i--) {
				Entity actor = getMap().getBackground().get(i);
				if (actor.getShape().contains(p)) {
					getFoundInplane().add(BvsR_Map.PLANE_BACKGROUND);
					return actor;
				}
			}
		}
		getFoundInplane().add(-1);

		return null;
	}
	/**
	 * Metodo encargado de "pegar" el contenido copiado
	 */
	private void paste() {
		for (int i = 0; i < getClipBoardSelectedEntity().size(); i++) {
			if (getClipBoardSelectedEntity().get(i).clone() != getMap()
					.getPlayer_one()
					&& getClipBoardSelectedEntity().get(i).clone() != getMap()
							.getPlayer_two())
				getMap().addEntity(
						(Entity) getClipBoardSelectedEntity().get(i).clone(),
						getFoundInplane().get(i));
		}
		setModified(true);
	}

	/**
	 * Metodo encargado de realizar una copia de la seleccion
	 */
	private void copy() {
		setClipBoardSelectedEntity(new ArrayList<Entity>(getSelectedActor()));
		setClipBoardfoundInplane(new ArrayList<Integer>(getFoundInplane()));
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

	/**
	 * @param selectedActor entidades en la seleccion
	 * @return	rectangulo de tama�o minimo que contiene toda la seleccion
	 */
	protected Rectangle getShape(ArrayList<Entity> selectedActor) {
		if (selectedActor.isEmpty())
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

		return new Rectangle(minx, miny, mostRight - minx, mostDown - miny);
	}
	/**
	 * Metodo que elimina del mapa los objetos seleccionados
	 */
	private void deleteSelected() {
		for (int i = 0; i < getSelectedActor().size(); i++) {
			switch (getFoundInplane().get(i)) {
			case BvsR_Map.PLANE_ACTORS:
				if (getSelectedActor().get(i).equals(
						(Actor) getMap().getPlayer_one())) {
					getMap().setPlayer_one(null);
				} else if (getSelectedActor().get(i).equals(
						(Actor) getMap().getPlayer_two())) {
					getMap().setPlayer_two(null);
				}
				getMap().getActors().remove(getSelectedActor().get(i));
				break;
			case BvsR_Map.PLANE_MAP:
				getMap().getStaticMap().remove(getSelectedActor().get(i));
				break;
			case BvsR_Map.PLANE_BACKGROUND:
				getMap().getBackground().remove(getSelectedActor().get(i));
				break;
			case BvsR_Map.PLANE_FOREKGROUND:
				getMap().getForeground().remove(getSelectedActor().get(i));
				break;
			default:
				break;
			}
		}
		getSelectedActor().clear();
		getShape();
		setModified(true);
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
	@Override
	public void mouseClicked(MouseEvent e) {
		setModified(true);				// fuerza la actualizacion de pantalla
	}

	@Override
	public void mousePressed(MouseEvent e) {
		setLastPoint(e.getPoint());
		if (!isAddingMode()
				&& !isRemoveMode()
				&& (getSelectedActor().size() < 2 || !getShape().contains(
						e.getPoint())))
			clearAll();
		Entity entity = getFirstFor(e.getPoint());
		if (isRemoveMode()) {
			getSelectedActor().remove(entity);
		} else if (entity != null && !getSelectedActor().contains(entity)) {
			getSelectedActor().add(entity);
			Rectangle shape = getShape(getSelectedActor());
			setShape(shape);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!getSelectedActor().isEmpty()) {
			Point point = new Point(e.getX() - (int) getLastPoint().getX(),
					e.getY() - (int) getLastPoint().getY());
			moveAdd(point);
			setLastPoint(e.getPoint());
			setModified(true);
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
		case KeyEvent.VK_C:
			if (isRemoveMode())
				copy();
			break;
		case KeyEvent.VK_V:
			if (isRemoveMode())
				paste();
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
	
	/**
	 * Getters y Setters
	 */
	public Rectangle getShape() {
		if (shape != null)
			return shape;
		else
			return new Rectangle();
	}
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
		DefaultTool.foundInplane = foundInplane;
	}

	public ArrayList<Entity> getSelectedActor() {
		return selectedEntity;
	}

	public void setSelectedActor(ArrayList<Entity> selectedActor) {
		DefaultTool.selectedEntity = selectedActor;
	}

	public static ArrayList<Entity> getClipBoardSelectedEntity() {
		return clipBoardSelectedEntity;
	}

	public static void setClipBoardSelectedEntity(
			ArrayList<Entity> clipBoardSelectedEntity) {
		DefaultTool.clipBoardSelectedEntity = clipBoardSelectedEntity;
	}

	public static ArrayList<Integer> getClipBoardfoundInplane() {
		return clipBoardfoundInplane;
	}
	public static void setClipBoardfoundInplane(ArrayList<Integer> clipBoardfoundInplane) {
		DefaultTool.clipBoardfoundInplane = clipBoardfoundInplane;
	}
	/*
	 * metodos sin usar de la clase padre 
	 * (conceptualmente definen la ausencia de reaccion por parte de la herramienta a cada tipo de accion)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
