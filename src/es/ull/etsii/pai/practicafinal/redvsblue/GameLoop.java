package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameLoop{
	private static GameLoop instance = null;
	  public static final int FRAMES_PER_SECOND = 60;
	  public static final long SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	  public static Scenario updater ;
	  public static GameScenario displayer ;
	  public static  Timer stepTimer ;
	 
	  private GameLoop(){
	  };
	  public static GameLoop getInstance(){
		  if(instance == null){
			  instance = new GameLoop();        
		  }
		  return instance;
	  }
	  public Scenario getUpdater() {
		return updater;
	}

	public static void setUpdater(Scenario updater) {
		GameLoop.updater = updater;
	}

	public static GameScenario getDisplayer() {
		return displayer;
	}

	public static  void setDisplayer(GameScenario displayer) {
		GameLoop.displayer = displayer;
	}
	public static void init(ActionListener toUdateobject){
	        stepTimer = new Timer((int) (1000/FRAMES_PER_SECOND), toUdateobject);
	        stepTimer.start();
	}
}
