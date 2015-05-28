package es.ull.etsii.pai.practicafinal.redvsblue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WinnerPanel extends JPanel {
	public static final String WIN_TEXT = " Wins!";
	public static final String CLOSE_TEXT = "Press any key to close";
	public static final String FONT_NAME = "Cantarell";
	public static final int FONT_STYLE = 1;
	public static final int FONT_MAIN_SIZE = 50;
	public static final int FONT_SUBTEXT_SIZE = 20;
	private String winnerName;
	private GameFrame frame;
	
	public WinnerPanel(String name, int width, int height, GameFrame frame) {
		this.winnerName = name;
		this.setBackground(Color.BLACK);
		setFrame(frame);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ScreenManager screen = ScreenManager.getInstance();
		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, screen.getWindWidth(), screen);
		g.setColor(Color.WHITE);
		g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_MAIN_SIZE));
		g.drawString(winnerName + WIN_TEXT, getWidth() / 2 - getWidth() / 3,(int) screen.getRate_y() * 180);
		g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SUBTEXT_SIZE));
		g.drawString(CLOSE_TEXT, getWidth() / 2 - getWidth()/ 4, (int)screen.getRate_y() * 300);
	}

	public GameFrame getFrame() {
		return frame;
	}

	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}

}