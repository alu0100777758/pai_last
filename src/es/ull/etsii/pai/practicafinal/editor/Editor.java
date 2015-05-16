package es.ull.etsii.pai.practicafinal.editor;

import javax.swing.JFrame;

public class Editor{
	public static void main(String[] args) {
		EditorFrame frame = new EditorFrame();
		frame.setTitle("Red VS Blue Editor");
		frame.setSize(1200, 800);
	 	frame.setLocationRelativeTo(null); // Center the frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
	}
}