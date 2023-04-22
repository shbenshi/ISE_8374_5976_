package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {
    @Test
    void testGetNormal()
    {
        /**
         * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
         */
        Plane P1 = new Plane(new Point(0, 0, 0), new Point(0, 5, 0), new Point(5,0,0));
        Vector normal = new Vector(0, 0, 1);

        assertTrue(normal.equals(P1.getNormal(new Point(1, 1, 0))) ||
                normal.equals(P1.getNormal(new Point(-1, -1, 0))),"The plane does not work as needed");
    }
    @Test
    public void testConstructor() {
    {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try
        {
            new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        } catch (IllegalArgumentException e)
        {
            fail("Failed constructing a correct polygon" + e.getMessage());
        }
    }
    @Test
    void testGetQ0()
    {

    }
}