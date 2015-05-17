package es.ull.etsii.pai.practicafinal.metaclass;

import java.io.Serializable;

import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.practicafinal.Side;
import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;


public abstract class Weapon implements Serializable{
	
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
	private boolean reloading;
	private int mainReloadingTime;
	private int reloadingCooldown;
	private int speed;
	private int width = 25; 
	private int height = 10;
	private int y_offset = 10;
	private int x_offset = 0;
	

	public Weapon(int x, int y ){
		setOwner(null);
		setGraphicShape(new GraphicRectangle(x+getX_offset(),y+ getY_offset(),getWidth(), getHeight()));
	}

	public Weapon(Player owner) {
		setOwner(owner);
		setGraphicShape();
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
		if (getMainAmmo() <= 0) {
			setReloading(true);
			setReloadingCooldown(getMainReloadingTime());
		}
		else if (getMainCooldown() <= 0) {
			setMainCooldown(getFireRate());
			setMainAmmo(getMainAmmo() - 1);
			return true;
		}
		else 
			decreaseCooldowns();
		
		return false;
	}
	private boolean canShootSecondary() {
		if (getSecondaryAmmo() <= 0) {
			setReloading(true);
			setReloadingCooldown(getMainReloadingTime());
		}
		else if (getSecondaryCooldown() <= 0) {
			setSecondaryCooldown(getFireRate());
			setSecondaryAmmo(getSecondaryAmmo() - 1);
			return true;
		}
		else 
			decreaseCooldowns();
		
		return false;
	}
	public void decreaseCooldowns() {
		setMainCooldown(getMainCooldown() - 1);
		setSecondaryCooldown(getSecondaryCooldown() - 1);
		setEffectDuration(-1);
	}
	private void setEffectDuration(int i) {
		//TODO
	}

	public void update(){
		int addy = getY_offset();
		if (getOwner() != null) {
			if (isReloading()) {
				setReloadingCooldown(getReloadingCooldown() - 1);
				if (getReloadingCooldown() <= 0) {
					setReloading(false);
					setMainAmmo(getMainClipSize());
					decreaseCooldowns();
				//	setSecondaryAmmo(getSecondaryClipSize());
				}
			}
			else if(isPulsedMainTrigger() && canShootPrimary()){
				shootMain();
				shootMainEffect();
			}else if(isPulsedSecondaryTrigger() && canShootSecondary()){
				shootSecondary();
				shootSecondaryEffect();
			}
			else
				decreaseCooldowns();
			
			if (getOwner().isCrounched())
				addy = getY_offset() /2 ;
			if (getOwner().getLookingAt() == Side.LEFT)
				getGraphicShape().setLocation((int)getOwner().getPosition().x() - getWidth() - getX_offset(), (int)getOwner().getPosition().y() + addy);
			else 
				getGraphicShape().setLocation((int)getOwner().getPosition().x() + Player.WIDTH + getX_offset(), (int)getOwner().getPosition().y() + addy);
		}
	}
	
	private void shootSecondaryEffect() {
		// TODO Auto-generated method stub
		
	}

	private void shootMainEffect() {
		// TODO Auto-generated method stub
		
	}
	
	
	protected int getSpeed() {
		if (getOwner().getLookingAt() == Side.LEFT)
			return speed - (int)getOwner().getSpeed().x();
		return speed + (int)getOwner().getSpeed().x();
	}

	protected void setSpeed(int speed) {
		this.speed = speed;
	}

	protected abstract void shootSecondary();

	
	protected abstract void shootMain();
	
	public void setGraphicShape(){
		setGraphicShape(new GraphicRectangle((int)getOwner().getPosition().x()+Player.WIDTH+getX_offset(), (int)getOwner().getPosition().y() + getY_offset(),getWidth(), getHeight()));
	}
	
	public int getX_offset() {
		return x_offset;
	}

	public void setX_offset(int x_offset) {
		this.x_offset = x_offset;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		getGraphicShape().setSize(width, (int)getGraphicShape().getHeight());
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		getGraphicShape().setSize((int)getGraphicShape().getWidth(), height);
	}

	public int getY_offset() {
		return y_offset;
	}

	public void setY_offset(int y_offset) {
		this.y_offset = y_offset;
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

	public boolean isReloading() {
		return reloading;
	}

	public void setReloading(boolean reloading) {
		this.reloading = reloading;
	}

	public int getMainReloadingTime() {
		return mainReloadingTime;
	}

	public void setMainReloadingTime(int mainReloadingTime) {
		this.mainReloadingTime = mainReloadingTime;
	}

	public int getReloadingCooldown() {
		return reloadingCooldown;
	}

	public void setReloadingCooldown(int reloadingCooldown) {
		this.reloadingCooldown = reloadingCooldown;
	}
	
}
