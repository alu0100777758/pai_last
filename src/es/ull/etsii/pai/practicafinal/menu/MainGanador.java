package es.ull.etsii.pai.practicafinal.menu;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainGanador extends JFrame implements KeyListener{

	private MenuGame mGame;

	public MainGanador() {
		Ganador ganador = new Ganador("paco", 500,400);
		add(ganador, BorderLayout.CENTER);
		addKeyListener(this);
	}

	public static void main(String[] args) {
		JFrame frame = new MainGanador();
		frame.setTitle("Red vs Blue");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("pulsando enter");
		if(arg0.getKeyCode()== KeyEvent.VK_ENTER) {
			JFrame frame = new MainGame();
			frame.setTitle("Red vs Blue");
			frame.setSize(500, 400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);	
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
