package es.ull.etsii.pai.practicafinal.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.GameFrame;

@SuppressWarnings("serial")
public class Ganador extends JPanel {

	private String nombreGanador;
	private int width;
	private int height;
	private GameFrame frame;
	
	public Ganador(String nombreGanador, int width, int height, GameFrame frame) {
		this.nombreGanador = nombreGanador;
		this.width = width;
		this.height = height;
		setFrame(frame);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.width, this.height);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Cantarell", 1, 50));
		g.drawString(nombreGanador + " Wins!", this.width / 2 - this.width / 3, 180);
		g.setFont(new Font("Cantarell", 1, 20));
		g.drawString("Press any key to close", this.width / 2 - this.width / 4, 300);
	}

	public GameFrame getFrame() {
		return frame;
	}

	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}

}