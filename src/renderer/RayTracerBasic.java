package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersectionsPoints = this._scene.geometries.findIntsersections(ray);
        if (intersectionsPoints == null)
            return this._scene.sceneBack;
        return calcColor(ray.findClosestPoint(intersectionsPoints));
    }

    private Color calcColor(Point point) {
        return this._scene.ambientLight.getIntesity();  // at this point of the project we'll return the ambient light
    }
}
