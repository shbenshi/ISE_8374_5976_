package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;



/**

 The Tube class inherits from RadialGeometry and represents a three-dimensional body based on radius and ray system.
 It is defined by a radius and a ray that defines its central axis.
 @author Tzofiya David and Shira Ben Shimol
 */
public class Tube extends RadialGeometry{

    /**
     * The central axis of the tube
     */
    protected final Ray axisRay;

    /**
     * Constructs a new Tube object with a given radius and a central axis Ray object.
     *
     * @param _axisRay The central axis Ray object of the tube
     * @param _radius  The radius of the tube
     */
    public Tube(Ray _axisRay, double _radius)
    {
        super(_radius);
        this.axisRay = _axisRay;
    }

    /**
     * Returns the normal vector at a given point on the tube.
     *
     * @param p1 A point on the tube
     * @return A normalized Vector object perpendicular to the tube at the given point
     */
    public Vector getNormal(Point p1)
    {
        if ((p1.subtract(axisRay.getP0())).dotProduct(axisRay.getDir()) == 0)
        {
            return p1.subtract(axisRay.getP0());
        }
        double t = ((p1.subtract(axisRay.getP0())).dotProduct(axisRay.getDir()));
        Point o = (axisRay.getP0()).add((axisRay.getDir()).scale(t));
        return ((p1.subtract(o).normalize()));   }
    /**
     * Returns the central axis Ray object of the tube.
     * @return The central axis Ray object of the tube
     */
    public Ray getAxisRay()
    {
        return axisRay;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {return null;}
}
