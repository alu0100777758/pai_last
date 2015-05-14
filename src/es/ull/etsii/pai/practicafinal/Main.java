package es.ull.etsii.pai.practicafinal;

import javax.swing.JFrame;

public class Main {
	public static final String DEFAULT_MAP = "testingPlayerInit.rvsbm";
	public static void main(String[] args) {
		String mapPath = DEFAULT_MAP;
		if(args.length>0)
			mapPath = args[0];
		
		GameFrame frame = new GameFrame(mapPath);
		frame.setTitle("Red VS Blue");
		frame.setSize(ResourceManager.getInstance().getWindWidth(), ResourceManager.getInstance().getWindHeight());
	//	frame.setExtendState(JFrame.MAXIMIZED_BOTH);
	 	frame.setLocationRelativeTo(null); // Center the frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		GameLoop.init(frame);
	}

} 
