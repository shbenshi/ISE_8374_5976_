package primitives;

/**
 This class represents a vector, which is a point with direction.
 It extends the Point class and includes methods for vector addition,
 scalar multiplication, cross product, and normalization, as well as
 methods for calculating the length and dot product of the vector.
 @author Tzofiya David (209918374) and Shira Ben Shimol (326065976)
 */
public class Vector extends Point
{
    /**
     * Constructs a new Vector object using three double values as the x, y, and z coordinates.
     * @param d1 The x coordinate of the point.
     * @param d2 The y coordinate of the point.
     * @param d3 The z coordinate of the point.
     * @throws IllegalArgumentException if the vector equals to zero.
     */
    public Vector(double d1, double d2, double d3)
    {
        super(d1, d2, d3);
        Double3 p1 = new Double3(d1,d2,d3);
        if (p1.equals(Double3.ZERO))
            throw new IllegalArgumentException("ERROR: the vector equals to zero");
    }
    /**
     * Constructs a new Vector object using a Double3 object as the point.
     * @param xyz The Double3 object representing the point.
     * @throws IllegalArgumentException if the vector equals to zero.
     */
    Vector(Double3 xyz)
    {
        this(xyz.d1,xyz.d2,xyz.d3);

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o instanceof Vector vector))return super.equals(o);
        return false;    }


    /**
     * Returns a string representation of the Vector object.
     * @return A string representation of the Vector object.
     */
    @Override
    public String toString()
    {
        return "Vector{ " +Double3.ZERO.toString()+super.toString()
                +"}";
    }

    /**
     * Adds two Vector objects together.
     * @param v1 The Vector object to be added.
     * @return A new Vector object representing the sum of the two vectors.
     */
    public Vector add(Vector v1)
    {
        //Point p=super.add(v1);
        //return new Vector(p.xyz);
        return new Vector(xyz.add(v1.xyz));
    }

    /**
     * Multiplies the Vector object by a scalar.
     * @param num The scalar to multiply the vector by.
     * @return A new Vector object representing the result of the scalar multiplication.
     */
    public Vector scale(double num)
    {
        return new Vector(xyz.scale(num));
    }

    /**
     * Calculates the cross product of two Vector objects.
     * @param v1 The Vector object to cross with.
     * @return A new Vector object representing the cross product of the two vectors.
     */
    public Vector crossProduct(Vector v1)
    {
        return new Vector(xyz.d2 *v1.xyz.d3 - xyz.d3 * v1.xyz.d2, xyz.d3 *v1.xyz.d1 - xyz.d1 * v1.xyz.d3,xyz.d1 *v1.xyz.d2 - xyz.d2 * v1.xyz.d1);
    }

    /**
     * Calculates the length of the Vector object squared.
     * @return The length of the Vector object squared.
     */
    public double lengthSquared()
    {
       return xyz.d1* xyz.d1 + xyz.d2* xyz.d2 + xyz.d3* xyz.d3;
       // return dotProduct(this);
    }

    /**
     * Calculates the length of the Vector object.
     * @return The length of the Vector object.
     */
    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes the Vector object.
     * @return A new Vector object representing the normalized vector.
     */
    public Vector normalize()
    {
        double lengthVec = Math.sqrt(dotProduct(this));
        return this.scale(1/lengthVec);
        /*double len=length();
        double x=  xyz.d1/len;
        double y = xyz.d2/len;
        double z = xyz.d3/len;
        return new Vector(x,y,z);*/
    }

    /**
     Calculates the dot product of this vector with the given vector.
     @param v1 the vector to calculate the dot product with.
     @return the dot product of this vector with the given vector.
     */
    public double dotProduct(Vector v1)
    {
        return this.xyz.d1 * v1.xyz.d1 + this.xyz.d2 * v1.xyz.d2 + this.xyz.d3 * v1.xyz.d3;
    }





}
