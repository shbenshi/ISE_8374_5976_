package geometries;
import primitives.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTests
{
    @Test
    void GetNormal()
    {
        Triangle T1 = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double  p =  Math.sqrt(1d / 3);
        assertEquals(new Vector(p, p, p), T1.getNormal(new Point(1, 0, 0)),
                "GetNormal() of triangle wrong result - bad normal");

        //assertTrue(normal.equals(triangle.getNormal(new Point(1, 1, 0))) ||
                //normal.equals(triangle.getNormal(new Point(-1, -1, 0))),"Triangle normal does not work");
    }
}