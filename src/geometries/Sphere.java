package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/** A sphere class that inherits from RadialGeometry and represents a three-dimensional body and is based on a point and radius
 * system
 * @author Tzofiya david 209918374
 * and Shira ben shimol 326065976 */
// any hakovaat
public class Sphere extends RadialGeometry {
    /**
     * Center point of the Sphere
     */
    final Point center;
    /**
     * the radius off the sphere
     */
    private double radius;

    /**
     * A constructor that receives the radius value and initializes it with the help of the father
     *
     * @param radius The radius value
     */
    public Sphere(Point _center, double radius) {
        super(radius);
        center = _center;
    }

    /**
     * Implementing the getNormal action of the Geometry interface
     *
     * @param p1 A point in a cylinder
     * @return at this point returns null
     */
    public Vector getNormal(Point p1) {
        return p1.subtract(center).normalize();
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
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        /*/*
         * @param ray the ray to compute the intersections with the sphere
         * @return a list with a single intersection point if the ray starts from the center of the sphere, or null otherwise.
         */

        /* If the starting point of the given ray is the center of the sphere, returns a list containing the single intersection
         * point that is furthest along the direction of the ray.
         */
        if (center.equals(ray.getP0()))
            return List.of(ray.getP0().add(ray.getDir().scale(radius))); // return the distance between the ray and the center

        /*Computes the distance from the ray's starting point to the sphere's center, projected onto the direction of the ray, and the distance from the sphere's
         center to the closest point on the ray's line, which is perpendicular to the ray's direction.*/
        Vector place = center.subtract(ray.getP0());
        double normal = place.dotProduct(ray.getDir().normalize());
        double dis  = Math.sqrt(place.lengthSquared() - normal * normal);
        if (dis >= radius)
            return null;
        /*Computes the distances from the ray's starting point to the intersection points with the sphere,
         * if they exist and are in front of the ray.
         */
        double Sq = Math.sqrt(radius * radius - dis * dis);
        if (normal + Sq <= 0 && normal - Sq <= 0)//both are on 'opposite' side of ray, so it doesn't count as an intersection
            return null;

        // Both intersection points are in front of the ray's starting point.
        if (normal + Sq > 0) {
            if (normal - Sq > 0)
                return List.of(ray.getP0().add(ray.getDir().scale(normal + Sq)), ray.getP0().add(ray.getDir().scale(normal - Sq)));
            return List.of(ray.getP0().add(ray.getDir().scale(normal + Sq))); //    // Only the first intersection point is in front of the ray's starting point.
        } else if (normal - Sq > 0)
            return List.of(ray.getP0().add(ray.getDir().scale(normal - Sq)));//    // Only the second intersection point is in front of the ray's starting point.
        return null; // Both intersection points are behind the ray's starting point, so they don't count as intersections.

    }
}
