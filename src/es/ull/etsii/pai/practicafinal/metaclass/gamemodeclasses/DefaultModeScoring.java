package es.ull.etsii.pai.practicafinal.metaclass.gamemodeclasses;

import es.ull.etsii.pai.practicafinal.redvsblue.Player;

public class DefaultModeScoring {
	public static final int HIT_SCORE = 30;
	public static final int WINNING_SCORE = 200;
	
	public static void addHitScore(Player player) {
		player.addToScore(HIT_SCORE);
	}
	public static void addWinningScore(Player player) {
		player.addToScore(WINNING_SCORE);
	}
}
