package primitives;

/** This class represent a vector that is a point with direction
 * @author Tzofiya david 209918374
 *  * and Shira ben shimol 326065976 */
public class Vector extends Point
{
    /**
     * this is a constructor that get 3 points and analyzing
     * @param d1 first point
     * @param d2 second point
     * @param d3 third point
     */
    public Vector(double d1, double d2, double d3)
    {
        super(d1, d2, d3);
        Double3 p1 = new Double3(d1,d2,d3);
        if (p1.equals(p1.ZERO))
            throw new IllegalArgumentException("ERROR: the vector equals to zero");
    }

    /**
     * this constructor get point of double xyz and analyzing him
     * @param xyz a point of three
     */
    Vector(Double3 xyz)
    {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("ERROR: the vector equals to zero");
    }
    @Override
    public String toString()
    {
        return "Vector{}";
    }

    /**
     * this func add two vectors
     * @param v1 a vector that we add
     * @return the results of the sum
     */
    public Vector add(Vector v1)
    {
        return new Vector(xyz.add(v1.xyz));
    }

    /**
     * this func Multiplier a vec by scalar
     * @param num the scalar
     * @return the result of the Multiplier
     */
    public Vector scale(double num)
    {
        return new Vector(xyz.scale(num));
    }

    /**
     * create a new vec that cross
     * @param v1 a vec that we went to cross to
     * @return new vwc that cross to two vectors
     */
    public Vector crossProduct(Vector v1)
    {
        return new Vector(xyz.d2 *v1.xyz.d3 - xyz.d3 * v1.xyz.d2, xyz.d3 *v1.xyz.d1 - xyz.d1 * v1.xyz.d3,xyz.d1 *v1.xyz.d2 - xyz.d2 * v1.xyz.d1);
    }

    /**
     * calculate the length of vec while squared
     * @return the squared vec
     */
    public double lengthSquared()
    {
        return xyz.d1* xyz.d1 + xyz.d2* xyz.d2 + xyz.d3* xyz.d3;
    }

    /**
     * return the length of vec after sqrt by calling another func
     * @return the length of vec after sqrt
     */
    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    /**
     *  normal the vec
     * @return new normal vec
     */
    public Vector normalize()
    {
        double lengthVec = Math.sqrt(dotProduct(this));
        return this.scale(1/lengthVec);

    }

    /**
     * multiply scalar
     * @param v1 vec to make on him multiply
     * @return  result after multiply
     */
    public double dotProduct(Vector v1)
    {
        return xyz.d1*v1.xyz.d1 + xyz.d2*v1.xyz.d2 + xyz.d3*v1.xyz.d3;
    }





}
