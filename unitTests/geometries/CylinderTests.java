package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 Unit tests for the Cylinder class.
 Tests the methods and functionality of the Cylinder class, a subclass of Tube.
 @author Tzofiya David and Shira Ben Shimol
 */
class CylinderTests {
    /**
     Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {

        Vector dir = new Vector(0, 0, 1);
        Vector normal1 = new Vector(0, 1, 0);
        Point p0 = new Point(0, 1, 2);
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(1, 0, 10);
        Cylinder C1 = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1, 10);
        assertTrue(normal1.equals(C1.getNormal(p0)), "get normal of cylinder doesn't work properly");
        // ============ Equivalence Partitions Tests ==============
        assertTrue((dir.scale(-1)).equals(C1.getNormal(p1)), "get normal of cylinder doesn't work properly");
        assertTrue(dir.equals(C1.getNormal(p2)), "get normal of cylinder doesn't work properly");

        // =============== Boundary Values Tests ==================
        assertTrue((dir.scale(-1)).equals(C1.getNormal(new Point(0, 0, 0))), "the point is on bases center");
        assertTrue((dir).equals(C1.getNormal(new Point(0, 0, 10))), "the point is on bases center");
    }
}