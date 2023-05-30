package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;


/**
 * The Geometry interface
 *   @author Tzofiya david 209918374
 *   and Shira ben shimol 326065976 */

public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;

    public void setEmission(Color emission) {
        this.emission = emission;
    }

    public Color getEmission() {
        return emission;
    }

    /**
     * gett func
     */
    public abstract Vector getNormal(Point p1);

}
