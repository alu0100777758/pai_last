package es.ull.etsii.pai.practicafinal.scenes;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import es.ull.etsii.pai.practicafinal.main.RvsB_Menu;
import es.ull.etsii.pai.practicafinal.metaclass.CollaboratorCreditText;
import es.ull.etsii.pai.practicafinal.metaclass.CreditText;
import es.ull.etsii.pai.practicafinal.metaclass.TitleCreditText;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;
import es.ull.etsii.pai.prct9.geometry.Point2D;
/**
 * Clase encargada de mostrar la escena de creditos.
 * @author Javier Martin Hernandez
 * @author Sabato Ceruso
 *
 */
public class CreditsScene extends ScenarioPanel{
	public final static String TITLE_1 = "Desarrolladores";
	public final static String TITLE_2 = "Artistas";
	public final static String TITLE_3 = "Voces";
	public final static String TITLE_4 = "Diseño de Mapas";
	public final static String TITLE_5 = "Agradecimientos";
	public final static String VOICE_1 = "Elisa Martín Dalens";
	public final static String VOICE_2 = "Gianna Caffera";
	public final static String ARTIST = "Rafa García";
	public final static String DEVELOPER_1 = "Javier Martín Hernández";
	public final static String DEVELOPER_2 = "Sabato Ceruso";
	public final static String MAPS_1 = "Mahy";
	public final static String MAPS_2 = "David Castelló Morales";
	public final static String MAPS_3 = "Javier Martín Hernández";
	public final static String MAPS_4 = "Sabato Ceruso";
	public final static String ACKNOWLEDGEMENT_1 = "OSL ULL";

	public final static int TITLE_GAP = 80;
	public final static int COLLABORATOR_GAP = 40;
	public final static Point2D TEXT_SPEED = new Point2D(0, -5);
	public final static int TIMER_DELAY = 40;
	public final static Color BACKGROUND_COLOR = Color.BLACK;
	public final static int SPEED = 4;
	
	private ArrayList<CreditText> creditText;									// Texto a mostrar.
	private Timer timer;														// Timer para el refresco.

	/**
	 * Crea la escena cargando el texto.
	 * @param width Ancho de la escena.
	 * @param height Alto de la escena.
	 */
	public CreditsScene() {
		int width = ScreenManager.getInstance().getCurrentWidth() - 400; // SOLO PROVISIONAL!!!!!
		int height = ScreenManager.getInstance().getCurrentHeight();
		
		setCreditText(new ArrayList<CreditText>());
		setTimer(new Timer(TIMER_DELAY, new TimerHandler()));
		setBackground(BACKGROUND_COLOR);
		
		getCreditText().add(new TitleCreditText(new Point2D(width / 2, height + 20), TEXT_SPEED, TITLE_1, Color.RED));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + COLLABORATOR_GAP), TEXT_SPEED, DEVELOPER_1));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + COLLABORATOR_GAP), TEXT_SPEED, DEVELOPER_2));
		getCreditText().add(new TitleCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + TITLE_GAP), TEXT_SPEED, TITLE_2, Color.RED));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + COLLABORATOR_GAP), TEXT_SPEED, ARTIST));
		getCreditText().add(new TitleCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + TITLE_GAP), TEXT_SPEED, TITLE_3, Color.RED));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + COLLABORATOR_GAP), TEXT_SPEED, VOICE_1));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + COLLABORATOR_GAP), TEXT_SPEED, VOICE_2));
		getCreditText().add(new TitleCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + TITLE_GAP), TEXT_SPEED, TITLE_4, Color.RED));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + COLLABORATOR_GAP), TEXT_SPEED,MAPS_1));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + COLLABORATOR_GAP), TEXT_SPEED, MAPS_2));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + COLLABORATOR_GAP), TEXT_SPEED,MAPS_3));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + COLLABORATOR_GAP), TEXT_SPEED, MAPS_4));
		getCreditText().add(new TitleCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + TITLE_GAP), TEXT_SPEED, TITLE_5, Color.RED));
		getCreditText().add(new CollaboratorCreditText(new Point2D(width / 2, getLastCreditTextPos().y() + COLLABORATOR_GAP), TEXT_SPEED,ACKNOWLEDGEMENT_1));
		
		getTimer().start();
		
	}
	/**
	 * Obtiene la posicion del ultimo texto posicionado.
	 * @return
	 */
	private Point2D getLastCreditTextPos() {
		return getCreditText().get(getCreditText().size() - 1).getPos();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g.create());
		for (int i = 0; i < getCreditText().size(); i++) 
			getCreditText().get(i).paint(g.create());
	}
	
	@Override
	public void releasedKey(int keyCode, char keyChar) {
		if (keyCode == KeyEvent.VK_SPACE)
			getSceneManager().switchScenario(new RvsB_Menu());
	}
	/**
	 * Getters y Setters.
	 * @return
	 */
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
			for(int i = 0; i < getCreditText().size(); i++) {
				getCreditText().get(i).update();
				/*if (getCreditText().get(i).getPos().y() <= getHeight() )
					getCreditText().get(i).setSpeed(new Point2D(-SPEED, -SPEED));
				if (getCreditText().get(i).getPos().y() <= getHeight()  * 2  / 4)
					getCreditText().get(i).setSpeed(new Point2D(SPEED, -SPEED));*/
			}
			
			repaint();
			
		}
		
	}

	@Override
	public void sizeUpdate() {
		// TODO Auto-generated method stub
		
	}
}
