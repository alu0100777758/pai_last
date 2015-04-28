package es.ull.etsii.pai.prct9;

import java.awt.Dimension;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.Timer;

public final class GameControl implements ActionListener {
	private static GameControl instance = null;
	private BolaMovilWindow window = new BolaMovilWindow();
	private Actor mainChar;
	private Scenario scenario;
	private ControlsPanel controls;
	private double actorSpeed = 20;
	private keyManager keymanager= new keyManager();
	private TimerEventManager timerManager= new TimerEventManager();
	private Timer stepTimer;
	private boolean walking = false;
	
	
	public boolean isWalking() {
		return walking;
	}

	public void setWalking(boolean walking) {
		this.walking = walking;
	}

	public Timer getStepTimer() {
		return stepTimer;
	}

	public void setStepTimer(Timer stepTimer) {
		this.stepTimer = stepTimer;
	}

	public double getActorSpeed() {
		return actorSpeed;
	}

	public void setActorSpeed(double actorSpeed) {
		this.actorSpeed = actorSpeed;
	}

	private GameControl() {
		scenario = new Scenario();
	}

	public static GameControl getInstance() {
		if (instance == null) {
			instance = new GameControl();
		}
		return instance;
	}

	public void buildInterface() {
		controls = new ControlsPanel();
		scenario.setPreferredSize(new Dimension(window.getWidth(),
				(int) (window.getHeight() - controls.getHeight())));
		scenario.setSize(new Dimension(window.getWidth(), (int) (window
				.getHeight() * window.getAlignmentY())));
		scenario.setLimits();
		window.add(scenario);
		window.add(controls);
		window.addKeyListener(keymanager);
		window.setFocusable(true);
	}

	public void setActors() {
		mainChar = new BallActor(
				scenario.getWidth() * scenario.getAlignmentX(),
				scenario.getHeight() * scenario.getAlignmentY());
		scenario.addActor(mainChar);
	}

	public void play() {
		buildInterface();
		setActors();
		window.setVisible(true);
		startRandomMove();
	}
	public void startRandomMove(){
		setStepTimer(new Timer(10, timerManager));
		getStepTimer().start();
		randomMove();
	}
	public void moveMainchar(short direction) {
		mainChar.move(actorSpeed, direction);
		System.out
				.println("dist to limit: " + scenario.lenghtToLimit(mainChar));
		if (!scenario.isIn(mainChar)) {
			mainChar.move(Math.abs(scenario.lenghtToLimit(mainChar)), (short)-direction);

		}
		window.repaint();
	}
	public void moveForRandomMainchar(short direction){
		mainChar.move(actorSpeed, direction);
		if (!scenario.isIn(mainChar)) {
			mainChar.move(Math.abs(scenario.lenghtToLimit(mainChar)), (short)-direction);
			getStepTimer().stop();
		}
		window.repaint();
	}
	public void moveCyclic1(short direction){
		mainChar.move(actorSpeed, direction);
		if (!scenario.isIn(mainChar) && scenario.) {
			mainChar.move(Math.abs(scenario.lenghtToLimit(mainChar)), (short)-direction);
			getStepTimer().stop();
		}
		window.repaint();
	}
	void randomMove(){
		short [] moves = {Actor.LEFT_DIR, Actor.RIGHT_DIR, Actor.UP_DIR, Actor.DOWN_DIR};
		Random rand = new Random();
		moveForRandomMainchar(moves[rand.nextInt(4)]);
		
	}
	public void validateAfterEvent() {
//		if (window.getHeight() > 1 && window.getWidth() > 1)
//			scenario.compensateRedim();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().getClass().equals(JButton.class)) {
			JButton pulsedButton = (JButton) e.getSource();
			if (pulsedButton.getText().equals(DPad.UP_LEFT)) {
				moveMainchar(Actor.LEFT_DIR);
				moveMainchar(Actor.UP_DIR);
			}
			if (pulsedButton.getText().equals(DPad.UP_RIGHT)) {
				moveMainchar(Actor.RIGHT_DIR);
				moveMainchar(Actor.UP_DIR);
			}
			if (pulsedButton.getText().equals(DPad.DOWN_LEFT)) {
				moveMainchar(Actor.DOWN_DIR);
				moveMainchar(Actor.LEFT_DIR);
			}
			if (pulsedButton.getText().equals(DPad.DOWN_RIGHT)) {
				moveMainchar(Actor.DOWN_DIR);
				moveMainchar(Actor.RIGHT_DIR);
			}
		}
		window.requestFocus();
	}
}
