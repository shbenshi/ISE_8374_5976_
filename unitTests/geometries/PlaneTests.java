package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;




import static org.junit.jupiter.api.Assertions.assertNull;



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

    @Test
    void findIntersections() {
        Plane p = new Plane((new Point(0,0,1)),new Point(1,0,0),new Point(0,1,0));
        // ============ Equivalence Partitions Tests ==============
        List<Point> i = p.findIntsersections(new Ray(new Point(2,1,0), new Vector(-2, -1, 1)));

        // EP01:
        assertEquals(i.size(), 1, "EP01 The count of intersection points is incorrect");
        assertEquals(i.get(0),new Point(0, 0, 1), "EP01 Incorrect intersection point");

        // EP02:
        assertNull(p.findIntsersections(new Ray(new Point(2, 1, 0), new Vector(2, 1, -1))), "EP01 No intersection - should return null");

        // ============ Boundary Values Tests ==============

        // BVA01:
        assertNull(p.findIntsersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, -1))), "Ray is included - should return null");

        // BVA02:
        assertNull(p.findIntsersections(new Ray(new Point(0, 0, 2), new Vector(1, 0, -1))), "Ray is parallel - no intersection, should return null");

        // BVA03:
        assertEquals(p.findIntsersections(new Ray(new Point(0, 0, 0), new Vector(1d / 3, 1d / 3, 1d / 3))).get(0), new Point(1d / 3, 1d / 3, 1d / 3), "Orthogonal ray with 1 intersection point does not work");

        // BVA04:
        assertNull(p.findIntsersections(new Ray(new Point(1d / 3, 1d / 3, 1d / 3), new Vector(1d / 3, 1d / 3, 1d / 3))), "Orthogonal ray that starts on does not work");

        // BVA05:
        assertNull(p.findIntsersections(new Ray(new Point(2, 2, 2), new Vector(1d / 3, 1d / 3, 1d / 3))), "Orthogonal ray with no intersection does not work");

        // BVA06:
        assertNull(p.findIntsersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 0))), "Not orthogonal ray that starts on plane does not work");

        // BVA07:
        assertNull(p.findIntsersections(new Ray(new Point(0, 0, 1), new Vector(1, 0, 0))), "Not orthogonal ray that starts on reference point does not work");
    }
}