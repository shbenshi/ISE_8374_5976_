package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Tubes
 *
 * @author shira and tzofiya
 */
class TubeTests {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============

        // EP01: Test get normal to point on the tube's surface
        Tube t = new Tube(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1);
        Point p0 = new Point(0, 1, 2);
        Vector t_nrl = new Vector(0,1,0); // The expected normal vector at p0 on the tube's surface
        assertTrue(t_nrl.equals(t.getNormal(p0)), "Bad normal to tube surface point");

        // EP02: Test get normal to point on the tube's surface
        Point p1 = new Point(0, 1, 0);
        assertTrue(t_nrl.equals(t.getNormal(p1)), "Bad normal to tube surface point");
    }


    @Test
    void testFindIntersections() {
        Tube t = new Tube(new Ray(new Point(0,0,1), new Vector(0,0,1)),1);

        // ============ Equivalence Partitions Tests ==============

        // *** Group: Ray starts on the outside, crosses the tube and is not orthogonal
        // TC01:
        List<Point> ans = t.findIntsersections(new Ray(new Point(0, -2, 0), new Vector(2, 4, 2)));
        assertEquals( 2, ans.size(), "Wrong number of points in TC01");
        Point p0 = new Point(0.6, -0.8, 0.6);
        Point p1 = new Point(1, 0, 1);
        assertTrue(List.of(p0, p1).equals(ans) || List.of(p1, p0).equals(ans), "Wrong intersection points in TC01");

        // *** Group: Ray starts on the outside, does not cross the tube and is not orthogonal
        // TC02:
        assertNull(t.findIntsersections(new Ray(new Point(0, -2, 0), new Vector(-2, -4, -2))), "There should be no intersection in TC02");

        // TC03:
        ans = t.findIntsersections(new Ray(new Point(0, -0.5, 0), new Vector(0, -1.5, 2)));
        assertEquals(1, ans.size(), "Wrong number of points in TC03");
        assertEquals(new Point(0, -1, 0.666666666666666666666), ans.get(0), "Wrong intersection point in TC03");

        // =============== Boundary Values Tests ==================


        // *** Group: Ray starts on the tube (not orthogonal)
        // TC11:
        assertNull(t.findIntsersections(new Ray(new Point(0, -1, 0), new Vector(0, -1, 2))), "There should be no intersection in TC04");

        // TC12:
        ans = t.findIntsersections(new Ray(new Point(0, -1, 0), new Vector(0, 2, -4)));
        assertEquals(1, ans.size(), "Wrong number of points in TC05");
        assertEquals(new Point(0, 1, -4), ans.get(0), "Wrong intersection point in TC05");

        // *** Group: Ray is parallel
        // TC13:
        assertNull(t.findIntsersections(new Ray(new Point(0, 0.5, 0), new Vector(0, 0, 1))), "There should be no intersection in TC06");

        // TC14:
        assertNull(t.findIntsersections(new Ray(new Point(0, 1, 0), new Vector(0, 0, 1))), "There should be no intersection in TC07");

        // TC15:
        assertNull(t.findIntsersections(new Ray(new Point(0, 2, 0), new Vector(0, 0, 1))), "There should be no intersection in TC08");

        // *** Group: Ray is orthogonal to tube vector
        // TC16:
        ans = t.findIntsersections(new Ray(new Point(2, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(2, ans.size(), "Wrong number of points in TC16");
        p0 = new Point(1, 0, 0);
        p1 = new Point(-1, 0, 0);
        assertTrue(List.of(p0, p1).equals(ans) || List.of(p1, p0).equals(ans), "Wrong intersection points in TC16");

        // TC18:
        assertNull(t.findIntsersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))), "There should be no intersection in TC18");

        // TC19:
        ans = t.findIntsersections(new Ray(new Point(1, 0, 0), new Vector(-1, 0, 0)));
        assertEquals(1, ans.size(), "Wrong number of points in TC19");
        assertEquals(new Point(-1, 0, 0), ans.get(0), "Wrong intersection point in TC19");

        // TC20:
        assertNull(t.findIntsersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0))), "There should be no intersection in TC20");

        // *** Group: Ray starts on tube's Ray
        // TC21:
        ans = t.findIntsersections(new Ray(new Point(0, 0, 1), new Vector(0, -1, -1)));
        assertEquals(1, ans.size(), "Wrong number of points in TC21");
        assertEquals(new Point(0, -1, 0), ans.get(0), "Wrong intersection point in TC21");

        // TC22:
        ans = t.findIntsersections(new Ray(new Point(0, 0, 1), new Vector(0, 1, 0)));
        assertEquals(1, ans.size(), "Wrong number of points in TC22");
        assertEquals(new Point(0, 1, 1), ans.get(0), "Wrong intersection point in TC22");

        // TC23:
        assertNull(t.findIntsersections(new Ray(new Point(0, 0, 1), new Vector(0, 0, 1))), "There should be no intersection in TC23");

    }

    }





