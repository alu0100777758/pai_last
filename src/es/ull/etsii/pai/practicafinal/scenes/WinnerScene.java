package es.ull.etsii.pai.practicafinal.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.main.RvsB_Menu;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

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
		g.drawString(winnerName + WIN_TEXT, screen.getCurrentWidth() / 2 - screen.getCurrentWidth() / 3,screen.getCurrentHeight() / 4);
		g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SUBTEXT_SIZE));
		g.drawString(CLOSE_TEXT, screen.getCurrentWidth() / 2 - screen.getCurrentWidth()/ 4, screen.getCurrentHeight() * 2 / 4 );
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