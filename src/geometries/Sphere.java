package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * A sphere class that inherits from RadialGeometry and represents a three-dimensional body and is based on a point and radius.
 * @author Tzofiya david 209918374
 * and Shira ben shimol 326065976 */

public class Sphere extends RadialGeometry
{
    /** Center point of the Sphere */
    private  Point center;
    /** the radius of the sphere */
    private double radius;

    /**
     * A constructor that receives the center point and radius value and initializes them with the help of the father.
     *
     * @param center The center point of the sphere.
     * @param radius The radius value of the sphere.
     */
    public Sphere(Point _center, double radius)
    {
        super(radius);
        this.center = _center;

    }

    /**
     * Implementation of the getNormal action of the Geometry interface.
     * @param p1 A point on the surface of the sphere.
     * @return A normalized vector that is perpendicular to the surface of the sphere at the given point.
     */
    public Vector getNormal(Point p1)
    {
        return p1.subtract(center).normalize();
    }

    /**
     * getter operation
     * @return center the center point
     */
    public Point getCenter()
    {
        return center;
    }


    /**
     * getter operation
     * @return radius The radius value
     */
    public double getRadius()
    {
        return radius;
    }
}
