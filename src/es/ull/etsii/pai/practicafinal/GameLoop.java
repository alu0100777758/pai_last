package es.ull.etsii.pai.practicafinal;

public class GameLoop {
	private static GameLoop instance = null;
	  public static final int FRAMES_PER_SECOND = 25;
	  public static final long SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	  public static Scenario updater ;
	  public static ScenarioPanel displayer ;
	  private GameLoop(){};
	  public GameLoop getInstance(){
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

	public static ScenarioPanel getDisplayer() {
		return displayer;
	}

	public static  void setDisplayer(ScenarioPanel displayer) {
		GameLoop.displayer = displayer;
	}
	public static void init(){
	long next_game_tick = System.nanoTime();
	    // GetTickCount() returns the current number of milliseconds
	    // that have elapsed since the system was started

	    long sleep_time = 0;

	    boolean game_is_running = true;

	    while( game_is_running ) {
//	    	System.out.println("updating    ");
	        update_game();
	        display_game();
	        next_game_tick += SKIP_TICKS *1000;
	        sleep_time = next_game_tick - System.nanoTime();
	        if( sleep_time >= 0 ) {
	            try {
					Thread.sleep( sleep_time/1000 );
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        else {
	            // Shit, we are running behind!
	        }
	    }
	}
	// tendr�n que ser eventos para que no molesten con los threads
	private static void display_game() {
		updater.update();
		
	}

	private static void update_game() {
		displayer.repaint();
		
	}
}