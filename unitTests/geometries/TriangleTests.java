package geometries;
import primitives.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**

 The TriangleTests class tests the functionalities of the Triangle class.
 Tests include the calculation of the normal to the triangle.
 @author tzofiya and shira
 */


class TriangleTests
{
    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     * Checks if the normal to the triangle is calculated correctly.
     */
    @Test
    void GetNormal()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the normal to a triangle is properly calculated
        Triangle t = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        Vector expectedNormal = new Vector(Math.sqrt(1d / 3), Math.sqrt(1d / 3), Math.sqrt(1d / 3));
        assertEquals(expectedNormal, t.getNormal(new Point(1, 0, 0)), "Triangle's normal is wrong");
        // ensure the result is a unit vector
        assertEquals(1, t.getNormal(new Point(1, 0, 0)).length(), 0.00000001, "Triangle's normal is not a unit vector");

    }
}