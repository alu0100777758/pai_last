package es.ull.etsii.pai.practicafinal.editor;

import java.io.File;

import javax.swing.JFrame;

import es.ull.etsii.pai.practicafinal.ScreenManager;

public class Editor{
	public static void main(String[] args) {
		EditorFrame frame = new EditorFrame();
		File texturesDir = new File(System.getProperty("user.dir")+ System.getProperty("file.separator")+ "textures");
		texturesDir.mkdir();
		frame.setTitle("Red VS Blue Editor");
//		frame.setSize(ScreenManager.getInstance().getWindWidth(), ScreenManager.getInstance().getWindHeight());
	 	frame.setLocationRelativeTo(null); // Center the frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
	}
}