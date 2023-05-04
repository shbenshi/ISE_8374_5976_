package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/** The cylinder class represents a three-dimensional body and based on height
 * system
 *   @author Tzofiya david 209918374
 *   and Shira ben shimol 326065976 */
public class Cylinder extends Tube {
    /**
     * represents the height of the cylinder
     */
    private double height;

    /**
     * A constructor that receives the radius value and initializes it with the help of the father
     *
     * @param _radius The radius value
     */
    public Cylinder(Ray _axis, double _radius, double _height) {
        super(_axis, _radius);
        height = _height;

    }

    /**
     * Implementing the getNormal action of the Geometry interface
     *
     * @param p1 A point in a cylinder
     * @return at this point returns null
     */
    public Vector getNormal(Point p1) {
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        //if the point and p0 are the same
        if (p1.equals(p0))
            return v;

        // projection of P-p0 on the ray:
        Vector u = p1.subtract(p0);

        // distance from p0 to the o who is in from of point
        double t = alignZero(u.dotProduct(v));

        // if the point is at a base
        if (t == 0 || isZero(height - t))
            return v;

        //the other point on the axis facing the given point
        Point o = p0.add(v.scale(t));

        //create the normal vector
        return p1.subtract(o).normalize();
    }



    /**
     *getter operation
     * @return height The height of the cylinder
     */
    public double getHeight()
    {
        return height;
    }
}
