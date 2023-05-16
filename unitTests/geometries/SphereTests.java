package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Testing Sphere
 *
 * @author Dan
 */
class SphereTests {
    /**
     * Test method for the {@link geometries.Sphere#getNormal(primitives.Point)} method.
     * This test checks if the getNormal method correctly calculates the normal vector of a point on the sphere.
     * Equivalence Partitions:
     * - EP01: A point on the sphere (i.e., the point is at the surface of the sphere).
     */
    /**
     * Tests the getNormal method of the Sphere class.
     *---------------delete----------------------
     * Test Method:
     * 1. Create a sphere with a center of (0,0,0) and a radius of 2.
     * 2. Create a point on the sphere at (2,0,0).
     * 3. Calculate the normal vector of the point.
     * 4. Verify that the calculated normal vector equals (1,0,0).
     */
    @Test
    void testGetNormal()
    {
        // Equivalence Partitions tests ======================================================================
        // EP01: A point on the sphere
        Point center = new Point(0, 0, 0);
        Sphere sp = new Sphere(center, 2d);
        Point point = new Point(2, 0, 0);
        Vector n = new Vector(1, 0, 0);
        assertTrue(n.equals(sp.getNormal(point)),"Bad normal for sphere");
    }
    /**
     * Test method for the {@link geometries.Sphere#getCenter()} method.
     * This test checks if the getCenter method correctly returns the center point of the sphere.
     */
    @Test
    void testGetCenter()
    {
        // TODO: implement test
    }

    /**
     * Test method for the {@link geometries.Sphere#getRadius()} method.
     * This test checks if the getRadius method correctly returns the radius of the sphere.
     */
    @Test
    void testGetRadius()
    {
        // TODO: implement test
    }

    @Test
    void testFindIntersections() {
        Sphere sphere = new Sphere(new Point (1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntsersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntsersections(new Ray(new Point(1.5, 0, 0),
                new Vector(1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(2,0,0), result.get(0), "the calculation is not correct");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntsersections(new Ray(new Point(1, 1, 0),
                new Vector(1, -1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(2,0,0), result.get(0), "the calculation is not correct");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(2, 0, 0), new Vector(1, 1, 1))),
                "Ray's line out of sphere");
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntsersections(new Ray(new Point(1, -3, 0),
                new Vector(0, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        p1 = new Point(1, -1, 0);
        p2 = new Point(1, 1, 0);
        //if (result.get(0).getX() > result.get(1).getX())
        //  result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntsersections(new Ray(new Point(1, -1, 0),
                new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(1,1,0), result.get(0), "the calculation is not correct");

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntsersections(new Ray(new Point(1, 0.5, 0),
                new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(1,1,0), result.get(0), "the calculation is not correct");

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntsersections(new Ray(new Point(1, 0, 0),
                new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(new Point(1,1,0), result.get(0), "the calculation is not correct");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(2.1, 0, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Point(2, -0.5, 0), new Vector(0, 1, 0))),
                "Ray's line out of sphere");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Point(2, 0, 0), new Vector(0, 1, 0))),
                "Ray's line out of sphere");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Point(2, 0.5, 0), new Vector(0, 1, 0))),
                "Ray's line out of sphere");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntsersections(new Ray(new Point(3, 0, 0), new Vector(0, 1, 0))),
                "Ray's line out of sphere");
    }
}