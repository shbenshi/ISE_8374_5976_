package primitives;

import java.util.Objects;
/**
 The Point class represents a point in three-dimensional space.
 It provides methods to perform basic operations on points, such as addition, subtraction, and distance calculation.
 @author Tzofiya David and Shira Ben Shimol
 */
public class Point
{

    /**
     * A Double3 object representing the point's three coordinates.
     */
    final Double3 xyz;
    //protected String toString;

    /**
     * Constructs a new Point object with the specified coordinates.
     *
     * @param _xyz The coordinates of the point as a Double3 object.
     */
     Point(Double3 _xyz)
     {
        this.xyz = _xyz;
     }

    /**
     * Constructs a new Point object with the specified coordinates.
     * @param _d1 The X coordinate of the point.
     * @param _d2 The Y coordinate of the point.
     * @param _d3 The Z coordinate of the point.
     */
    public Point (double _d1,double _d2, double _d3)
    {
        xyz = new Double3(_d1,_d2,_d3);
    }

    /**
     * Returns a string representation of the Point object.
     * @return A string representation of the Point object.
     */
    @Override
    public String toString()
    {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
    /**
     * Checks whether this Point object is equal to the specified object.
     * @param o The object to compare to this Point.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Point point)) return false;
        return xyz.equals(point.xyz);
    }
    /*
    if (o == null || getClass() != o.getClass()) return false;
    Point point = (Point) o;
    return Objects.equals(xyz, point.xyz);
    }
    /**
     * Returns a hash code value for the Point object.
     * @return A hash code value for the Point object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(xyz);
    }

    /**
     * Calculates the distance between this Point object and the specified Point object.
     * @param p1 The Point to calculate the distance to.
     * @return The distance between this Point and the specified Point.
     */
    public double distance (Point p1)
    {
        return Math.sqrt(distanceSquared(p1));
    }

    /**
     * Calculates the square of the distance between this Point object and the specified Point object.
     * @param p1 The Point to calculate the distance to.
     * @return The square of the distance between this Point and the specified Point.
     */
    public double distanceSquared (Point p1)
    {
        return (xyz.d1 - p1.xyz.d1) * (xyz.d1 - p1.xyz.d1) + (xyz.d2 - p1.xyz.d2) * (xyz.d2 - p1.xyz.d2) + (xyz.d3 - p1.xyz.d3) * (xyz.d3 - p1.xyz.d3);
    }

    /**
     * Subtracts the specified Point object from this Point object and returns the result as a Vector.
     * @param p0 The Point to subtract from this Point.
     * @return A Vector representing the result of the subtraction.
     */
    public Vector subtract (Point p0){
        return new Vector((this.xyz.subtract(p0.xyz)));
    }

    public Point add (Vector v1)
    {
        return new Point(xyz.add(v1.xyz));
    }

    /**
     * get of x
     * @return d1
     */
    public double getX () {
        return this.xyz.d1;
    }


    /**
     * get of y
     * @return d2
     */
    public double getY () {
        return this.xyz.d2;
    }

    /**
     * get of z
     * @return d3
     */
    public double getZ () {
        return this.xyz.d3;
    }

    /**
     * Adds the specified Vector to this Point object and returns the result as a new Point object.
     * @param v1 The Vector to add to this Point.
     * @return A new Point object representing the result of the addition.
     */
}

