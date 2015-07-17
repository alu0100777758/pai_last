package es.ull.etsii.pai.practicafinal.redvsblue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.main.RvsB_Menu;

@SuppressWarnings("serial")
public class WinnerScene extends ScenarioPanel {
	public static final String WIN_TEXT = " Wins!";
	public static final String CLOSE_TEXT = "Press spacebar to close";
	public static final String FONT_NAME = "Cantarell";
	public static final int FONT_STYLE = 1;
	public static final int FONT_MAIN_SIZE = 50;
	public static final int FONT_SUBTEXT_SIZE = 20;
	private String winnerName;
	
	public WinnerScene(String name, int width, int height) {
		this.winnerName = name;
		this.setBackground(Color.BLACK);
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ScreenManager screen = ScreenManager.getInstance();
		g.setColor(Color.BLACK);
		g.setColor(Color.WHITE);
		g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_MAIN_SIZE));
		g.drawString(winnerName + WIN_TEXT, getWidth() / 2 - getWidth() / 3,(int) screen.getRate_y() * 180);
		g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SUBTEXT_SIZE));
		g.drawString(CLOSE_TEXT, getWidth() / 2 - getWidth()/ 4, (int)screen.getRate_y() * 300);
	}

	@Override
	public void pulsedKey(int keyCode, char keyChar) {
		
		
	}

	@Override
	public void releasedKey(int keyCode, char keyChar) {
		if (keyCode == KeyEvent.VK_SPACE)
			getSceneManager().switchScenario(new RvsB_Menu());
	}

	@Override
	public void sizeUpdate() {
		// TODO Auto-generated method stub
		
	}
	
}