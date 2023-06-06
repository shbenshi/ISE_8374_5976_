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
     * getter operation
     *
     * @return radius The radius value

    public double getRadius() {
        return radius;
    }*/

    @Override
    public List<GeoPoint> findGeoIntsersectionsHelper(Ray ray) {
        // if the ray starts at the center of the sphere
        double tm = 0;
        double d = 0;
        if (!center.equals(ray.getP0())) { // if the ray doesn't start at the center of the sphere
            Vector L = center.subtract(ray.getP0());
            tm = L.dotProduct(ray.getDir());
            d = L.lengthSquared() - tm * tm; // d = (|L|^2 - tm^2)
            if (d < 0)
                d = -d;
            d = Math.sqrt(d);
        }
        if (d > getRadius()) // if the ray doesn't intersect the sphere
            return null;
        // computing the distance from the ray's start point to the intersection points
        double th = Math.sqrt(getRadius() * getRadius() - d * d);
        double t1 = tm - th;
        double t2 = tm + th;
        if (t1 <= 0 && t2 <= 0)
            return null;
        if (alignZero(t2) == 0) // if the ray is tangent to the sphere
            return null;
        if (th == 0)
            return null;
        if (t1 <= 0) { // if the ray starts inside the sphere or the ray starts after the sphere
            return List.of(new GeoPoint(this, ray.getPoint(t2)));
        }
        if (t2 <= 0) { //if the ray starts after the sphere
            return List.of(new GeoPoint(this, ray.getPoint(t1)));
        }
        return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2))); // if the ray intersects the sphere twice
    }
}
