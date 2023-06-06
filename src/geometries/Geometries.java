package geometries;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import primitives.Point;
import primitives.Ray;
import java.util.ArrayList;

/**
 * Geometries represents a collection of intersectable geometries.
 * It implements the Intersectable interface, allowing for ray intersection tests.
 * The class provides methods to add geometries to the collection and find intersections
 * with a given ray.
 *
 * @author Tzofiya and Shira
 */

public class Geometries extends Intersectable {
    private  List<Intersectable> geometries;

    /**
     * Constructs an empty Geometries object.
     */
    public Geometries()
    {
        this.geometries = new LinkedList<Intersectable>();
    }

    /**
     * Constructs a Geometries object initialized with the given intersectable geometries.
     *
     * @param geometry An array of intersectable geometries to add to the collection.
     */
    public Geometries(Intersectable...geometry) {
        geometries = new LinkedList<Intersectable>();
        Collections.addAll(geometries, geometry);
    }

    /**
     * Adds one or more intersectable geometries to the collection.
     *
     * @param geometries An array of intersectable geometries to add to the collection.
     */
    public void add(Intersectable... geometries){
        Collections.addAll(this.geometries,geometries);//check

    }

    /**
     * Finds the intersections between the collection of geometries and a given ray.
     *
     * @param ray The ray to find intersections with.
     * @return A list of intersection points with the ray, or null if no intersections are found.
     */
    public List<GeoPoint> findGeoIntsersectionsHelper(Ray ray) {
        //if there are no intersections return null
        List<GeoPoint> result = new LinkedList<>();
        for (Intersectable geo : geometries) {
            List<GeoPoint> temp = geo.findGeoIntsersectionsHelper(ray);
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
