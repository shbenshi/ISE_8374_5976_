package renderer;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
/**
 * This class represents a camera in a renderer.
 *
 * It defines the position of the camera, the direction it is facing, and the up vector.
 * It also provides methods for setting the view plane size and distance from the camera.
 *
 * Authors:
 * Tzofiya David (209918374)
 * Shira Ben Shimol (326065976)
 */

class CameraIntegrationTest {

    //field
    static final Point ZERO_POINT = new Point(0, 0, 0);


    /**
     * Performs the integration between a camera and an intersectable geometry and returns the total number of intersections.
     *
     * @param camera     The camera object used for the integration.
     * @param geometries The intersectable geometry to perform intersection tests with.
     * @return The total number of intersections between the camera and the geometry.
     */
    public int cameraIntegrations(Camera camera, Intersectable geometries) {
        int counter = 0;
        List<Point> points = null;
        camera.setVPSize(3, 3);
        camera.setVPDistance(1);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                List<Point> intersections = geometries.findIntsersections(camera.constructRay(3, 3, j, i));

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


    /**
     * Integration test for Camera and Sphere.
     * This method tests the integration between a Camera and a Sphere, calculating the number of intersections.
     */
    @Test
    public void cameraRaySphereIntegration() {
        Camera camera0 = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0));
        Camera camera1 = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Test case for camera-ray-sphere integration
        assertEquals(2, cameraIntegrations(camera0, new Sphere(new Point(0, 0, -3), 1)));

        // TC02: Test case for camera-ray-sphere integration
        assertEquals(18, cameraIntegrations(camera1, new Sphere(new Point(0, 0, -2.5), 2.5)));

        // TC03: Test case for camera-ray-sphere integration
        assertEquals(10, cameraIntegrations(camera1, new Sphere(new Point(0, 0, -2), 2)));

        // TC04: Test case for camera-ray-sphere integration
        assertEquals(9, cameraIntegrations(camera1, new Sphere(new Point(0, 0, -1), 4)));

        // TC05: Test case for camera-ray-sphere integration
        assertEquals(0, cameraIntegrations(camera0, new Sphere(new Point(0, 0, 1), 0.5)));
    }

    /**
     * Integration test for Camera and Triangle.
     * This method tests the integration between a Camera and a Triangle, calculating the number of intersections.
     */
    @Test
    public void cameraRayTriangleIntegration() {
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Test case for camera-ray-triangle integration
        assertEquals(1, cameraIntegrations(camera, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -1, -2))));

        // TC02: Test case for camera-ray-triangle integration
        assertEquals(2, cameraIntegrations(camera, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2))));
    }

    /**
     * Integration test for Camera and Plane.
     * This method tests the integration between a Camera and a Plane, calculating the number of intersections.
     */
    @Test
    public void cameraRayPlaneIntegration() {
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0));


        // TC01: Test case for camera-ray-plane integration
        assertEquals(9, cameraIntegrations(camera, new Plane(new Point(0, 0, -5), new Vector(0, 0, 1))));

        // TC02: Test case for camera-ray-plane integration
        assertEquals(9, cameraIntegrations(camera, new Plane(new Point(0, 0, -5), new Vector(0, 1, 2))));

        // TC03: Test case for camera-ray-plane integration
        assertEquals(6, cameraIntegrations(camera, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1))));

        // TC04: Test case for camera-ray-plane integration
        assertEquals(0, cameraIntegrations(camera, new Plane(new Point(0, 0, -5), new Vector(0, -1, 0))));

    }
}











