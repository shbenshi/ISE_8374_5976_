package geometries;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import primitives.Point;
import primitives.Ray;


public class Geometries {
    private List<Intersectable> geoList;

    public Geometries()
    {
        this.geoList = new LinkedList<>();
    }
    public Geometries(Intersectable...geometry) {
        geoList = List.of(geometry);
    }
    public void add(Intersectable... geometries){
        geoList.addAll(Arrays.asList(geometries));//check

    }
    public List<Point> findIntersections(Ray ray){
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
