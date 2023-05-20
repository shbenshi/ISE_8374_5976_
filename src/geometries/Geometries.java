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
        List<Point> Intersections = null;
        boolean flag = false;
        for (Intersectable geometry : geoList) {
            if (geometry.findIntsersections(ray) != null) {
                if (!flag) {
                    Intersections = geometry.findIntsersections(ray);
                    flag = true;
                } else {
                    Intersections.addAll(geometry.findIntsersections(ray));
                }
            }
        }
        if (flag) {
            return Intersections;
        }
        return null;
    }
}
