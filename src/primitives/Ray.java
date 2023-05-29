package primitives;
import java.util.List;
import java.util.Objects;

/** This class will represent a ray which is an argument that keep all the points on
 * @author Tzofiya david 209918374
 *  * and Shira ben shimol 326065976 */
public class Ray
{
    /* this is the first point */
    final private Point p0;
    /*this is the direction of the points*/
    final private Vector dir;


    /**
     * this is a constructor that get vec and point and analyzing them
     * @param p0 this is a point to analyzing
     * @param v1 this is a vector that we're analyzing
     */
    public Ray(Point p0, Vector v1)
    {
        this.p0 = p0;
        this.dir = v1.normalize();
    }

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
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
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
    public Point getPoint(double t)
    {
        return p0.add(dir.scale(t));
    }
    public Point findClosetPoint(List<Point> collection)
    {

    }

}
