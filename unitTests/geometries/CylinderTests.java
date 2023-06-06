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

        // TC01: Testt that the normal of a point on the side of the cylinder is correct
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
    /*
    @Test
    void TestGetNormal() {
        Cylinder cyl = new Cylinder(new Ray(new Point(0, 0, 1), new Vector(0, 1, 0)), 1d, 1d);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point at a side of the cylinder
        assertEquals(new Vector(0, 0, 1), cyl.getNormal(new Point(0, 0.5, 2)), "Bad normal to cylinder");

        // TC02: Point at a 1st base of the cylinder
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 0, 1.5)), "Bad normal to lower base of cylinder");

        // TC03: Point at a 2nd base of the cylinder
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 1, 0.5)), "Bad normal to upper base of cylinder");

        // =============== Boundary Values Tests ==================
        // TC11: Point at the center of 1st base
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 0, 1)), "Bad normal to center of lower base");

        // TC12: Point at the center of 2nd base
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 1, 1)), "Bad normal to center of upper base");

        // TC13: Point at the edge with 1st base
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 0, 2)), "Bad normal to edge with lower base");

        // TC14: Point at the edge with 2nd base
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 1, 2)), "Bad normal to edge with upper base");
    }*/
}