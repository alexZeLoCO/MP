import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCircleWithCentre {

	@Test
	void testCircleWithCentre() {

		CircleWithCentre c1 = new CircleWithCentre(1, 2, 3.4);

		// Constructor and getter
		assertEquals(c1.getRadius(), 3.4);
		assertEquals(c1.getCentre().getX(), 1);
		assertEquals(c1.getCentre().getY(), 2);

		// setters
		c1.setRadius(2.3);
		assertEquals(c1.getRadius(), 2.3);

		c1.setCentre(new Point(4, 5));
		assertEquals(c1.getCentre().getX(), 4);
		assertEquals(c1.getCentre().getY(), 5);

		c1.setCentre(1, 2);
		assertEquals(c1.getCentre().getX(), 1);
		assertEquals(c1.getCentre().getY(), 2);
	}

	@Test
	void testPerimeter() {
		CircleWithCentre c1 = new CircleWithCentre(1, 2, 2.3);

		// check perimeter()
		assertEquals(c1.perimeter(), 14.45, 0.01, "incorrect perimeter() result");
	}

	@Test
	void testArea() {
		CircleWithCentre c1 = new CircleWithCentre(1, 2, 2.3);


		// check area()
		assertEquals(c1.area(), 16.61, 0.01, "incorrect area() result");
	}

	@Test
	void testOverlaps() {
		CircleWithCentre c1 = new CircleWithCentre(1, 1, 10);
		CircleWithCentre c2 = new CircleWithCentre(20, 1, 10);
		CircleWithCentre c3 = new CircleWithCentre(20, 1, 8.9);

		// check overlaps()
		assertTrue(CircleWithCentre.overlaps(c1, c2));
		assertFalse(CircleWithCentre.overlaps(c1, c3));
	}

	@Test
	void testToString() {
		CircleWithCentre c1 = new CircleWithCentre(1, 2, 3.4);
		// check toString()
		assertFalse(c1.toString().contains("CircleWithCentre@"), "not (re)defined CircleWithCentre.toString() ");
	}


}