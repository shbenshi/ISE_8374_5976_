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
                new Sphere(new Point(-50,37,100), 10).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-62.6,36.7,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-62,41,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-60.3,45,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-56.3,48,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-52,49.5,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-47.5,49.5,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-43,48,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-39.8,45,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-38,41.2,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-37.6,37,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-38,33,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-40,29,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-43,26,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-47,24.3,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-51.5,24,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-56,25.3,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-59.8,27.8,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6)),
                new Sphere(new Point(-61.8,32,100),1.7d).setEmission(new Color(yellow)).
                        setMaterial(new Material().setKd(0.9).setKs(0.3).setShininess(1000).setKt(0.6))
        );

        //snow
        /*scene.getGeometries().add(
                new Triangle(new Point(-40,0,0), new Point(-31,13,0),new Point(-25,0,0))
                        .setEmission(new Color(250,245,245))
                        .setMaterial(new Material().setKd(0.3).setKs(0.6).setShininess(200).setKr(0.4)),
                new Triangle(new Point(-7,6,0),new Point(12,28,0),new Point(19,6,0))
                    .setEmission(new Color(250,245,245))
                    .setMaterial(new Material().setKd(0.3).setKs(0.6).setShininess(200).setKr(0.4)),
                new Triangle(new Point(41,-2,0),new Point(49,11,0),new Point(53.8,-2,0))
                        .setEmission(new Color(250,245,245))
                        .setMaterial(new Material().setKd(0.3).setKs(0.6).setShininess(200).setKr(0.4))


                );*/
        //mountain
        scene.getGeometries().add(
                //1- left
                new Triangle(new Point(-47,-37,150),new Point(-28,16,0),new Point(-23,-45,0)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(200)),
                new Triangle(new Point(-5,-27,150),new Point(-28,16,0),new Point(-23,-45,0)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(200)),
                new Triangle(new Point(-47,-37,150),new Point(-28,16,0),new Point(-37,-42,0)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(200)),
                new Triangle(new Point(-5,-27,150),new Point(-28,16,0),new Point(-37,-42,-150)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(200)),



                //2- middle
                new Triangle(new Point(-20,-39,170),new Point(8,28,0),new Point(20,-42,170)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),
                new Triangle(new Point(-20,-39,170),new Point(8,28,0),new Point(10,-30,0)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),
                new Triangle(new Point(10,-30,0),new Point(8,28,0),new Point(35,-35,0)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),
                new Triangle(new Point(20,-42,170),new Point(8,28,0),new Point(35,-35,0)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100))




                //3- right
               /* new Triangle(new Point(15,-45,0),new Point(49,11,0), new Point(69,-45,0)).setEmission(new Color(175,175,175))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100))
*/
        );


        //cloud
        scene.getGeometries().add(
                //1-b
                new Sphere(new Point(10, 50, 0), 10d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(1, 47, 0), 7d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(19, 47, 0), 7d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),

                //2-s
                new Sphere(new Point(55, 64, 0), 6d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(60, 62, 0), 4d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(50, 62, 0), 4d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),

                //3 -m
                new Sphere(new Point(47, 37, 0), 7d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(53, 35, 0), 5d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(41, 35, 0), 5d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000))


                );



     /*  scene.getLights().add(
                new SpotLight(new Color(10000, 4000, 12), new Point( -10,30,150), new Vector(13, -2, 0))
                        .setKl(0.00000009).setKq(0.0009));
*/
       scene.getLights().add(
                new DirectionalLight(new Color(0, 0, 60).scale(0.00000000000001), new Vector(0,45 ,0))
        );
        scene.getLights().add(
                new PointLight(new Color(153,153,133),new Point( -10,30,150))
        );






                camera.setImageWriter(new ImageWriter("minip1", 1000, 1000)) //
                 .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
