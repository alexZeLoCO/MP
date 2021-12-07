import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPoint {

	@Test
	void testPoint() {
		
		Point p1 = new Point(1, 2);

		// Constructor and getters
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 2);

		// setters
		p1.setX(3);
		p1.setY(4);
		assertEquals(p1.getX(), 3);
		assertEquals(p1.getY(), 4);
	}

	@Test
	void testDistance() {
		Point p1 = new Point(3, 4);
		Point p2 = new Point(5, 6);

		// check distance()
		assertEquals(Point.distance(p1, p2), 2.82/*84271247461903*/, 0.01, "incorrect distance() result");
	}

	@Test
	void testToString() {
		Point p1 = new Point(3, 4);
		// check toString()
		assertFalse(p1.toString().contains("Point@"), "not (re)defined Point.toString() ");
	}
}