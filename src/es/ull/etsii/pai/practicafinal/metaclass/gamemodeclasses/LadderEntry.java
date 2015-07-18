package es.ull.etsii.pai.practicafinal.metaclass.gamemodeclasses;

import java.io.Serializable;

public class LadderEntry implements Serializable, Comparable<LadderEntry>{
	private static final long serialVersionUID = -227229248112847234L;
	private String name;
	private String mode;
	private int score;
	
	public LadderEntry() {
		
	}
	
	public LadderEntry(String name, int score) {
		setName(name);
		setScore(score);
	}
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
	/* @Override
	public int compare(LadderEntry arg0, LadderEntry arg1) {
		return arg0.getScore() - arg1.getScore();
	}
	*/
	@Override
	public int compareTo(LadderEntry arg0) {
		// TODO Auto-generated method stub
		return this.getScore() - arg0.getScore();
	}
	
}
