import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCircle {

	@Test
	void testCircle() {

		Circle c1 = new Circle(1.2);

		// Constructor and getter
		assertEquals(c1.getRadius(), 1.2);

		// setter
		c1.setRadius(2.3);
		assertEquals(c1.getRadius(), 2.3);
	}

	@Test
	void testPerimeter() {
		Circle c1 = new Circle(2.3);

		// check perimeter()
		assertEquals(c1.perimeter(), 14.45, 0.01, "incorrect perimeter() result");
	}

	@Test
	void testArea() {
		Circle c1 = new Circle(2.3);


		// check area()
		assertEquals(c1.area(), 16.61, 0.01, "incorrect area() result");
	}

}