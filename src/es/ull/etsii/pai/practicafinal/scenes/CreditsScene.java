package es.ull.etsii.pai.practicafinal.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import es.ull.etsii.pai.practicafinal.metaclass.CollaboratorCreditText;
import es.ull.etsii.pai.practicafinal.metaclass.CreditText;
import es.ull.etsii.pai.practicafinal.metaclass.TitleCreditText;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class CreditsScene extends ScenarioPanel{
	public final static String TITLE_1 = "Desarrolladores";
	public final static String TITLE_2 = "Artistas";
	public final static String TITLE_3 = "Voces";
	public final static String VOICE_1 = "Elisa Martin";
	public final static String VOICE_2 = "Gianna Caffera";
	public final static String ARTIST = "Rafa";
	public final static String DEVELOPER_1 = "Javier Martin Hernandez";
	public final static String DEVELOPER_2 = "Sabato Ceruso";
	public final static int TITLE_GAP = 80;
	public final static int COLLABORATOR_GAP = 40;
	public final static Point2D TEXT_SPEED = new Point2D(0, -5);
	public final static int TIMER_DELAY = 40;
	public final static Color BACKGROUND_COLOR = Color.BLACK;
	
	private ArrayList<CreditText> creditText;
	private Timer timer;

	public CreditsScene(int width, int height) {
		setCreditText(new ArrayList<CreditText>());
		setTimer(new Timer(TIMER_DELAY, new TimerHandler()));
		setBackground(BACKGROUND_COLOR);
		
		getCreditText().add(new TitleCreditText(new Point2D(width / 2, height + 20), TEXT_SPEED, TITLE_1, Color.RED));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getCreditText().get(getCreditText().size() - 1).getPos().y() + COLLABORATOR_GAP), TEXT_SPEED, DEVELOPER_1));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getCreditText().get(getCreditText().size() - 1).getPos().y() + COLLABORATOR_GAP), TEXT_SPEED, DEVELOPER_2));
		getCreditText().add(new TitleCreditText(new Point2D(width / 2,getCreditText().get(getCreditText().size() - 1).getPos().y() + TITLE_GAP), TEXT_SPEED, TITLE_2, Color.RED));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getCreditText().get(getCreditText().size() - 1).getPos().y() + COLLABORATOR_GAP), TEXT_SPEED, ARTIST));
		getCreditText().add(new TitleCreditText(new Point2D(width / 2, getCreditText().get(getCreditText().size() - 1).getPos().y() + TITLE_GAP), TEXT_SPEED, TITLE_3, Color.RED));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2,getCreditText().get(getCreditText().size() - 1).getPos().y() + COLLABORATOR_GAP), TEXT_SPEED, VOICE_1));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getCreditText().get(getCreditText().size() - 1).getPos().y() + COLLABORATOR_GAP), TEXT_SPEED, VOICE_2));
		
		
		
		getTimer().start();
		
	}
	

	protected void paintComponent(Graphics g) {
		super.paintComponent(g.create());
		
		g.setColor(Color.WHITE);
		for (int i = 0; i < getCreditText().size(); i++) 
			getCreditText().get(i).paint(g.create());
		g.drawString(TITLE_1, 960, 1048);
		
	}
	public ArrayList<CreditText> getCreditText() {
		return creditText;
	}

	public void setCreditText(ArrayList<CreditText> creditText) {
		this.creditText = creditText;
	}
	
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	class TimerHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < getCreditText().size(); i++)
				getCreditText().get(i).update();
			
			repaint();
			
		}
		
	}
}
