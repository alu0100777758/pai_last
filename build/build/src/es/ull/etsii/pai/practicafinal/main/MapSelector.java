package es.ull.etsii.pai.practicafinal.main;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.GameScenario;
import es.ull.etsii.pai.practicafinal.redvsblue.ResourceManager;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class MapSelector extends ScenarioPanel implements ActionListener {
	private static final int PREVIEW_ROW = 4;
	public static final int PREVIEW_COL = 4;
	public static final double TOP_MARGIN = 0.15;
	public static final double BOTTOM_MARGIN = 0.25;
	public static final double SIDE_MARGINS = 0.10;
	public static final double GAP_BETWEEN_LEVELS = 0.05;
	public static final String BACKGROUND = "textures/metal.png";
	private BufferedImage img;
	private static final String MAPS_PATH = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "maps";

	private ArrayList<String> maps = new ArrayList<String>();
	private int currentMap = 0;
	private int currentPreview = 0;
	private JComponent center;
	private ArrayList<MapPreview> levels = new ArrayList<MapPreview>();
	private JLabel mapText = new JLabel();

	public MapSelector() {
		scanDir();
		fillGrid();

		img = ResourceManager.getInstance().getBufferedImage(BACKGROUND);
		setCurrentMap(0);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g.create());

		g.drawImage(img, 0, 0, ScreenManager.getInstance().getCurrentWidth(),
				ScreenManager.getInstance().getCurrentHeight(), this);
	}

	private void fillGrid() {
		setLayout(new BorderLayout());

		ScreenManager screen = ScreenManager.getInstance();

		setBackground(Color.GRAY);

		add(Box.createHorizontalStrut((int) (screen.getCurrentWidth() * SIDE_MARGINS)),
				BorderLayout.WEST);
		add(Box.createHorizontalStrut((int) (screen.getCurrentWidth() * SIDE_MARGINS)),
				BorderLayout.EAST);
		add(Box.createVerticalStrut((int) (screen.getCurrentHeight() * TOP_MARGIN)),
				BorderLayout.NORTH);
		add(Box.createVerticalStrut((int) (screen.getCurrentHeight() * BOTTOM_MARGIN)),
				BorderLayout.SOUTH);
		setCenter(buildPreview(new JPanel()));
		add(getCenter(), BorderLayout.CENTER);

		// add(southPanel, BorderLayout.SOUTH);
		add(new bottomLayer(), BorderLayout.SOUTH);

	}

	public JPanel buildPreview(JPanel maps) {
		GridLayout layout = new GridLayout(PREVIEW_COL, PREVIEW_ROW);
		layout.setHgap((int) (ScreenManager.getInstance().getCurrentWidth() * GAP_BETWEEN_LEVELS));
		layout.setVgap((int) (ScreenManager.getInstance().getCurrentHeight() * GAP_BETWEEN_LEVELS));
		maps.setOpaque(false);
		maps.setLayout(layout);
		int end = (1 + getCurrentPreview()) * PREVIEW_COL * PREVIEW_ROW;
		end = end < getMaps().size() ? end : getMaps().size();
		int begin = getCurrentPreview() * PREVIEW_COL * PREVIEW_ROW;
//		JButton random = new MapPreview("void");
//		maps.add(random);
		for (int i = begin; i < end; i++) {
			JButton button = new MapPreview(getMaps().get(i));
			maps.add(button);
			button.addActionListener(this);
			getLevels().add((MapPreview) button);
		}
		return maps;
	}

	private void scanDir() {
		File curDir = new File(MAPS_PATH);
		File[] filesList = curDir.listFiles();
		for (File fil : filesList)
			maps.add(MAPS_PATH + System.getProperty("file.separator")
					+ fil.getName());
	}

	public ArrayList<String> getMaps() {
		return maps;
	}

	public void setMaps(ArrayList<String> maps) {
		this.maps = maps;
	}

	@Override
	public void releasedKey(int keyCode, char keyChar) {
		switch (keyCode) {
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_ENTER:
			playMap();
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			int map = getCurrentMap() + 1;

			if (map >= PREVIEW_ROW * PREVIEW_COL)
				map = getCurrentMap();
			setCurrentMap(map);
			
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			map = getCurrentMap() - 1;
			if (map < 0)
				map = getCurrentMap();
			setCurrentMap(map);
			
			break;
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			 map = getCurrentMap() - PREVIEW_ROW;

				if (map < 0)
					map += PREVIEW_ROW;
				setCurrentMap(map);
				
				break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			map = getCurrentMap() + PREVIEW_ROW;

			if (map >= PREVIEW_ROW * PREVIEW_COL)
				map = getCurrentMap();
			setCurrentMap(map);
			
			break;
			
		default:
			break;
		}
	}
	public void playMap(){
		String path = getMaps().get(getCurrentMap());
		if(path.substring(path.length() - 7).equals("0random"))
			getSceneManager().switchScenario(new GameScenario(getMaps().get(new Random().nextInt(getMaps().size()-2)+1),getSceneManager()));
		else
		 getSceneManager().switchScenario(
				new GameScenario(getMaps().get(getCurrentMap()),getSceneManager()));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// MapPreview prev = (MapPreview)e.getSource();
		switch (e.getActionCommand()) {
		case "play":
			playMap();
			break;
		case "return":
			getSceneManager().switchScenario(new RvsB_Menu());
			break;
		case "increase":
			advance();
			break;
		case "decrease":
			back();
			break;
		default:
			if (((MapPreview) e.getSource()).isSelected())
				playMap();
			setCurrentMap(getMaps().indexOf(e.getActionCommand()));

			try {
				mapText.setText(BvsR_Map.load(getMaps().get(getCurrentMap()))
						.getDescription());
				mapText.setText(getMaps().get(getCurrentMap()));

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

//			System.out.println(mapText.getText());
			repaint();
			break;
		}

	}

	private void back() {
		setCurrentPreview(getCurrentPreview() - 1);
		reemplaceCenter(buildPreview(new JPanel()));
	}

	private void advance() {
		setCurrentPreview(getCurrentPreview() + 1);
		reemplaceCenter(buildPreview(new JPanel()));

	}

	private void reemplaceCenter(JPanel buildPreview) {
		remove(getCenter());
		// getCenter().removeAll();
		add(buildPreview, BorderLayout.CENTER);
		validate();
		repaint();
	}

	public int getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(int currentMap) {
		this.currentMap = currentMap;

		for (MapPreview button : getLevels())
			button.setSelected(false);
		getLevels().get(currentMap).setSelected(true);
		repaint();
	}

	public ArrayList<MapPreview> getLevels() {
		return levels;
	}

	public void setLevels(ArrayList<MapPreview> levels) {
		this.levels = levels;
	}

	class bottomLayer extends JPanel {
		public bottomLayer() {
			ScreenManager screen = ScreenManager.getInstance();
			setLayout(new BorderLayout());
			JPanel gridPanel = new JPanel(new GridLayout(3, 1));
			JPanel content = new JPanel(new BorderLayout());
			gridPanel.setOpaque(false);
			GridBagConstraints c = new GridBagConstraints();
			JPanel description = new JPanel();
			description.setBackground(Color.GREEN);
			description.setOpaque(false);
			this.setOpaque(false);
			description.add(mapText);
			mapText.setBackground(Color.WHITE);
			description.setLayout(new GridLayout(4, 4));
			JButton leftArrow = new JButton();
			JButton rightArrow = new JButton();

			leftArrow.setIcon(new ImageIcon(ResourceManager.getInstance()
					.getBufferedImage("icons/return.png")));
			leftArrow.setActionCommand("return");
			leftArrow.addActionListener(MapSelector.this);

			rightArrow.setActionCommand("play");
			rightArrow.addActionListener(MapSelector.this);
			rightArrow.setIcon(new ImageIcon(ResourceManager.getInstance()
					.getBufferedImage("icons/play.png")));

			gridPanel.add(Box.createHorizontalStrut(1));

			content.add(leftArrow, BorderLayout.WEST);
			content.add(description, BorderLayout.CENTER);
			content.add(rightArrow, BorderLayout.EAST);
			content.setOpaque(false);
			gridPanel.add(Box.createHorizontalStrut(1));
			gridPanel.add(content);

			add(gridPanel, BorderLayout.CENTER);
			add(Box.createHorizontalStrut((int) (screen.getCurrentWidth()
					* GAP_BETWEEN_LEVELS / 2)), BorderLayout.WEST);
			add(Box.createHorizontalStrut((int) (screen.getCurrentWidth()
					* GAP_BETWEEN_LEVELS / 2)), BorderLayout.EAST);
			add(Box.createVerticalStrut((int) (screen.getCurrentHeight()
					* GAP_BETWEEN_LEVELS / 2)), BorderLayout.SOUTH);
			this.setMinimumSize(new Dimension(screen.getCurrentWidth(),
					(int) (screen.getCurrentHeight() * BOTTOM_MARGIN)));
			this.setMaximumSize(new Dimension(screen.getCurrentWidth(),
					(int) (screen.getCurrentHeight() * BOTTOM_MARGIN)));
			this.setPreferredSize(new Dimension(screen.getCurrentWidth(),
					(int) (screen.getCurrentHeight() * BOTTOM_MARGIN)));
			this.setOpaque(false);
		}
	}

	public int getCurrentPreview() {
		return currentPreview;
	}

	public void setCurrentPreview(int currentPreview) {
		this.currentPreview = currentPreview;
	}

	public JComponent getCenter() {
		return center;
	}

	public void setCenter(JComponent center) {
		this.center = center;
	}

	@Override
	public void sizeUpdate() {
		// TODO Auto-generated method stub

	}

}
