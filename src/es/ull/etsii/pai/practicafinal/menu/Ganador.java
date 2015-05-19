package es.ull.etsii.pai.practicafinal.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ganador extends JPanel {

	private String nombreGanador;
	private int width;
	private int height;

	public Ganador(String nombreGanador, int width, int height) {
		this.nombreGanador = nombreGanador;
		this.width = width;
		this.height = height;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.width, this.height);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Cantarell", 1, 50));
		g.drawString(nombreGanador + " Wins!", this.width/2- this.width/3 , 180);
		g.setFont(new Font("Cantarell", 1, 20));
		g.drawString("Press enter for return", this.width/2- this.width/4 , 300);
	}


}