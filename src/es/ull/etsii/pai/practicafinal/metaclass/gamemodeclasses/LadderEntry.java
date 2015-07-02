package es.ull.etsii.pai.practicafinal.metaclass.gamemodeclasses;

import java.io.Serializable;
import java.util.Comparator;

public class LadderEntry implements Serializable, Comparator<LadderEntry>{
	private static final long serialVersionUID = -227229248112847234L;
	private String name;
	private String mode;
	private int score;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public int compare(LadderEntry arg0, LadderEntry arg1) {
		return arg0.getScore() - arg1.getScore();
	}
	
}
