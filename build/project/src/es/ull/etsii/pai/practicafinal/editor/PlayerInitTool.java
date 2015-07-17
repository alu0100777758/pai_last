package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.prct9.geometry.Point2D;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
/**
 * Herramienta encargada de establecer las posiciones de inicio de los jugadores.
 *
 */
public class PlayerInitTool extends EditorTool {
	public static final String T_PLAYERINIT_ICON = "/icons/PlayerInitTool.png"; 		//	ruta del icono
	public PlayerInitTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource(T_PLAYERINIT_ICON))));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()== MouseEvent.BUTTON1)
			add(e);
	}

	protected void add(MouseEvent e) {
		addPlayer(new Player(new Point2D(e.getX(), e.getY()), null));
		setModified(true);
	}
	/**
	 * @param player metodo que inserta el jugador en el mapa
	 */
	public void addPlayer(Player player) {
		if (player != null) {
			if (getMap().getPlayer_one() == null) {
				getMap().setPlayer_one(player);
				player.getGraphicShapes().get(player.getStats().getBODY())
						.setTexturePath("textures/blue.png");
				player.getGraphicShapes().get(player.getStats().getBODY())
						.setTextureAnchor(player.getPhysicalRectangle());
				player.getGraphicShapes().get(player.getStats().getBODY()).setImage(true);
				getMap().addActor(getMap().getPlayer_one());
			} else if (getMap().getPlayer_two() == null) {
				player.setColor(Color.RED);
				getMap().setPlayer_two(player);
				player.getGraphicShapes().get(player.getStats().getBODY())
						.setTexturePath("textures/red.png");
				player.getGraphicShapes().get(player.getStats().getBODY())
						.setTextureAnchor(player.getPhysicalRectangle());
				player.getGraphicShapes().get(player.getStats().getBODY()).setImage(true);
				getMap().addActor(getMap().getPlayer_two());
			}
		}
	}
	
	/*
	 * metodos sin usar de la clase padre 
	 * (conceptualmente definen la ausencia de reaccion por parte de la herramienta a cada tipo de accion)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	@Override
	public void mouseDragged(MouseEvent e) {
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
