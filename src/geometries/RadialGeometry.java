package geometries;

/**
 * The RadialGeometry class is an abstract class that implements the Geometry interface and is based on radius.
 * It represents a three-dimensional geometric object with a radial component.
 * This class is meant to be extended by other geometry classes that have a radius field.
 * @author Tzofiya
 * @author Shira
 */
public abstract class RadialGeometry extends Geometry
{
    /** The radius field with protected access */
    protected double radius;

    /**
     * Constructs a new RadialGeometry object with the specified radius.
     * @param radius The radius value of the RadialGeometry object.
     */
    public RadialGeometry(double radius)
    {
        this.radius = radius;
    }

    /**
     * Returns the radius value of the RadialGeometry object.
     * @return The radius value of the RadialGeometry object.
     */
    public double getRadius()
    {
        return radius;
    }
}
