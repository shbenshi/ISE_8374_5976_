package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {

    @Test
    void testGetNormal() {
        /*
         * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
         */
        @Test
        public void testConstructor() {
            // ============ Equivalence Partitions Tests ==============

            // TC01: Correct concave quadrangular with vertices in correct order
            try {
                new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
            } catch (IllegalArgumentException e) {
                fail("Failed constructing a correct polygon" + e.getMessage());
            }
    }

    void testTestGetNormal() {
    }

    @Test
    void testGetQ0() {
    }
}