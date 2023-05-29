/**
 This class represents a camera in a renderer.
 It defines the position of the camera, the direction it is facing, and the up vector.
 It also provides methods for setting the view plane size and distance from the camera.
 Authors:
 Tzofiya David (209918374)
 Shira Ben Shimol (326065976)
 */
package renderer;
import geometries.Plane;
import primitives.*;
//import renderer.


import java.util.MissingResourceException;

import static primitives.Util.*;
public class Camera {
    // filed
    private Point P0;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double dis;
    private double height;
    private double width;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;
    /**
     * Get the position of the camera.
     * @return The position of the camera as a Point.
     */
    public Point getP0() {
        return P0;
    }
    /**
     * Get the direction vector of the camera.
     * @return The direction vector of the camera as a Vector.
     */
    public Vector getvTo() {
        return vTo;
    }
    /**
     * Get the up vector of the camera.
     * @return The up vector of the camera as a Vector.
     */
    public Vector getvUp() {
        return vUp;
    }
    /**
     * Get the right vector of the camera.
     * @return The right vector of the camera as a Vector.
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * Get the distance from the camera to the view plane.
     * @return The distance from the camera to the view plane as a double.
     */
    public double getDis() {
        return dis;
    }
    /**
     * Get the height of the view plane.
     * @return The height of the view plane as a double.
     */
    public double getHeight() {
        return height;
    }
    /**
     * Get the width of the view plane.
     * @return The width of the view plane as a double.
     */
    public double getWidth() {
        return width;
    }
    /**
     * Constructs a new Camera object with the specified position, direction, and up vector.
     * @param _P0 The position of the camera as a Point.
     * @param _vTo The direction vector of the camera as a Vector.
     * @param _vUp The up vector of the camera as a Vector.
     * @throws IllegalArgumentException if the direction vector and up vector are not perpendicular.
     */
    public Camera(Point _P0, Vector _vTo, Vector _vUp)  throws IllegalArgumentException {
        if (!(isZero(_vTo.dotProduct(_vUp)))) {
            throw new IllegalArgumentException("the vector are not verticals");
        }
        this.P0 = _P0;
        this.vUp = _vUp.normalize();
        this.vTo = _vTo.normalize();
        this.vRight =  _vTo.crossProduct(_vUp).normalize();
    }
    /**
     * Set the size of the view plane.
     * @param _width The width of the view plane as a double.
     * @param _height The height of the view plane as a double.
     * @return The Camera object for method chaining.
     */
    public Camera setVPSize(double _width, double _height)
    {
        width = _width;
        height = _height;
        return this;
    }

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    /**
     * Set the distance from the camera to the view plane.
     * @param distance The distance from the camera to the view plane as a double.
     * @return The Camera object for method chaining.
     */
    public Camera setVPDistance(double distance)
    {
        dis = distance;
        return this;
    }
    /**

     Constructs a ray from the camera's eye to a specific point on the view plane.

     The view plane is divided into nX by nY grid of pixels, and the ray is constructed for a specific pixel (j, i).

     @param nX The number of pixels in the width of the view plane.

     @param nY The number of pixels in the height of the view plane.

     @param j The horizontal coordinate of the pixel.

     @param i The vertical coordinate of the pixel.

     @return The constructed Ray object from the camera's eye to the specified pixel on the view plane.
     */
    public Ray constructRay(int nX, int nY, int j, int i)
    {
        Point PC = P0.add(vTo.scale(dis));
        double Rx = width / nX;
        double Ry = height / nY;

        Point PIJ = PC;

        double Xj = alignZero((j - (nX - 1) / 2d) * Rx);
        double Yj = alignZero(-(i - (nY - 1) / 2d) * Ry);

        if (!isZero(Yj)) {
            PIJ = PIJ.add(getvUp().scale(Yj));
        }

        if (!isZero(Xj)) {
            PIJ = PIJ.add(getvRight().scale(Xj));//לא עובר את השורה הזאת בכלל
        }
        //vector from camera's eye in the direction of point(i,j) in the view-plane
        Vector Vij = PIJ.subtract(P0);
        return new Ray(P0, Vij);
    }
    private void castRay(int nX, int nY, int i, int j) {
        Ray ray = constructRay(nX, nY, j, i);
        Color pixelColor = rayTracerBase.traceRay(ray);
        imageWriter.writePixel(j, i, pixelColor);
    }
    public Camera renderImage() {
        try {
            if (P0 == null)
                throw new MissingResourceException("no img", ImageWriter.class.getName(), "");
            if (vTo == null)
                throw new MissingResourceException("no img", ImageWriter.class.getName(), "");
            if (vUp == null)
                throw new MissingResourceException("no img", ImageWriter.class.getName(), "");
            if (vRight == null)
                throw new MissingResourceException("no img", ImageWriter.class.getName(), "");
            if (width == 0)
                throw new MissingResourceException("no img", ImageWriter.class.getName(), "");
            if (height == 0)
                throw new MissingResourceException("no img", ImageWriter.class.getName(), "");
            if (dis == 0)
                throw new MissingResourceException("no img", ImageWriter.class.getName(), "");
            if (imageWriter == null) {
                throw new MissingResourceException("no img", ImageWriter.class.getName(), "");
            }
            if (rayTracerBase == null) {
                throw new MissingResourceException("no img", RayTracerBase.class.getName(), "");
            }
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            //rendering the image
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    castRay(nX, nY, i, j);
                }
            }
//            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        return this;
    }
    public void printGrid(int interval, Color color) throws MissingResourceException {
        if (this.imageWriter == null)
            throw new MissingResourceException("missing Camera", ImageWriter.class.getName(), "");
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if (j % interval == 0 || i % interval == 0) {
                    imageWriter.writePixel(i, j, color);
                }
            }
        }
        imageWriter.writeToImage();
    }

        public void writeToImage() {
        if (this.imageWriter == null) // the image writer is uninitialized
            throw new MissingResourceException("missing Camera", ImageWriter.class.getName(), "");
        imageWriter.writeToImage();
    }
}


