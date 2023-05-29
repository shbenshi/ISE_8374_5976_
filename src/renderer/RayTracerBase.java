package renderer;

import scene.*;
import primitives.*;

public abstract class RayTracerBase {
    protected Scene _scene;
    public RayTracerBase(Scene _scene) {
        this._scene = _scene;
    }
    public abstract Color traceRay(Ray ray);

}
