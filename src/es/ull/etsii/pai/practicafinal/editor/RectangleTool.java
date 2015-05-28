package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.redvsblue.StaticPlatform;


/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
/**
 * Herramienta encargada de insertar una superficie fisica  en  un mapa.
 */
public class RectangleTool extends EditorTool {
	public static final String T_RECTANGLE_ICON = "/icons/rectangleTool.png";	// ruta del icono
	private boolean drawing = false;											//	verdadero si la herramienta se encuentra dibujando el rectangulo
	private Point begin;														//	posicion del rectangulo (punto en el que se hace clic)
	private Point lastVisited;													//	Ultimo punto visitado durante el arrastre del boton

	public RectangleTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource(
				T_RECTANGLE_ICON))));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	protected void addRectangle(int i, int j, int i2, int j2) {
		int beginX = Math.min(i, i2);
		int beginY = Math.min(j, j2);
		int endX = Math.max(i, i2);
		int endY = Math.max(j, j2);
		StaticPlatform rect = new StaticPlatform(beginX, beginY, endX - beginX,
				endY - beginY);
		getMap().addStaticMap(rect);
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
		setModified(true);
	}

	/* (non-Javadoc)
	 * @see es.ull.etsii.pai.practicafinal.editor.EditorTool#paint(java.awt.Graphics)
	 * Dibuja el rectangulo encargado de ofrecer feedback visual de el rectangulo que va a crear al dejar de arrastrar.
	 */
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
	}

	/**
	 * Getters y setters
	 */
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
	
	/*
	 * metodos sin usar de la clase padre 
	 * (conceptualmente definen la ausencia de reaccion por parte de la herramienta a cada tipo de accion)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
