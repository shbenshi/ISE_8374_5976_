package primitives;

import java.util.Objects;

/** This class will represent a point which is a basic argument im geometry
 * @author Tzofiya david 209918374
 *  * and Shira ben shimol 326065976 */
public class Point
{

    /* this is an object that implement 3 coordinate*/
    Double3 xyz;

    /**
     * this a constructor that get a points
     * @param xyz the points
     */
     Point(Double3 xyz)
    {
        this.xyz = xyz;
    }

    /**
     * a constractor that get 3 points and analyze the double3 object
     * @param _d1 a coordinate
     * @param _d2 a coordinate
     * @param _d3 a coordinate
     */
    public Point (double _d1,double _d2, double _d3)
    {
        xyz = new Double3(_d1,_d2,_d3);
    }

    /**
     * convert points to sring
     * @return string of points
     */
    @Override
    public String toString()
    {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * this func check if two objects are equals
     * @param o an object that we went to check is quality
     * @return if o and this equals
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(xyz);
    }

    /**
     * this func calculate distance by calling to other func
     * @param p1 a point that we check distance from
     * @return the distance between two points
     */
    public double distance(Point p1)
    {
        return Math.sqrt(distanceSquared(p1));
    }
    /**
     * this func calculate distance without sqrt
     * @param p1 a point that we check distance from
     * @return the distance without sqrt between two points
     */
    public double distanceSquared(Point p1)
    {
        return (xyz.d1- p1.xyz.d1)*(xyz.d1- p1.xyz.d1) +(xyz.d2- p1.xyz.d2)*(xyz.d2- p1.xyz.d2)+(xyz.d3- p1.xyz.d3)*(xyz.d3- p1.xyz.d3);
    }

    /**
     * this func does subtract by calling other func
     * @param p1 it is the point that we make subtract on her
     * @return the result of subtract
     */
    public Vector subtract(Point p1) {
        return new Vector((xyz.subtract(p1.xyz)));

    }

    /**
     * this func add point to vec
     * @param v1 a vector that we add point to him
     * @return the result of adding
     */
    public Point add(Vector v1)
    {
        return new Point(xyz.add(v1.xyz));
    }

}
