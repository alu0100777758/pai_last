package es.ull.etsii.pai.practicafinal.menu;

import es.ull.etsii.pai.practicafinal.GameFrame;
import es.ull.etsii.pai.practicafinal.GameLoop;
import es.ull.etsii.pai.practicafinal.ResourceManager;
import es.ull.etsii.pai.practicafinal.editor.EditorFrame;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MenuState extends GameState {

	private Background bg;

	private int currentChoice = 0;
	private int currentChoice2 = 0;
	private String[] options = { "Iniciar Partida", "Editar Escenario", "Opciones", "Salir" };
	private String[] optiones = { "Sonido ON", "volver" };

	private boolean jbOpciones = false;
	private boolean OpcionesSonido = true;

	private Color titleColor;
	private Font titleFont;

	private Font font;

	public MenuState(GameStateManager gsm) {

		this.gsm = gsm;

		try {

			bg = new Background("Resources/Backgrounds/grassbg1.gif", 1);
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
		if (!jbOpciones) {
			for (int i = 0; i < options.length; i++) {
				if (i == currentChoice) {
					g.setColor(Color.BLACK);
				} else {
					g.setColor(Color.RED);
				}
				g.drawString(options[i], 110, 100 + i * 15);
			}

		} else {
			for (int i = 0; i < optiones.length; i++) {
				if (i == currentChoice2) {
					g.setColor(Color.BLACK);
				} else {
					g.setColor(Color.RED);
				}
				g.drawString(optiones[i], 110, 100 + i * 15);
			}
		}

	}

	private void select() {
		if (currentChoice == 0) {
			// Iniciar partida
			GameFrame frame = new GameFrame("test1.rvsbm");
			frame.setTitle("Red VS Blue");
			// frame.setSize(1200, 800);
			// Maximizar frame
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setLocationRelativeTo(null); // Center the frame
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			GameLoop.init(frame);

		}
		if (currentChoice == 1) {
			// Editar
			EditorFrame frame = new EditorFrame();
			frame.setTitle("Red VS Blue Editor");
			frame.setSize(1200, 800);
			// Maximizar frame
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setLocationRelativeTo(null); // Center the frame
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.setVisible(true);
			frame.setFocusable(true);
		}
		if (currentChoice == 2) {
			// Opciones
			jbOpciones = true;
		}
		if (currentChoice == 3) {
			System.exit(0);
		}
	}

	private void selectOpciones() {
		if (currentChoice2 == 0) {
			// Sonido
			if (OpcionesSonido) {
			optiones[0] = "Sonido OFF";
			OpcionesSonido = false;
			} else {
				optiones[0] = "Sonido ON";
				OpcionesSonido = true;
			}
			System.out.println("currentChoice2 == 0");
		}
		if (currentChoice2 == 1) {
			// Volver
			jbOpciones = false;
			System.out.println("currentChoice2 == 1");
		}

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			if (OpcionesSonido) {
				Sounds.playSound("Resources/Backgrounds/pacman_chomp.wav");
			}
			if (!jbOpciones) {
				System.out.println("select();");
				select();
				
			} else {
				System.out.println("selectOpciones();");
				selectOpciones();
			}
		}
		if (k == KeyEvent.VK_UP) {
			if (OpcionesSonido) {
			Sounds.playSound("Resources/Backgrounds/button-10.wav");
			}
			
			
			if (!jbOpciones) {
				currentChoice--;
				if (currentChoice == -1) {
					currentChoice = options.length - 1;
				}
			} else {
				currentChoice2--;
				if (currentChoice2 == -1) {
					currentChoice2 = optiones.length - 1;
				}
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			if (OpcionesSonido) {
			Sounds.playSound("Resources/Backgrounds/button-10.wav");
			}
			
			if (!jbOpciones) {
				currentChoice++;
				if (currentChoice == options.length) {
					currentChoice = 0;
				}
			} else {
				currentChoice2++;
				if (currentChoice2 == optiones.length) {
					currentChoice2 = 0;
				}
			}
		}
	}

	public void keyReleased(int k) {
	}

}
