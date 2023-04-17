package geometries;

import primitives.Point;
import primitives.Vector;

import static primitives.Util.isZero; //????/



/** Polygon class represents a plane essentially a point in space and a vertical vector and based on a point and vector normalized to the plane
 * system
 * @author Tzofiya david 209918374
 * and Shira ben shimol 326065976 */
public class Plane implements Geometry
{
    /** A point in the plane */
    private Point q0;
    /** The vector perpendicular to the plane */
    private Vector normal;

    /**
     *A planne constructor is based on a point in the plane and a vector that is perpendicular to the plane
     * @param q0   A point in the plane
     * @param normal  The vector perpendicular to the plan
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    /**
     * A plane builder is based on 3 points in the plane. At this stage we will put in normal null. The constructor keeps one of the points as the reference point of the plane.
     * @param q0  A point in the plane will be the reference point of the plane
     * @param q1  A point in the plane
     * @param q2  A point in the plane
     */
    public Plane(Point q0, Point q1, Point q2 )
    {
        this.q0 = q0;


        Vector v1 = q1.subtract(q0);
        Vector v2= q2.subtract(q0);
        if(!isZero(v1.dotProduct(v2))) // check if the points are on the same line 31:14
            throw new IllegalArgumentException("The point are on the same line");
        this.normal = v1.crossProduct(v2).normalize();
    }

    /**
     *Function getNormal returns its normalized vector
     * @return normal
     */
    public Vector getNormal()
    {
        return normal;
    }

    /**
     * A getter operation
     * @return p1 the point in the plane
     */
    @Override
    public Vector getNormal(Point p1)
    {
        return normal;
    }

    /**
     * A getter operation
     * @return q0 the point in the plane
     */
    public Point getQ0()
    {
        return q0;
    }
}
