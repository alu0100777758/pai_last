package es.ull.etsii.pai.practicafinal.menu;

import es.ull.etsii.pai.practicafinal.GameFrame;
import es.ull.etsii.pai.practicafinal.GameLoop;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class MenuState extends GameState {

	private Background bg;

	private int currentChoice = 0;
	private String[] options = { "Iniciar Partida", "Editar Escenario", "Opciones", "Salir" };

	private Color titleColor;
	private Font titleFont;

	private Font font;

	public MenuState(GameStateManager gsm) {

		this.gsm = gsm;

		try {

			bg = new Background("/Backgrounds/menubg.gif", 1);
			bg.setVector(-0.1, 0);

			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);

			font = new Font("Arial", Font.PLAIN, 12);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void init() {
	}

	public void update() {
		bg.update();
	}

	public void draw(Graphics2D g) {

		// draw bg
		bg.draw(g);

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Red Vs Blue", 80, 70);

		// draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 120, 140 + i * 15);
		}

	}

	private void select() {
		if (currentChoice == 0) {
			// Iniciar partida
			GameFrame frame = new GameFrame("test1.rvsbm");
			frame.setTitle("Red VS Blue");
			frame.setSize(1200, 800);
		 	frame.setLocationRelativeTo(null); // Center the frame
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			GameLoop.init(frame);
		}
		if (currentChoice == 1) {
			// Editar
		}
		if (currentChoice == 2) {
			// Opciones
		}
		if (currentChoice == 3) {
			System.exit(0);
		}
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			Sounds.playSound("Resources/Backgrounds/pacman_chomp.wav");
			select();
		}
		if (k == KeyEvent.VK_UP) {
			Sounds.playSound("Resources/Backgrounds/button-10.wav");
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			Sounds.playSound("Resources/Backgrounds/button-10.wav");
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	public void keyReleased(int k) {
	}

}
