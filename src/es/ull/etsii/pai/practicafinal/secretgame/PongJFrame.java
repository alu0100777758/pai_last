package es.ull.etsii.pai.practicafinal.secretgame;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class PongJFrame extends JFrame implements ActionListener, KeyListener {

	// Objetos
	public static PongJFrame pong;
	public PongJPanel renderer;

	Timer timer = new Timer(20, this);
	Timer timer2 = new Timer(500, this);
	// Botones
	private boolean w, s, up, down;

	// 0 = Menu, 1 = Paused, 2 = Playing, 3 = Over

	/** Constructor */
	public PongJFrame() {

		new Random();

		renderer = new PongJPanel();

		add(renderer);
		addKeyListener(this);

		timer2.start();
	}

	public void start() { // EMPEZAR JUEGO
		renderer.gameStatus = 2;
		renderer.player1 = new Raqueta(this.getWidth(), this.getHeight(), 1);
		renderer.player2 = new Raqueta(this.getWidth(), this.getHeight(), 2);
		renderer.ball = new Bola();
	}

	public void update() { // ACTUALIZAR JUEGO
		if (renderer.player1.getScore() >= renderer.scoreLimit) {
			renderer.playerWon = 1;
			renderer.gameStatus = 3;
		}

		if (renderer.player2.getScore() >= renderer.scoreLimit) {
			renderer.gameStatus = 3;
			renderer.playerWon = 2;
		}

		if (w) {
			renderer.player1.mover(true, this.getHeight());
		}
		if (s) {
			renderer.player1.mover(false, this.getHeight());
		}

		if (up) {
			renderer.player2.mover(true, this.getHeight());
		}
		if (down) {
			renderer.player2.mover(false, this.getHeight());
		}

		renderer.ball.update(renderer.player1, renderer.player2, this.getWidth(), this.getHeight());
	}

	public void render(Graphics2D g) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (renderer.gameStatus == 2) { // PLAYING A 0,02seg
			renderer.repaint();
			update();
		}
		if (renderer.gameStatus == 0) { // MENÚ A 1seg
			renderer.repaint();
		}
		if (renderer.gameStatus == 1) { // MENÚ A 1seg
			renderer.repaint();
		}
	}

	public static void main(String[] args) {
		pong = new PongJFrame();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			w = true;
			break;
		case KeyEvent.VK_S:
			s = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_SPACE:
			if (renderer.gameStatus == 0 || renderer.gameStatus == 3) { // Menú
																		// o
																		// Terminado
				timer2.stop();
				timer.start();
				start();
			} else if (renderer.gameStatus == 1) { // Paused
				renderer.gameStatus = 2;
			} else if (renderer.gameStatus == 2) { // Poner PAUSED en Player1
				renderer.gameStatus = 1;
				renderer.player = true;
			}
			break;
		case KeyEvent.VK_ENTER:
			if (renderer.gameStatus == 2) { // IF (PLAYING - Player2) THEN
											// (PAUSED)
				renderer.gameStatus = 1;
				renderer.player = false;
			} else if (renderer.gameStatus == 1) { // IF (PAUSED - Player2) THEN
				// (PLAYING)
				renderer.gameStatus = 2;
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int id = e.getKeyCode();

		if (id == KeyEvent.VK_W) {
			w = false;
		} else if (id == KeyEvent.VK_S) {
			s = false;
		} else if (id == KeyEvent.VK_UP) {
			up = false;
		} else if (id == KeyEvent.VK_DOWN) {
			down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
