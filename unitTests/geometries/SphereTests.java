package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

    @Test
    void testGetNormal()
    {
        // Equivalence Partitions tests ======================================================================
        // EP01 get normal of point on sphere
        Point center = new Point(0, 0, 0);
        Sphere sp = new Sphere(center, 2d);
        Point point = new Point(2, 0, 0);
        Vector n = new Vector(1, 0, 0); // normal
        Vector n2 = new Vector(-1, 0, 0); // normal
        //Vector sNormal = sp.getNormal(point);
        assertTrue(n.equals(sp.getNormal(point)),"Bad normal for sphere"); //|| sNormal.equals(n2),

    }

    @Test
    void testGetCenter()
    {
    }

    @Test
    void testGetRadius()
    {
    }
}