package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TubeTests {

    @Test
    void testGetNormal()
    {
        // ============ Equivalence Partitions Tests ==============
        Tube t = new Tube(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1);
        Point p0 = new Point(0, 1, 2);
        //Vector n1 = new Vector(1, 0, 0);
        //Vector n2 = new Vector(-1, 0, 0);
        Vector t_nrl = new Vector(0,1,0);//t.getNormal(p0);
        assertTrue(t_nrl.equals(t.getNormal(p0)),"calculate tube does not work");

        assertTrue(t_nrl.equals(t.getNormal(new Point(0,1,0))),"calculate tube does not work"); //|| t.equals(n2), "calculate tube does not work"
    }
}

        // =============== Boundary Values Tests ==================
        // When vector resulting from subtraction of point and p0 is orthogonal to axisRay vector


