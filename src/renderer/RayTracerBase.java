package renderer;

import scene.*;
import primitives.*;
import java.util.List;


/**
 * This class represents a camera in a renderer.
 * It defines the position, direction, and other properties of the camera.
 * The camera is used for rendering scenes and tracing rays to determine the color at intersection points.
 * Author:
 *  Tzofiya David (209918374)
 *  Shira Ben Shimol (326065976)
 */

public abstract class RayTracerBase {
    protected Scene _scene;

    /**
     * Constructs a RayTracerBase object with the specified scene.
     *
     * @param _scene The scene to be rendered.
     */
    public RayTracerBase(Scene _scene) {
        this._scene = _scene;
    }

    /**
     * Traces a ray and determines the color at the intersection point with the scene.
     *
     * @param ray The ray to be traced.
     * @return The color at the intersection point.
     */
    public abstract Color traceRay(Ray ray);

    /**
     * Calculates the average color from a list of rays.
     *
     * @param rays The list of rays to calculate the average color from.
     * @return The average color calculated from the list of rays.
     */
    public abstract Color average_color_calculator(List<Ray> rays);
    /**
     * Performs recursive adaptive super-sampling to compute the color at a given point.
     *
     * @param centerP    The center point of the pixel being sampled.
     * @param Width      The width of the pixel being sampled.
     * @param Height     The height of the pixel being sampled.
     * @param minWidth   The minimum width of the sub-pixels during adaptive super-sampling.
     * @param minHeight  The minimum height of the sub-pixels during adaptive super-sampling.
     * @param cameraLoc  The location of the camera.
     * @param Vright     The right vector of the camera.
     * @param Vup        The up vector of the camera.
     * @param prePoints  The list of precomputed points for adaptive super-sampling.
     * @return The color computed for the given point using adaptive super-sampling.
     */
    public abstract Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, Point cameraLoc, Vector Vright, Vector Vup, List<Point> prePoints);

}
