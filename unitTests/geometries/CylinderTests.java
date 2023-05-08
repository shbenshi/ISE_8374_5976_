package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 Unit tests for the Cylinder class.
 Tests the methods and functionality of the Cylinder class, a subclass of Tube.
 @author Tzofiya David and Shira Ben Shimol
 */
class CylinderTests {
    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Vector dir = new Vector(0, 0, 1);
        Vector normal1 = new Vector(0, 1, 0);
        Point p0 = new Point(0, 1, 2);
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(1, 0, 10);
        Cylinder C1 = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1, 10);

        // TC01: Test that the normal of a point on the side of the cylinder is correct
        assertTrue(normal1.equals(C1.getNormal(p0)), "TC01: Failed to get the correct normal of a point on the side of the cylinder.");

        // ============ Equivalence Partitions Tests ==============
        // TC02: Test that the normal of a point outside the cylinder is correct
        assertTrue((dir.scale(-1)).equals(C1.getNormal(p1)), "TC02: Failed to get the correct normal of a point outside the cylinder.");
        // TC03: Test that the normal of a point inside the cylinder is correct
        assertTrue(dir.equals(C1.getNormal(p2)), "TC03: Failed to get the correct normal of a point inside the cylinder.");

        // =============== Boundary Values Tests ==================
        // TC04: Test that the normal at the bottom base center is correct
        assertTrue((dir.scale(-1)).equals(C1.getNormal(new Point(0, 0, 0))), "TC04: Failed to get the correct normal at the bottom base center.");
        // TC05: Test that the normal at the top base center is correct
        assertTrue((dir).equals(C1.getNormal(new Point(0, 0, 10))), "TC05: Failed to get the correct normal at the top base center.");
    }
}