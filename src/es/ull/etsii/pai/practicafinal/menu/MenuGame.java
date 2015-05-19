package es.ull.etsii.pai.practicafinal.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.AudioManager;
import es.ull.etsii.pai.practicafinal.GameFrame;
import es.ull.etsii.pai.practicafinal.GameLoop;
import es.ull.etsii.pai.practicafinal.ScreenManager;
import es.ull.etsii.pai.practicafinal.editor.EditorFrame;
import es.ull.etsii.pai.practicafinal.secretgame.PongJFrame;

@SuppressWarnings("serial")
public class MenuGame extends JPanel implements KeyListener {

	private String secretGame = "";
	private int currentChoice = 0;
	private int currentChoice2 = 0;
	private String[] options = { "Iniciar Partida", "Editar Escenario", "Opciones", "Salir" };
	private String[] optiones = { "Sonido ON", "Efectos sonido ON", "Resolución Escritorio", "volver" };
	private boolean jbOpciones = false;
	private boolean OpcionesSonido = true;
	private boolean OpcionesEfectoSonido = true;
	private int OpcionesResolucion = 1;
	private Font titleFont;

	private Font font;

	public MenuGame() {
		addKeyListener(this);
		setFocusable(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Dibujar Fondo
		Background bg = new Background("");
		bg.paint(g);
		
		// Dibujar Titulo
		g.setColor(Color.RED);
		g.setFont(titleFont);
		g.setFont(new Font("Cantarell", 1, 70));
		g.drawString("Red Vs Blue", 0, 70);

		// Dibujar Menú
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
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	                AudioManager.stopAll();
	                GameLoop.stepTimer.stop();
	                ScreenManager.getInstance().setRate_x(1);
	                ScreenManager.getInstance().setRate_y(1);
	            }
	        });
			GameLoop.init(frame);

		}
		if (currentChoice == 1) { 	// INICIAR EDITOR
			EditorFrame frame = new EditorFrame();
			frame.setTitle("Red VS Blue Editor");
			frame.setSize(ScreenManager.getInstance().getWindWidth(), ScreenManager.getInstance().getWindHeight());
			//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setLocationRelativeTo(null); // Center the frame
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setFocusable(true);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
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
				AudioManager.setMusic(false);
			} else {
				optiones[0] = "Sonido ON";
				AudioManager.setMusic(true);
				OpcionesSonido = true;
			}
			System.out.println("currentChoice2 == 0");
		}
		if (currentChoice2 == 1) {	// OPCIONES/EFECTOS DE SONIDO
			if (OpcionesEfectoSonido) {
				optiones[1] = "Efectos sonido OFF";
				AudioManager.setSfx(false);
				OpcionesEfectoSonido = false;
			} else {
				optiones[1] = "Efectos sonido ON";
				AudioManager.setSfx(true);
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
			secretGame = secretGame + "0";
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
			secretGame =secretGame + 1;
			if (secretGame.equals("101111")) {
				PongJFrame frame = new PongJFrame();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
			}
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
