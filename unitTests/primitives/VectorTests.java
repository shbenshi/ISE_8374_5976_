package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTests {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    /* Test method for {@link primitives.Vector#Vector(double, double, double)}.
     */
    @Test
    void testVectorZero()
    {
        try
        { // test zero vector
            new Vector(0, 0, 0);
            out.println("ERROR: zero vector does not throw an exception");
        } catch (IllegalArgumentException ignore) {} catch (Exception ignore) {
            out.println("ERROR: zero vector throws wrong exception");
        }
        // ============ Equivalence Partitions Tests ==============
        // No EP tests

        // =============== Boundary Values Tests ==================
        // TC11: Zero vector
        //assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0),"ERROR: zero vector should have thrown an exception");
    }

    /* Test method for {@link primitives.Vector#add(primitives.Vector)}.
            */
    @Test
    void testAddVector()
    {
        try {
            v1.add(new Vector(-1, -2, -3));
            out.println("ERROR: Vector + -itself does not throw an exception");
        } catch (IllegalArgumentException ignore) {} catch (Exception ignore) {
            out.println("ERROR: Vector + itself throws wrong exception");
        }
        if (!v1.add(v2).equals(new Vector(-1, -2, -3)))
            out.println("ERROR: Point - Point does not work correctly");

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        //assertEquals(new Vector(1, 1, 1), new Vector(2, 3, 4).add(new Vector(-1, -2, -3)), //
                //"Wrong vector add");

        // =============== Boundary Values Tests ==================
        // TC11: test adding v + (-v)
        // assertThrows(IllegalArgumentException.class, () -> new Vector(1, 2, 3).add(new Vector(-1, -2, -3)), //
                //"Add v plus -v must throw exception");
    }

    /* Test method for {@link primitives.Vector#subtract(Point)} (primitives.Vector)}.
        */
@Test
    void testSubtractVector() {
    try {
        v1.subtract(v1);
        out.println("ERROR: Vector - itself does not throw an exception");
    } catch (IllegalArgumentException ignore) {} catch (Exception ignore) {
        out.println("ERROR: Vector + itself throws wrong exception");
    }
    if (!v1.subtract(v2).equals(new Vector(3, 6, 9)))
        out.println("ERROR: Point - Point does not work correctly");

    // ============ Equivalence Partitions Tests ==============
            // TC01: Simple test
            //assertEquals(new Vector(1, 1, 1), new Vector(2, 3, 4).subtract(new Vector(1, 2, 3)), //
            //"Wrong vector subtract");

            // =============== Boundary Values Tests ==================
            // TC11: test subtracting same vector
            //assertThrows(IllegalArgumentException.class, () -> new Vector(1, 2, 3).subtract(new Vector(1, 2, 3)), //
        //"Subtract v from v must throw exception");
        }

        /* Test method for {@link primitives.Vector#scale(double)}.
        */
@Test
    void testScale() {
            // ============ Equivalence Partitions Tests ==============
            // TC01: Simple test
    //assertEquals(new Vector(2, 4, 6), new Vector(1, 2, 3).scale(2), //
            //"Wrong vector scale");

            // =============== Boundary Values Tests ==================
            // TC11: test scale by 0
            //assertThrows(IllegalArgumentException.class, () -> new Vector(1, 2, 3).scale(0d), //
        //"Scale by 0 must throw exception");
        }
        /*
        * assertEquals(new Vector(2, 4, 6),new Vector(2, 4, 6).scale(2),"scale() results with wrong vector");
        assertThrows(IllegalArgumentException.class,()-> new Vector(2, 4, 6).scale(0),"scaling vector by zero are not alowd");
        * */
        /* Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
        */
@Test
    void testDotProduct()
{
    if (!isZero(v1.dotProduct(v3)))
        out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
    if (!isZero(v1.dotProduct(v2) + 28))
        out.println("ERROR: dotProduct() wrong value");

    // Vector v1 = new Vector(1, 2, 3);

            // ============ Equivalence Partitions Tests ==============
            // TC01: Simple dotProduct test
            //Vector v2 = new Vector(-2, -4, -6);
            //assertEquals(-28d, v1.dotProduct(v2), 0.00001, //
            //"dotProduct() wrong value");

            // =============== Boundary Values Tests ==================
            // TC11: dotProduct for orthogonal vectors
            //Vector v3 = new Vector(0, 3, -2);
            //assertEquals(0d, v1.dotProduct(v3), 0.00001, //
            //"dotProduct() for orthogonal vectors is not zero");
}

            /* Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
        */
@Test
    void testCrossProduct() {
    try { // test zero vector
        v1.crossProduct(v2);
        out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
    } catch (Exception e) {
    }
    Vector vr = v1.crossProduct(v3);
    if (!isZero(vr.length() - v1.length() * v3.length()))
        out.println("ERROR: crossProduct() wrong result length");
    if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
        out.println("ERROR: crossProduct() result is not orthogonal to its operands");
}

    /*Vector v1 = new Vector(1, 2, 3);

    // ============ Equivalence Partitions Tests ==============
    Vector v2 = new Vector(0, 3, -2);
    Vector vr = v1.crossProduct(v2);

    // TC01: Test that length of cross-product is proper (orthogonal vectors taken
    // for simplicity)
    assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, //
            "crossProduct() wrong result length");
   */

    @Test
    void testLengthSquared()
    {
        // test length..
        if (!isZero(v1.lengthSquared() - 14))
            out.println("ERROR: lengthSquared() wrong value");

    }

    @Test
    void testLength()
    {
        if (!isZero(new Vector(0, 3, 4).length() - 5))
            out.println("ERROR: length() wrong value");
    }

    @Test
    void testNormalize()
    {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        if (!isZero(u.length() - 1))
            out.println("ERROR: the normalized vector is not a unit vector");

    }

}