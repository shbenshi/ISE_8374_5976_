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

    public abstract Color average_color_calculator(List<Ray> rays);
    public abstract Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, Point cameraLoc, Vector Vright, Vector Vup, List<Point> prePoints);
    public abstract  Color traceBeamRay(List<Ray> beam);


}
