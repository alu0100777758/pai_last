package es.ull.etsii.pai.practicafinal.menu;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainGame extends JFrame implements KeyListener{

	private MenuGame mGame;

	public MainGame() {
		mGame = new MenuGame();
		add(mGame, BorderLayout.CENTER);

	}
	
	public static void main(String[] args) {
		JFrame frame = new MainGame();
		frame.setTitle("Red vs Blue");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
