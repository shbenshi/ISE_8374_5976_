package geometries;
import primitives.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**

 The TriangleTests class tests the functionalities of the Triangle class.
 Tests include the calculation of the normal to the triangle.
 @author tzofiya and shira
 */


class TriangleTests
{
    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     * Checks if the normal to the triangle is calculated correctly.
     */
    @Test
    void GetNormal()
    {
        Triangle T1 = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double  p =  Math.sqrt(1d / 3);
        assertEquals(new Vector(p, p, p), T1.getNormal(new Point(1, 0, 0)),
                "GetNormal() of triangle wrong result - bad normal");
    }
}