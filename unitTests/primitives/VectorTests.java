package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTests {
      /* Test method for {@link primitives.Vector#Vector(double, double, double)}.
            */
    @Test
    void testVector() {
        // ============ Equivalence Partitions Tests ==============
        // No EP tests

        // =============== Boundary Values Tests ==================
        // TC11: Zero vector
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0));
    }

    /* Test method for {@link primitives.Vector#add(primitives.Vector)}.
            */
    @Test
    void testAddVector() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(1, 1, 1), new Vector(2, 3, 4).add(new Vector(-1, -2, -3)), //
                "Wrong vector add");

        // =============== Boundary Values Tests ==================
        // TC11: test adding v + (-v)
        assertThrows(IllegalArgumentException.class, () -> new Vector(1, 2, 3).add(new Vector(-1, -2, -3)), //
                "Add v plus -v must throw exception");
    }

    /* Test method for {@link primitives.Vector#subtract(Point)} (primitives.Vector)}.
        */
@Test
    void testSubtractVector() {
            // ============ Equivalence Partitions Tests ==============
            // TC01: Simple test
            assertEquals(new Vector(1, 1, 1), new Vector(2, 3, 4).subtract(new Vector(1, 2, 3)), //
            "Wrong vector subtract");

            // =============== Boundary Values Tests ==================
            // TC11: test subtracting same vector
            assertThrows(IllegalArgumentException.class, () -> new Vector(1, 2, 3).subtract(new Vector(1, 2, 3)), //
        "Subtract v from v must throw exception");
        }

        /* Test method for {@link primitives.Vector#scale(double)}.
        */
@Test
    void testScale() {
            // ============ Equivalence Partitions Tests ==============
            // TC01: Simple test
            assertEquals(new Vector(2, 4, 6), new Vector(1, 2, 3).scale(2), //
            "Wrong vector scale");

            // =============== Boundary Values Tests ==================
            // TC11: test scale by 0
            assertThrows(IllegalArgumentException.class, () -> new Vector(1, 2, 3).scale(0d), //
        "Scale by 0 must throw exception");
        }

        /* Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
        */
@Test
    void testDotProduct() {
            Vector v1 = new Vector(1, 2, 3);

            // ============ Equivalence Partitions Tests ==============
            // TC01: Simple dotProduct test
            Vector v2 = new Vector(-2, -4, -6);
            assertEquals(-28d, v1.dotProduct(v2), 0.00001, //
            "dotProduct() wrong value");

            // =============== Boundary Values Tests ==================
            // TC11: dotProduct for orthogonal vectors
            Vector v3 = new Vector(0, 3, -2);
            assertEquals(0d, v1.dotProduct(v3), 0.00001, //
            "dotProduct() for orthogonal vectors is not zero");
            }

            /* Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
        */
@Test
    void testCrossProduct() {
    Vector v1 = new Vector(1, 2, 3);

    // ============ Equivalence Partitions Tests ==============
    Vector v2 = new Vector(0, 3, -2);
    Vector vr = v1.crossProduct(v2);

    // TC01: Test that length of cross-product is proper (orthogonal vectors taken
    // for simplicity)
    assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, //
            "crossProduct() wrong result length");}
    /*@Test
    void testScale() {
    }

    @Test
    void testLengthSquared() {
    }

    @Test
    void testLength() {
    }

    @Test
    void testDotProduct() {
    }*/
}