package geometries;

import primitives.Point;
import primitives.Vector;


/**
 * The Geometry interface
 *   @author Tzofiya david 209918374
 *   and Shira ben shimol 326065976 */

public interface Geometry extends Intersectable {

    /**
     * gett func
     */
    public Vector getNormal(Point p1);

}
