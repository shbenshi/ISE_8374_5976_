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
/*        Scene scene = new Scene.SceneBuilder("finalPicture")
                .build();// mens black
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setVPSize(150, 150).setVPDistance(1000);

        Material mountain1 = new Material().setKd(0.6).setKs(0.4).setShininess(200);
        scene.getGeometries().add(
                new Triangle(new Point(-120, 10,0), new Point(-200, -45, 0),new Point(-200, 0, 80)).setEmission(new Color(YELLOW)).setMaterial(new Material().setKd(0).setKs(0).setShininess(1000)),
                new Triangle(new Point(-120,10,0), new Point(-200, 45, 0),new Point(-200, 0, 80)).setEmission(new Color(YELLOW)).setMaterial(new Material().setKd(0).setKs(0).setShininess(1000)),
                new Triangle(new Point(-260,10,0), new Point(-200, -45, 0), new Point(-200, 0, 80)).setEmission(new Color(YELLOW)).setMaterial(new Material().setKd(0).setKs(0).setShininess(1000)),
                new Triangle(new Point(-260,10,0), new Point(-200, 45, 0), new Point(-200, 0, 80)).setEmission(new Color(YELLOW)).setMaterial(new Material().setKd(0).setKs(0).setShininess(1000))
        );

        scene.getLights().add(
                new SpotLight(new Color(10000, 4000, 12), new Point(0, 123, 0), new Vector(0, -2, 0))
                        .setKl(0.00000009).setKq(0.0009));

        scene.getLights().add(
                new DirectionalLight(new Color(0, 0, 120).scale(0.8), new Vector(1,67 ,0))
        );
        scene.getLights().add(
                new PointLight(new Color(90,250,120),new Point( 100,100,0))
        );
        camera.setImageWriter(new ImageWriter("minip1", 1000, 1000)) //
        .setRayTracer(new RayTracerBasic(scene)) //
        .renderImage() //
        .writeToImage();*/


public class myFirstPictureTest {
    @Test
    public void myFirstPictureTest() {
        Scene scene = new Scene.SceneBuilder("our picture")
                .build();// mens black
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setVPSize(150, 150).setVPDistance(1000);
        scene.getGeometries().add(

                new Sphere(new Point(0, 0, -50), 65).setEmission(new Color(YELLOW)) //
                        .setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),//.setKt(0.7)),

                new Sphere(new Point(32, 9, 100), 6d).setEmission(new Color(115,81,55)) //
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),

                new Sphere(new Point(-32, 9, 100), 6d).setEmission(new Color(115,81,55)) //
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(1301)),

                new Sphere(new Point(0, -25, 0), 20).setEmission(new Color(115,81,55)) //
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),

                new Triangle(new Point(-17,0,0),new Point(17,0,0), new Point(-45,65,0)).setEmission(new Color(115,81,55))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),

                new Triangle(new Point(17,0,0),new Point(-17,0,0), new Point(45,65,0)).setEmission(new Color(115,81,55))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),

                new Plane(new Point(0, 0, -100), new Vector(0, 0, 1)).setEmission(new Color(255, 197, 91))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))

                );


        scene.getLights().add(
                new SpotLight(new Color(10000, 4000, 12), new Point(0, 123, 0), new Vector(0, -2, 0))
                        .setKl(0.00000009).setKq(0.0009));

        scene.getLights().add(
                new DirectionalLight(new Color(0, 0, 120).scale(0.8), new Vector(1,67 ,0))
        );
        scene.getLights().add(
                new PointLight(new Color(90,250,120),new Point( 100,100,0))
        );





        camera.setImageWriter(new ImageWriter("ourPicture", 600, 600)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
                camera.writeToImage();
    }


}