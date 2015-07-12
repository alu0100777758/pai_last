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
import java.awt.Event;
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
import es.ull.etsii.pai.practicafinal.scenes.CreditsScene;

public class RvsB_Menu extends ScenarioPanel implements ActionListener {
	private BackgroundPanel pict;
	public static final String PLAY_PICT = "Recursos\\textures\\menu_play.png";
	public static final String EDITOR_PICT = "Recursos\\textures\\menu_editor.png";
	public static final String CREDITS_PICT = "Recursos\\textures\\menu_credits.png";
	public static final String EXIT_PICT = "Recursos\\textures\\menu_exit.png";
	public static final String DEFAULT_PICT = PLAY_PICT;
	public static final String BACKGROUND = "Recursos\\textures\\menu_background.png";

	public RvsB_Menu() {
		BackgroundPanel mainmenu = new BackgroundPanel(ResourceManager.getInstance()
				.getBufferedImage(BACKGROUND));
		mainmenu.setLayout(new BoxLayout(mainmenu, BoxLayout.X_AXIS));
		JPanel mainMenu = new JPanel();
		setPict(new BackgroundPanel(ResourceManager.getInstance()
				.getBufferedImage(DEFAULT_PICT)));
		pict.setBackground(Color.RED);
		// mainMenu.setBackground(Color.GREEN);
		mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));
		mainMenu.add(Box.createVerticalStrut(200));

		menuButton button = new menuButton("jugar");
		button.setActionCommand("play");
		button.addActionListener(this);
		mainMenu.add(button);
		mainMenu.add(Box.createVerticalStrut(20));

		menuButton button2 = new menuButton("editor");
		button2.setActionCommand("edit");
		button2.addActionListener(this);
		mainMenu.add(button2);

		menuButton button3 = new menuButton("Creditos");
		button3.setActionCommand("Credits");
		button3.addActionListener(this);
		mainMenu.add(button3);

		menuButton button4 = new menuButton("Salir");
		button4.setActionCommand("exit");
		button4.addActionListener(this);
		mainMenu.add(button4);

		mainmenu.add(mainMenu);
		mainmenu.add(pict);
		mainmenu.setSize(getMaximumSize());
		add(mainmenu);
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

}
