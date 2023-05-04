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
        Plane P1 = new Plane(new Point(0, 0, 1),
                            new Point(1, 0, 0),
                            new Point(0,1,0));
        double p = Math.sqrt(1d / 3);

        assertTrue(P1.getNormal().equals(new Vector(p, p, p)) ||
                P1.getNormal().scale(-1).equals(new Vector(p, p, p)) ||
                P1.getNormal().length() !=1, "");

        //assertEquals(new Vector(p, p, p), P1.getNormal(), "GetNormal() wrong result");
        //assertTrue(P1.equa.ls(P1.getNormal(new Point(1, 1, 0))) ||
                //normal.equals(P1.getNormal(new Point(-1, -1, 0))),"The plane does not work as needed");
    }
    @Test
    public void testConstructor()
    {
        // =============== Boundary Values Tests ==================
        // checks weather the Plane constructor works correctly
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,2,4),new Point(1,2,4), new Point(2,3,4) ), "two same points");
        // =============== Boundary Values Tests ==================
        // checks weather the Plane constructor works correctly
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,2,4),new Point(2,4,8), new Point(-1,-2,-4) ), "the points on the same line");
    }
    @Test
    void testGetQ0()
    {}
}

