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

    public RayTracerBasic(Scene _scene)
    {
        super(_scene);
    }

    /**
     * Traces a ray and determines the color at the
     * intersection point with the scene
     *and calc the color of the point.
     * find a point mifgash between the ray and the picture
     * and then calc the color
     *If there is no intersection, it returns the background color of the scene.
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
     * Constructs a refracted ray based
     * on the incident ray, the intersection point,
     * and the surface normal.
     *calc the dir of the return ray
     *  calculates a ray that represents the path of light after entering a transparent medium
     *  and changing direction due to refraction.
     * @param p The intersection point.
     * @param v The incident ray direction.
     * @param n The surface normal.
     * @return The refracted ray.
     */
    private Ray constructRefractedRay(Point p, Vector v, Vector n)
    {
        double nv = n.dotProduct(v);
        Vector directedNormal = nv < 0 ? n.scale(-1) : n;
        return new Ray(p, v, directedNormal);
    }

    /**
     * Constructs a reflected ray based on
     * the incident ray, the intersection point,
     * and the surface normal.
     *calculates a ray that represents the path of light after reflecting off a surface,
     *  obeying the law of reflection.
     * @param p The intersection point.
     * @param v The incident ray direction.
     * @param n The surface normal.
     * @return The reflected ray.
     */
    private Ray constructReflectedRay(Point p, Vector v, Vector n) {
        double nv = n.dotProduct(v);
        Vector directedNormal = nv < 0 ? n : n.scale(-1);
        return new Ray(p, v.subtract(n.scale(nv * 2)), directedNormal);
    }
    /**
     * Calculates the global effects
     * (reflected and refracted colors) at a given
     * intersection point.
     *
     * @param gp     The intersection point
     * @param ray    The ray being traced
     * @param level  The recursion level for calculating reflections and refractions
     * @param k      The attenuation factor for the current ray
     * @return       The color resulting from the global effects at the intersection point
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material mat = gp.geometry.getMaterial();
        Double3 kr = mat.kR, kkr = k.product(kr);
        Vector n = gp.geometry.getNormal(gp.point);
        // Calculate the reflected ray and find the closest intersection point
        Ray reflectedRay = constructReflectedRay(gp.point,n , ray.getDir());
        GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
        // If the reflection is not negligible and there is an intersection point, calculate the color recursively
        if (!(kkr.lowerThan(MIN_CALC_COLOR_K)) && reflectedPoint != null) {
            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }
        Double3 kt = mat.kT, kkt = k.product(kt);
        // Calculate the refracted ray and find the closest intersection point
        Ray refractedRay = constructRefractedRay(gp.point,n, ray.getDir());
        GeoPoint refractedPoint = findClosestIntersection(refractedRay);
        // If the refraction is not negligible and there is an intersection point, calculate the color recursively
        if (!(kkt.lowerThan(MIN_CALC_COLOR_K)) && refractedPoint != null) {
            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }
    /**
     * Calculates the average color for a list of rays
     * by tracing each ray and taking the average.
     *
     * @param rays  The list of rays
     * @return      The average color
     */
    public Color average_color_calculator(List<Ray> rays) {
        Color aver = Color.BLACK;
        // If the list is empty, return the default color
        if (rays.size() == 0)
            return aver;
        // Calculate the color for each ray and accumulate the results
        for (Ray ray : rays) {
            GeoPoint point = findClosestIntersection(ray);

            if (point == null)
                aver = aver.add(_scene.getSceneBack());
            else {
                Color c = calcColor(point, ray);
                aver = aver.add(c);
            }
        }
        // Reduce the accumulated color by dividing it by the number of rays
        return aver.reduce(new Double3(rays.size()));
    }

    /**
     * Calculates the color at the closest
     * intersection point for a given ray by combining
     * local and global effects.
     * @param closestPoint  The closest intersection point
     * @param ray           The ray being traced
     * @return              The color at the intersection point
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.getAmbientLight().getIntensity());
    }
    /**
     * Calculates the color at an intersection point considering local and global effects recursively.
     *
     * @param intersection  The intersection point
     * @param ray           The ray being traced
     * @param level         The recursion level for calculating global effects
     * @param k             The attenuation factor for the current ray
     * @return              The color at the intersection point
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray, k);
        // If the recursion level is 1,
        // return the color without calculating global effects
        // Calculate and add the global effects to the color
        return 1 == level ? color
                : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * Calculates the local effects at an
     * intersection point (e.g., diffuse and specular
     * reflections).
     * @param gp    The intersection point
     * @param ray   The ray being traced
     * @param k     The attenuation factor for the current ray
     * @return      The color resulting from the local effects at the intersection point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = gp.geometry.getEmission();
        Vector vector = ray.getDir();
        Vector normal = gp.geometry.getNormal(gp.point);
        double nv = alignZero(normal.dotProduct(vector));
        // If the dot product of the normal
        // and the ray direction is zero,
        // return the emission color
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        // Calculate the local effects for each light source in the scene
        for (LightSource lightSource : _scene.getLights()) {
            Vector lightVector = lightSource.getL(gp.point);
            double nl = alignZero(normal.dotProduct(lightVector));
            // If the dot product of the normal and the light vector has the same sign as nv, calculate the effects
            if (nl * nv > 0) {
                Double3 ktr = transparency(gp, lightSource, lightVector, normal);
                // If the transparency factor is not negligible, calculate the contributions from the light source
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(gp.point).scale(ktr);
                    // Add the contributions from diffuse and specular reflections to the color
                    color = color.add(lightIntensity.scale(calcDiffusive(material, nl)),
                            lightIntensity.scale(calcSpecular(material, normal, lightVector, nl, vector)));
                }
            }
        }
        return color;
    }

    /**
     * Calculates the specular reflection factor for a given material and light interaction.
     *
     * @param material  The material of the intersected geometry
     * @param n         The surface normal at the intersection point
     * @param l         The direction of the light source
     * @param nl        The dot product of the surface normal and the light direction
     * @param v         The direction of the viewer
     * @return          The specular reflection factor
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double max = -r.dotProduct(v);
        // If the maximum value is greater than zero, calculate the specular reflection factor
        return alignZero(max) > 0 ? material.kS.scale(Math.pow(max, material.nShininess)) : Double3.ZERO;
    }
    /**
     * Calculates the diffuse reflection factor for a given material and light interaction.
     *
     * @param material  The material of the intersected geometry
     * @param nl        The dot product of the surface normal and the light direction
     * @return          The diffuse reflection factor
     */
    private Double3 calcDiffusive(Material material, double nl) {
        //In the rendering equation, the diffuse
        // reflection term is typically calculated as
        // the dot product between the surface normal and
        // the light direction, multiplied by the diffuse
        // reflection coefficient (kD) of the material
        if (nl<0)
            return material.kD.scale(-nl);
        return material.kD.scale(nl);
    }

    /**
     * Finds the closest intersection point between a ray and the geometries in the scene.
     *
     * @param ray   The ray to intersect with the scene geometries
     * @return      The closest intersection point, or null if no intersection is found
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var intersections = _scene.getGeometries().findGeoIntsersections(ray);
        // If no intersections are found, return null
        if (intersections==null)
            return null;
        // Find and return the closest intersection point
        return ray.findClosestGeoPoint(intersections);
    }
    /**
     * Calculates the transparency factor (ktr) for a given intersection point and light source.
     *
     * @param gp        The intersection point
     * @param light     The light source
     * @param l         The direction of the light source
     * @param n         The surface normal at the intersection point
     * @return          The transparency factor
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, n, lightDirection); // ray to check shading
        var intersections = _scene.getGeometries().findGeoIntsersections(lightRay);
        // If no intersections are found, return full transparency (ktr = 1)
        if (intersections == null)
            return Double3.ONE;
        Double3 ktr = Double3.ONE;
        // Iterate over the intersections and calculate the transparency factor
        for (GeoPoint geoPoint : intersections) {
            if (alignZero(geoPoint.point.distance(gp.point) - light.getDistance(gp.point)) <= 0) {
                ktr = ktr.product(geoPoint.geometry.getMaterial().kT);
                // If the transparency factor becomes zero, stop iterating
                if (ktr.equals(Double3.ZERO)) {
                    break;
                }
            }
        }
        return ktr;

}


    /**
     * Performs adaptive super-sampling for a given pixel by subdividing it into smaller sub-pixels.
     * Traces rays within those sub-pixels to calculate the final color.
     *
     * @param centerP    The center point of the pixel.
     * @param Width      The width of the pixel.
     * @param Height     The height of the pixel.
     * @param minWidth   The minimum width threshold for subdividing the pixel.
     * @param minHeight  The minimum height threshold for subdividing the pixel.
     * @param cameraLoc  The location of the camera.
     * @param Vright     The vector representing the right direction of the camera.
     * @param Vup        The vector representing the up direction of the camera.
     * @param prePoints  A list of previously sampled points to avoid resampling.
     * @return The color calculated for the pixel using adaptive super-sampling.
     */
    @Override
    public Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, Point cameraLoc, Vector Vright, Vector Vup, List<Point> prePoints) {
        // Check if the current pixel needs further subdivision
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

        // Check if all colors in the current subdivision are equal
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

        // Recursive subdivision of the pixel
        tempColor = primitives.Color.BLACK;
        for (Point center : nextCenterPList) {
            tempColor = tempColor.add(AdaptiveSuperSamplingRec(center, Width/2,  Height/2,  minWidth,  minHeight ,  cameraLoc, Vright, Vup, cornersList));
        }
        return tempColor.reduce(nextCenterPList.size());


    }
    /**
     * Checks if a given point is present in the list of points.
     * @param pointsList The list of points to check against
     * @param point      The point to check for
     * @return {@code true} if the point is found in the list, {@code false} otherwise
     */
    private boolean isInList(List<Point> pointsList, Point point) {
        for (Point tempPoint : pointsList) {
            if(point.equals(tempPoint))
                return true;
        }
        return false;
    }
}





