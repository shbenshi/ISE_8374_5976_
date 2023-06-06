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
    void testFindIntersections() {
        Point p1=new Point(0,2,-1);
        Point p2=new Point(4,2,0);
        Point p3=new Point(2,2,2);
        Triangle triangle=new Triangle(p1,p2,p3);
        Ray ray;

        // ============ Equivalence Partitions Tests ==============
        // TC01: intersection point in the triangle(1 point)
        ray = new Ray(new Point(3,1,0), new Vector(-1,1,1));
        List<Point> expected = List.of(new Point(2,2,1));
        assertEquals(expected, triangle.findIntsersections(ray),"wrong point for regular ray that crosses the plane inside the triangle");
        // TC02: intersection point out of the triangle in front of one of the edges(0 points)
        ray = new Ray(new Point(3,1,0), new Vector(0,1,2));
        assertNull(triangle.findIntsersections(ray),"intersection point out of the triangle in front of one of the edges shouldn't returned value");
        // TC03: intersection point out of the triangle in front of one of the vertices(0 points)
        ray = new Ray(new Point(3,1,0), new Vector(-1,1,3));
        assertNull(triangle.findIntsersections(ray),"intersection point on one of the vertices shouldn't returned value");




        // =============== Boundary Values Tests ==================
        // TC11: intersection point on one of the edges(0 points)
        ray = new Ray(new Point(3,1,0), new Vector(0,1,1));
        assertNull(triangle.findIntsersections(ray),"intersection point on one of the edges shouldn't returned value");
        // TC12: intersection point on one of the vertices(0 points)
        ray = new Ray(new Point(3,1,0), new Vector(1,1,0));
        assertNull(triangle.findIntsersections(ray),"intersection point on one of the vertices shouldn't returned value");
        // TC13: intersection point on the continuation of one of the edges(0 points)
        ray = new Ray(new Point(3,1,0), new Vector(-3,1,4));
        assertNull(triangle.findIntsersections(ray),"intersection point on the continuation of one of the edges shouldn't returned value");
    }

}
/*        Triangle T1 = new Triangle(new Point(1,1,0), new Point(0,1,0), new Point(1, 0, 0));
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

    }*/