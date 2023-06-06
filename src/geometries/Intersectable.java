package geometries;
import primitives.*;
import java.util.List;

public abstract class Intersectable {
     /**
      * Finds the geometric intersections of the ray with the geometry.
      *
      * @param ray The ray for which to find the intersections.
      * @return A list of GeoPoint objects representing the geometric intersections,
      *         or null if there are no intersections.
      */
     public final List<GeoPoint> findGeoIntsersections(Ray ray) {

          return findGeoIntsersectionsHelper(ray);
     }

     /**
      * Helper method to find the geometric intersections of the ray with the geometry.
      * Subclasses must implement this method.
      *
      * @param ray The ray for which to find the intersections.
      * @return A list of GeoPoint objects representing the geometric intersections,
      *         or null if there are no intersections.
      */
     protected abstract List<GeoPoint> findGeoIntsersectionsHelper(Ray ray);


     /**
      * Finds the intersections of the ray with the geometry, returning a list of points.
      *
      * @param ray The ray for which to find the intersections.
      * @return A list of Point objects representing the intersections,
      *         or null if there are no intersections.
      */
     public List<Point> findIntsersections(Ray ray) {
          var geoList = findGeoIntsersections(ray);
          return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
     }

     /**
      * Represents a point of intersection between a ray and a geometry.
      */
     public static class GeoPoint {
          public Geometry geometry;
          public Point point;

     @Override
          public String toString() {
               return "GeoPoint{" +
                       "geometry=" + geometry +
                       ", point=" + point +
                       '}';
          }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (!(o instanceof GeoPoint geoPoint)) return false;
          return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
     }

     /**
      * Constructs a GeoPoint object with the specified geometry and point.
      *
      * @param geometry The geometry of the intersection.
      * @param point    The point of intersection.
      */
     public GeoPoint(Geometry geometry, Point point) {
               this.geometry = geometry;
               this.point = point;
          }
          /**
           * Returns the x-coordinate of the intersection point.
           *
           * @return The x-coordinate of the intersection point.
           */
          public double getX() {
               return point.getX();
          }
          /**
           * Returns the y-coordinate of the intersection point.
           *
           * @return The y-coordinate of the intersection point.
           */
          public double getY() {
               return point.getY();
          }

          /**
           * Returns the z-coordinate of the intersection point.
           *
           * @return The z-coordinate of the intersection point.
           */
          public double getZ() {
               return point.getZ();
          }
     }

}
