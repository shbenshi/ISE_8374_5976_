package geometries;
import primitives.*;

import org.junit.jupiter.api.Test;
import java.util.List;
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

    @Test
    void testFindIntersections()
    {
        Triangle T1 = new Triangle(new Point(1,1,0), new Point(0,1,0), new Point(1, 0, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: ray start outside triangle and cross.
        Point p1 = new Point(0.5,0.6,0);
        List<Point> ans = T1.findIntsersections(new Ray(new Point(1, 1, 1), new Vector(-0.5, -0.4, -1)));
        assertEquals(1, ans.size(), "num of points wrong");
        assertEquals(List.of(p1), ans, "ray cross");

        // TC02: ray start outside triangle and not cross
        assertNull(T1.findIntsersections(new Ray(new Point(-0.4, -0.4, 0), new Vector(-0.6, -0.6, -1))),
                "ray not cross");

        // TC03: ray start outside triangle and not cross plane
        assertNull(T1.findIntsersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))),
                "ray not cross plane");

        //TC04: ray start outside triangle
        assertNull(T1.findIntsersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, -1))),
                "ray start and finish outside");


        // =============== Boundary Values Tests ==================
        //TC05: start outside triangle and end in side
        assertNull(T1.findIntsersections(new Ray(new Point(1,1,1), new Vector(0, -0.5, -1))),
                "ray start opposite vertex");
        //TC06: ray start outside triangle and goes vertex
        assertNull(T1.findIntsersections(new Ray(new Point(1,1,1), new Vector(0, -1, -1))),
                "start opposite the vertex");
        //TC07: ray start outside triangle
        assertNull(T1.findIntsersections(new Ray(new Point(1,1,1), new Vector(0, 1, -1))),
                "start opposite the vertex");
        //TC08: ray start at triangle change to inside
        assertNull(T1.findIntsersections(new Ray(new Point(0.5,0.6,0), new Vector(0, 1, 0))),
                "start opposite the vertex");
        //TC09: ray start at triangle change outside
        assertNull(T1.findIntsersections(new Ray(new Point(0.5,0.6,0), new Vector(1, 1, 1))),
                "ray start opposite the vertex");

    }

}