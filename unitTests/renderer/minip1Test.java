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
        Scene scene = new Scene.SceneBuilder("finalPicture").setBackground(new Color(135,206,250))
                .build().setSceneBack(new Color(135,206,250));// mens black
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setVPSize(150, 150).setVPDistance(1000);

        //back
        scene.getGeometries().add(

                new Plane(new Point(5, -80, -300), new Vector(5, -240, -20))
                        .setEmission(new Color(185, 600, 1000).scale(0.35))//
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
        );

        //gross
        scene.getGeometries().add(
                new Plane(new Point(0, -55, -750), new Vector(0, 0.5, 0))
                        .setEmission(new Color(178,255,141))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
        );




        scene.getLights().add(
                new DirectionalLight(new Color(200, 200, 0), new Vector(0,-1 ,-3))
        );
        /*scene.getLights().add(
                new DirectionalLight(new Color(200, 200, 0), new Vector(0,0 ,-1))
        );*/

     /*   scene.getLights().add(
                new PointLight(new Color(153,153,133),new Point( -10,80,200))
        );*/




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
                new Triangle(new Point(-47,-47,70),new Point(-28,16,0),new Point(-23,-65,0)).setEmission(new Color(97,61,5))
                        .setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(200)),
                new Triangle(new Point(-5,-37,70),new Point(-28,16,0),new Point(-23,-65,0)).setEmission(new Color(97,61,5))
                        .setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(200)),
                new Triangle(new Point(-47,-47,70),new Point(-28,16,0),new Point(-37,-62,0)).setEmission(new Color(97,61,5))
                        .setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(200)),
                new Triangle(new Point(-5,-37,70),new Point(-28,16,0),new Point(-37,-62,-70)).setEmission(new Color(97,61,5))
                        .setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(200)),


                //2- middle
                new Triangle(new Point(-25,-55,100),new Point(8,28,0),new Point(20,-62,100)).setEmission(new Color(125,70,0))
                        .setMaterial(new Material().setKd(0).setKs(7).setShininess(100)),
                new Triangle(new Point(-25,-60,100),new Point(8,28,0),new Point(10,-50,0)).setEmission(new Color(125,70,0))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),
                new Triangle(new Point(10,-50,0),new Point(8,28,0),new Point(35,-65,0)).setEmission(new Color(125,70,0))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),
                new Triangle(new Point(20,-62,100),new Point(8,28,0),new Point(35,-65,0)).setEmission(new Color(125,70,0))
                        .setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(100)),




                //3- right
                new Triangle(new Point(25,-65,-5),new Point(45,10,0), new Point(70,-65,0)).setEmission(new Color(97,61,5))
                        .setMaterial(new Material().setKd(0).setKs(0).setShininess(100)),
                new Triangle(new Point(10,-45,-5),new Point(45,10,0), new Point(40,-45,-5)).setEmission(new Color(97,61,5))
                        .setMaterial(new Material().setKd(0.3).setKs(0.6).setShininess(200)),
                new Triangle(new Point(25,-65,-5),new Point(45,10,0), new Point(10,-45,0)).setEmission(new Color(97,61,5))
                        .setMaterial(new Material().setKd(0.3).setKs(0.6).setShininess(200)),
                new Triangle(new Point(40,-45,-5),new Point(45,10,0), new Point(70,-65,0)).setEmission(new Color(97,61,5))
                        .setMaterial(new Material().setKd(0.3).setKs(0.6).setShininess(200))

                );


        //cloud
        scene.getGeometries().add(
                //1-b
                new Sphere(new Point(10, 60, 0), 10d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(1, 57, 0), 7d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(19, 57, 0), 7d).setEmission(new Color(WHITE))
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
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),



                new Sphere(new Point(-58, 7.5, -200), 7d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.54).setKs(0.003).setShininess(3000).setKt(0.15)),
                new Sphere(new Point(-63, 6, -200), 5d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.54).setKs(0.003).setShininess(3000).setKt(0.15)),
                new Sphere(new Point(-52, 6, -200), 5d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.54).setKs(0.003).setShininess(3000).setKt(0.15)),


                new Sphere(new Point(-30, 64, 0), 6d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-35, 62, 0), 4d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-24, 62, 0), 4d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),



                new Sphere(new Point(-24, 34, 0), 7d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-30, 32, 0), 5d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-18, 32, 0), 5d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),

                //2-s
                new Sphere(new Point(62, 15, 0), 6d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(68, 13, 0), 4d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(57, 13, 0), 4d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),

                new Sphere(new Point(10, 27, -200), 15d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-3, 21, -200), 12d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(23, 21, -200), 12d).setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000))
                );


        scene.getGeometries().add(
                new Sphere(new Point(-55,-70,160),40).setEmission(new Color(0,121,67))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-60,-50,-120),40).setEmission(new Color(41,185,51))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(60,-60,-80),50).setEmission(new Color(116,255,1))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(55,-73,160),40).setEmission(new Color(0,121,67))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-5,-90,120),50).setEmission(new Color(116,255,1))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000))


                );
                camera.setImageWriter(new ImageWriter("minip1", 1000, 1000)) //
                 .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
