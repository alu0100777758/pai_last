package es.ull.etsii.pai.practicafinal.main;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.metal.MetalButtonUI;

import es.ull.etsii.pai.practicafinal.editor.EditorFrame;
import es.ull.etsii.pai.practicafinal.redvsblue.ResourceManager;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;
import es.ull.etsii.pai.practicafinal.scenes.CreditsScene;

public class RvsB_Menu extends ScenarioPanel implements ActionListener {
	private BackgroundPanel pict;
	private BackgroundPanel menuBackground;
	private JPanel menuButtons;
	public static final String PLAY_PICT = "Recursos\\textures\\menu_play.png";
	public static final String EDITOR_PICT = "Recursos\\textures\\menu_editor.png";
	public static final String CREDITS_PICT = "Recursos\\textures\\menu_credits.png";
	public static final String EXIT_PICT = "Recursos\\textures\\menu_exit.png";
	public static final String DEFAULT_PICT = PLAY_PICT;
	public static final String BACKGROUND = "Recursos\\textures\\menu_background.png";

	public RvsB_Menu() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setMenuBackground(new BackgroundPanel(ResourceManager.getInstance()
				.getBufferedImage(BACKGROUND)));
		LayoutManager menulay ;
//		menulay = new BoxLayout(getMenuBackground(), BoxLayout.X_AXIS);
		menulay = new GridBagLayout();
		getMenuBackground().setLayout(menulay);
		setMenuButtons(new JPanel());
		setPict(new BackgroundPanel(ResourceManager.getInstance()
				.getBufferedImage(DEFAULT_PICT)));
		pict.setBackground(Color.RED);
		// mainMenu.setBackground(Color.GREEN);
		getMenuButtons().setLayout(new BoxLayout(getMenuButtons(), BoxLayout.Y_AXIS));
		getMenuButtons().add(Box.createVerticalStrut(200));

		menuButton button = new menuButton("jugar");
		button.setActionCommand("play");
		button.addActionListener(this);
		getMenuButtons().add(button);
		getMenuButtons().add(Box.createVerticalStrut(20));

		menuButton button2 = new menuButton("editor");
		button2.setActionCommand("edit");
		button2.addActionListener(this);
		getMenuButtons().add(button2);

		menuButton button3 = new menuButton("Creditos");
		button3.setActionCommand("Credits");
		button3.addActionListener(this);
		getMenuButtons().add(button3);

		menuButton button4 = new menuButton("Salir");
		button4.setActionCommand("exit");
		button4.addActionListener(this);
		getMenuButtons().add(button4);

		getMenuBackground().add(getMenuButtons());
		getMenuBackground().add(pict);
		getMenuBackground().setSize(getMaximumSize());
		add(getMenuBackground());
		// setBackground(Color.RED);
	}

	public void changePict(String nemoticon) {
		switch (nemoticon) {
		case "play":
			getPict().setImage(
					ResourceManager.getInstance().getBufferedImage(PLAY_PICT));
			break;
		case "Credits":
			getPict().setImage(
					ResourceManager.getInstance()
							.getBufferedImage(CREDITS_PICT));
			break;
		case "edit":
			getPict().setImage(
					ResourceManager.getInstance()
							.getBufferedImage(EDITOR_PICT));
			break;
		case "exit":
			getPict().setImage(
					ResourceManager.getInstance()
							.getBufferedImage(EXIT_PICT));
			break;

		default:
			break;
		}
		repaint();
		validate();
		System.out.println("cambiando imagen a : " + nemoticon);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "play":
			getSceneManager().switchScenario(new MapSelector());
			break;
		case "Credits":
			getSceneManager().switchScenario(
					new CreditsScene(this.getWidth(), this.getHeight()));
			break;
		case "edit":
			EditorFrame frame1 = new EditorFrame();
			frame1.setTitle("Red VS Blue Editor");
			frame1.setLocationRelativeTo(null); // Center the frame
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame1.setVisible(true);
			frame1.setFocusable(true);
			frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			break;
		case "exit":
			System.exit(0);
		default:
			break;
		}
	}

	class menuButton extends JButton {
		public menuButton(String string) {
			super(string);
			addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					RvsB_Menu.this.changePict(getActionCommand());
				}
			});
		}

	}

	public BackgroundPanel getPict() {
		return pict;
	}

	public void setPict(BackgroundPanel pict) {
		this.pict = pict;
	}

	@Override
	public void sizeUpdate() {
		ScreenManager sm = ScreenManager.getInstance();
		getMenuBackground().setSize(sm.getCurrentWidth(),sm.getCurrentHeight());
		getPict().setSize(sm.getCurrentWidth()/2, sm.getCurrentHeight());
		getMenuButtons().setSize(sm.getCurrentWidth()/2, sm.getCurrentHeight());
	}

	public BackgroundPanel getMenuBackground() {
		return menuBackground;
	}

	public void setMenuBackground(BackgroundPanel menuBackground) {
		this.menuBackground = menuBackground;
	}

	public JPanel getMenuButtons() {
		return menuButtons;
	}

	public void setMenuButtons(JPanel menuButtons) {
		this.menuButtons = menuButtons;
	}

}
