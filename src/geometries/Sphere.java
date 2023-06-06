package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/** A sphere class that inherits from RadialGeometry and represents a three-dimensional body and is based on a point and radius
 * system
 * @author Tzofiya david 209918374
 * and Shira ben shimol 326065976 */
// any hakovaat
public class Sphere extends RadialGeometry {
    /**
     * Center point of the Sphere
     */
    final private Point center;

    /**
     * A constructor that receives the radius value and initializes it with the help of the father
     *
     * @param _radius The radius value
     */
    public Sphere(Point _center, double _radius) {
        super(_radius);
        center = _center;
    }

    /**
     * Implementing the getNormal action of the Geometry interface
     *
     * @param p1 A point in a cylinder
     * @return at this point returns null
     */
    public Vector getNormal(Point p1) {

        return (Vector)p1.subtract(center).normalize();
    }

    /**
     * getter operation
     *
     * @return center the center point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Finds the geometric intersections between a given ray and the sphere.
     * If no intersections are found, returns null.
     *
     * @param ray The ray to intersect with the sphere
     * @return A list of geometric intersection points (GeoPoints), or null if no intersections
     */
    @Override
    public List<GeoPoint> findGeoIntsersectionsHelper(Ray ray) {
        // Calculate the parameters for intersection calculation
        double tm = 0;
        double d = 0;
        if (!center.equals(ray.getP0())) {
            Vector L = center.subtract(ray.getP0());
            tm = L.dotProduct(ray.getDir());
            d = L.lengthSquared() - tm * tm;
            if (d < 0)
                d = -d;
            d = Math.sqrt(d);
        }

        // Check for intersection with the sphere
        if (d > getRadius())
            return null;

        // Calculate the intersection points
        double th = Math.sqrt(getRadius() * getRadius() - d * d);
        double t1 = tm - th;
        double t2 = tm + th;

        // Check if both intersection points are behind the ray's origin
        if (t1 <= 0 && t2 <= 0)
            return null;

        // Check for degenerate cases
        if (alignZero(t2) == 0)
            return null;
        if (th == 0)
            return null;

        // Return the intersection points
        if (t1 <= 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t2)));
        }
        if (t2 <= 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t1)));
        }
        return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
    }
}
