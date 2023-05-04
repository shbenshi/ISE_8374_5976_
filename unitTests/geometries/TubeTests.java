package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;

class TubeTests {

    @Test
    void testGetNormal()
    {
        Tube T1 = new Tube(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 2d);
        // ============ Equivalence Partitions Tests ==============

        Point P1 = new Point(1, 0, 2);
        Vector n1 = new Vector(1, 0, 0);
        Vector n2 = new Vector(-1, 0, 0);
        Vector Tn1 = T1.getNormal(P1);
        assertTrue(
                T1.equals(n1) || T1.equals(n2),
                "calculate tube does not work"
        );

        // =============== Boundary Values Tests ==================
        // When vector resulting from subtraction of point and p0 is orthogonal to axisRay vector
        Point Bp = new Point(1, 0, 0); // boundary Normal
        Vector BTN = T1.getNormal(Bp); // boundary Tube Normal
        assertTrue(
                BTN.equals(n1) || BTN.equals(n2),
                "BVA for tube does not work correctly"
        );
    }
}