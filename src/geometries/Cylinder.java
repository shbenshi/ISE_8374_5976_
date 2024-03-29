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
      private final double height;

    /**
     * Constructs a cylinder with a given axis ray, radius, and height.
     * @param _axis The axis ray of the cylinder
     * @param _radius The radius of the cylinder
     * @param _height The height of the cylinder
     */
    public Cylinder(Ray _axis, double _radius, double _height) {
        super(_axis, _radius);
        if (_height < 0) {
            throw new IllegalArgumentException("height cannot be negative");
        }
        height = _height;

    }

    /**
     * Returnss the normal vector at a given point on the cylinder.
     * @param p1 A point on the cylinder
     * @return The normal vector at the given point
     */
    public Vector getNormal(Point p1) {
        double t = 0;
        if (!p1.equals(axisRay.getP0()))
            t = axisRay.getDir().dotProduct(p1.subtract(axisRay.getP0()));
        if (t == this.height) // if is on base B return the dir
            return axisRay.getDir();
        else if (t == 0) // if is on base A return the opposite of dir
            return axisRay.getDir().scale(-1);
        else
            return super.getNormal(p1);// if is not on the bases then return get normal of tube
    }

    /**
     * Getter operation for the height of the cylinder.
     * @return The height of the cylinder
     */
    public double getHeight() {
        return height;
    }


    /**
     * Helper function to find the geometric intersections of the ray with the geometry.
     * This function returns a list of GeoPoint objects representing the intersections.
     *
     * @param ray The ray for which to find the intersections.
     * @return A list of GeoPoint objects representing the geometric intersections, or null if there are no intersections.
     */
    @Override
    public List<GeoPoint> findGeoIntsersectionsHelper(Ray ray) {
        return null;
    }
}
