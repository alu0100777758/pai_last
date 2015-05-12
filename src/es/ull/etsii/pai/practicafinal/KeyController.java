package es.ull.etsii.pai.practicafinal;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyController {
	private Map<KeyActions, Integer> keyMap ;
	
	public KeyController() {
		setKeyMap(new HashMap<KeyActions, Integer> ());
		
		getKeyMap().put(KeyActions.P1LEFT, KeyEvent.VK_A);
		getKeyMap().put(KeyActions.P1DOWN, KeyEvent.VK_S);
		getKeyMap().put(KeyActions.P1RIGHT, KeyEvent.VK_D);
		getKeyMap().put(KeyActions.P1UP, KeyEvent.VK_W);
		getKeyMap().put(KeyActions.P1SHOOTLEFT, KeyEvent.VK_J);
		getKeyMap().put(KeyActions.P1SHOOTRIGHT, KeyEvent.VK_K);
		
		getKeyMap().put(KeyActions.P2LEFT, KeyEvent.VK_LEFT);
		getKeyMap().put(KeyActions.P2DOWN, KeyEvent.VK_DOWN);
		getKeyMap().put(KeyActions.P2RIGHT, KeyEvent.VK_RIGHT);
		getKeyMap().put(KeyActions.P2UP, KeyEvent.VK_UP);
		getKeyMap().put(KeyActions.P2SHOOTLEFT, KeyEvent.VK_NUMPAD2);
		getKeyMap().put(KeyActions.P2SHOOTRIGHT, KeyEvent.VK_NUMPAD3);
	}
	
	public void addKeyValue(KeyActions action, int keyCode) {
		getKeyMap().put(action, keyCode);
	}
	public Map<KeyActions, Integer> getKeyMap() {
		return keyMap;
	}
	public void setKeyMap(Map<KeyActions, Integer> keyMap) {
		this.keyMap = keyMap;
	}
	
	
	
}
