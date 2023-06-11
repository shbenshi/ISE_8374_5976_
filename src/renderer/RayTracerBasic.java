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
public class RayTracerBasic extends RayTracerBase{
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
    public Color traceRay(Ray ray){
        var intersections = _scene.geometries.findGeoIntsersections(ray);
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return closestPoint == null ? _scene.sceneBack : calcColor(closestPoint, ray);

    }


    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
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
    private Color calcLocalEffects(GeoPoint gp, Ray ray,Double3 k) {
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
                Double3 ktr = transparency(gp, lightSource, l, n, nl);

                if (ktr.product(k).greaterThan(MIN_CALC_COLOR_K)) {
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
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;

        GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) return _scene.sceneBack.scale(kx);

        return Util.isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))
                ? Color.BLACK : calcColor(gp, ray, level - 1, kkx).scale(kx);
    }
    private Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n) {
        return new Ray(gp.point, n, v);
    }

    //constructs the reflected ray
    private Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n) {
        double vn = v.dotProduct(n);
        if (vn == 0) {
            return null;
        }
        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(gp.point, n, r);
    }


    private GeoPoint findClosestIntersection(Ray ray) {
        var intersections = _scene.geometries.findGeoIntsersections(ray);
        return ray.findClosestGeoPoint(intersections);
    }

    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(GeoPoint.point, lightDirection, n);
        List<GeoPoint> intersections = _scene.geometries.findGeoIntsersections(lightRay);

        Double3 ktr = Double3.ONE;
        if (intersections == null) return ktr;

        double distance = LightSource.getDistance(GeoPoint.point);

        for (GeoPoint intersection : intersections) {

            if (distance > intersection.point.distance(GeoPoint.point)) {
                ktr = ktr.product(intersection.geometry.getMaterial().kT);
            }
        }
        return ktr;
    }



}
