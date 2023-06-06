package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/** Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * @author Dan */
public class Polygon extends Geometry {
   /** List of polygon's vertices */
   protected final List<Point> vertices;
   /** Associated plane in which the polygon lays */
   protected final Plane plane;
   private final int size;

   /** Polygon constructor based on vertices list. The list must be ordered by edge
    * path. The polygonn must be convex.
    * @param  vertices                 list of vertices according to their order by
    *                                  edge path
    * @throws IllegalArgumentException in any case of illegal combination of
    *                                  vertices:
    *                                  <ul>
    *                                  <li>Less than 3 vertices</li>
    *                                  <li>Consequent vertices are in the same
    *                                  point
    *                                  <li>The vertices are not in the same
    *                                  plane</li>
    *                                  <li>The order of vertices is not according
    *                                  to edge path</li>
    *                                  <li>Three consequent vertices lay in the
    *                                  same line (180&#176; angle between two
    *                                  consequent edges)
    *                                  <li>The polygon is concave (not convex)</li>
    *                                  </ul>
    */
   public Polygon(Point... vertices) {
      if (vertices.length < 3)
         throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
      this.vertices = List.of(vertices);
      size          = vertices.length;

      // Generate the plane according to the first three vertices and associate the
      // polygon with this plane.
      // The plane holds the invariant normal (orthogonal unit) vector to the polygon
      plane         = new Plane(vertices[0], vertices[1], vertices[2]);
      if (size == 3) return; // no need for more tests for a Triangle

      Vector  n        = plane.getNormal();
      // Subtracting any subsequent points will throw an IllegalArgumentException
      // because of Zero Vector if they are in the same point
      Vector  edge1    = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
      Vector  edge2    = vertices[0].subtract(vertices[vertices.length - 1]);

      // Cross Product of any subsequent edges will throw an IllegalArgumentException
      // because of Zero Vector if they connect three vertices that lay in the same
      // line.
      // Generate the direction of the polygon according to the angle between last and
      // first edge being less than 180 deg. It is hold by the sign of its dot product
      // with
      // the normal. If all the rest consequent edges will generate the same sign -
      // the
      // polygon is convex ("kamur" in Hebrew).
      boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
      for (var i = 1; i < vertices.length; ++i) {
         // Test that the point is in the same plane as calculated originally
         if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
            throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
         // Test the consequent edges have
         edge1 = edge2;
         edge2 = vertices[i].subtract(vertices[i - 1]);
         if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
            throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
      }
   }

   @Override
   public Vector getNormal(Point point) { return plane.getNormal(); }

   @Override
   public List<GeoPoint> findGeoIntsersectionsHelper(Ray ray) {
      // check if the ray intersects the plane of the polygon
      Plane plane = new Plane(vertices.get(0), vertices.get(1), vertices.get(2));
      if (plane.findGeoIntsersectionsHelper(ray) == null) return null;
      // need to have list of intersections, as the barycentric coordinates method asks
      List<GeoPoint> intsersections = plane.findGeoIntsersectionsHelper(ray);
      // there could be 1 intersection point with the plane, check if it is inside the polygon
      GeoPoint point = intsersections.get(0);
      // Find the triangle in the polygon that contains the point
      for (int i = 0; i < size - 2; i++) {
         Point vertex1 = vertices.get(0);
         Point vertex2 = vertices.get(i + 1);
         Point vertex3 = vertices.get(i + 2);
      /*
      Explaination of the barycentric coordinates method:
        https://en.wikipedia.org/wiki/Barycentric_coordinate_system#Conversion_between_barycentric_and_Cartesian_coordinates
       */
         // Calculate the barycentric coordinates of the point with respect to the triangle
         //w1 = ((y2 - y3) * (x - x3) + (x3 - x2) * (y - y3)) / ((y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3))
         double w1 = ((vertex2.getY() - vertex3.getY()) * (point.getX() - vertex3.getX()) +
                 (vertex3.getX() - vertex2.getX()) * (point.getY() - vertex3.getY())) /
                 ((vertex2.getY() - vertex3.getY()) * (vertex1.getX() - vertex3.getX()) +
                         (vertex3.getX() - vertex2.getX()) * (vertex1.getY() - vertex3.getY()));
         //w2 = ((y3 - y1) * (x - x3) + (x1 - x3) * (y - y3)) / ((y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3))
         double w2 = ((vertex3.getY() - vertex1.getY()) * (point.getX() - vertex3.getX()) +
                 (vertex1.getX() - vertex3.getX()) * (point.getY() - vertex3.getY())) /
                 ((vertex2.getY() - vertex3.getY()) * (vertex1.getX() - vertex3.getX()) +
                         (vertex3.getX() - vertex2.getX()) * (vertex1.getY() - vertex3.getY()));

         double w3 = 1 - w1 - w2;

         if (w1 > 0 && w2 > 0 && w3 > 0) {
            // The point is inside the triangle (and therefore inside the polygon)
            return List.of(new GeoPoint(this, point.point));
         }
      }
      // The point is outside the polygon
      return null;
   }
}
