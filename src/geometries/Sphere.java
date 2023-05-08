package geometries;

import primitives.Point;
import primitives.Vector;

/** A sphere class that inherits from RadialGeometry and represents a three-dimensional body and is based on a point and radius
 * system
 * @author Tzofiya david 209918374
 * and Shira ben shimol 326065976 */
// any hakovaat
public class Sphere extends RadialGeometry
{
    /** Center point of the Sphere */
    final Point center;
    /**  the radius off the sphere */
    private double radius;

    /**
     * A constructor that receives the radius value and initializes it with the help of the father
     * @param radius The radius value
     */
    public Sphere(Point _center, double radius) {
        super(radius);
        center = _center;
    }

    /**
     *Implementing the getNormal action of the Geometry interface
     * @param p1 A point in a cylinder
     * @return  at this point returns null
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
