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

    @Override
    public List<GeoPoint> findGeoIntsersectionsHelper(Ray ray) {
        if(this.plane.findGeoIntsersectionsHelper(ray)==null){
            return null;
        };

        Point P0=ray.getP0();
        Vector v=ray.getDir();

        Point P1=this.vertices.get(0);
        Point P2=this.vertices.get(1);
        Point P3=this.vertices.get(2);

        Vector v1=P1.subtract(P0);
        Vector v2=P2.subtract(P0);
        Vector v3=P3.subtract(P0);

        double n1v=v1.crossProduct(v2).normalize().dotProduct(v);
        double n2v=v2.crossProduct(v3).normalize().dotProduct(v);
        double n3v=v3.crossProduct(v1).normalize().dotProduct(v);
        if((n1v==0)||(n2v==0)||(n3v==0)){
            return null;
        } else if ((n1v>0)&&(n2v>0)&&(n3v>0)) {
            Point point = plane.findGeoIntsersectionsHelper(ray).get(0).point;
            return  List.of(new GeoPoint(this, point));
        } else if ((n1v<0)&&(n2v<0)&&(n3v<0)) {
            Point point = plane.findGeoIntsersectionsHelper(ray).get(0).point;
            return  List.of(new GeoPoint(this, point));
        }
        else{
            return null;
        }


    }


}
