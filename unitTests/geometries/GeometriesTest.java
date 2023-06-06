package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import java.util.List;
import primitives.Vector;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {


    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Intersectable triangle = new Triangle(new Point(-1,-2,0), new Point(0,1,0), new Point(3,0,0));
        Intersectable sphere = new Sphere(new Point(0,1,1), 1);
        Intersectable plane = new Plane(new Point(0,0,2), new Point(0,1,2), new Point(1,0,2));
        Geometries objects = new Geometries(triangle, sphere,plane);
        // TC01 a few objects has intersections points but not all of them
        assertEquals(2, objects.findIntsersections(new Ray(
                        new Point(0,-1,-1), new Vector(0,0,5))).size(),
                "Ray intersect with not all of the objects");

        // =============== Boundary Values Tests ==================
        // TC11 empty objects collection
        assertNull(new Geometries().findIntsersections(new Ray(
                        new Point(0,0,4), new Vector(0,0,1))),
                "There is no objects for the ray to intersect with");

        // TC12 there is no intersection points with none of the objects
        assertNull(objects.findIntsersections(new Ray(
                        new Point(0,0,4), new Vector(0,0,1))),
                "Ray doesn't intersect with any of the objects");

        // TC13 there is intersection points with exactly one object
        assertEquals(1, objects.findIntsersections(new Ray(
                        new Point(0,0,1), new Vector(0,0,2))).size(),
                "Ray intersect with exactly one object");

        // TC14 there is intersection points with all the objects
        assertEquals(4, objects.findIntsersections(new Ray(
                        new Point(1,-1,-1), new Vector(-1,3,5))).size(),
                "Ray intersect with all of the objects");

    }
}