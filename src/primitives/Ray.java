package primitives;

import java.util.Objects;

/** This class will represent a ray which is an argument that keep all the points on
 * @author Tzofiya david 209918374
 *  * and Shira ben shimol 326065976 */
public class Ray
{
    /* this is the first point */
    private Point p0;
    /*this is the direction of the points*/
    private Vector dir;

    /**
     * this func convert the parameters to string
     * @return string of all the parameters
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * this func get an object and check if he is equal to this
     * @param o an object that we check his quality
     * @return if they are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    /**
     * this is a constructor that get vec and point and analyzing them
     * @param p0 this is a point to analyzing
     * @param v1 this is a vector that we're analyzing
     */
    public Ray(Point p0, Vector v1)
    {
        this.p0 = p0;
        this.dir = v1;
        v1.normalize();
    }

    /**
     * this is a get func to the point
     * @return the point
     */
    public Point getP0() {
        return p0;
    }
    /**
     * this is a get func to the vec of direction
     * @return the direction
     */
    public Vector getDir() {
        return dir;
    }
}
