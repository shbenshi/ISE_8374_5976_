package geometries;
import primitives.*;
import java.util.List;
public abstract class Intersectable {
     public List<Point> findIntsersections(Ray ray) {
          return null;
     }
     public static class GeoPoint {
          public Geometry geometry;
          public Point point;

          public GeoPoint(Geometry geometry, Point point) {
               this.geometry = geometry;
               this.point = point;
          }
     }

}
