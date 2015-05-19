package es.ull.etsii.pai.practicafinal.secretgame;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;


public class Test_Raqueta {
	private Raqueta player1;
		
	@Before
	public void before() {
		player1 = new Raqueta(1200, 700, 1);
		
	}
	
	@Test 
	public void variables() {
		assertEquals("Variable velocidad de la raqueta = 15", player1.getVelocidadRaqueta(), 15);
	}
	
	@Test 
	public void moverArriba() {
		int player1yAnterior = player1.getY();
		player1.mover(true, 700);
		assertEquals("Comprobar que sube la raqueta su posición con v = 15", player1.getY(), player1yAnterior-15); 
	}
	
	@Test 
	public void moverAbajo() {
		int player1yAnterior = player1.getY();
		player1.mover(false, 700);
		assertEquals("Comprobar que baja la raqueta su posición con v = 15", player1.getY(), player1yAnterior+15); 
	}

}
