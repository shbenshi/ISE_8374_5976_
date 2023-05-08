package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Planes
 *
 * @author Dan
 */

class PlaneTests {
    /**
     * This test verifies the correctness of the Plane.getNormal() method.
     * It tests whether the method returns the correct normal vector of the plane.
     *
     * @throws AssertionError if the test fails
     */
    @Test
    void testGetNormal() {
        Plane P1 = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0,1,0));
        double d = Math.sqrt(1d / 3);
        // TC01: Test that the result of getNormal() is correct
        assertEquals(new Vector(d, d, d), P1.getNormal(), "GetNormal(Point) wrong result ");
    }

    /**
     * This test verifies the correctness of the Polygon constructor.
     * It tests whether the constructor creates a valid polygon with given points.
     *
     * @throws AssertionError if the test fails
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon" + e.getMessage());
        }
    }
}