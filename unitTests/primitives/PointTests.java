package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**

 Unit tests for primitives.Point class.
 Tests the methods for adding, subtracting, and calculating distances between points.
 @author Tzofiya David and Shira Ben Shimol
 */
class PointTests {
    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     * Tests adding a vector to a point.
     * Equivalence partitions:
     * TC01: Simple test - adds a vector to a point.
     * Boundary values:
     * There are no boundary tests.
     */    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Point(2, 3, 4),
                new Point(1, 1, 1).add(new Vector(1, 2, 3)),
                "Wrong point add");

        // =============== Boundary Values Tests ==================
        // there are no boundary tests
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     * Tests subtracting a point from another point.
     * Equivalence partitions:
     * TC01: Simple test - subtracts one point from another point.
     *
     * Boundary values:
     * TC11: Tests subtracting the same point from itself, which should throw an IllegalArgumentException.
     */    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(1, 1, 1),
                new Point(2, 3, 4).subtract(new Point(1, 2, 3)),
                "Wrong point subtract");

        // =============== Boundary Values Tests ==================
        // TC11: test subtracting same point
        assertThrows(IllegalArgumentException.class,
                () -> new Point(1, 2, 3).subtract(new Point(1, 2, 3)),
                "Subtract P from P must throw exception");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     * Tests calculating the squared distance between two points.
     * Equivalence partitions:
     * TC01: Simple test - calculates the squared distance between two points.
     * Boundary values:
     * TC11: Tests calculating the squared distance between a point and itself.
     */    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(14d, new Point(1, 1, 1).distanceSquared(new Point(2, 3, 4)),
                0.0001, "Wrong squared distance between the point and itself");

        // =============== Boundary Values Tests ==================
        // TC11: test distance with the same point
        assertEquals(0d, new Point(1, 2, 3).distanceSquared(new Point(1, 2, 3)),
                0.0001, "Wrong squared distance between the point and itself");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     * Tests calculating the distance between two points.
     * Equivalence partitions:
     * TC01: Simple test - calculates the distance between two points.
     * Boundary values:
     * TC11: Tests calculating the distance between a point and itself.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(Math.sqrt(14), new Point(1, 1, 1).distance(new Point(2, 3, 4)),
                0.0001, "Wrong distance between the point and itself");

        // =============== Boundary Values Tests ==================
        // TC11: test distance with the same point
        assertEquals(0d, new Point(1, 2, 3).distance(new Point(1, 2, 3)),
                0.0001, "Wrong distance between the point and itself");
    }
}