package es.ull.etsii.pai.practicafinal.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import es.ull.etsii.pai.practicafinal.metaclass.PowerUpWeapon;
import es.ull.etsii.pai.practicafinal.metaclass.Weapon;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.Hook;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.Pistol;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.RocketLauncher;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.Slipper;
import es.ull.etsii.pai.practicafinal.metaclass.weapons.UZI;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
/**
 * Herramienta encargada de establecer las posiciones de los powerups.
 *	TODO incluir una clase "insertEntityTool" como base de la jerarquia, aunque actualmente tenga sentido 
 *  desde el punto de vista logico el estado actual en el diseño de clases en este punto es poco intuitivo.
 */
public class PowerUPTool extends PlayerInitTool {
	public static final String T_POWERUP_ICON = "/icons/upgradeTool.png";
	public static final int UZI = 0;					// Constante que define la referencia a un arma tipo "UZI"
	public static final int ROCKETLAUNCHER = 1;			// Constante que define la referencia a un arma tipo "rocket launcher"	
	public static final int PISTOL= 2;					// Constante que define la referencia a un arma tipo "pistol"
	public static final int SLIPPER = 3;				// Constante que define la referencia a un arma tipo "slipper"
	public static final int HOOK = 4;
	private int selectedWeapon = 0;						// emtero que define el tipo de arma seleccionada
	private JPopupMenu selectionPopup;					// menu desplegable desde donde se selecciona el tipo de arma

	public PowerUPTool() {
		setMenu();
		setButton(new JButton(new ImageIcon(getClass().getResource(
				T_POWERUP_ICON))));
	}

	/**
	 * Metodo encargado de inicializar el JPopupMenu
	 */
	private void setMenu() {
		setPopup(new JPopupMenu());
		JMenuItem wlc = new JMenuItem("LanzaCohetes");
		wlc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectedWeapon(ROCKETLAUNCHER);
			}
		});
		JMenuItem wuzi = new JMenuItem("UZI");
		wuzi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectedWeapon(UZI);
			}
		});
		JMenuItem wpis = new JMenuItem("Pistola");
		wpis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectedWeapon(PISTOL);
			}
		});
		JMenuItem wsli = new JMenuItem("Chola");
		wsli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectedWeapon(SLIPPER);
			}
		});
		JMenuItem whoo = new JMenuItem("Gancho");
		whoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectedWeapon(HOOK);
			}
		});
		getPopup().add(wlc);
		getPopup().add(wuzi);
		getPopup().add(wpis);
		getPopup().add(wsli);
		getPopup().add(whoo);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		switch (arg0.getButton()) {
		case MouseEvent.BUTTON3:
			selectionPopup.show(getFrame().getContentPane(), arg0.getX(),
					arg0.getY());
			break;

		default:
			super.mousePressed(arg0);
			break;
		}
	}

	@Override
	protected void add(MouseEvent e) {
		Weapon weapon = null;
		switch (getSelectedWeapon()) {
		case PISTOL:
			weapon = new Pistol((int) e.getX(), (int) e.getY());
			break;
		case UZI:
			weapon = new UZI((int) e.getX(), (int) e.getY());
			break;
		case ROCKETLAUNCHER:
			weapon = new RocketLauncher((int) e.getX(), (int) e.getY());
			break;
		case SLIPPER:
			weapon = new Slipper((int) e.getX(), (int) e.getY());
			break;
		case HOOK:
			weapon = new Hook((int) e.getX(), (int) e.getY());
			break;
		default:
			break;
		}
		getMap().addStaticMap(new PowerUpWeapon(weapon));
		setModified(true);
	}

	/**
	 * Getters y setters
	 */
	public int getSelectedWeapon() {
		return selectedWeapon;
	}

	public void setSelectedWeapon(int selectedWeapon) {
		this.selectedWeapon = selectedWeapon;
	}

	public JPopupMenu getPopup() {
		return selectionPopup;
	}

	public void setPopup(JPopupMenu popup) {
		this.selectionPopup = popup;
	}

	/*
	 * metodos sin usar de la clase padre (conceptualmente definen la ausencia
	 * de reaccion por parte de la herramienta a cada tipo de accion)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

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
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
