package geometries;
import primitives.*;
import java.util.List;
import java.util.Objects;
public interface Intersectable {
    public List<Point> findIntsersections(Ray ray);
}
