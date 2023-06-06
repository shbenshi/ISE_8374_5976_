package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import primitives.Material;


/**
 * The Geometry interface
 *   @author
 *   Tzofiya david 209918374
 *   and Shira ben shimol 326065976
 *   */

public abstract class Geometry extends Intersectable {
    Material material = new Material();
    protected Color emission = Color.BLACK;

    /**
     * Returns the material of the geometry.
     *
     * @return The material of the geometry.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material of the geometry.
     *
     * @param material The material to be set.
     * @return The updated Geometry object.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }


    /**
     * Sets the emission color of the geometry.
     *
     * @param emission The emission color to be set.
     * @return The updated Geometry object.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Returns the emission color of the geometry.
     *
     * @return The emission color of the geometry.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Returns the normal vector to the geometry at the specified point.
     *
     * @param p1 The point for which to calculate the normal vector.
     * @return The normal vector to the geometry at the specified point.
     */
    public abstract Vector getNormal(Point p1);

}
