package es.ull.etsii.pai.practicafinal.menu;

import javax.swing.JFrame;

public class MainGame {

	public static void main(String[] args) {
		JFrame frame = new GUIGame();
		frame.setTitle("Red vs Blue");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	}
}
