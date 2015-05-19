package es.ull.etsii.pai.practicafinal.menu;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainGanador extends JFrame {

	private MenuGame mGame;

	public MainGanador() {
		Ganador ganador = new Ganador("paco", 500,400);
		add(ganador, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		JFrame frame = new MainGanador();
		frame.setTitle("Red vs Blue");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	}
}
