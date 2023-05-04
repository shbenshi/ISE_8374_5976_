package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/** The Tube class inherits from RadialGeometry and represents a three-dimensional body and is based on radius and ray
 * system
 * @author Tzofiya david 209918374
 * and Shira ben shimol 326065976 */
public class Tube extends RadialGeometry{

    /*a ray*/
    protected Ray axisRay;

    /**
     * constructor thatt call to the father func
     * @param _radius get radius and analyzing him
     */
    public Tube(Ray _axisRay, double _radius)
    {
        super(_radius);
        axisRay = _axisRay;
    }

    /**
     * a get func for point
     * @param p1 a point
     * @return null
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
     * a get func that return the ray
     * @return ray
     */
    public Ray getAxisRay()
    {
        return axisRay;
    }
}
