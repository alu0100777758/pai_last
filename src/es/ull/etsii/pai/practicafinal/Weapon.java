package es.ull.etsii.pai.practicafinal;


public class Weapon {
	private BvsR_Map map;	// mapa en el que está  -> se podría obtener desde el dueño?
	private Player owner;	// dueño del arma
	private int fireRate; 	// cadencia de disparo en forma de periodo en "ticks" suponiendo 60 fps
	private int mainCharger;		// número máximo de balas.
	private int mainChargerBulletCounter; 	// cantidad de balas actualmente en el cargador
	private int mainBulletCounter = -1;  // munición disponible, infinito si < 0;
	private int SecondaryCharger;		// número máximo de balas.
	private int SecondaryChargerBulletCounter; 	// cantidad de balas actualmente en el cargador
	private int SecondaryBulletCounter = -1;  // munición disponible, infinito si < 0;
	boolean pulsedMainTrigger = false;
	boolean pulsedSecondaryTrigger = false;
	public Weapon(BvsR_Map map, Player owner) {
		setMap(map);
		setOwner(owner);
	}
	
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public BvsR_Map getMap() {
		return map;
	}

	public void setMap(BvsR_Map map) {
		this.map = map;
	}

	protected double getFireRate() {
		return fireRate;
	}
	protected void setFireRate(double fireRate) {
		this.fireRate = (int)((double)60/fireRate);
	}
	protected int getCharger() {
		return mainCharger;
	}
	protected void setCharger(int charger) {
		this.mainCharger = charger;
	}
	protected int getChargerBulletCounter() {
		return mainChargerBulletCounter;
	}
	protected void setChargerBulletCounter(int chargerBulletCounter) {
		mainChargerBulletCounter = chargerBulletCounter;
	}
	protected int getBulletCounter() {
		return mainBulletCounter;
	}
	protected void setBulletCounter(int bulletCounter) {
		this.mainBulletCounter = bulletCounter;
	}
	protected boolean isPulsedMainTrigger() {
		return pulsedMainTrigger;
	}
	protected void setPulsedMainTrigger(boolean pulsedMainTrigger) {
		this.pulsedMainTrigger = pulsedMainTrigger;
	}
	
	public int getSecondaryCharger() {
		return SecondaryCharger;
	}

	public void setSecondaryCharger(int secondaryCharger) {
		SecondaryCharger = secondaryCharger;
	}

	public int getSecondaryChargerBulletCounter() {
		return SecondaryChargerBulletCounter;
	}

	public void setSecondaryChargerBulletCounter(int secondaryChargerBulletCounter) {
		SecondaryChargerBulletCounter = secondaryChargerBulletCounter;
	}

	public int getSecondaryBulletCounter() {
		return SecondaryBulletCounter;
	}

	public void setSecondaryBulletCounter(int secondaryBulletCounter) {
		SecondaryBulletCounter = secondaryBulletCounter;
	}

	protected boolean isPulsedSecondaryTrigger() {
		return pulsedSecondaryTrigger;
	}
	protected void setPulsedSecondaryTrigger(boolean pulsedSecondaryTrigger) {
		this.pulsedSecondaryTrigger = pulsedSecondaryTrigger;
	}
	
	public void triggerMain(){
		setPulsedMainTrigger(true);
	}
	public void triggerSecondary(){
		setPulsedSecondaryTrigger(true);
	}
	public void releaseMain(){
		setPulsedMainTrigger(false);
	}
	public void releaseSecondary(){
		setPulsedSecondaryTrigger(false);
	}

	public void update(){
		if(isPulsedMainTrigger()){
			shootMain();
		}else if(isPulsedSecondaryTrigger())
			shootSecondary();
	}

	private void shootSecondary() {
		// TODO Auto-generated method stub
		
	}

	private void shootMain() {
		// TODO Auto-generated method stub
		
	}
	

}
