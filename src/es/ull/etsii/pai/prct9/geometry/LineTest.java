package es.ull.etsii.pai.prct9.geometry;

import static org.junit.Assert.*;

import org.junit.Test;

import es.ull.etsii.pai.prct9.geometry.Line;

public class LineTest {

	@Test
	public void detectaPuntoEnLaRecta() {
		Line test1=new Line(1,0,1,2);
		assertTrue("columna",test1.belongs(1, 8));
		Line test4=new Line(3,2,1,2);
		assertTrue("fila",test4.belongs(1, 2));
		Line test2=new Line(2,0,3,1);
		assertTrue("diagonal",test2.belongs(5, 3));
		assertEquals("no pertenece", false, test2.belongs(2, 1));
		Line test3=new Line(2,1,3,3);
		assertTrue("Cualquier recta",test3.belongs(4,5));
		Line test6=new Line(3,3,2,1);
		assertTrue("Cualquier recta componente x mayor primero",test6.belongs(4,5));
		Line test7=new Line(4,0,3,1);
		assertTrue("Cualquier recta componente x mayor primero",test7.belongs(2,2));
	}

}
