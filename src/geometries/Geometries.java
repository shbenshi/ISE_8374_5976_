package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import primitives.Point;
import primitives.Ray;


public class Geometries {
    private List<Intersectable> geoList;

    public Geometries() {
        this.geoList = new LinkedList<>();
    }
    public Geometries(Intersectable...geometries){

    }
    public void add(Intersectable... geometries){

    }
    public List<Point> findIntersections(Ray ray){
        return null;

    }
    //
}
