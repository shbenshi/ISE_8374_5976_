package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import java.util.List;

import static primitives.Util.isZero; //????/


/**
 * The Plane class represents a plane in 3D space, defined by a point and a normal vector.
 * It implements the Geometry interface.
 * The plane is essentially a point in space and a vertical vector, normalized to the plane.
 * @author Tzofiya david 209918374
 * and Shira ben shimol 326065976
 */
public class Plane implements Geometry {
    /** A point in the plane. */
    private Point q0;
    /** The normal vector to the plane. */
    private Vector normal;

    /**
     * Constructs a plane based on a point in the plane and a vector that is perpendicular to the plane.
     * @param q0     A point in the plane
     * @param normal The vector perpendicular to the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    /**
     * Constructs a plane based on three points in the plane.
     * The constructor takes one of the points as the reference point of the plane.
     * @param q0 A point in the plane that will be the reference point of the plane
     * @param q1 A point in the plane
     * @param q2 A point in the plane
     */
    public Plane(Point q0, Point q1, Point q2) {
        this.q0 = q0;

        Vector v1 = q1.subtract(q0);
        Vector v2 = q2.subtract(q0);

        this.normal =  v1.crossProduct(v2).normalize();//normal.normalize();
    }

    /**
     * Returns the normalized normal vector to the plane.
     * @return The normalized normal vector to the plane
     */
    public Vector getNormal()
    {
        return normal;
    }

    /**
     * Returns the normal vector to the plane at a given point, which is always the same for a plane.
     *
     * @param p1 A point in the plane
     * @return The normal vector to the plane
     */
    @Override
    public Vector getNormal(Point p1)
    {
        return normal;
    }



    /**
     * Returns a point in the plane.
     * @return A point in the plane
     */
    public Point getQ0() {
        return q0;
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        if (q0.equals(ray.getP0()))// both start from same place
            return null;
        if (isZero(normal.dotProduct(ray.getDir()))) // if the dot product ret zero they are vertical
            return null;
        double res = normal.dotProduct(q0.subtract(ray.getP0())) / normal.dotProduct(ray.getDir()); // res represent the dis from the ray
        if (res <= 0 || isZero(res))// if it is equal or small then zero it means that there is no intersection
            return null;
        return List.of(ray.getP0().add(ray.getDir().scale(res)));
    }
}
