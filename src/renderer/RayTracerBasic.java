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
    //private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double DELTA = 0.1;

    /* private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource, double nv) {
        Vector lightDirection = l.scale(-1); //vector from the point to the light source

        Ray lightRay = new Ray(gp.point, lightDirection,n);

        double lightDistance = lightSource.getDistance(gp.point);
        //finding only points that are closer to the point than the light
        List<GeoPoint> intersections = _scene.geometries.findGeoIntsersections(lightRay);

        //if there are no intersections return true (there is no shadow)
        if (intersections == null) {
            return true;
        }

        //for each intersection
        for (GeoPoint intersection : intersections) {
            //if the material is not transparent return false
            if (intersection.geometry.getMaterial().kT == Double3.ZERO) {
                return false;
            }

        }
        return true;
    }*/
/*    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA); // move the point to the direction of the normal
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(gp.point, n, lightDirection); // ray to check shading
        var intersections = _scene.getGeometries().findGeoIntersections(lightRay);
        if (intersections == null)
            return true;
        double lightDistance = lightSource.getDistance(gp.point);
        for (GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint.point.distance(gp.point) - lightDistance) <= 0 &&
                geoPoint.geometry.getMaterial().getKt() == Double3.ZERO)
                    return false;
        }
        return true;
     }*/

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

        /*List<GeoPoint> intersectionsPoints = this._scene.geometries.findGeoIntsersections(ray);
        if (intersectionsPoints == null)
            return this._scene.sceneBack;
        GeoPoint geoPoint = ray.findClosestGeoPoint(intersectionsPoints);
        return calcColor(geoPoint, ray);*/
        /*List<Point> intersectionsPoints = this._scene.geometries.findIntsersections(ray);
        if (intersectionsPoints == null)
            return this._scene.sceneBack;
        return calcColor(ray.findClosestPoint(intersectionsPoints));*/
    }
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return _scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray)); //for now return the ambient light intensity

/*        Color result = this._scene.ambientLight.getIntensity();  // add the ambient light to the color

        result = result.add(geoPoint.geometry.getEmission());   // add emission light to the color

        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDir();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return result;

        Double3 kD = geoPoint.geometry.getMaterial().kD;    // the diffuse factor - percentage of light dispersal
        // specular factor - the percentage of the energy that goes to direct reflection
        Double3 kS = geoPoint.geometry.getMaterial().kS;
        Point point = geoPoint.point;
        int Nsh = geoPoint.geometry.getMaterial().nShininess;   // the shininess

        for (LightSource lightSource:this._scene.lights) { // add diffuse and specular for each light source
            Vector l = lightSource.getL(point);
            double nl = alignZero(n.dotProduct(l));
            if(nl * nv > 0) {
                Color IL = lightSource.getIntensity(point); // get the intensity of the light source
                Vector r = l.add(n.scale(-2 * nl)); // calculate r = l+(-2*nl*n)
                result = result.add(
                        IL.scale(getDiffuse(kD,nl)  // add diffuse
                                .add(getSpecular(kS,v,r,Nsh)))); // add specular
            }
        }
        return result;*/
    }
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nl){
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nl<0 ? DELTA : -DELTA); // move the point to the direction of the normal
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection); // ray to check shading
        var intersections = _scene.geometries.findGeoIntsersections(lightRay);
        if (intersections == null) return true;
        double lightDistance = lightSource.getDistance(gp.point);
        for (GeoPoint geoPoint : intersections){ //if there are points in the intersections list that are closer to the point than lightDistance, the point is shaded
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
            if (nl * nv > 0) { // sign(nl) == sing(nv)
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
