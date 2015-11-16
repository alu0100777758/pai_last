package es.ull.etsii.pai.practicafinal.scenes;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;

import es.ull.etsii.pai.practicafinal.main.RvsB_Menu;
import es.ull.etsii.pai.practicafinal.metaclass.gamemodeclasses.Ladder;
import es.ull.etsii.pai.practicafinal.metaclass.gamemodeclasses.LadderEntry;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class ScoreScene extends ScenarioPanel {
	public static final String FONT = "SanSerif";
	public static final int VERTICAL_GAP = 50;
	public static final Color BACKGROUND_COLOR = Color.BLACK;
	private Ladder ladder;
	
	public ScoreScene () {
		ladder = Ladder.getInstance();
		this.setBackground(BACKGROUND_COLOR);
		this.setLayout(new GridLayout(2, 3));
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g.create());
		ArrayList<LadderEntry> entries = ladder.getAllEntries();
		ScreenManager screen = ScreenManager.getInstance();
		int yPosition = 3 * VERTICAL_GAP;
		
		
		g.setFont(new Font(FONT, Font.ITALIC, 48));
		
		g.setColor(Color.RED);
		
		g.drawString("Records", screen.getCurrentWidth() / 4 + 180, VERTICAL_GAP);
		g.setColor(Color.WHITE);        
		for (int i = entries.size() - 1; i >= 0; i--) {
			g.drawString(entries.get(i).getName(), screen.getCurrentWidth() / 4 + 120, yPosition);
			g.drawString(entries.get(i).getScore() + "", screen.getCurrentWidth() / 2, yPosition);
			yPosition += VERTICAL_GAP;
		}
		
		
	}
	
	@Override
	public void releasedKey(int keyCode, char keyChar) {
		if (keyCode == KeyEvent.VK_SPACE)
			getSceneManager().switchScenario(new RvsB_Menu());
	}
	@Override
	public void sizeUpdate() {
		
		
	}

}
