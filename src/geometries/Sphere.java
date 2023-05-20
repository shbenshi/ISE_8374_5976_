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
    public List<Point> findIntsersections(Ray ray) {
        double rad = getRadius();
        Point P0 = ray.getP0();
        Vector u = ray.getDir();
        if (P0.equals(center))
            return List.of(ray.getPoint(rad));
        Vector v= center.subtract(P0);
        double d1=u.dotProduct(v);
        double d2= alignZero(Math.sqrt(v.lengthSquared()-d1*d1));

        if(d2>=rad||isZero(d2-rad))
            return null;
        if(d2>=rad)
            return null;
        double d =alignZero( Math.sqrt(rad * rad - d2 * d2));

        if (d1 - d > 0 && (d1 + d > 0)) {
            Point p1=ray.getPoint(d1+d2);
            Point p2 = ray.getPoint(d1 - d);
            return List.of(p1,p2);
        }
        if (d1 + d > 0) {
            Point p1 = ray.getPoint(d1 + d);
            return List.of(p1);
        }
        if (d1 - d > 0){
            Point p2 = ray.getPoint(d1 - d);
            return List.of(p2);
        }

        return null;
        /*
        Vector v = ray.getDir();
        Point p0 = ray.getP0();
        if (center.equals(p0)) { // p0 is the center
            return List.of(ray.getPoint(radius));
        }
        Vector u = center.subtract(p0);
        double tm = alignZero(v.dotProduct(u));
        double dSquared = isZero(tm) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(radius * radius - dSquared);
        // if (thSquared <= 0)
        //    return null;
        double th = alignZero(Math.sqrt(thSquared));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        //there is 2 intersection points
        if (t1 > 0 && t2 > 0) {
            Point p1=ray.getPoint(t1);
            Point p2=ray.getPoint(t2);
            return List.of(p1,p2);
        }
        //only t1 intersects the sphere
        if(t1 > 0){
            Point p1=ray.getPoint(t1);
            return List.of(p1);
        }
        //only t2 intersects the sphere
        if(t2 > 0){
            Point p2=ray.getPoint(t2);
            return List.of(p2);
        }
        return null;
*/
    }
}
