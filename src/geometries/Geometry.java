package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import primitives.Material;


/**
 * The Geometry interface
 *   @author Tzofiya david 209918374
 *   and Shira ben shimol 326065976 */

public abstract class Geometry extends Intersectable {
    private Material material = new Material();
    protected Color emission = Color.BLACK;

    public Material getMaterial() {
        return material;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

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
