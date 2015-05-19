package es.ull.etsii.pai.practicafinal.secretgame;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		PongJFrame frame = new PongJFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
