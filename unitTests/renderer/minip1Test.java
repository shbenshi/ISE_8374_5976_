package renderer;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import scene.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import static java.awt.Color.*;
public class minip1Test {
    @Test
    public void minip1Test() {
        Scene scene = new Scene.SceneBuilder("final picture")
                .build();// mens black
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setVPSize(150, 150).setVPDistance(1000);
        scene.getGeometries().add(
        );
        scene.getLights().add(


        );
        camera.setImageWriter(new ImageWriter("minip1", 1000, 1000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
