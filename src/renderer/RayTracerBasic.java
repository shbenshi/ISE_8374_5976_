package renderer;

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
public class RayTracerBasic extends RayTracerBase {
    //private static final double DELTA = 0.1;
    private static final Double3 INITIAL_K = Double3.ONE;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;


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
        var intersections = _scene.getGeometries().findGeoIntsersections(ray);
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return closestPoint == null ? _scene.getSceneBack() : calcColor(closestPoint, ray);

    }

    /**
     * Calculates the color at the intersection point of a ray with the scene.
     *
     * @param closestPoint The closest intersection point.
     * @param ray          The ray being traced.
     * @return The color at the intersection point.
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.getAmbientLight().getIntensity());
    }

    /*
     * @param intersection - the intersection point
     * @param ray           - the ray
     * @param level         - the level of recursion
     * @param k             - the k for the recursion
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray, k);
        return 1 == level ? color
                : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * Calculates the global effects of reflection and refraction at the intersection point.
     *
     * @param gp    The intersection point.
     * @param ray   The ray being traced.
     * @param level The recursion level.
     * @param k     The current coefficient of transparency.
     * @return The color resulting from the global effects.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructReflectedRay(gp, v, n),
                level, k, material.kR)
                .add(calcGlobalEffect(constructRefractedRay(gp, v, n),
                        level, k, material.kT));
    }
    /**
     * Calculates the effect of reflection or refraction at a specific intersection point.
     *
     * @param ray  The reflected or refracted ray.
     * @param level The recursion level.
     * @param k     The current coefficient of transparency.
     * @param kx    The coefficient of reflection or refraction.
     * @return The color resulting from the effect.
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;

        GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) return _scene.getSceneBack().scale(kx);

        return Util.isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))
                ? Color.BLACK : calcColor(gp, ray, level - 1, kkx).scale(kx);
    }



    /*    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nl){
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
        }*/
    /**
     * Calculates the local effects of diffusive and specular reflection at an intersection point.
     *
     * @param gp  The intersection point.
     * @param ray The ray being traced.
     * @param k   The current coefficient of transparency.
     * @return The color resulting from the local effects.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = gp.geometry.getEmission();
        Vector vector = ray.getDir();
        Vector normal = gp.geometry.getNormal(gp.point);
        double nv = alignZero(normal.dotProduct(vector));
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : _scene.getLights()) {
            Vector lightVector = lightSource.getL(gp.point);
            double nl = alignZero(normal.dotProduct(lightVector));
            if (nl * nv > 0) {
                Double3 ktr = transparency(gp, lightSource, lightVector, normal);

                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(lightIntensity.scale(calcDiffusive(material, nl)),
                            lightIntensity.scale(calcSpecular(material, normal, lightVector, nl, vector)));
                }
            }
        }
        return color;
    }

    /**
     * Calculates the specular reflection color at an intersection point.
     *
     * @param material The material of the intersected geometry.
     * @param n        The normal vector at the intersection point.
     * @param l        The vector towards the light source.
     * @param nl       The dot product of the normal and light vectors.
     * @param v        The direction vector of the ray.
     * @return The color resulting from the specular reflection.
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double max = -r.dotProduct(v);
        return alignZero(max) > 0 ? material.kS.scale(Math.pow(max, material.nShininess)) : Double3.ZERO;
    }

    /**
     * Calculates the diffusive reflection color at an intersection point.
     *
     * @param material The material of the intersected geometry.
     * @param nl       The dot product of the normal and light vectors.
     * @return The color resulting from the diffusive reflection.
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(nl < 0 ? -nl : nl);
    }

    /**
     * Constructs a refracted ray at an intersection point.
     *
     * @param gp The intersection point.
     * @param v  The direction vector of the incident ray.
     * @param n  The normal vector at the intersection point.
     * @return The refracted ray.
     */
    private Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n) {
        return new Ray(gp.point, n, v);
    }


    /**
     * Constructs a reflected ray at an intersection point.
     *
     * @param gp The intersection point.
     * @param v  The direction vector of the incident ray.
     * @param n  The normal vector at the intersection point.
     * @return The reflected ray.
     */
    private Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n) {
        double vn = v.dotProduct(n);
        if (vn == 0) {
            return null;
        }
        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(gp.point, n, r);
    }

    /**
     * Finds the closest intersection of a ray with the scene.
     *
     * @param ray The ray being traced.
     * @return The closest intersection point.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var intersections = _scene.getGeometries().findGeoIntsersections(ray);
        return ray.findClosestGeoPoint(intersections);
    }


    /**
     * Calculates the coefficient of transparency for a ray passing through a geometry to a light source.
     *
     * @param gp     The intersection point between the ray and the geometry.
     * @param light  The light source.
     * @param l      The direction vector towards the light source.
     * @param n      The normal vector at the intersection point.
     * @return The coefficient of transparency.
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, n, lightDirection); // ray to check shading
        var intersections = _scene.getGeometries().findGeoIntsersections(lightRay);
        if (intersections == null)
            return Double3.ONE;
        Double3 ktr = Double3.ONE;
        for (GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint.point.distance(gp.point) - light.getDistance(gp.point)) <= 0) {
                ktr = ktr.product(geoPoint.geometry.getMaterial().kT);
                if (ktr.equals(Double3.ZERO)) {
                    break;
                }
            }
        }
        return ktr;

    }
}





