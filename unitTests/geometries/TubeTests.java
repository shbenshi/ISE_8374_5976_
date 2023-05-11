package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


    }

    }



