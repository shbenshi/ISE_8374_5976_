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

    /**
     * Finds the intersection points between the triangle and a given ray.
     *
     * @param ray The ray to intersect with the triangle
     * @return A list of intersection points (GeoPoints) between the triangle and the ray,
     *         or null if there are no intersections
     */
    @Override
    public List<GeoPoint> findGeoIntsersectionsHelper(Ray ray) {
        // Check if the ray intersects the triangle's plane
        if (this.plane.findGeoIntsersectionsHelper(ray) == null) {
            return null;
        }

        // Extract the necessary points and vectors for calculations
        Point P0 = ray.getP0();
        Vector v = ray.getDir();
        Point P1 = this.vertices.get(0);
        Point P2 = this.vertices.get(1);
        Point P3 = this.vertices.get(2);

        // Calculate the necessary vectors
        Vector v1 = P1.subtract(P0);
        Vector v2 = P2.subtract(P0);
        Vector v3 = P3.subtract(P0);

        // Calculate the dot products between the triangle's normal vectors and the ray's direction
        double n1v = v1.crossProduct(v2).normalize().dotProduct(v);
        double n2v = v2.crossProduct(v3).normalize().dotProduct(v);
        double n3v = v3.crossProduct(v1).normalize().dotProduct(v);

        // Check if the ray is parallel to any of the triangle's edges
        if (n1v == 0 || n2v == 0 || n3v == 0) {
            return null;
        } else if ((n1v > 0 && n2v > 0 && n3v > 0) || (n1v < 0 && n2v < 0 && n3v < 0)) {
            // Check if the ray intersects the triangle
            Point point = plane.findGeoIntsersectionsHelper(ray).get(0).point;
            return List.of(new GeoPoint(this, point));
        } else {
            // The ray does not intersect the triangle
            return null;
        }
    }

}
