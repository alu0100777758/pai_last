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
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.editor.EditorFrame;
import es.ull.etsii.pai.practicafinal.redvsblue.ResourceManager;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;
import es.ull.etsii.pai.practicafinal.scenes.CreditsScene;
import es.ull.etsii.pai.practicafinal.scenes.ScoreScene;

public class RvsB_Menu extends ScenarioPanel implements ActionListener {
	private static final long serialVersionUID = 169028678534289322L;
	private BackgroundPanel pict;
	private BackgroundPanel menuBackground;
	private JPanel menuButtons;
	public static final String PLAY_PICT = "textures/menu_play.png";
	public static final String EDITOR_PICT = "textures/menu_editor.png";
	public static final String CREDITS_PICT = "textures/menu_credits.png";
	public static final String LADDER_PICT = "textures/menu_ladder.png";
	public static final String EXIT_PICT = "textures/menu_exit.png";
	public static final String DEFAULT_PICT = PLAY_PICT;
	public static final String BACKGROUND = "textures/menu_background.png";
	public static final int OPTIONS = 4;
	public static menuButton selection = null;
	private int indexOfSelection = 1;

	public RvsB_Menu() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setMenuBackground(new BackgroundPanel(ResourceManager.getInstance()
				.getBufferedImage(BACKGROUND)));
		LayoutManager menulay;
		// menulay = new BoxLayout(getMenuBackground(), BoxLayout.X_AXIS);
		menulay = new GridBagLayout();
		getMenuBackground().setLayout(menulay);
		setMenuButtons(new JPanel());
		setPict(new BackgroundPanel(ResourceManager.getInstance()
				.getBufferedImage(DEFAULT_PICT)));
		pict.setBackground(Color.RED);
		// mainMenu.setBackground(Color.GREEN);
		// getMenuButtons().setLayout(new BoxLayout(getMenuButtons(),
		// BoxLayout.Y_AXIS));
		getMenuButtons().setLayout(new GridLayout(6, 2));

		getMenuButtons().add(Box.createVerticalStrut(20));

		menuButton button = new menuButton("jugar");
		button.setActionCommand("play");
		button.addActionListener(this);
		getMenuButtons().add(button);

		menuButton button2 = new menuButton("editor");
		button2.setActionCommand("edit");
		button2.addActionListener(this);
		getMenuButtons().add(button2);

		menuButton button3 = new menuButton("Créditos");
		button3.setActionCommand("Credits");
		button3.addActionListener(this);
		getMenuButtons().add(button3);

		/*menuButton score = new menuButton("Puntuaciones");
		score.setActionCommand("score");
		score.addActionListener(this);
		getMenuButtons().add(score);
		*/
		menuButton button4 = new menuButton("Salir");
		button4.setActionCommand("exit");
		button4.addActionListener(this);
		getMenuButtons().add(button4);

		getMenuBackground().add(getMenuButtons());
		getMenuBackground().add(pict);
		getMenuBackground().setSize(getMaximumSize());

		getMenuButtons().add(Box.createVerticalStrut(20));

		add(getMenuBackground());
		setSelection((menuButton) getMenuButtons().getComponent(
				getIndexOfSelection()));
		ScreenManager sm = ScreenManager.getInstance();
		getMenuButtons().setPreferredSize(
				new Dimension(sm.getCurrentWidth() / 2, sm.getCurrentHeight()));
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
			getPict()
					.setImage(
							ResourceManager.getInstance().getBufferedImage(
									EDITOR_PICT));
			break;
		case "exit":
			getPict().setImage(
					ResourceManager.getInstance().getBufferedImage(EXIT_PICT));
			break;
		//case "score":
		//	getPict().setImage(
		//			ResourceManager.getInstance().getBufferedImage(LADDER_PICT));
		//	break;
		default:
			break;
		}
		repaint();
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "play":
			//getSceneManager().setKeyHandlerON(false);
			getSceneManager().switchScenario(new MapSelector());
			break;
		case "Credits":
			getSceneManager().setKeyHandlerON(false);
			getSceneManager().switchScenario(new CreditsScene());
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
	//	case "score":
	//		getSceneManager().switchScenario(new ScoreScene());
	//		break;
		case "exit":
			System.exit(0);
		default:
			break;
		}
	}

	@Override
	public void releasedKey(int keyCode, char keyChar) {
		switch (keyCode) {
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
		case KeyEvent.VK_RIGHT:
			next();
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
		case KeyEvent.VK_LEFT:
			prev();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_SPACE:
			actionPerformed(new ActionEvent(getSelection(),
					ActionEvent.ACTION_FIRST, getSelection().getActionCommand()));	
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("serial")
	class menuButton extends JButton {
		private boolean selected;

		public menuButton(String string) {
			super(string);
			addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					RvsB_Menu.this.changePict(getActionCommand());
				}
			});
			setUI(new MenuUI());
			setOpaque(false);
			setContentAreaFilled(false);
			setBorderPainted(false);
			addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent evt) {
					select();
				}
			});
		}

		public void select() {
			RvsB_Menu.setSelection(this);
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
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
		getMenuBackground()
				.setSize(sm.getCurrentWidth(), sm.getCurrentHeight());
		getPict().setSize(sm.getCurrentWidth() / 2, sm.getCurrentHeight());
		getMenuButtons().setPreferredSize(
				new Dimension(sm.getCurrentWidth() / 2, sm.getCurrentHeight()));
	}

	public void next() {
		setIndexOfSelection(getIndexOfSelection() + 1);
		if (getIndexOfSelection() > OPTIONS)
			setIndexOfSelection(1);
		setSelection((menuButton) getMenuButtons().getComponent(
				getIndexOfSelection()));
		changePict(getSelection().getActionCommand());
		repaint();
	}

	public void prev() {
		setIndexOfSelection(getIndexOfSelection() - 1);
		if (getIndexOfSelection() < 1)
			setIndexOfSelection(OPTIONS);
		setSelection((menuButton) getMenuButtons().getComponent(
				getIndexOfSelection()));
		changePict(getSelection().getActionCommand());
		repaint();
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

	public static menuButton getSelection() {
		return selection;
	}

	public static void setSelection(menuButton slection) {
		RvsB_Menu.selection = slection;
	}

	public int getIndexOfSelection() {
		return indexOfSelection;
	}

	public void setIndexOfSelection(int indexOfSelection) {
		this.indexOfSelection = indexOfSelection;
	}

}
