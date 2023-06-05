package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

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
        var intersections = _scene.getGeometries().findGeoIntersections(ray);
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return closestPoint == null ? _scene.getSceneBack() : calcColor(closestPoint, ray);

        /*List<Point> intersectionsPoints = this._scene.geometries.findIntsersections(ray);
        if (intersectionsPoints == null)
            return this._scene.sceneBack;
        return calcColor(ray.findClosestPoint(intersectionsPoints));*/
    }

    /**
     * Calculates the color at the specified point using the scene's ambient light.
     *
     * @param point The point at which to calculate the color.
     * @return The color at the specified point.
     */
    private Color calcColor(GeoPoint intersection, Ray point) {
        return this._scene.ambientLight.getIntensity();  // at this point of the project we'll return the ambient light
    }
}
