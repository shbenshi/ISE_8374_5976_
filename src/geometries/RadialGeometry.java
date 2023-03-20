package geometries;

/** RadialGeometry abstract class that implements th geometry interface. and is based on radius
 * system
 * @author Tzofiya and Shira*/
public abstract class RadialGeometry implements Geometry
{
    /**  radius field with permission protected */
    protected double radius;

    /**
     * A constructor that accepts a value for the radius as a parameter and initializes the field
     * @param radius The radius value
     */
    public RadialGeometry(double radius)
    {
        this.radius = radius;
    }

    /**
     *getter operationn
     * @return radius The radius value
     */
    public double getRadius()
    {
        return radius;
    }
}
