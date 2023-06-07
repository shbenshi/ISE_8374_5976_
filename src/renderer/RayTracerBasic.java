package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

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
    private static final double DELTA = 0.1;


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
    public Color traceRay(Ray ray){
        var intersections = _scene.geometries.findGeoIntsersections(ray);
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return closestPoint == null ? _scene.sceneBack : calcColor(closestPoint, ray);

    }
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return _scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray));
    }
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nl){
        Vector lightDirection = l.scale(-1);
        Vector epsVector = n.scale(nl<0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        var intersections = _scene.geometries.findGeoIntsersections(lightRay);
        if (intersections == null) return true;
        double lightDistance = lightSource.getDistance(gp.point);
        for (GeoPoint geoPoint : intersections){
            if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0) return false;
        }
        return true;
    }
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {
                if (unshaded(gp, lightSource, l, n,nl)) {
                    Color iL = lightSource.getIntensity(gp.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }


    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double max = -r.dotProduct(v);
        return alignZero(max) > 0 ? material.kS.scale(Math.pow(max, material.nShininess)) : Double3.ZERO;
    }

    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(nl < 0 ? -nl : nl);
    }



}
