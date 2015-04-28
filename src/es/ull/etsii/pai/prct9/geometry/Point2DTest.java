package es.ull.etsii.pai.prct9.geometry;

import static org.junit.Assert.*;

import org.junit.Test;

public class Point2DTest {

	@Test
	public void test() {
		Point2D testpoint1 = new Point2D(0,0);
		Point2D testpoint2 = new Point2D(1,5);
		Point2D testpointSuma = testpoint1.add(testpoint2);
		Point2D testpointSumaDouble = testpointSuma.add(new Point2D(43.5,76.1));
		assertTrue("representa correctamente el (0,0)", testpoint1.x()==0 && testpoint1.y()==0);
		assertTrue("summa correctamente",testpointSuma.x()==1 && testpointSuma.y()==5);
		assertTrue("summa correctamente precision doble",testpointSumaDouble.x()==44.5 && testpointSumaDouble.y()==81.1);
		assertTrue("compara correctamente dos puntos iguales", testpoint1.equals(new Point2D(0,0)));
		assertFalse("compara correctamente dos puntos diferentes", testpoint1.equals(new Point2D(3,0)));
	}

}
