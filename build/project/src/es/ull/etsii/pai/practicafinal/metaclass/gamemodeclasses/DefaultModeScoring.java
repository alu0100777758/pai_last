package es.ull.etsii.pai.practicafinal.metaclass.gamemodeclasses;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import es.ull.etsii.pai.practicafinal.redvsblue.Player;

public class DefaultModeScoring {
	public static final int HIT_SCORE = 300;
	public static final int WINNING_SCORE = 2000;
	public static final double TIME_SECOND_PENALTY = 0.0001;
	public static final double PERFECT_SCORE = 1.5;

	public static void addHitScore(Player player) {
		player.addToScore(HIT_SCORE);
	}

	public static void addWinningScore(Player player) {
		player.addToScore(WINNING_SCORE);
	}

	public static double getFinalScore(Player player) {
		double score = player.getScore();
		int seconds = 100; // TODO calcular guardando momento de inicio en el escenario (actualmente en gameframe).
		score -= score * (seconds * TIME_SECOND_PENALTY);
		if(player.getHp() == player.getMaxHp())
			score *= PERFECT_SCORE;
		return score;
	}
}
