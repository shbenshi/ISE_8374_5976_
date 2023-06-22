package renderer;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.SpotLight;
import scene.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import static java.awt.Color.*;


public class myFirstPictureTest {
    @Test
    public void myFirstPictureTest() {
        Scene scene = new Scene.SceneBuilder("final picture")
                .build();// mens black
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setVPSize(150, 150).setVPDistance(1000);
        scene.getGeometries().add(
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(YELLOW)) //
                        .setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(100).setKt(0.6)),//.setKt(0.7)),

                new Sphere(new Point(17, 10, 100), 6d).setEmission(new Color(BLACK)) //
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),

                new Sphere(new Point(-17, 10, 100), 6d).setEmission(new Color(BLACK)) //
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),

                new Sphere(new Point(0, -23, 0), 13).setEmission(new Color(BLACK)) //
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),
                new Plane(new Point(0, 0, -100), new Vector(0, 0, 1)).setEmission(new Color(255, 199, 108)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
        );

               /* new Sphere(new Point(0, 0, 0), 250d)
                .setEmission(new Color(java.awt.Color.yellow)).setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100)
                        .setKt(0.3)),
                new Sphere(new Point(0, 0, 0), 25d)
                .setEmission(new Color(java.awt.Color.black)).setMaterial(new Material().setKd(0.25).setKs(0.5).setShininess(100)));
                        //.setKt(new Double3(0.5, 0, 0))));*/

        scene.getLights().add(
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
                        .setKl(0.0004).setKq(0.0000006));
        scene.getLights().add(
                new DirectionalLight(new Color(200, 300, 100).scale(0.8), new Vector(1, -2, -8))
        );
        scene.getLights().add(
                new DirectionalLight()


        camera.setImageWriter(new ImageWriter("ourPicture", 600, 600)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }


}