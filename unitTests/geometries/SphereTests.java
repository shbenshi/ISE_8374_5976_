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
        Point point = new Point(0, 0, 2);
        Vector n1 = new Vector(0, 0, 1); // normal
        Vector n2 = new Vector(0, 0, -1); // normal
        Vector sNormal = sp.getNormal(point);
        assertTrue(
                sNormal.equals(n1) || sNormal.equals(n2),
        "Bad normal for sphere");
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