package es.ull.etsii.pai.practicafinal.menu;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainGame extends JFrame {

	private MenuGame mGame;

	public MainGame() {
		mGame = new MenuGame();
		add(mGame, BorderLayout.CENTER);
//		Ganador ganador = new Ganador("paco", getWidth(),getHeight());
//		add(ganador, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		JFrame frame = new MainGame();
		frame.setTitle("Red vs Blue");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	}

}
