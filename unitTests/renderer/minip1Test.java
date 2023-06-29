package renderer;

import geometries.Geometries;
import lighting.AmbientLight;
import static java.awt.Color.*;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import scene.*;
import org.junit.jupiter.api.Test;
import primitives.*;

public class minip1Test {
    @Test
    public void minip1Test() {
        Scene scene = new Scene.SceneBuilder("minipF").setBackground(new Color(135,206,250))
                .build().setSceneBack(new Color(135,206,250));// mens black
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
        camera.setVPSize(150, 150).setVPDistance(1000).setThreadsCount(4).setNumberOfRays(81).setadaptive(true);

        //back
        scene.getGeometries().add(

                new Plane(new Point(5, -80, -300), new Vector(5, -240, -20))
                        .setEmission(new Color(174,250,1300).scale(0.35))//
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
        );

        //gross
        scene.getGeometries().add(
                new Plane(new Point(0, -55, -750), new Vector(0, 0.5, 0))
                        .setEmission(new Color(178,255,141))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
        );



        // light
        scene.getLights().add(
                new DirectionalLight(new Color(200, 200, 0), new Vector(0,-4 ,-1))
        );
        scene.getLights().add(
                new SpotLight(new Color(600,700,400),new Point(-48,28,-2), new Vector(0,1,0))
        );

        scene.getLights().add(
                new PointLight(new Color(100,100,0),new Point(-40,8,0))
        );
// new_min2
        scene.getLights().add(
                new SpotLight(new Color(100, 200, 700), new Point(-80, 100, 100), new Vector(-1, -1,100)) //
                        .setKl(4E-4).setKq(2E-5)
        );

        //sun
        scene.getGeometries().add(
                new Sphere(new Point(-50,37,100), 10).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-62.6,36.7,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-62,41,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-60.3,45,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-56.3,48,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-52,49.5,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-47.5,49.5,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-43,48,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-39.8,45,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-38,41.2,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-37.6,37,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-38,33,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-40,29,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-43,26,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-47,24.3,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-51.5,24,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-56,25.3,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-59.8,27.8,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-61.8,32,100),1.7d).setEmission(new Color(248,246,20))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000))
        );

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
        //bubble
        scene.getGeometries().add(
                //1 not this, with KR, mavrika yoter
                new Sphere(new Point(-60,-34,0),1.5d)
                        .setEmission(new Color(255,255,255).scale(0.05))
                        .setMaterial(new Material().setKd(0.3)
                                .setKs(0.6).setShininess(300).setKt(0.8).setKr(0.4).setKt(0.9)),
                // this one
                new Sphere(new Point(-70,-32,0),1.3d)
                        .setEmission(new Color(255,255,255).scale(0.05))
                        .setMaterial(new Material().setKd(0.1)
                                .setKs(0.9).setShininess(300).setKt(0.8).setKr(0.4)),
                // another inside
                new Sphere(new Point(-63,-26,0),2d)
                        .setEmission(new Color(255,255,255).scale(0.05))
                        .setMaterial(new Material().setKd(0.1)
                                .setKs(0.9).setShininess(100).setKt(0.8).setKr(0.4).setKt(0.9)),
                //
                new Sphere(new Point(-44,-30,155),1d)
                        .setEmission(new Color(255,255,255).scale(0.05))
                        .setMaterial(new Material().setKd(0.1)
                                .setKs(0.9).setShininess(300).setKt(0.8).setKr(0.4).setKt(0.9)),
                // left down side
                new Sphere(new Point(-55,-13,210),1d)
                        .setEmission(new Color(255,255,255).scale(0.05))
                        .setMaterial(new Material().setKd(0.1)
                                .setKs(0.9).setShininess(300).setKt(0.8).setKr(0.4).setKt(0.9)),

                new Sphere(new Point(-15,-37,220),2d)
                        .setEmission(new Color(255,255,255).scale(0.05))
                        .setMaterial(new Material().setKd(0.1)
                                .setKs(0.9).setShininess(300).setKt(0.6).setKr(0.4).setKt(0.9)),

                new Sphere(new Point(-50,-14,-18),1.5d)
                        .setEmission(new Color(255,255,255).scale(0.05))
                        .setMaterial(new Material().setKd(0.1)
                                .setKs(0.9).setShininess(300).setKt(0.8).setKr(0.6)),



                // next to left mount
                new Sphere(new Point(-30,-8,221),1d)
                        .setEmission(new Color(255,255,255).scale(0.05))
                        .setMaterial(new Material().setKd(0.1)
                                .setKs(0.9).setShininess(300).setKt(0.8).setKr(0.4).setKt(0.9)),
                // under the sun
                new Sphere(new Point(-54,-5,221),1.7d)
                        .setEmission(new Color(255,255,255).scale(0.05))
                        .setMaterial(new Material().setKd(0.1)
                                .setKs(0.9).setShininess(300).setKt(0.8).setKr(0.4).setKt(0.9))
                // next to sun right
               );


        //cloud
        scene.getGeometries().add(
                //4
                new Sphere(new Point(10, 44, 165), 10d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(1, 41,165), 7d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(19, 41, 165), 7d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),

                //5
                new Sphere(new Point(55, 64, 0), 6d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(60, 62, 0), 4d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(50, 62, 0), 4d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),

                //6
                new Sphere(new Point(44, 26, 180), 7d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(50, 24, 180), 5d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(38, 24, 180), 5d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),


                //1
                new Sphere(new Point(-58, -1, -110), 7d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-63, -3, -110), 5d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-52, -3, -110), 5d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),

                //3
                new Sphere(new Point(-30, 64, 0), 6d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-35, 62, 0), 4d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-24, 62, 0), 4d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),


                //7
                new Sphere(new Point(65, -1, -65), 6d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(72, -3, -65), 4d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(60, -3, -65), 4d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),

                new Sphere(new Point(-20, 35, 0), 7.5d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-27, 33, 0), 5.5d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000)),
                new Sphere(new Point(-13, 33, 0), 5.5d).setEmission(new Color(248,245,245))
                        .setMaterial(new Material().setKd(0.5).setKs(0.6).setShininess(3000))
                );

        //hills
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

                camera.setImageWriter(new ImageWriter("FINISH", 2500, 2500)) //
                 .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
                camera.writeToImage();
    }
}
