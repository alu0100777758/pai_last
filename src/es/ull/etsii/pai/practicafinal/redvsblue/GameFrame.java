package es.ull.etsii.pai.practicafinal.redvsblue;

/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JFrame implements ActionListener{
	private JPanel scenarioPanel;				// Panel con el escenario.
	private JPanel end;
	private Timer timer; 
	private long startTime;
	
	/**
	 * Inicia una ventana con un mapa cargado identificado con el parametro.
	 * 
	 * @param mapName
	 */
	public GameFrame(String mapName) {
		setScenarioPanel(new GameScenario(mapName));
		this.add(getScenarioPanel());
		this.addKeyListener(new KeyHandler());
		addComponentListener(new MyAdapter());
		GameLoop.setDisplayer((GameScenario)scenarioPanel);
		GameLoop.setUpdater(((GameScenario)scenarioPanel).getScenario());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setTimer(new Timer(1000, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (((GameScenario)getScenarioPanel()).getScenario().isEnded()) {
					double xrate = ScreenManager.getInstance().getRate_x();
					double yrate = ScreenManager.getInstance().getRate_y();
					if (((GameScenario)getScenarioPanel()).getScenario().isBlueWins())
						setEnd(new WinnerScene("Blue", (int)(ScreenManager.getInstance().getWindWidth() * xrate), (int)(ScreenManager.getInstance().getWindHeight() * yrate)));		
					else
						setEnd(new WinnerScene("Red",(int)(ScreenManager.getInstance().getWindWidth() * xrate), (int)(ScreenManager.getInstance().getWindHeight() * yrate)));	
					getThis().addKeyListener(new WinnerKeyHandler());
					remove(getScenarioPanel());
					getContentPane().add(getEnd());
					validate();
					repaint();
					getTimer().stop();
				}				
			}
		}));
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		getTimer().start();
		setStartTime(System.currentTimeMillis()/1000);
	}

	/**
	 * Getters y Setters.
	 * 
	 * @return
	 */
	
	public JPanel getScenarioPanel() {
		return scenarioPanel;
	}

	public JPanel getEnd() {
		return end;
	}
	public void setEnd(JPanel end) {
		this.end = end;
	}
	public void setScenarioPanel(JPanel scenarioPanel) {
		this.scenarioPanel = scenarioPanel;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public GameFrame getThis() { 
		return this;
		}
	/**
	 * Manejador de teclas.
	 * 
	 * @author Sabato Ceruso.
	 * @author Javier Martin Hernandez.
	 *
	 */
	class KeyHandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			((GameScenario) getScenarioPanel()).getScenario().getKeyController()
					.pulsedKey(e.getKeyCode(), e.getKeyChar());
		}

		@Override
		public void keyReleased(KeyEvent arg0) {;		
			((GameScenario)getScenarioPanel()).getScenario().getKeyController().releasedKey(arg0.getKeyCode(), arg0.getKeyChar());
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

	}

	class WinnerKeyHandler extends KeyAdapter {
		
		public void keyTyped(KeyEvent e) {
				getThis().dispose();
			
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().getClass().equals(Timer.class)) {
			((GameScenario) getScenarioPanel()).getScenario().update();
			getScenarioPanel().repaint();
			Toolkit.getDefaultToolkit().sync();
		}
	}

	class MyAdapter extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent e) {
			ScreenManager screen = ScreenManager.getInstance();
			screen.setRate_x((double)getWidth()/screen.getWindWidth());
			screen.setRate_y((double)getHeight()/screen.getWindHeight());
			((GameScenario) getScenarioPanel()).getScenario().getMapData().markForTexture();
		}
	}
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
}
