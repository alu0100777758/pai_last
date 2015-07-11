package es.ull.etsii.pai.practicafinal.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.redvsblue.GameFrame;
import es.ull.etsii.pai.practicafinal.redvsblue.GameLoop;
import es.ull.etsii.pai.practicafinal.redvsblue.GameScenario;
import es.ull.etsii.pai.practicafinal.redvsblue.ResourceManager;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class MapSelector extends ScenarioPanel implements ActionListener {
	private static final int PREVIEW_ROW = 4;
	public static final int PREVIEW_COL = 4;
	private static final String MAPS_PATH = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "maps";
	private ArrayList<String> maps = new ArrayList<String>();
	private int currentMap = 0;
	private int currentPreview = 0;
	private JComponent center;

	public MapSelector() {
		scanDir();
		fillGrid();
	}

	private void fillGrid() {
		setLayout(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		JButton leftArrow = new JButton();
		JButton rightArrow = new JButton();
		leftArrow.setIcon(new ImageIcon(ResourceManager.getInstance()
				.getBufferedImage("textures/selector_larrow.png")));
		leftArrow.setActionCommand("decrease");
		leftArrow.addActionListener(this);
		add(leftArrow, BorderLayout.WEST);
		setCenter(buildPreview(new JPanel()));
		add(getCenter(), BorderLayout.CENTER);
		rightArrow.setIcon(new ImageIcon(ResourceManager.getInstance()
				.getBufferedImage("textures/selector_arrow.png")));
		rightArrow.setActionCommand("increase");
		rightArrow.addActionListener(this);
		add(rightArrow, BorderLayout.EAST);
		add(new bottomLayer(), BorderLayout.SOUTH);
	}

	public JPanel buildPreview(JPanel maps) {
		maps.setBackground(Color.GREEN);
		maps.setLayout(new GridLayout(PREVIEW_COL, PREVIEW_ROW));
		int end = (1 + getCurrentPreview()) * PREVIEW_COL * PREVIEW_ROW;
		end = end < getMaps().size() ? end : getMaps().size();
		int begin = getCurrentPreview()*PREVIEW_COL * PREVIEW_ROW;
		for (int i = begin; i < end; i++) {
			JButton button = new MapPreview(getMaps().get(i));
			maps.add(button);
			button.addActionListener(this);
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
			/*GameFrame frame = new GameFrame(getMaps().get(getCurrentMap()));
			frame.setTitle("Red VS Blue");
			frame.setSize(ScreenManager.getInstance().getWindWidth(),
					ScreenManager.getInstance().getWindHeight());
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			GameLoop.init(frame);
			*/
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

	class bottomLayer extends JPanel {
		public bottomLayer() {
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			JPanel description = new JPanel();
			description.setBackground(Color.GREEN);
			description.setLayout(new GridLayout(4, 4));
			JButton leftArrow = new JButton();
			JButton rightArrow = new JButton();
			c.fill = GridBagConstraints.VERTICAL;
			c.weightx = 0.0;
			c.weighty = 0.2;
			c.anchor = GridBagConstraints.WEST;
			leftArrow.setIcon(new ImageIcon(ResourceManager.getInstance()
					.getBufferedImage("icons/return.png")));
			add(leftArrow, c);
			leftArrow.setActionCommand("return");
			leftArrow.addActionListener(MapSelector.this);
			c.anchor = GridBagConstraints.CENTER;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 0.5;
			c.weighty = 0.5;
			add(description, c);
			c.fill = GridBagConstraints.VERTICAL;
			c.weightx = 0.0;
			c.weighty = 0.2;
			c.anchor = GridBagConstraints.EAST;
			rightArrow.setActionCommand("play");
			rightArrow.addActionListener(MapSelector.this);
			rightArrow.setIcon(new ImageIcon(ResourceManager.getInstance()
					.getBufferedImage("icons/play.png")));
			add(rightArrow, c);
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

}
