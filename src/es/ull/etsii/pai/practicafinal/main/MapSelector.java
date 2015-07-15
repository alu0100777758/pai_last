package es.ull.etsii.pai.practicafinal.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

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
	private BufferedImage img;
	private static final String MAPS_PATH = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "maps";
	
	private ArrayList<String> maps = new ArrayList<String>();
	private int currentMap = 0;
	private int currentPreview = 0;
	private JComponent center;
	private ArrayList<MapPreview> levels = new ArrayList<MapPreview>();

	public MapSelector() {
		scanDir();
		fillGrid();
		
		
		try {
			img = ImageIO.read(new File ("Recursos/textures/metal.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void paintComponent (Graphics g) {
		super.paintComponent(g.create());
		
		g.drawImage(img, 0, 0, ScreenManager.getInstance().getCurrentWidth(), ScreenManager.getInstance().getCurrentHeight(), this);
	}
	private void fillGrid() {
		setLayout(new BorderLayout());
		
		ScreenManager screen = ScreenManager.getInstance();

		setBackground(Color.GRAY);
		
		add(Box.createHorizontalStrut((int)(screen.getCurrentWidth() * SIDE_MARGINS)), BorderLayout.WEST);
		add(Box.createHorizontalStrut((int)(screen.getCurrentWidth() * SIDE_MARGINS)), BorderLayout.EAST);
		add(Box.createVerticalStrut((int)(screen.getCurrentHeight() * TOP_MARGIN)), BorderLayout.NORTH);
		add(Box.createVerticalStrut((int)(screen.getCurrentHeight() * BOTTOM_MARGIN)), BorderLayout.SOUTH);
		setCenter(buildPreview(new JPanel()));
		add(getCenter(), BorderLayout.CENTER);
		
		//add(southPanel, BorderLayout.SOUTH);
		add(new bottomLayer(), BorderLayout.SOUTH);
		
	}

	public JPanel buildPreview(JPanel maps) {
		GridLayout layout = new GridLayout(PREVIEW_COL, PREVIEW_ROW);
		layout.setHgap((int)(ScreenManager.getInstance().getCurrentWidth() * GAP_BETWEEN_LEVELS));
		layout.setVgap((int)(ScreenManager.getInstance().getCurrentHeight() * GAP_BETWEEN_LEVELS));
		maps.setOpaque(false);
		maps.setLayout(layout);
		int end = (1 + getCurrentPreview()) * PREVIEW_COL * PREVIEW_ROW;
		end = end < getMaps().size() ? end : getMaps().size();
		int begin = getCurrentPreview()*PREVIEW_COL * PREVIEW_ROW;
		for (int i = begin; i < end; i++) {
			JButton button = new MapPreview(getMaps().get(i));
			maps.add(button);
			button.addActionListener(this);
			getLevels().add((MapPreview) button);
			System.out.println(getMaps().get(i));
		}
		System.out.println("begin: "+begin + " end: "+end);
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
	public void actionPerformed(ActionEvent e) {
		// MapPreview prev = (MapPreview)e.getSource();
		switch (e.getActionCommand()) {
		case "play":
			getSceneManager().switchScenario(new GameScenario(getMaps().get(getCurrentMap())));
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
			setCurrentMap(getMaps().indexOf(e.getActionCommand()));	
			for (MapPreview button : getLevels())
					button.setSelected(false);
			((MapPreview)e.getSource()).setSelected(true);
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
//		getCenter().removeAll();
		add(buildPreview, BorderLayout.CENTER);
		validate();
		repaint();
	}

	public int getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(int currentMap) {
		this.currentMap = currentMap;
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
			gridPanel.setOpaque(false);
			GridBagConstraints c = new GridBagConstraints();
			JPanel description = new JPanel();
			description.setBackground(Color.GREEN);
			description.setOpaque(false);
			this.setOpaque(false);
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
			JPanel content = new JPanel(new BorderLayout());
			content.add(leftArrow, BorderLayout.WEST);
			content.add(description, BorderLayout.CENTER);
			content.add(rightArrow, BorderLayout.EAST);
			content.setOpaque(false);
			gridPanel.add(Box.createHorizontalStrut(1));
			gridPanel.add(content);
			
			add(gridPanel, BorderLayout.CENTER);
			add(Box.createHorizontalStrut((int)(screen.getCurrentWidth() * GAP_BETWEEN_LEVELS / 2)), BorderLayout.WEST);  
			add(Box.createHorizontalStrut((int)(screen.getCurrentWidth() * GAP_BETWEEN_LEVELS / 2)), BorderLayout.EAST);  
			add(Box.createVerticalStrut((int)(screen.getCurrentHeight() * GAP_BETWEEN_LEVELS / 2)), BorderLayout.SOUTH);  
			this.setMinimumSize(new Dimension(screen.getCurrentWidth(),(int)(screen.getCurrentHeight() * BOTTOM_MARGIN)));
			this.setMaximumSize(new Dimension(screen.getCurrentWidth(),(int)(screen.getCurrentHeight() * BOTTOM_MARGIN)));
			this.setPreferredSize(new Dimension(screen.getCurrentWidth(),(int)(screen.getCurrentHeight() * BOTTOM_MARGIN)));
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
