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
    Ray axisRay;

    /**
     * constructor thatt call to the father func
     * @param radius get radius and analyzing him
     */
    public Tube(double radius)
    {
        super(radius);
    }

    /**
     * a get func for point
     * @param p1 a point
     * @return null
     */
    public Vector getNormal(Point p1) {
        return null;
    }
    /**
     * a get func that return the ray
     * @return ray
     */
    public Ray getAxisRay()
    {
        return axisRay;
    }
}
