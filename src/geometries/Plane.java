package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import java.util.List;



/**
 * The Plane class represents a plane in 3D space, defined by a point and a normal vector.
 * It implements the Geometry interface.
 * The plane is essentially a point in space and a vertical vector, normalized to the plane.
 * @author Tzofiya david 209918374
 * and Shira ben shimol 326065976
 */
public class Plane extends Geometry {
    /** A point in the plane. */
    private final Point q0;
    /** The normal vector to the plane. */
    private final Vector normal;

    /**
     * Constructs a plane based on a point in the plane and a vector that is perpendicular to the plane.
     * @param q0     A point in the plane
     * @param normal The vector perpendicular to the plane
     */
    public Plane(Point q0, Vector normal)
    {
        this.q0 = q0;
        if(!(isZero(normal.length()-1d))){
            this.normal = normal.normalize();
        }
        else {this.normal = normal;}
    }

    /**
     * Constructs a plane based on three points in the plane.
     * The constructor takes one of the points as the reference point of the plane.
     * @param q0 A point in the plane that will be the reference point of the plane
     * @param q1 A point in the plane
     * @param q2 A point in the plane
     */
    public Plane(Point q0, Point q1, Point q2) {
        this.q0 = q0;
        if(q0.equals(q1)||q0.equals(q2)||q1.equals(q2))
            throw new IllegalArgumentException("two of the points are identical");

        Vector v1 = q1.subtract(q0);
        Vector v2 = q2.subtract(q0);
        if(v1.normalize().equals(v2.normalize()))
            throw new IllegalArgumentException("there is a linear dependents between the vectors");

        this.normal =  v1.crossProduct(v2).normalize();//normal.normalize();
    }

    /**
     * Returns the normalized normal vector to the plane.
     * @return The normalized normal vector to the plane
     */
    public Vector getNormal()
    {
        return normal;
    }

    /**
     * Returns the normal vector to the plane at a given point, which is always the same for a plane.
     *
     * @param p1 A point in the plane
     * @return The normal vector to the plane
     */
    @Override
    public Vector getNormal(Point p1)
    {
        return getNormal();
    }



    /**
     * Returns a point in the plane.
     * @return A point in the plane
     */
    public Point getQ0() {
        return q0;
    }

    @Override
    public List<GeoPoint> findGeoIntsersectionsHelper(Ray ray) {
        double denominator = this.getNormal().dotProduct(ray.getDir());
        if (isZero(denominator)) {
            return null;
        }
        if (q0.equals(ray.getP0()))
            return null;
        double t = (this.getNormal().dotProduct(q0.subtract(ray.getP0()))) / denominator;
        if (t > 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t)));
        }
        return null;
    }



/*    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        Point P0=ray.getP0();
        Vector v=ray.getDir();
        Vector n=normal;
        if(q0.equals(P0)){
            return null;
        }
        Vector P0_Q0=q0.subtract(P0);
        double nP0Q0=alignZero(n.dotProduct(P0_Q0));

        if(isZero(nP0Q0)){
            return null;
        }
        double nv=alignZero(n.dotProduct(v));
        if(isZero(nv)){
            return null;
        }
        double t=alignZero(nP0Q0/nv);
        if(t<=0){
            return null;
        }
        Point point=ray.getPoint(t);
        return List.of(new GeoPoint(this, point));
    }*/
}
