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
    void testFindIntsersections(){
        Plane plane = new Plane(new Point(-1,0,1), new Point(0,1,1), new Point(1,0,1));
        // ============ Equivalence Partitions Tests ==============
        // The Ray can't be orthogonal nor parallel to the plane
        // TC01 the ray intersects the plane
        List<Point> result1 = plane.findIntsersections(new Ray(new Point(2, 0, 0),
                new Vector(-2, 0, 2)));
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(new Point(1, 0,1), result1.get(0),"Ray not orthogonal nor parallel to the plane and intersect it");
        // TC02 the ray does not intersect the plane
        assertNull(plane.findIntsersections(new Ray(new Point(1, 0, 2),
                new Vector(1, 0, 1))),"Ray Ray is neither orthogonal nor parallel to the plane and doesn't intersect it");


        // =============== Boundary Values Tests ==================
        // **** Group: The ray is parallel to the plane
        // TC11 the ray is included in the plane
        assertNull(plane.findIntsersections(new Ray(new Point(1, 0, 2),
                new Vector(-2, 0, 0))),"Ray parallel to the plane and not included in it");

        // TC12 the ray is not included in the plane
        assertNull(plane.findIntsersections(new Ray(new Point(-2, 0, 1),
                new Vector(4, 1, 0))),"Ray parallel to the plane and included in it");


        // **** Group: The ray is orthogonal to the plane
        // TC13 the ray starts before the plane
        List<Point> result13 = plane.findIntsersections(new Ray(new Point(1, 0, 0),
                new Vector(0, 0, 2)));
        assertEquals(1, result13.size(), "Wrong number of points");
        assertEquals(new Point(1, 0,1), result13.get(0),"Ray starts before the plane");

        // TC14 the ray starts in the plane (1 point)
        assertNull(plane.findIntsersections(new Ray(new Point(2, 0, 1),
                new Vector(0,0,1))),"Ray starts in the plane");

        // TC15 the ray starts after the plane
        assertNull(plane.findIntsersections(new Ray(new Point(2, 0, 2),
                new Vector(0,0,1))),"Ray starts after the plane");


        // **** Group: Ray is neither orthogonal nor parallel to the plane
        // TC16 the ray begins at the plane (p0 is in the plane)
        assertNull(plane.findIntsersections(new Ray(new Point(0, 0, 1),
                        new Vector(2,0,2))),
                "Ray is neither orthogonal nor parallel to the plane and starts at the plane");

        // TC17 the ray begins in the same point which appears as reference point in the plane
        assertNull(plane.findIntsersections(new Ray(new Point(1, 0, 1),
                        new Vector(1,0,2))),
                "Ray is neither orthogonal nor parallel to the plane and starts at a reference point of the plane");

    }
}