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
    public void FindIntersectionsest() {
        Sphere sphere = new Sphere( new Point(1, 0, 0), 1d);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntsersections(new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0)
        ));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere (1 point)
        Point p3 = new Point(1.88674969975976, 0.46224989991992, 0);
        List<Point> result2 = sphere.findIntsersections(new Ray(new Point(0.5, 0, 0), new Vector(3, 1, 0)
        ));
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(List.of(p3), result2, "Ray crosses sphere on 1 point");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(2.5, 0, 0), new Vector(3, 1, 0))),
                "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere (touches it) and goes inside (1 points)
        //Problem with the function
        Point p4 = new Point(1.697674418604651, 0.697674418604651, 0.162790697674419);
        assertEquals(List.of(p4), sphere.findIntsersections(new Ray(new Point(1, 0, -1), new Vector(3, 3, 5)
        )), "Ray intersection has 1 point");


        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(2, 0, 0), new Vector(3, 3, 5))),
                "Ray's line out of sphere");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        Point p5 = new Point(0.422649730810374, -0.577350269189626, -0.577350269189626);
        Point p6 = new Point(1.577350269189626, 0.577350269189626, 0.577350269189626);
        List<Point> result3 = sphere.findIntsersections(new Ray(new Point(0, -1, -1), new Vector(1, 1, 1)
        ));
        assertEquals(2, result3.size(), "Wrong number of points");
        if (result3.get(0).getX() > result3.get(1).getX())
            result3 = List.of(result3.get(1), result3.get(0));
        assertEquals(List.of(p5, p6), result3, "Ray crosses sphere");

        // TC14: Ray starts at sphere and goes inside (1 points)
        Point p7 = new Point(0, 0, 0);
        List<Point> resultC2 = sphere.findIntsersections(new Ray(new Point(2, 0, 0), new Vector(-1, 0, 0)
        ));
        assertEquals(1, resultC2.size(), "Wrong number of points");
        assertEquals(List.of(p7), resultC2, "Ray crosses sphere on 1 point");

        // TC15: Ray starts inside (1 points)
        Point p8 = new Point(1.577350269189626, -0.577350269189626, -0.577350269189626);
        List<Point> resultC3 = sphere.findIntsersections(new Ray(new Point(0.5, 0.5, 0.5), new Vector(1, -1, -1)
        ));
        assertEquals(1, resultC3.size(), "Wrong number of points");
        assertEquals(List.of(p8), resultC3, "Ray crosses sphere on 1 point");

        // TC16: Ray starts at the center (1 points)
        Point p9 = new Point(1.047511433814558, 0.997740110105718, 0.04751143381455799);
        List<Point> resultC4 = sphere.findIntsersections(new Ray(new Point(1, 0, 0), new Vector(1, 21, 1)
        ));
        assertEquals(1, resultC4.size(), "Wrong number of points");
        assertEquals(List.of(p9), resultC4, "Ray crosses sphere on 1 point");


        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(4, 0, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Point(2, 0, -3), new Vector(0, 0, 1))),
                "Ray's line out of sphere");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Point(2, 0, 0), new Vector(0, 0, 1))),
                "Ray's line out of sphere");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Point(2, 0, 3), new Vector(0, 0, 1))),
                "Ray's line out of sphere");

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        Point p_o = new Point(3, 1, 1);
        Vector cToP = p_o.subtract(sphere.getCenter());// (0,1,0) is orthogonal.
        assertNull(sphere.findIntsersections(new Ray(p_o, new Vector(0, 1, 0))),
                "Ray's line out of sphere");
    }
}