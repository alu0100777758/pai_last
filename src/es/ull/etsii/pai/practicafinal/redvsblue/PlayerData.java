package es.ull.etsii.pai.practicafinal.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Color;
import java.io.Serializable;

public class PlayerData implements Serializable{
	private static final long serialVersionUID = -9152234778591815284L;
	private int maxJumpTTL;
	private double climbPertTick;
	private int jumpTTL;
	private int width;
	private int height;
	private int SPEED;
	private double GRAVITY;
	private int BODY;
	private int WEAPON;
	private int DEFAULT_MAX_HP;
	private int PUSH_RESIST;
	private int maxHp;
	private Color color;
	private String[] hitSounds;

	public PlayerData(int maxJumpTTL, double climbPertTick, int jumpTTL,
			int width, int height, int sPEED, double gRAVITY, int bODY,
			int wEAPON, int dEFAULT_MAX_HP, int pUSH_RESIST, Color color,
			String[] hitSounds) {
		this.maxJumpTTL = maxJumpTTL;
		this.climbPertTick = climbPertTick;
		this.jumpTTL = jumpTTL;
		this.width = width;
		this.height = height;
		SPEED = sPEED;
		GRAVITY = gRAVITY;
		BODY = bODY;
		WEAPON = wEAPON;
		DEFAULT_MAX_HP = dEFAULT_MAX_HP;
		PUSH_RESIST = pUSH_RESIST;
		this.color = color;
		this.hitSounds = hitSounds;
	}

	public int getMaxJumpTTL() {
		return maxJumpTTL;
	}

	public void setMaxJumpTTL(int maxJumpTTL) {
		this.maxJumpTTL = maxJumpTTL;
	}

	public double getClimbPertTick() {
		return climbPertTick;
	}

	public void setClimbPertTick(double climbPertTick) {
		this.climbPertTick = climbPertTick;
	}

	public int getJumpTTL() {
		return jumpTTL;
	}

	public void setJumpTTL(int jumpTTL) {
		this.jumpTTL = jumpTTL;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSPEED() {
		return SPEED;
	}

	public void setSPEED(int sPEED) {
		SPEED = sPEED;
	}

	public double getGRAVITY() {
		return GRAVITY;
	}

	public void setGRAVITY(double gRAVITY) {
		GRAVITY = gRAVITY;
	}

	public int getBODY() {
		return BODY;
	}

	public void setBODY(int bODY) {
		BODY = bODY;
	}

	public int getWEAPON() {
		return WEAPON;
	}

	public void setWEAPON(int wEAPON) {
		WEAPON = wEAPON;
	}

	public int getDEFAULT_MAX_HP() {
		return DEFAULT_MAX_HP;
	}

	public void setDEFAULT_MAX_HP(int dEFAULT_MAX_HP) {
		DEFAULT_MAX_HP = dEFAULT_MAX_HP;
	}

	public int getPUSH_RESIST() {
		return PUSH_RESIST;
	}

	public void setPUSH_RESIST(int pUSH_RESIST) {
		PUSH_RESIST = pUSH_RESIST;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String[] getHitSounds() {
		return hitSounds;
	}

	public void setHitSounds(String[] hitSounds) {
		this.hitSounds = hitSounds;
	}
}