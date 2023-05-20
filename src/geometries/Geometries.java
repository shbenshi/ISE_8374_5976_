package geometries;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import primitives.Point;
import primitives.Ray;


public class Geometries implements Intersectable {
    private List<Intersectable> geoList;

    public Geometries()
    {
        this.geoList = new LinkedList<Intersectable>();
    }
    public Geometries(Intersectable...geometry) {
        geoList = new LinkedList<Intersectable>();
        Collections.addAll(geoList, geometry);
    }
    public void add(Intersectable... geometries){
        Collections.addAll(geoList,geometries);//check

    }
    // njnj
    public List<Point> findIntsersections(Ray ray) {
                     //findIntsersections
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
