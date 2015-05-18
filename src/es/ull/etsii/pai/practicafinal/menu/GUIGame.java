package es.ull.etsii.pai.practicafinal.menu;

import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.image.BufferedImage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GUIGame extends JFrame {

//	// Dimensiones JFRAME
//	 private static final int WIDTH = 1280;
//	 private static final int HEIGHT = 720;

	// image
//	private BufferedImage image;
//	private Graphics2D g;

	// game state manager
	private MenuGame mGame;

	public GUIGame() {
		mGame = new MenuGame();
		add(mGame, BorderLayout.CENTER);
	}



//	private void drawToScreen() {
//		Graphics g2 = getGraphics();
////		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
//		g2.dispose();
//	}

}
