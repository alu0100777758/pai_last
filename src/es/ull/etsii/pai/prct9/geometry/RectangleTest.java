package es.ull.etsii.pai.prct9.geometry;

import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void test() {
		Rectangle rect1 = new Rectangle(new Point2D(0,0), new Point2D(50,70));
		assertTrue("guarda correctamente la informacion", rect1.getStart().equals(new Point2D(0,0)) && rect1.getEnd().equals(new Point2D(50,70)));
		assertTrue("Devuelve el segmento superior",rect1.getTop_segment().equals(new Segment(new Point2D(0,0), new Point2D(50,0))));
		assertTrue("Devuelve el segmento inferior",rect1.getBottom_segment().equals(new Segment(new Point2D(0,70), new Point2D(50,70))));
		assertTrue("Devuelve el segmento izquierdo",rect1.getLeft_segment().equals(new Segment(new Point2D(0,0), new Point2D(0,70))));
		assertTrue("Devuelve el segmento derecho",rect1.getRight_segment().equals(new Segment(new Point2D(50,0), new Point2D(50,70))));
		rect1.setWidth(100);
		rect1.setHeight(50);
		assertTrue("Se redimensiona correctamente", rect1.getStart().equals(new Point2D(0,0)) && rect1.getEnd().equals(new Point2D(100,50)));
	}

}
