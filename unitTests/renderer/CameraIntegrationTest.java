package renderer;
import primitives.*;
import geometries.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

class CameraIntegrationTest {
    /**
     * Performs the integration between a camera and an intersectable geometry and returns the total number of intersections.
     *
     * @param camera The camera object used for the integration.
     * @param geo    The intersectable geometry to perform intersection tests with.
     * @return The total number of intersections between the camera and the geometry.
     */
    public int cameraIntegrations(Camera camera, Intersectable geo) {
        int counter = 0;
        List<Point> points = null;
        camera.setVPSize(3, 3);
        camera.setVPDistance(1);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                List<Point> intersections = geo.findIntsersections(camera.constructRay(3, 3, j, i));

                // Add the intersections to the list of all points
                if (intersections != null) {
                    if (points == null) {
                        points = new LinkedList<>();
                    }
                    points.addAll(intersections);
                }

                // Increment the counter by the number of intersections
                if (intersections != null) {
                    counter += intersections.size();
                }
            }
        }
        return counter;
    }














    //int nX = 3;
    //int nY = 3;
    //view plane 3X3 (WxH 3X3 & nx,ny =3 => Rx,Ry =1)

















//    public void cameraIntegrations(Geometry geo, Camera camera, int expected, String testCase) {
//        List<Point> intersectionPoints;
//        int intersections = 0;
//        for (int i = 0; i < 3; ++i) {
//            for (int j = 0; j < 3; ++j) {
//                intersectionPoints = geo.findIntsersections(
//                        camera.constructRay(3, 3, j, i));
//                if (intersectionPoints != null)
//                    intersections += intersectionPoints.size();
//            }
//        }
//        assertEquals("ERROR " + testCase + ": Wrong amount of intersections", intersections, expected);
//    }
}



/*
        int counter = 0;
        List<Point> allpoints = null;
        cam.setVPSize(3, 3);
        cam.setVPDistance(1);
        int nX = 3;
        int nY = 3;
        //view plane 3X3 (WxH 3X3 & nx,ny =3 => Rx,Ry =1)
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                List<Point> intersections = geo.findIntSersections(cam.constructRay(nX, nY, j, i));
                //adding the amount of intersections
                if (intersections != null) {
                    if (allpoints == null) {
                        allpoints = new LinkedList<>();
                    }
                    allpoints.addAll(intersections);
                }
                if (intersections != null)
                    counter += intersections.size();
            }
        }
        return counter;

 */