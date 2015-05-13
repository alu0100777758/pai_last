package es.ull.etsii.pai.practicafinal.metaclass;

import java.io.Serializable;

import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.practicafinal.Side;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;


public abstract class Weapon implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2320121838158394980L;
	private Player owner;						// dueño del arma
	private int fireRate; 						// cadencia de disparo en forma de periodo en "ticks" suponiendo 60 fps
	private int mainClipSize;					// número máximo de balas.
	private int mainAmmo; 						// cantidad de balas actualmente en el cargador
	private int mainBulletCounter = -1;  		// munición disponible, infinito si < 0;
	private int secondaryClipSize;				// número máximo de balas.
	private int secondaryAmmo;					 // cantidad de balas actualmente en el cargador
	private int SecondaryBulletCounter = -1;  	// munición disponible, infinito si < 0;
	private int mainCooldown = 0;
	private int secondaryCooldown = 0;
	private boolean pulsedMainTrigger = false;
	private boolean pulsedSecondaryTrigger = false;
	private GraphicRectangle graphicShape;
	public static final int WIDTH = 25;
	public static final int HEIGHT = 10;
	public static final int ADD_TO_Y = 10;
	
	public Weapon(Player owner) {
		setOwner(owner);
		setGraphicShape(new GraphicRectangle((int)getOwner().getPosition().x(), (int)getOwner().getPosition().y() + ADD_TO_Y, WIDTH, HEIGHT));
	}
	
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	protected int getFireRate() {
		return fireRate;
	}
	protected void setFireRate(double fireRate) {
		this.fireRate = (int)((double)60/fireRate);
	}
	
	public int getMainClipSize() {
		return mainClipSize;
	}

	public void setMainClipSize(int mainClipSize) {
		this.mainClipSize = mainClipSize;
	}

	public int getSecondaryClipSize() {
		return secondaryClipSize;
	}

	public void setSecondaryClipSize(int secondaryClipSize) {
		this.secondaryClipSize = secondaryClipSize;
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
	
	private int getMainCooldown() {
		return mainCooldown;
	}

	private void setMainCooldown(int mainCooldown) {
		this.mainCooldown = mainCooldown;
	}

	private int getSecondaryCooldown() {
		return secondaryCooldown;
	}

	private void setSecondaryCooldown(int secondaryCooldown) {
		this.secondaryCooldown = secondaryCooldown;
	}

	protected int getSecondaryCharger() {
		return secondaryClipSize;
	}

	protected int getMainAmmo() {
		return mainAmmo;
	}

	protected void setMainAmmo(int mainAmmo) {
		this.mainAmmo = mainAmmo;
	}

	protected int getSecondaryAmmo() {
		return secondaryAmmo;
	}

	protected void setSecondaryAmmo(int secondaryAmmo) {
		this.secondaryAmmo = secondaryAmmo;
	}

	protected int getSecondaryBulletCounter() {
		return SecondaryBulletCounter;
	}

	protected void setSecondaryBulletCounter(int secondaryBulletCounter) {
		SecondaryBulletCounter = secondaryBulletCounter;
	}

	protected boolean isPulsedSecondaryTrigger() {
		return pulsedSecondaryTrigger;
	}
	protected void setPulsedSecondaryTrigger(boolean pulsedSecondaryTrigger) {
		this.pulsedSecondaryTrigger = pulsedSecondaryTrigger;
	}
	
	public GraphicRectangle getGraphicShape() {
		return graphicShape;
	}

	public void setGraphicShape(GraphicRectangle graphicShape) {
		this.graphicShape = graphicShape;
	}

	public void triggerMain(){
		setPulsedMainTrigger(true);
	}
	public void triggerSecondary(){
		setPulsedSecondaryTrigger(true);
	}
	public void releaseMain(){
		setPulsedMainTrigger(false);
		//setMainCooldown(0);
	}
	public void releaseSecondary(){
		setPulsedSecondaryTrigger(false);
		//setSecondaryCooldown(0);
	}
	private boolean canShootPrimary() {
		if (getMainCooldown() <= 0) {
			setMainCooldown(getFireRate());
			return true;
		}
		else 
			decreaseCooldowns();
		
		return false;
	}
	private boolean canShootSecondary() {
		if (getSecondaryCooldown() <= 0) {
			setSecondaryCooldown(getFireRate());
			return true;
		}
		else 
			decreaseCooldowns();
		
		return false;
	}
	public void decreaseCooldowns() {
		setMainCooldown(getMainCooldown() - 1);
		setSecondaryCooldown(getSecondaryCooldown() - 1);
	}
	public void update(){
		 int addy = ADD_TO_Y;
		if(isPulsedMainTrigger() && canShootPrimary()){
			shootMain();
		}else if(isPulsedSecondaryTrigger() && canShootSecondary())
			shootSecondary();
		else
			decreaseCooldowns();
		
		if (getOwner().isCrounched())
			addy = ADD_TO_Y /2 ;
		if (getOwner().getLookingAt() == Side.LEFT)
			getGraphicShape().setLocation((int)getOwner().getPosition().x() - WIDTH, (int)getOwner().getPosition().y() + addy);
		else 
			getGraphicShape().setLocation((int)getOwner().getPosition().x() + Player.WIDTH, (int)getOwner().getPosition().y() + addy);
	}
	
	protected abstract void shootSecondary();

	protected abstract void shootMain();
}
