package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;



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
       /* public Ray constructReflectedRay(Point p,Vector v, Vector n){
        if (v.dotProduct(n)==0)
            return new Ray(p,v);
        Ray Reflected= new Ray(p,(v.subtract(n.scale(2*(v.dotProduct(n))))),n);
        return Reflected;
    };

    public Ray constructRefractedRay(Point p, Vector v,Vector n){
        return new Ray(p,v,n);
    };*/

    private Ray constructRefractedRay(Point p, Vector v, Vector n)

    {
        double nv = n.dotProduct(v);
        Vector directedNormal = nv < 0 ? n.scale(-1) : n;
        return new Ray(p, v, directedNormal);
/*
        return new Ray(gp, n, v);
*/
    }

    //constructs the reflected ray
    private Ray constructReflectedRay(Point p, Vector v, Vector n) {
        double nv = n.dotProduct(v);
        Vector directedNormal = nv < 0 ? n : n.scale(-1);
        return new Ray(p, v.subtract(n.scale(nv * 2)), directedNormal);

        /*double vn = v.dotProduct(n);
        if (vn == 0) {
            return null;
        }
        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(p, n, r);*/
        /*if (v.dotProduct(n)==0)
            return new Ray(p,v);
        Ray Reflected= new Ray(p,(v.subtract(n.scale(2*(v.dotProduct(n))))),n);
        return Reflected;*/
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material mat = gp.geometry.getMaterial();
        Double3 kr = mat.kR, kkr = k.product(kr);
        Vector n = gp.geometry.getNormal(gp.point);
        Ray reflectedRay = constructReflectedRay(gp.point,n , ray.getDir());
        GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
        if (!(kkr.lowerThan(MIN_CALC_COLOR_K)) && reflectedPoint != null) {
            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }
        Double3 kt = mat.kT, kkt = k.product(kt);
        Ray refractedRay = constructRefractedRay(gp.point,n, ray.getDir());
        GeoPoint refractedPoint = findClosestIntersection(refractedRay);
        if (!(kkt.lowerThan(MIN_CALC_COLOR_K)) && refractedPoint != null) {
            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }
    public Color average_color_calculator(List<Ray> rays) {
        Color aver = Color.BLACK;
        if (rays.size() == 0)
            return aver;

        for (Ray ray : rays) {
            GeoPoint point = findClosestIntersection(ray);

            if (point == null)
                aver = aver.add(_scene.getSceneBack());
            else {
                Color c = calcColor(point, ray);
                aver = aver.add(c);
            }
        }

        return aver.reduce(new Double3(rays.size()));
    }


    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.getAmbientLight().getIntensity());
    }
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray, k);
        return 1 == level ? color
                : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

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

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double max = -r.dotProduct(v);
        return alignZero(max) > 0 ? material.kS.scale(Math.pow(max, material.nShininess)) : Double3.ZERO;
    }

    private Double3 calcDiffusive(Material material, double nl) {
        if (nl<0)
            return material.kD.scale(-nl);
        return material.kD.scale(nl);
    }


    private GeoPoint findClosestIntersection(Ray ray) {
        var intersections = _scene.getGeometries().findGeoIntsersections(ray);
        if (intersections==null)
            return null;
        return ray.findClosestGeoPoint(intersections);
    }
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

    @Override
    public Color traceBeamRay(List<Ray> beam) {
        Color tmp = Color.BLACK;
        for (Ray ray:
                beam) {
            Color color = traceRay(ray);
            tmp = tmp.add(color);
        }
        return tmp.reduce(beam.size());
    }

    @Override
    public Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, Point cameraLoc, Vector Vright, Vector Vup, List<Point> prePoints) {
        if (Width < minWidth * 2 || Height < minHeight * 2) {
            return this.traceRay(new Ray(cameraLoc, centerP.subtract(cameraLoc))) ;
        }

        List<Point> nextCenterPList = new LinkedList<>();
        List<Point> cornersList = new LinkedList<>();
        List<primitives.Color> colorList = new LinkedList<>();
        Point tempCorner;
        Ray tempRay;
        for (int i = -1; i <= 1; i += 2){
            for (int j = -1; j <= 1; j += 2) {
                tempCorner = centerP.add(Vright.scale(i * Width / 2)).add(Vup.scale(j * Height / 2));
                cornersList.add(tempCorner);
                if (prePoints == null || !isInList(prePoints, tempCorner)) {
                    tempRay = new Ray(cameraLoc, tempCorner.subtract(cameraLoc));
                    nextCenterPList.add(centerP.add(Vright.scale(i * Width / 4)).add(Vup.scale(j * Height / 4)));
                    colorList.add(traceRay(tempRay));
                }
            }
        }


        if (nextCenterPList == null || nextCenterPList.size() == 0) {
            return primitives.Color.BLACK;
        }


        boolean isAllEquals = true;
        primitives.Color tempColor = colorList.get(0);
        for (primitives.Color color : colorList) {
            if (!tempColor.isAlmostEquals(color))
                isAllEquals = false;
        }
        if (isAllEquals && colorList.size() > 1)
            return tempColor;


        tempColor = primitives.Color.BLACK;
        for (Point center : nextCenterPList) {
            tempColor = tempColor.add(AdaptiveSuperSamplingRec(center, Width/2,  Height/2,  minWidth,  minHeight ,  cameraLoc, Vright, Vup, cornersList));
        }
        return tempColor.reduce(nextCenterPList.size());


    }
    private boolean isInList(List<Point> pointsList, Point point) {
        for (Point tempPoint : pointsList) {
            if(point.equals(tempPoint))
                return true;
        }
        return false;
    }
}





