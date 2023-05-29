package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the Ray class.
 *
 * @author Tzofiya David 20991874
 * and Shira ben shimol 326065976
 */
class RayTest {
    /**
     * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
     */
    @Test
    void findClosestPoint() {
        // Create a new Ray object with the specified parameters
        Ray ray = new Ray(new Point(0,1,0), new Vector(0,-1,-2));
        // ============ Equivalence Partitions Tests ==============

        // TC01:Find the closest point from a list of points
        List<Point> list1 = new ArrayList<>();
        list1.add(new Point(0, -0.68, 3.36));
        list1.add(new Point(0, 0, 2));
        list1.add(new Point(0, -1.25, 4.51));
        assertEquals(new Point(0, 0, 2), ray.findClosestPoint(list1),
                "ERROR! Wrong point");

        // ============ Boundary Values Tests ==============

        // TC11: Find the closest point when the collection is null
        assertNull(new Ray(new Point(1, 2, 3), new Vector(1, 0, 0)).findClosestPoint(null),
                "ERROR! There is no closest point");

        // TC12: Find the closest point from a list of points (boundary case)
        List<Point> list2 = new ArrayList<>();
        list2.add(new Point(0, 0, 2));
        list2.add(new Point(0, -0.68, 3.36));
        list2.add(new Point(0, -1.25, 4.51));
        assertEquals(new Point(0, 0, 2), ray.findClosestPoint(list2),
                "ERROR! Wrong point");

        // TC13: Find  the closest point from a list of points (boundary case)
        List<Point> list3 = new ArrayList<>();
        list3.add(new Point(0, -0.68, 3.36));
        list3.add(new Point(0, -1.25, 4.51));
        list3.add(new Point(0, 0, 2));
        assertEquals(new Point(0, 0, 2), ray.findClosestPoint(list3),
                "ERROR! Wrong point");
        }


}
