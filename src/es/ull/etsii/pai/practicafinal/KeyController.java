package es.ull.etsii.pai.practicafinal;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyController {
	private Player playerOne;
	private Player playerTwo;
	private Map<Integer, KeyActions> keyMap ;
	
	public KeyController(Player playerOne, Player playerTwo) {
		setKeyMap(new HashMap<Integer, KeyActions> ());
		
		getKeyMap().put(KeyEvent.VK_A, KeyActions.P1LEFT);
		getKeyMap().put(KeyEvent.VK_S, KeyActions.P1DOWN);
		getKeyMap().put(KeyEvent.VK_D, KeyActions.P1RIGHT);
		getKeyMap().put(KeyEvent.VK_W, KeyActions.P1UP);
		getKeyMap().put(KeyEvent.VK_J, KeyActions.P1SHOOTLEFT);
		getKeyMap().put(KeyEvent.VK_K, KeyActions.P1SHOOTRIGHT);
		
		getKeyMap().put(KeyEvent.VK_UP, KeyActions.P2LEFT);
		getKeyMap().put(KeyEvent.VK_LEFT, KeyActions.P2DOWN);
		getKeyMap().put(KeyEvent.VK_RIGHT, KeyActions.P2RIGHT);
		getKeyMap().put(KeyEvent.VK_UP, KeyActions.P2UP);
		getKeyMap().put(KeyEvent.VK_2, KeyActions.P2SHOOTLEFT);
		getKeyMap().put(KeyEvent.VK_3, KeyActions.P2SHOOTRIGHT);
	}
	
	public void pulsedKey(int keyCode, char keyChar) {
		
		if (keyCode == KeyEvent.VK_LEFT)
			getPlayerTwo().setLeft(true);
		else if (keyCode == KeyEvent.VK_RIGHT)
			getPlayerTwo().setRight(true);
		else if (keyCode == KeyEvent.VK_DOWN)
			getPlayerTwo().setDown(true);
		else if (keyCode == KeyEvent.VK_UP)
			getPlayerTwo().jump();
		if (keyChar == 'a') {
			getPlayerOne().setLeft(true);
		} else if (keyChar == 'd') {
			getPlayerOne().setRight(true);
		} else if (keyChar == 'w') {
			getPlayerOne().jump();
		} else if (keyChar == 's') {
			getPlayerOne().setDown(true);
		}
		 else if (keyChar == 'j') {
			;//	getActors().add(getPlayerOne().shoot());
		}else if (keyChar == '0') {
			;//getActors().add(getPlayerTwo().shoot());
	}
	}

	public void releasedKey(int keyCode, char keyChar) {
		if (keyCode == KeyEvent.VK_LEFT)
			getPlayerTwo().setLeft(false);
		else if (keyCode == KeyEvent.VK_RIGHT)
			getPlayerTwo().setRight(false);
		else if (keyCode == KeyEvent.VK_DOWN) {
			getPlayerTwo().setDown(false);
			getPlayerTwo().setUP(true);
		}
		if (keyChar == 'a') {
			getPlayerOne().setLeft(false);
		} else if (keyChar == 'd') {
			getPlayerOne().setRight(false);
		} else if (keyChar == 'w') {
			// getPlayer_one().setUP(false);
		} else if (keyChar == 's') {
			getPlayerOne().setDown(false);
			getPlayerOne().setUP(true);
		}
	}
	public Player getPlayerOne() {
		return playerOne;
	}
	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}
	public Player getPlayerTwo() {
		return playerTwo;
	}
	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}
	public Map<Integer, KeyActions> getKeyMap() {
		return keyMap;
	}
	public void setKeyMap(Map<Integer, KeyActions> keyMap) {
		this.keyMap = keyMap;
	}
	
	
	
}
