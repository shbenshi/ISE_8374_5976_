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
        List<GeoPoint> intersectionsPoints = this._scene.geometries.findGeoIntsersections(ray);
        if (intersectionsPoints == null)
            return this._scene.sceneBack;
        GeoPoint geoPoint = ray.findClosestGeoPoint(intersectionsPoints);
        return calcColor(geoPoint, ray);
        /*List<Point> intersectionsPoints = this._scene.geometries.findIntsersections(ray);
        if (intersectionsPoints == null)
            return this._scene.sceneBack;
        return calcColor(ray.findClosestPoint(intersectionsPoints));*/
    }
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        Color result = this._scene.ambientLight.getIntensity();  // add the ambient light to the color

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
        return result;
    }

    /**
     * calculate the specular light according phong model
     * @param kS specular factor
     * @param v the camera vector
     * @param r reflection
     * @param nsh shininess
     * @return the specular light
     */
    private Double3 getSpecular(Double3 kS, Vector v, Vector r, int nsh) {
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0) {
            return Double3.ZERO; // the color is black if its opposite to the vector r
        }
        return kS.scale(Math.pow(minusVR, nsh));   //ks*((-vr)^nsh)
    }

    /**
     * calculate the diffuse light according to the phong model
     * @param kD the diffuse factor
     * @param nl  dot product of n,l
     * @return the diffuse
     */
    private Double3 getDiffuse(Double3 kD, double nl){
        return kD.scale(Math.abs(nl));
    }

    /**
     * Calculates the color at the specified point using the scene's ambient light.
     *
     * @param ray The point at which to calculate the color.
     * @return The color at the specified point.
     */

}
