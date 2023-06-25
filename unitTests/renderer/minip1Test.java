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
        Scene scene = new Scene.SceneBuilder("finalPicture")
                .build().setSceneBack(new Color(173, 238, 255));// mens black
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setVPSize(150, 150).setVPDistance(1000);


        //sun
        scene.getGeometries().add(
                new Sphere(new Point(-50,30,0), 10).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-62.6,29.7,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-62,34,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-60.3,38,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-56.3,41,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-52,42.5,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-47.5,42.5,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-43,41,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-39.8,38,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-38,34.2,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-37.6,30,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-38,26,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-40,22,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-43,19,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-47,17.3,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-51.5,17,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-56,18.3,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-59.8,20.8,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-61.8,25,0),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6))
        );//shirra

        //mountain
        scene.getGeometries().add(
                new Triangle(new Point(-70,-45,0),new Point(-31,13,0),new Point(-5,-45,0)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),
                new Triangle(new Point(-50,-45,0),new Point(12,28,0),new Point(35,-45,0)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),
                new Triangle(new Point(15,-45,0),new Point(49,11,0), new Point(69,-45,0)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100))
        );



                camera.setImageWriter(new ImageWriter("minip1", 1000, 1000)) //
                 .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
