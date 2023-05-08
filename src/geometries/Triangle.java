package geometries;

import primitives.Point;

/**
 * The Triangle class represents a flat body and inherits from the Polygon class.
 * It is defined by three vertices in three-dimensional space.
 * @author Tzofiya david 209918374
 * and Shira ben shimol 326065976 */

public class Triangle extends Polygon
{
    /**
     * Constructs a new triangle with the given three vertices.
     *
     * @param q0 The first vertex of the triangle.
     * @param q1 The second vertex of the triangle.
     * @param q2 The third vertex of the triangle.
     */

    public Triangle(Point q0, Point q1, Point q2)
    {
        super(q0,q1,q2);
    }
}
