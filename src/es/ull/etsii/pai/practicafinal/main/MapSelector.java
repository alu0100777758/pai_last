package es.ull.etsii.pai.practicafinal.main;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.redvsblue.GameFrame;
import es.ull.etsii.pai.practicafinal.redvsblue.GameLoop;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class MapSelector extends ScenarioPanel implements ActionListener {
	private static final String MAPS_PATH = System.getProperty("user.dir")+ System.getProperty("file.separator")+ "maps";
	private ArrayList<String> maps = new ArrayList<String>();
	private int currentMap = 0;
	public MapSelector(){
		scanDir();
		fillGrid();
	}
	
	private void fillGrid() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel maps = new JPanel();
		maps.setBackground(Color.GREEN);
		maps.setLayout(new GridLayout(5,10));
		for(String name : getMaps()){
			JButton button = new MapPreview(name);
			maps.add(button);
			button.addActionListener(this);
		}
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 100;      //make this component tall
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridwidth = 3;
		c.gridx = 3;
		c.gridy = 3;
		add(maps,c);
	}

	private void scanDir() {
		File curDir = new File(MAPS_PATH);
		 File[] filesList = curDir.listFiles();
		 for(File fil: filesList)
			 maps.add(MAPS_PATH+System.getProperty("file.separator")+fil.getName());
	}

	public ArrayList<String> getMaps() {
		return maps;
	}
	public void setMaps(ArrayList<String> maps) {
		this.maps = maps;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		MapPreview prev = (MapPreview)e.getSource();
		GameFrame frame = new GameFrame(e.getActionCommand());
		frame.setTitle("Red VS Blue");
		frame.setSize(ScreenManager.getInstance().getWindWidth(), ScreenManager.getInstance().getWindHeight());
	 	frame.setLocationRelativeTo(null); 
	 	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 	frame.setVisible(true);
		GameLoop.init(frame);
		
	}

	public int getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(int currentMap) {
		this.currentMap = currentMap;
	}
	
}
