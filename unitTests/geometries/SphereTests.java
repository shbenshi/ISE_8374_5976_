package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

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
    void findIntsersections() {
        Sphere s = new Sphere(new Point(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01:
        assertNull(s.findIntsersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p0 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p1 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> ans = s.findIntsersections(new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0)));
        assertNotNull(ans, "TC02 returned null");
        assertEquals(2, ans.size(), "TC02 Wrong number of points");
       assertTrue(List.of(p0, p1).equals(ans) || List.of(p1, p0).equals(ans), "TC02 Ray crosses sphere");

        // TC03:
        ans = s.findIntsersections(new Ray(new Point(1.5d, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, ans.size(), "Wrong number of points in TC03");
        assertEquals(List.of(new Point(2, 0, 0)),ans, "Wrong intersection point in TC03");

        // TC04:
        assertNull(s.findIntsersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))), "There should be no intersection in TC04");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11:
        ans = s.findIntsersections(new Ray(new Point(1d, -1, 0), new Vector(0.5, 1, 0)));
        assertEquals(1, ans.size(), "Wrong number of points in TC11");
        assertEquals(List.of(new Point(1.8, 0.6, 0)), ans, "Wrong intersection point in TC11");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(s.findIntsersections(new Ray(new Point(1, -1, 0), new Vector(-0.5, -1, 0))), "There should be no intersection in TC12");

        // **** Group: Ray's line goes through the center
        // TC13:
        ans = s.findIntsersections(new Ray(new Point(1d, -2, 0), new Vector(0, 1, 0)));
        assertEquals(2, ans.size(), "Wrong number of points in TC13");
        p0 = new Point(1, 1, 0);
        p1 = new Point(1, -1, 0);
        assertTrue(List.of(p0, p1).equals(ans) || List.of(p1, p0).equals(ans), "Wrong intersection points in TC13");

        // TC14:
        ans = s.findIntsersections(new Ray(new Point(1d, -1, 0), new Vector(0, 1, 0)));
        assertEquals(1, ans.size(), "Wrong number of points in TC14");
        assertEquals(List.of(new Point(1, 1, 0)), ans, "Wrong intersection point in TC14");

        // TC15:
        ans = s.findIntsersections(new Ray(new Point(0.25, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, ans.size(), "Wrong number of points in TC15");
        assertEquals(List.of(new Point(2, 0, 0)), ans, "Wrong intersection point in TC15");

        // TC16:
        ans = s.findIntsersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, ans.size(), "Wrong number of points in TC16");
        assertEquals(List.of(new Point(2, 0, 0)), ans, "Wrong intersection point in TC16");

        // TC17:
        assertNull(s.findIntsersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, 0))),"There should be no intersection in TC17");

        // TC18:
        assertNull(s.findIntsersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))), "There should be no intersection in TC18");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19:
        assertNull(s.findIntsersections(new Ray(new Point(0, 0, 1), new Vector(1, 0, 0))), "There should be no intersection in TC19");

        // TC20:
        assertNull(s.findIntsersections(new Ray(new Point(1, 0, 1), new Vector(1, 0, 0))), "There should be no intersection in TC20");

        // TC21:
        assertNull(s.findIntsersections(new Ray(new Point(2, 0, 1), new Vector(1, 0, 0))), "There should be no intersection in TC21");

        // **** Group: Special cases
        // TC22:
        assertNull(s.findIntsersections(new Ray(new Point(3, 0, 1), new Vector(0, 0, 1))), "There should be no intersection in TC22");

    }


}