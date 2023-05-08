package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
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
}