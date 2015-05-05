package es.ull.etsii.pai.prct9;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyManager implements KeyListener {
public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode){
		case (KeyEvent.VK_UP):
			GameControl.getInstance().moveMainchar(Actor.UP_DIR);
		break;
		case (KeyEvent.VK_LEFT):
			GameControl.getInstance().moveMainchar(Actor.LEFT_DIR);
		break;
		case (KeyEvent.VK_RIGHT):
			GameControl.getInstance().moveMainchar(Actor.RIGHT_DIR);
		break;
		case (KeyEvent.VK_DOWN):
			GameControl.getInstance().moveMainchar(Actor.DOWN_DIR);
		break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
