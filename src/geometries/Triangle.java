package geometries;

import primitives.Point;

/** The triangle class represents a flat body and inherits from the polygon class
 * system
 * @author Tzofiya david 209918374
 * and Shira ben shimol 326065976 */
public class Triangle extends Polygon
{
    /**
     * A constructor that receives three points and activates the parent constructor accordingly
     * @param q0 the apex of the triangle
     * @param q1 the apex of the triangle
     * @param q2 the apex of the triangle
     */
    /**
     *  a constractor that get three points and analyzing by the father func
     * @param q0 first pointt
     * @param q1 second point
     * @param q2 third point
     */
    public Triangle(Point q0, Point q1, Point q2) {
        super(q0,q1,q2);
    }
}
