package primitives;
import java.util.List;
import java.util.Objects;
import renderer.RayTracerBasic;
import geometries.Intersectable.GeoPoint;


/** This class will represent a ray which is an argument that keep all the points on
 * @author Tzofiya david 209918374
 *  * and Shira ben shimol 326065976 */
public class Ray
{
    /* this is the first point */
    final private Point p0;
    /*this is the direction of the points*/
    final private Vector dir;
    private static final double DELTA = 0.1;



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
     * Constructs a new Ray object with the specified starting point, direction, and surface normal.
     *
     * @param p0  The starting point of the ray
     * @param n   The surface normal vector
     * @param dir The direction vector of the ray
     */
    public Ray(Point p0, Vector n, Vector dir) {
        double eps= dir.dotProduct(n)>=0? DELTA :-DELTA;
        this.p0 = p0.add(n.scale(eps));
        this.dir = dir.normalize();
    }


    /**
     * this func convert the parameters to string
     * @return string of all the parameters
     */
    @Override
    public String toString() {
        return p0.toString() + dir.toString();
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
     * this is a get funcc to the point
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
    /**
     * Returns a point on the ray at a given parameter value (t).
     * The point is calculated by adding the scaled direction vector to the ray's starting point.
     *
     * @param t The parameter value along the ray
     * @return The point on the ray at parameter t
     */
    public Point getPoint(double t)
    {
        if (t==0)
            return p0;
        return p0.add(dir.scale(t));
    }
    /**
     * Finds the closest GeoPoint from a list of GeoPoints.
     * The closest GeoPoint is determined based on the distance between the ray's starting point (p0)
     * and the points in the list. Returns null if the list is null or empty.
     *
     * @param intsersections The list of GeoPoints to find the closest one from
     * @return The closest GeoPoint, or null if the list is null or empty
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intsersections) {
        if (intsersections == null || intsersections.isEmpty())
            return null;
        double minDistance = Double.MAX_VALUE;
        GeoPoint minPoint = intsersections.get(0);
        for (GeoPoint gp : intsersections) {
            double dist = gp.point.distanceSquared(p0);
            if (dist < minDistance) {
                minDistance = dist;
                minPoint = gp;
            }
        }
        return minPoint;
    }
    /**
     * Finds the closest point to a given point from a collection of points.
     *
     * @param points A list of points to search for the closest point.
     * @return The closest point from the collection, or null if the collection is null.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream()
                .map(p -> new GeoPoint(null, p))
                .toList()).point;
    }

}
