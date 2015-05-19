package es.ull.etsii.pai.practicafinal.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.AudioManager;
import es.ull.etsii.pai.practicafinal.GameFrame;
import es.ull.etsii.pai.practicafinal.GameLoop;
import es.ull.etsii.pai.practicafinal.editor.EditorFrame;

@SuppressWarnings("serial")
public class MenuGame extends JPanel implements KeyListener {

	private Background bg;

	private int currentChoice = 0;
	private int currentChoice2 = 0;
	private String[] options = { "Iniciar Partida", "Editar Escenario", "Opciones", "Salir" };
	private String[] optiones = { "Sonido ON", "Efectos sonido ON", "Resolución Escritorio", "volver" };

	private boolean jbOpciones = false;
	private boolean OpcionesSonido = true;
	private boolean OpcionesEfectoSonido = true;
	private int OpcionesResolucion = 1;

	private Color titleColor;
	private Font titleFont;

	private Font font;

	public MenuGame() {
		addKeyListener(this);
		
		setFocusable(true);
		requestFocus();
		try {

//			bg = new Background("Resources/Backgrounds/grassbg1.gif", 1);
//			bg.paint(g);
//			bg.setVector(-0.1, 0);

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
//		bg.paint(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		Background bg = new Background("");
		bg.paint(g);
		// draw title
		Ganador ganador = new Ganador("paco", getWidth(), getHeight());
		ganador.paint(g);
		g.setColor(Color.RED);
		g.setFont(titleFont);
		g.setFont(new Font("Cantarell", 1, 70));
		g.drawString("Red Vs Blue", 0, 70);

		// draw menu options
		g.setFont(font);
		if (!jbOpciones) {
			for (int i = 0; i < options.length; i++) {
				if (i == currentChoice) {
					g.setColor(Color.BLACK);
					options[i] = "   " + options[i];
				} else {
					g.setColor(Color.BLUE);
				}
				g.setFont(new Font("Cantarell", 1, 30));
				g.drawString(options[i], 110, 150 + i * 30);
				if (i == currentChoice) {
					options[i] = options[i].replace("   ", "");
				}
			}

		} else {
			for (int i = 0; i < optiones.length; i++) {
				if (i == currentChoice2) {
					g.setColor(Color.BLACK);
					optiones[i] = "   " + optiones[i];
				} else {
					g.setColor(Color.RED);
				}
				g.setFont(new Font("Cantarell", 1, 30));
				g.drawString(optiones[i], 110, 150 + i * 30);
				if (i == currentChoice2) {
					optiones[i] = optiones[i].replace("   ", "");
				}
			}
		}

	}

	private void select() {
		if (currentChoice == 0) { 	// INICIAR JUEGO
			GameFrame frame = new GameFrame("test1.rvsbm");
			frame.setTitle("Red VS Blue");
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setLocationRelativeTo(null); 	
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			GameLoop.init(frame);

		}
		if (currentChoice == 1) { 	// INICIAR EDITOR
			EditorFrame frame = new EditorFrame();
			frame.setTitle("Red VS Blue Editor");
			frame.setSize(1200, 800);
			//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setLocationRelativeTo(null); // Center the frame
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setFocusable(true);
			
		}
		if (currentChoice == 2) {	//INICIAR OPCIONES
			jbOpciones = true;
		}
		if (currentChoice == 3) {	// SALIR DEL MENÚ
			System.exit(0);
		}
	}

	private void selectOpciones() {
		if (currentChoice2 == 0) {	// OPCIONES/SONIDO
			if (OpcionesSonido) {
				optiones[0] = "Sonido OFF";
				OpcionesSonido = false;
			} else {
				optiones[0] = "Sonido ON";
				OpcionesSonido = true;
			}
			System.out.println("currentChoice2 == 0");
		}
		if (currentChoice2 == 1) {	// OPCIONES/EFECTOS DE SONIDO
			if (OpcionesEfectoSonido) {
				optiones[1] = "Efectos sonido OFF";
				OpcionesEfectoSonido = false;
			} else {
				optiones[1] = "Efectos sonido ON";
				OpcionesEfectoSonido = true;
			}
			System.out.println("currentChoice2 == 0");
		}
		if (currentChoice2 == 2) {	// OPCIONES/RESOLUCIÓN
			System.out.println("currentChoice2 == 0");
			System.out.println("OpcionesResolucion: " + OpcionesResolucion);
			if (OpcionesResolucion == 0) {
				System.out.println("Dentro");
				optiones[2] = "Resolución Tablet";
			}
			if (OpcionesResolucion == 1) {
				optiones[2] = "Resolución Móvil";
			}
			if (OpcionesResolucion == 2) {
				optiones[2] = "Resolución Escritorio";
			}
			if (OpcionesResolucion == 0) {
				OpcionesResolucion = 1;
			} else {
				if (OpcionesResolucion == 1) {
					OpcionesResolucion = 2;
				} else {
					if (OpcionesResolucion == 2) {
						OpcionesResolucion = 0;
					}
				}

			}

		}
		if (currentChoice2 == 3) {	// OPCIONES/VOLVER
			jbOpciones = false;
			System.out.println("currentChoice2 == 1");
		}

	}

	public void keyPressed(int k) {

	}

	public void keyReleased(int k) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("entrando");
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (OpcionesSonido) {
				AudioManager.startAudio("pacman_chomp.wav");
			}
			if (!jbOpciones) {
				System.out.println("select();");
				select();

			} else {
				System.out.println("selectOpciones();");
				selectOpciones();
			}
			this.repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (OpcionesSonido) {
				AudioManager.startAudio("button-10.wav");
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
			this.repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (OpcionesSonido) {
				AudioManager.startAudio("button-10.wav");
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
			System.out.println("abajo");
			this.repaint();
		}

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
