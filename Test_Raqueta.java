package TestGame;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Game.PongJFrame;
import Game.Raqueta;

public class Test_Raqueta {
	private PongJFrame pong;
	private Raqueta player1;
		
	@Before
	public void before() {
		pong = new PongJFrame();
		player1 = new Raqueta(pong.getWidth(), pong.getHeight(), 1);
		
	}
	
	@Test 
	public void variables() {
		assertEquals("Comprobar que la velocidad de la raqueta es 15", player1.getVelocidadRaqueta(), 15);
	}
	
	@Test 
	public void moverArriba() {
		int player1yAnterior = player1.getY();
		player1.mover(true, pong.getHeight());
		assertEquals("Comprobar que sube la raqueta su posición con v = 15", player1.getY(), player1yAnterior-15); 
	}
	
	@Test 
	public void moverAbajo() {
		int player1yAnterior = player1.getY();
		player1.mover(false, pong.getHeight());
		assertEquals("Comprobar que baja la raqueta su posición con v = 15", player1.getY(), player1yAnterior+15); 
	}

}
