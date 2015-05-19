package es.ull.etsii.pai.practicafinal.secretgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PongJPanel extends JPanel {
	private static String TITULOGAME = "PONG GAME";
	// Jugadores
	public Raqueta player1;
	public Raqueta player2;
	public boolean player; // Nos indica el jugador.
	public Bola ball;

	// Datos Juego
	public int gameStatus = 0, scoreLimit = 7, playerWon;
	boolean blinkState = true;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON); // Quita
		// efecto
		// pantalla

		if (gameStatus == 0) { // MENÚ

			g.setColor(Color.WHITE);
			g.setFont(new Font("Cantarell", 1, 100));
			g.drawString(TITULOGAME, this.getWidth() / 5, 200);

			// if (!selectingDifficulty) {
			blinkState = !blinkState;
			if (blinkState) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", 1, 40));
				g.drawString("Press Any Key", this.getWidth() / 2 - 150, this.getHeight() / 2 - 25);
			} else {
				g.setColor(Color.BLACK);
				g.setFont(new Font("Arial", 1, 30));
				g.drawString("Press Any Key", this.getWidth() / 2 - 150, this.getHeight() / 2 - 25);
			}

			// g.drawString("Press Any Key", width / 2 - 150, height / 2 -
			// 25); // SPACE NOW
			// g.drawString("Press Shift to Play with Bot", width / 2 - 200,
			// height / 2 + 25);
			// g.drawString("<< Score Limit: " + scoreLimit + " >>", width /
			// 2 - 150, height / 2 + 75);
			// }
			g.setColor(Color.WHITE);
			g.setFont(new Font("Cantarell", 1, 20));
			g.drawString("Teclas del juego", this.getWidth() / 2 - this.getWidth() / 12, 400);
			g.drawString("Player 1:", this.getWidth() / 4, 420);
			g.drawString("[w] - Subir raqueta.", this.getWidth() / 4, 460);
			g.drawString("[s] - Bajar raqueta.", this.getWidth() / 4, 480);
			g.drawString("[space] - Pausar juego.", this.getWidth() / 4, 500);
		}

		// PAUSED
		if (gameStatus == 1) {
			System.out.println("pausado");
			g.setColor(Color.WHITE);
			g.setFont(new Font("Cantarell", 1, 50));
			if (player) {
				g.drawString("PAUSED", this.getWidth() / 2 - (this.getWidth() / 4) - 100, this.getHeight() / 2 + 50);
			} else {
				g.drawString("PAUSED", this.getWidth() / 2 + (this.getWidth() / 4) - 100, this.getHeight() / 2 + 50);
			}
		}

		if (gameStatus == 1 || gameStatus == 2) {
			g.setColor(Color.WHITE);
			// g.setStroke(new BasicStroke(2f));
			g.drawLine(this.getWidth() / 2, this.getWidth() / 100, this.getWidth() / 2, this.getHeight() - (this.getWidth() / 100)); // Línea
			// central
			g.drawLine(this.getWidth() / 100, this.getWidth() / 100, this.getWidth() - (this.getWidth() / 100), this.getWidth() / 100); // Línea
			// superior
			g.drawLine(this.getWidth() / 100, this.getHeight() - (this.getWidth() / 100), this.getWidth() - (this.getWidth() / 100), this.getHeight() - (this.getWidth() / 100)); // Línea
			// inferior
			g.drawLine(this.getWidth() / 100, this.getWidth() / 100, this.getWidth() / 100, this.getHeight() - (this.getWidth() / 100)); // Línea
			// izquierda
			g.drawLine(this.getWidth() - (this.getWidth() / 100), this.getWidth() / 100, this.getWidth() - (this.getWidth() / 100), this.getHeight() - (this.getWidth() / 100)); // Línea
			// izquierda
			g.drawLine((this.getWidth() / 2) - (this.getWidth() / 8), this.getWidth() / 100, (this.getWidth() / 2) - (this.getWidth() / 8), this.getHeight() - (this.getWidth() / 100)); // Línea
			// central
			// izquierda
			g.drawLine((this.getWidth() / 2) + (this.getWidth() / 8), this.getWidth() / 100, (this.getWidth() / 2) + (this.getWidth() / 8), this.getHeight() - (this.getWidth() / 100)); // Línea
			// central
			// derecha

			// AffineTransform orig = g.getTransform();
			AffineTransform at = AffineTransform.getQuadrantRotateInstance(1);
			// g.setTransform(at);
			g.setFont(new Font("Cantarell", 1, 35));
			g.drawString("PAI  PROJECT", this.getWidth() / 100, -(this.getWidth() / 2) + (this.getWidth() / 45));
			at = AffineTransform.getQuadrantRotateInstance(3);
			// g.setTransform(at);
			g.drawString("PAI  PROJECT", -this.getHeight() + (this.getWidth() / 100), this.getWidth() / 2 + (this.getWidth() / 45));
			// g.setTransform(orig);

			// g.rotate(90);
//			g.drawString(String.valueOf(player1.getScore()), 100, 100);
			// g.rotate(-90);

			// Dibujar Player1 y Score
			g.setFont(new Font("Cantarell", 1, 12));
			g.drawString("Player 1", this.getWidth() / 2 - (this.getWidth() / 4) - 6, this.getHeight() / 2 - 50);
			g.setFont(new Font("Cantarell", 1, 50));
			g.drawString(String.valueOf(player1.getScore()), this.getWidth() / 2 - (this.getWidth() / 4), this.getHeight() / 2);

			// Dibujar Player2 y Score
			g.setFont(new Font("Cantarell", 1, 12));
			g.drawString("Player 2", this.getWidth() / 2 + (this.getWidth() / 4) - 6, this.getHeight() / 2 - 50);
			g.setFont(new Font("Cantarell", 1, 50));
			g.drawString(String.valueOf(player2.getScore()), this.getWidth() / 2 + (this.getWidth() / 4), this.getHeight() / 2);

			// Creamos jugadores y bola
			player1.dibujar(g);
			player2.dibujar(g);
			ball.dibujar(g);
		}

		if (gameStatus == 3) { // JUEGO TERMINADO
			g.setColor(Color.WHITE);
			g.setFont(new Font("Cantarell", 1, 50));

			g.drawString("PONG", this.getWidth() / 2 - 75, 50);
			g.drawString("Player " + playerWon + " Wins!", this.getWidth() / 2 - 165, 200);

		}
	}

}
