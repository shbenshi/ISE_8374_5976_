package geometries;
import primitives.*;
import java.util.List;
import java.util.Objects;

public abstract class Intersectable {
     public List<Point> findIntsersections(Ray ray) {
          return null;
     }

     public List<GeoPoint> findGeoIntersections() {
          return null;
     }

     protected List<GeoPoint> findGeoIntersectionsHelper;
     public static class GeoPoint {
          public Geometry geometry;
          public Point point;

          @Override
          public boolean equals(Object o) {
               if (this == o) return true;
               if (o instanceof GeoPoint other)
                    return this.geometry.equals(other.geometry) && this.point.equals(other.point);
               return false;
          }

          @Override
          public int hashCode() {
               return Objects.hash(geometry, point);
          }

          @Override
          public String toString() {
               return "GeoPoint{" +
                       "geometry=" + geometry +
                       ", point=" + point +
                       '}';
          }

          public GeoPoint(Geometry geometry, Point point) {
               this.geometry = geometry;
               this.point = point;
          }
     }

}
