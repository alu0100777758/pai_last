package es.ull.etsii.pai.practicafinal.editor;

import javax.swing.JFrame;

import es.ull.etsii.pai.practicafinal.GameFrame;

public class Editor{
	private void JFrame() { 

	}
	public static void main(String[] args) {
		EditorFrame frame = new EditorFrame();
		frame.setTitle("Red VS Blue Editor");
		frame.setSize(1200, 800);
	//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	 	frame.setLocationRelativeTo(null); // Center the frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);

	}

}
