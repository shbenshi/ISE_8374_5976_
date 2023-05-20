package geometries;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import primitives.Point;
import primitives.Ray;


/**
 * Geometries represents a collection of intersectable geometries.
 * It implements the Intersectable interface, allowing for ray intersection tests.
 * The class provides methods to add geometries to the collection and find intersections
 * with a given ray.
 *
 * @author Tzofiya and Shira
 */

public class Geometries implements Intersectable {
    private List<Intersectable> geoList;

    /**
     * Constructs an empty Geometries object.
     */
    public Geometries()
    {
        this.geoList = new LinkedList<Intersectable>();
    }

    /**
     * Constructs a Geometries object initialized with the given intersectable geometries.
     *
     * @param geometry An array of intersectable geometries to add to the collection.
     */
    public Geometries(Intersectable...geometry) {
        geoList = new LinkedList<Intersectable>();
        Collections.addAll(geoList, geometry);
    }

    /**
     * Adds one or more intersectable geometries to the collection.
     *
     * @param geometries An array of intersectable geometries to add to the collection.
     */
    public void add(Intersectable... geometries){
        Collections.addAll(geoList,geometries);//check

    }

    /**
     * Finds the intersections between the collection of geometries and a given ray.
     *
     * @param ray The ray to find intersections with.
     * @return A list of intersection points with the ray, or null if no intersections are found.
     */
    public List<Point> findIntsersections(Ray ray) {
        List<Point> result = new LinkedList<>();
        for (Intersectable geo : geoList) {
            List<Point> temp = geo.findIntsersections(ray);
            if (temp != null) {
                result.addAll(temp);
            }
        }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }
}
