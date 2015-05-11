package es.ull.etsii.pai.practicafinal;

public class Weapon {
	private double fireRate; 	// cadencia de disparo en balas/segundo 
	private int charger;		// número máximo de balas.
	private int ChargerBulletCounter; 	// cantidad de balas actualmente en el cargador
	private int bulletCounter = -1;  // munición disponible, infinito si < 0;
	boolean pulsedMainTrigger = false;
	boolean pulsedSecondaryTrigger = false;
	protected double getFireRate() {
		return fireRate;
	}
	protected void setFireRate(double fireRate) {
		this.fireRate = fireRate;
	}
	protected int getCharger() {
		return charger;
	}
	protected void setCharger(int charger) {
		this.charger = charger;
	}
	protected int getChargerBulletCounter() {
		return ChargerBulletCounter;
	}
	protected void setChargerBulletCounter(int chargerBulletCounter) {
		ChargerBulletCounter = chargerBulletCounter;
	}
	protected int getBulletCounter() {
		return bulletCounter;
	}
	protected void setBulletCounter(int bulletCounter) {
		this.bulletCounter = bulletCounter;
	}
	protected boolean isPulsedMainTrigger() {
		return pulsedMainTrigger;
	}
	protected void setPulsedMainTrigger(boolean pulsedMainTrigger) {
		this.pulsedMainTrigger = pulsedMainTrigger;
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
	
	
}
