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
    void findIntersections() {
        Geometries geo = new Geometries(new Sphere(new Point(0,0,2),0.5),
                new Polygon(new Point( 1, 0, 0), new Point(0,  1, 0), new Point(-1, 0, 0), new Point(0, -1, 0)),
                new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)));
        // ============ Equivalence Partitions Tests ==============
        //TC01:A ray intersects with a few geometries
        List<Point> result = geo.findIntsersections(new Ray(new Point(-1,-1,-1),new Vector(2,2,2)));
        assertEquals(2, result.size(), "A few geometries intersects");
        // =============== Boundary Values Tests ==================
        //TC11:A ray intersects with all geometries
        result=geo.findIntsersections(new Ray(new Point(0.2,0.2,-0.6),new Vector(0,0,1)));
        assertEquals(4,result.size(),"All geometries intersects");
        //TC12:A ray intersects with only one geometry
        result=geo.findIntsersections(new Ray(new Point(0.2,0.2,0.2),new Vector(1,1,1)));
        assertEquals(1,result.size(),"Only 1 geometry intersect");
        //TC13: A ray does not intersect with any geometry
        assertNull(geo.findIntsersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))), "No geometries intersects");
        //TC14: An empty list of geometries, so no intersection is expected
        assertNull(new Geometries().findIntsersections(new Ray(new Point(1,2,3), new Vector(2,2,2))), "Empty list of geometries");

    }
}