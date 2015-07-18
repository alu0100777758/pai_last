package es.ull.etsii.pai.practicafinal.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.metaclass.gamemodeclasses.Ladder;
import es.ull.etsii.pai.practicafinal.metaclass.gamemodeclasses.LadderEntry;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class ScoreScene extends ScenarioPanel {
	public static final String FONT = "SanSerif";
	public static final int VERTICAL_GAP = 50;
	private Ladder ladder;
	
	public ScoreScene () {
			ladder = Ladder.getInstance();
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		ArrayList<LadderEntry> entries = ladder.getAllEntries();
		ScreenManager screen = ScreenManager.getInstance();
		int yPosition = VERTICAL_GAP;
		
		super.paintComponent(g);
		g.setFont(new Font(FONT, Font.ITALIC, 48));
		g.setColor(Color.WHITE);        
		
		for (int i = entries.size() - 1; i >= 0; i--) {
			g.drawString(entries.get(i).getName(), screen.getCurrentWidth() / 4, yPosition);
			g.drawString(entries.get(i).getScore() + "", screen.getCurrentWidth() / 2, yPosition);
			yPosition += VERTICAL_GAP;
		}
		
		
	}

	@Override
	public void sizeUpdate() {
		
		
	}

}
