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
      double height;

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
        //Point p0 = axisRay.getP0();
        //Vector v = axisRay.getDir();

        //if the point and p0 are the same
        if ((p1.equals(axisRay.getP0())))
            return ((axisRay.getDir()).scale(-1)); //v.scale(-1)


        if (p1.equals(((axisRay.getP0()).add((axisRay.getDir()).scale(height))))) {
            return axisRay.getDir();
        }

        if ((p1.subtract(axisRay.getP0())).dotProduct(axisRay.getDir()) == 0) {
            return ((axisRay.getDir()).scale(-1));
        }
        if ((p1.subtract((axisRay.getP0()).add((axisRay.getDir()).scale(height)))).dotProduct(axisRay.getDir()) == 0)
            return axisRay.getDir();

        double H = ((p1.subtract(axisRay.getP0())).dotProduct(axisRay.getDir()));
        Point p2 = (axisRay.getP0()).add((axisRay.getDir()).scale(H));
        return ((p1.subtract(p2)).normalize());

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
