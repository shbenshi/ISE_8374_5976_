package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTests {

    @Test
    void testGetNormal()
    {
        /*Vector n = new Vector(0, 1, 0);
        Point p0 = new Point(0, 1, 2);
        Vector dir = new Vector (0, 0, 1);
        Ray r = new Ray(new Point(0, 0, 0), new Vector (0, 0, 1));
        Cylinder c = new Cylinder(new Ray(new Point(0, 0, 0), new Vector (0, 0, 1)), 1, 10);*/

        assertTrue(new Vector(0, 1, 0).equals(new Cylinder(new Ray(new Point(0, 0, 0), new Vector (0, 0, 1)), 1, 10).getNormal(new Point(0, 1, 2))), "get normal of cylinder doesn't work");
        // ============ Equivalence Partitions Tests ==============
        // checks weather the getNormal function for Tube works correctly for P on one of the bases
        //******* Point p1 = new Point(1, 0, 0);*********
        assertTrue((new Vector (0, 0, 1).scale(-1)).equals(new Cylinder(new Ray(new Point(0, 0, 0), new Vector (0, 0, 1)), 1, 10).getNormal(new Point(1, 0, 0))),"get normal of cylinder doesn't work");
        // ============ Equivalence Partitions Tests ==============
        // checks weather the getNormal function for Tube works correctly for P on the second base
       //******* Point p2 = new Point(1,0,10);***********
        assertTrue(new Vector (0, 0, 1).equals(new Cylinder(new Ray(new Point(0, 0, 0), new Vector (0, 0, 1)), 1, 10).getNormal(new Point(1,0,10))),"get normal of cylinder doesn't work");
        // =============== Boundary Values Tests ==================
        //checks weather the getNormal function for Tube works correctly in case that P equals to one of the bases center
        assertTrue((new Vector (0, 0, 1).scale(-1)).equals(new Cylinder(new Ray(new Point(0, 0, 0), new Vector (0, 0, 1)), 1, 10).getNormal(new Point(0,0,0))),"the point is on bases center");
        // =============== Boundary Values Tests ==================
        //checks weather the getNormal function for Tube works correctly in case that P equals to the second base center
        assertTrue((new Vector (0, 0, 1)).equals(new Cylinder(new Ray(new Point(0, 0, 0), new Vector (0, 0, 1)), 1, 10).getNormal(new Point(0,0,10))),"the point is on bases center");


    }

}