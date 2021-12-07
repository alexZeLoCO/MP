import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircleWithCentreTest {

	private CircleWithCentre c1;
	
	@BeforeEach
	void setUp() throws Exception {
		c1=new CircleWithCentre (2,5,3.6);
	}

	@Test
	void testPerimeter() {
		assertEquals(2*Math.PI * c1.getRadius(), c1.perimeter());
	}

	@Test
	void testArea() {
		assertEquals(Math.PI * c1.getRadius(), c1.area());
	}

	@Test
	void testOverlaps() {
		CircleWithCentre c2 = new CircleWithCentre (4,3,2.9);
		CircleWithCentre c3 = new CircleWithCentre(5,9,1.2);
		assertTrue(CircleWithCentre.overlaps(c1, c2));
		assertTrue(CircleWithCentre.overlaps(c1, c3));
	}

}
