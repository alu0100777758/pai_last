package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.metaclass.PowerUpWeapon;
import es.ull.etsii.pai.practicafinal.metaclass.Weapon;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.Pistol;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.RocketLauncher;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.UZI;
import es.ull.etsii.pai.practicafinal.physics.PhysicalRectangle;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class PowerUPTool extends PlayerInitTool {
	public static final int UZI = 0;
	public static final int ROCKETLAUNCHER = 1;
	public static final int PISTOL= 2;
	private int selectedWeapon = 0;
	
	private JPopupMenu popup;
	public PowerUPTool() {
		setPopup(new JPopupMenu());
		 JMenuItem wlc = new JMenuItem("LanzaCohetes");
		 wlc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				setSelectedWeapon(ROCKETLAUNCHER);
			}});
		 JMenuItem wuzi = new JMenuItem("UZI");
		 wuzi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				setSelectedWeapon(UZI);
			}});
		 JMenuItem wpis = new JMenuItem("Pistola");
		 wpis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				setSelectedWeapon(PISTOL);
			}});
		 getPopup().add(wlc);
		 getPopup().add(wuzi);
		 getPopup().add(wpis);
		setButton(new JButton(new ImageIcon(getClass().getResource("/icons/upgradeTool.png"))));
	}
	
	public int getSelectedWeapon() {
		return selectedWeapon;
	}

	public void setSelectedWeapon(int selectedWeapon) {
		this.selectedWeapon = selectedWeapon;
	}

	public JPopupMenu getPopup() {
		return popup;
	}

	public void setPopup(JPopupMenu popup) {
		this.popup = popup;
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
		if(arg0.getButton() == MouseEvent.BUTTON3){
			
		        popup.show(getFrame().getContentPane(), arg0
		            .getX(), arg0.getY());
		        System.out.println("menu");
		}else
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
		Weapon weapon = null;
		switch (getSelectedWeapon()) {
		case PISTOL:
			weapon = new Pistol((int)e.getX(),(int)e.getY());
			break;
		case UZI:
			weapon = new UZI((int)e.getX(),(int)e.getY());
			break;
		case ROCKETLAUNCHER:
			weapon = new RocketLauncher((int)e.getX(),(int)e.getY());
			break;
		default:
			break;
		}
		getMap().addStaticMap(new PowerUpWeapon(weapon));
		setModified(true);
	}
}
