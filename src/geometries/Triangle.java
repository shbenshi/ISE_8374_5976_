package geometries;

import primitives.*;

import java.util.List;

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


    @Override
    public List<Point> findIntsersections(Ray ray) {
        List<Point> intersection = plane.findIntsersections(ray);
        Vector v0 = vertices.get(0).subtract(ray.getP0());
        Vector v1 = vertices.get(1).subtract(ray.getP0());
        Vector v2 = vertices.get(2).subtract(ray.getP0());

        Vector n0 = v0.crossProduct(v1).normalize();
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v0).normalize();

        double t0 = ray.getDir().dotProduct(n0);
        double t1 = ray.getDir().dotProduct(n1);
        double t2 = ray.getDir().dotProduct(n2);
        // if there is intersection
        if ((t0>0 && t1>0 && t2>0) || (t0<0 && t1<0 && t2<0))
            return intersection;
        // if there is ni intersection
        return null;
    }

}
