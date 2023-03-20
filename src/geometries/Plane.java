package geometries;

import primitives.Point;
import primitives.Vector;


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
        this.normal = null;
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
        return null;
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
