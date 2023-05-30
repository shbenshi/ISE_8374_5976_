package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import java.util.List;



/**
 * The Plane class represents a plane in 3D space, defined by a point and a normal vector.
 * It implements the Geometry interface.
 * The plane is essentially a point in space and a vertical vector, normalized to the plane.
 * @author Tzofiya david 209918374
 * and Shira ben shimol 326065976
 */
public class Plane extends Geometry {
    /** A point in the plane. */
    private final Point q0;
    /** The normal vector to the plane. */
    private final Vector normal;

    /**
     * Constructs a plane based on a point in the plane and a vector that is perpendicular to the plane.
     * @param q0     A point in the plane
     * @param normal The vector perpendicular to the plane
     */
    public Plane(Point q0, Vector normal)
    {
        this.q0 = q0;
        if(!(isZero(normal.length()-1d))){
            this.normal = normal.normalize();
        }
        else {this.normal = normal;}
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
        if(q0.equals(q1)||q0.equals(q2)||q1.equals(q2))
            throw new IllegalArgumentException("two of the points are identical");

        Vector v1 = q1.subtract(q0);
        Vector v2 = q2.subtract(q0);
        if(v1.normalize().equals(v2.normalize()))
            throw new IllegalArgumentException("there is a linear dependents between the vectors");

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
        return getNormal();
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
        if (q0.equals(ray.getP0()))// start from same place
            return null;
        if (isZero(getNormal().dotProduct(ray.getDir()))) // if vertical
            return null;
        double res = getNormal().dotProduct(q0.subtract(ray.getP0())) / normal.dotProduct(ray.getDir()); // represent the distance from the ray
        if (res <= 0 || isZero(res))// if there is no intersection
            return null;
        return List.of(ray.getP0().add(ray.getDir().scale(res)));
    }
}
