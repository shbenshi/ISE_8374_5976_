package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * This class represents a basic implementation of a ray tracer.
 * It extends the {@link RayTracerBase} class and provides a simple implementation of the {@link #traceRay(Ray)} method.
 * The traceRay method traces a ray in the scene and determines the color at the intersection point.
 * Author:
 * Tzofiya David (209918374)
 * Shira Ben Shimol (326065976)
 */
public class RayTracerBasic extends RayTracerBase{
    /**
     * Constructs a RayTracerBasic object with the specified scene.
     *
     * @param _scene The scene to be rendered.
     */
    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    /**
     * Traces a ray and determines the color at the intersection point with the scene.
     *
     * @param ray The ray to be traced.
     * @return The color at the intersection point.
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersectionsPoints = this._scene.geometries.findIntsersections(ray);
        if (intersectionsPoints == null)
            return this._scene.sceneBack;
        return calcColor(ray.findClosestPoint(intersectionsPoints));
    }

    /**
     * Calculates the color at the specified point using the scene's ambient light.
     *
     * @param point The point at which to calculate the color.
     * @return The color at the specified point.
     */
    private Color calcColor(Point point) {
        return this._scene.ambientLight.getIntesity();  // at this point of the project we'll return the ambient light
    }
}
