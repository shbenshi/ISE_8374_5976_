package geometries;
import primitives.*;
import java.util.List;

public abstract class Intersectable {
     protected abstract List<GeoPoint> findGeoIntsersectionsHelper(Ray ray) ;
     public List<GeoPoint> findGeoIntsersections(Ray ray)  {
          return findGeoIntsersectionsHelper(ray);
     }
     public List<Point> findIntsersections(Ray ray) {
          var geoList = findGeoIntsersections(ray);
          return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
     }
     /*public final List<GeoPoint> findGeoIntersections(Ray ray) {

          return findGeoIntersectionsHelper(ray);
     }
     //should be public
     protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

     public  List<Point> findIntsersections(Ray ray) {
          var geoList = findGeoIntersections(ray);
          return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
     }*/
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


     public GeoPoint(Geometry geometry, Point point) {
               this.geometry = geometry;
               this.point = point;
          }
          public double getX() {
               return point.getX();
          }

          public double getY() {
               return point.getY();
          }

          public double getZ() {
               return point.getZ();
          }
     }

}
