package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class PlayerInitTool extends EditorTool {
	public PlayerInitTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource("/icons/PlayerInitTool.png"))));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
		if(e.getButton()== MouseEvent.BUTTON1)
			add(e);
	}

	protected void add(MouseEvent e) {
		addPlayer(new Player(new Point2D(e.getX(), e.getY()), getMap()));
		setModified(true);
	}
	public void addPlayer(Player player) {
		if (player != null) {
			if (getMap().getPlayer_one() == null) {
				getMap().setPlayer_one(player);
				player.getGraphicShapes().get(Player.BODY)
						.setTexturePath("textures/blue.png");
				player.getGraphicShapes().get(Player.BODY)
						.setTextureAnchor(player.getPhysicalRectangle());
				player.getGraphicShapes().get(Player.BODY).setImage(true);
				getMap().addActor(getMap().getPlayer_one());
			} else if (getMap().getPlayer_two() == null) {
				player.setColor(Color.RED);
				getMap().setPlayer_two(player);
				player.getGraphicShapes().get(Player.BODY)
						.setTexturePath("textures/red.png");
				player.getGraphicShapes().get(Player.BODY)
						.setTextureAnchor(player.getPhysicalRectangle());
				player.getGraphicShapes().get(Player.BODY).setImage(true);
				getMap().addActor(getMap().getPlayer_two());
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseDragged(MouseEvent e) {
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
