package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.metaclass.PowerUpWeapon;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.RocketLauncher;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.UZI;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class PowerUPTool extends PlayerInitTool {
	public PowerUPTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource("/icons/upgradeTool.png"))));
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	protected void add(MouseEvent e) {
		getMap().addStaticMap(new PowerUpWeapon(new UZI((int)e.getX(),(int)e.getY())));
		setModified(true);
	}
}
