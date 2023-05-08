package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/** The cylinder class represents a three-dimensional body and based on height
 * system
 *   @author Tzofiya david 209918374 and Shira ben shimol 326065976 */
public class Cylinder extends Tube {
    /**
     * The height of the cylinder.
     */
      double height;

    /**
     * Constructs a cylinder with a given axis ray, radius, and height.
     * @param _axis The axis ray of the cylinder
     * @param _radius The radius of the cylinder
     * @param _height The height of the cylinder
     */
    public Cylinder(Ray _axis, double _radius, double _height) {
        super(_axis, _radius);
        height = _height;

    }

    /**
     * Returnss the normal vector at a given point on the cylinder.
     * @param p1 A point on the cylinder
     * @return The normal vector at the given point
     */
    public Vector getNormal(Point p1) {
        /* if the given point equals to the exist point
         you need to return the opposite direction*/
        if ((p1.equals(axisRay.getP0())))
            return ((axisRay.getDir()).scale(-1));

        /* if the given point equals to the exist point + dir*height
         you need to return the exist direction*/
        if (p1.equals(((axisRay.getP0()).add((axisRay.getDir()).scale(height))))) {
            return axisRay.getDir();
        }
        /* if the given point minus exist point  X exist dir equal  zero
         you need to return the opposite direction*/
        if ((p1.subtract(axisRay.getP0())).dotProduct(axisRay.getDir()) == 0) {
            return ((axisRay.getDir()).scale(-1));
        }
        /* if the given point minus exist point  + exist* height X dir equal  zero
         you need to return the exist direction*/
        if ((p1.subtract((axisRay.getP0()).add((axisRay.getDir()).scale(height)))).dotProduct(axisRay.getDir()) == 0)
            return axisRay.getDir();

        // if it is none of the written above return just the normal of cylinder
        double H = ((p1.subtract(axisRay.getP0())).dotProduct(axisRay.getDir()));
        Point p2 = (axisRay.getP0()).add((axisRay.getDir()).scale(H));
        return ((p1.subtract(p2)).normalize());

    }

    /**
     * Getter operation for the height of the cylinder.
     * @return The height of the cylinder
     */
    public double getHeight()
    {
        return height;
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
