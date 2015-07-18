package es.ull.etsii.pai.practicafinal.metaclass;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.io.Serializable;

import es.ull.etsii.pai.practicafinal.graphics.GraphicRectangle;
import es.ull.etsii.pai.practicafinal.redvsblue.Bullet;
import es.ull.etsii.pai.practicafinal.redvsblue.Player;
import es.ull.etsii.pai.practicafinal.redvsblue.Side;



public abstract class Weapon implements Serializable{
	
	private static final long serialVersionUID = -2320121838158394980L;
	private Player owner;						// Due�o del arma
	private int fireRate; 						// Cadencia de disparo en forma de periodo en "ticks" suponiendo 60 fps
	private int mainClipSize;					// Numero maximo de balas.
	private int mainAmmo; 						// Cantidad de balas actualmente en el cargador
	private int mainBulletCounter = -1;  		// Municion disponible, infinito si < 0;
	private int secondaryClipSize;				// Numero maximo de balas.
	private int secondaryAmmo;					// Cantidad de balas actualmente en el cargador
	private int SecondaryBulletCounter = -1;  	// Municion disponible, infinito si < 0;
	private int mainCooldown = 0;				// Tiempo de espera para volver a disparar el arma principal.
	private int secondaryCooldown = 0;			// Tiempo de espera para volver a disparar el arma secundaria.
	private boolean pulsedMainTrigger = false;	// True si esta presionado el gatillo del disparo principal.
	private boolean pulsedSecondaryTrigger = false;	// True si esta presionado el gatillo del arma secundaria.
	private GraphicRectangle graphicShape;		// Forma grafica del arma.
	private boolean reloading;					// True si esta recargando.
	private int mainReloadingTime;				// Tiempo de recarga para el disparo principal.
	private int reloadingCooldown;				// Tiempo restante de recarga.
	private int speed;							// Velocidad de la bala.
	private int width = 25; 					// Ancho del arma.
	private int height = 10;					// Alto del arma.
	private int y_offset = 10;					// Offset de la y.
	private int x_offset = 0;					// Offset de la x.
	private boolean reload_able = true; 		// marca si un arma necesita recargar.
	

	/**
	 * Crea un arma en la posicion indicada.
	 * @param x
	 * @param y
	 */
	public Weapon(int x, int y ){
		setOwner(null);
		setGraphicShape(new GraphicRectangle(x+getX_offset(),y+ getY_offset(),getWidth(), getHeight()));
	}
	/**
	 * Crea un arma con un propietario.
	 * @param owner
	 */
	public Weapon(Player owner) {
		setOwner(owner);
		setGraphicShape();
		setReload_able(true);
	}
	
	/**
	 * Pulsa el gatillo principal.
	 */
	public void triggerMain(){
		setPulsedMainTrigger(true);
	}
	/**
	 * Pulsa el gatillo secundario.
	 */
	public void triggerSecondary(){
		setPulsedSecondaryTrigger(true);
	}
	/**
	 * Suelta el gatillo principal.
	 */
	public void releaseMain(){
		setPulsedMainTrigger(false);
		//setMainCooldown(0);
	}
	/**
	 * Suelta el gatillo secundario.
	 */
	public void releaseSecondary(){
		setPulsedSecondaryTrigger(false);
		//setSecondaryCooldown(0);
	}
	/**
	 * True si puede disparar el primario.
	 * @return
	 */
	private boolean canShootPrimary() {
		if (isReload_able() && getMainAmmo() <= 0) {
			setReloading(true);
			setReloadingCooldown(getMainReloadingTime());
			getOwner().playReload();
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
	/**
	 * True si puede disparar el secundario.
	 * @return
	 */
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
	/**
	 * Decrementa los enfriamientos del arma.
	 */
	public void decreaseCooldowns() {
		setMainCooldown(getMainCooldown() - 1);
		setSecondaryCooldown(getSecondaryCooldown() - 1);
		setEffectDuration(-1);
	}
	private void setEffectDuration(int i) {
		//TODO
	}
	/**
	 * Actualiza el arma, su posicion y si debe disparar o recargar.
	 */
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
				getGraphicShape().setLocation((int)getOwner().getPosition().x() - getWidth() - getX_offset(), (int)getOwner().getPosition().y() + addy + getY_offset());
			else 
				getGraphicShape().setLocation((int)getOwner().getPosition().x() + getOwner().getStats().getWidth() + getX_offset(), (int)getOwner().getPosition().y() + addy + getY_offset());
		}
	}
	protected void addBullet(Bullet bullet){
		getOwner().getMap().addBullet(bullet);
	}
	private void shootSecondaryEffect() {
		// TODO Auto-generated method stub
		
	}

	private void shootMainEffect() {
		// TODO Auto-generated method stub
		
	}
	protected abstract void shootSecondary();

	
	protected abstract void shootMain();
	/**
	 * Getters y Setters.
	 * @return
	 */
	protected int getSpeed() {
		if (getOwner().getLookingAt() == Side.LEFT)
			return speed - (int)getOwner().getSpeed().x();
		return speed + (int)getOwner().getSpeed().x();
	}

	protected void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setGraphicShape(){
		setGraphicShape(new GraphicRectangle((int)getOwner().getPosition().x()+getOwner().getStats().getWidth()+getX_offset(), (int)getOwner().getPosition().y() + getY_offset(),getWidth(), getHeight()));
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

	public int getMainAmmo() {
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
	public boolean isReload_able() {
		return reload_able;
	}
	public void setReload_able(boolean reload_able) {
		this.reload_able = reload_able;
	}
	
	
}
