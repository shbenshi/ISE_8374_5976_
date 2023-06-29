/**
 This class represents a camera in a renderer.
 It defines the position of the camera, the direction it is facing, and the up vector.
 It also provides methods for setting the view plane size and distance from the camera.
 Authors:
 Tzofiya David (209918374)
 Shira Ben Shimol (326065976)
 */


//tzofy do you?
package renderer;

import geometries.Plane;
import static java.lang.Math.*;

import java.util.ArrayList;
import primitives.*;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
    private RayTracerBase rayTracer;

    private boolean adaptive;
    private int threadsCount;
    private int numberOfRays = 81;


    /**
     * Renders the image using the configured ray tracer and image writer.
     *IBUD
     * @return The current Camera object for method chaining.
     * @throws UnsupportedOperationException if the ray tracer, image writer, view plane size, or distance is not set.
     * @throws IllegalArgumentException      if the number of rays is set to 0.
     */
    public Camera renderImage() {
        if (this.rayTracer == null || this.imageWriter == null
                || this.width == 0 || this.height == 0
                || this.dis == 0) //exception
            throw new UnsupportedOperationException("MissingResourcesException");
        if (numberOfRays == 0) {
            throw new IllegalArgumentException("num Of Rays can not be 0");
        }
        Color color = Color.BLACK;
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        PixelManager pixelManager = new PixelManager(nY, nX, 100l);
        if(threadsCount==0) {
            if (numberOfRays == 1) {// if there is no thread and Antialiasing
                for (int i = 0; i < nY; i++) {
                    for (int j = 0; j < nX; j++) {
                        Ray ray = constructRay(nX, nY, i, j);
                        color = castRay(ray);
                        imageWriter.writePixel(i,j, color);

                    }
                }
            } else if (!adaptive) { // if there is no thread and there is Antialiasing
                for (int i = 0; i < nY; i++) {
                    for (int j = 0; j < nX; j++) {
                        color = rayTracer.average_color_calculator(constructRayBeam(i,j, imageWriter.getNx(),  imageWriter.getNy(), 17,17, height / imageWriter.getNy(), width / imageWriter.getNx()));
                        imageWriter.writePixel(j, i, color);
                    }
                }
            } else {// if there is no thread and there is super sampling
                for (int i = 0; i < nY; i++) {
                    for (int j = 0; j < nX; j++) {
                        imageWriter.writePixel(i, j, AdaptiveSuperSampling(imageWriter.getNx(), imageWriter.getNy(), j, i, numberOfRays));
                    }
                }
            }
            return this;
        }
        else { // if there is thread
            var threads = new LinkedList<Thread>(); // list of threads
            while (threadsCount-- > 0)
                threads.add(new Thread(() -> { // add a thread with its code
                    PixelManager.Pixel pixel;
                    // allocate pixel(row,col) in loop until there are no more pixels
                    while ((pixel = pixelManager.nextPixel()) != null) {
                        // cast ray through pixel (and color it – inside castRay)
                        if (numberOfRays == 1) // if there is no Antialiasing
                            castRay(nX, nY, pixel.col(), pixel.row());
                        else if (!adaptive) {  // if there is no super sampling
                            Color color1 = rayTracer.average_color_calculator(constructRayBeam(pixel.col(),pixel.row(), imageWriter.getNx(),  imageWriter.getNy(), 17,17, height / imageWriter.getNy(), width / imageWriter.getNx()));
                            imageWriter.writePixel(
                                    pixel.row(),pixel.col(), color1);
                        }
                        else imageWriter.writePixel(pixel.col(), pixel.row(), AdaptiveSuperSampling(imageWriter.getNx(), imageWriter.getNy(), pixel.col(), pixel.row(), numberOfRays));
                    }
                }));
            // start all the threads
            for (var thread : threads) thread.start();
            // wait until all the threads have finished
            try { for (var thread : threads) thread.join(); } catch (InterruptedException ignore) {}
        }
        return this;
    }

    /**
     * Applies adaptive super-sampling technique to compute the color for a specific pixel.
     *
     * @param nX         The number of pixels in the X-axis.
     * @param nY         The number of pixels in the Y-axis.
     * @param j          The row index of the current pixel.
     * @param i          The column index of the current pixel.
     * @param numOfRays  The total number of rays used for super-sampling.
     * @return The computed color for the pixel using adaptive super-sampling.
     */
    private Color AdaptiveSuperSampling(int nX, int nY, int j, int i,  int numOfRays)  {
        // Extract necessary camera vectors and location
        Vector Vright = vRight;
        Vector Vup = vUp;
        Point cameraLoc = this.P0;
        // Calculate the number of rays in each row/column
        int numOfRaysInRowCol = (int)Math.floor(Math.sqrt(numOfRays));
        // If there is only one ray, return the result of casting a single ray
        if(numOfRaysInRowCol == 1)  return castRay(nX,nY,j,i);
        // Calculate the pixel dimensions and center point
        double rY = alignZero(height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);
        Point pIJ = getCenterOfPixel(i,j,nX,nY,rY,rX);
        // Calculate the sub-pixel sizes
        double PRy = rY/numOfRaysInRowCol;
        double PRx = rX/numOfRaysInRowCol;
        // Call the recursive AdaptiveSuperSamplingRec method to compute the color

        return rayTracer.AdaptiveSuperSamplingRec(pIJ, rX, rY, PRx, PRy,cameraLoc,Vright, Vup,null);
    }

    /**
     * Prints a grid pattern on the image, using the specified interval and color.
     *
     * @param interval  The interval between grid lines.
     * @param color     The color of the grid lines.
     * @return The Camera object for method chaining.
     * @throws UnsupportedOperationException if the imageWriter is null.
     */
    public Camera printGrid(int interval, Color color)
    {
        if (imageWriter == null) throw new UnsupportedOperationException("MissingResourcesException");

        // Draw vertical grid lines
        for (int i = 0; i < imageWriter.getNx(); i+=interval) {
            for (int j = 0; j < imageWriter.getNy(); j+=1) {
                imageWriter.writePixel(i, j, new Color(color.getColor()));
            }
        }
        // Draw horizontal grid lines
        for (int i = 0; i < imageWriter.getNy(); i+=interval) {
            for (int j = 0; j < imageWriter.getNx(); j+=1) {
                imageWriter.writePixel(j, i, new Color(color.getColor()));
            }
        }
        return this;
    }


    /**
     * Writes the image using the initialized image writer.
     *
     * @throws MissingResourceException if the image writer is uninitialized.
     */
    public void writeToImage() {
        if (this.imageWriter == null) // the image writer is uninitialized
            throw new MissingResourceException("missing Camera", ImageWriter.class.getName(), "");
        imageWriter.writeToImage();
    }
    /**
     * Constructs a Camera object with the specified parameters.
     *
     * @param _P0  The camera's location.
     * @param _vTo The direction the camera is pointing towards.
     * @param _vUp The upward direction of the camera.
     * @throws IllegalArgumentException if the direction vectors of the camera are not orthogonal.
     */
    public Camera(Point _P0, Vector _vTo, Vector _vUp) throws IllegalArgumentException {
        if (!isZero(_vTo.dotProduct(_vUp)))
            throw new IllegalArgumentException("direction vectors of camera must be orthogonal");
        this.P0 = _P0;
        this.vTo = _vTo.normalize();
        this.vUp = _vUp.normalize();
        this.vRight = _vTo.crossProduct(_vUp).normalize();
    }

    /**
     * Constructs a ray from the camera's position through the specified pixel coordinates.
     *
     * @param nX The number of pixels in the X direction.
     * @param nY The number of pixels in the Y direction.
     * @param j  The X coordinate of the pixel.
     * @param i  The Y coordinate of the pixel.
     * @return The constructed ray.
     */
    public Ray constructRay(int nX, int nY, double j, double i) {
        double ry = height / nY;
        double rx = width / nX;
        double yScale = alignZero((j - nX / 2d) * rx + rx / 2d);
        double xScale = alignZero((i - nY / 2d) * ry + ry / 2d);
        Point p = P0.add(vTo.scale(dis)); // center
        if (!isZero(yScale))
            p = p.add(vRight.scale(yScale));
        if (!isZero(xScale))
            p = p.add(vUp.scale(-1 * xScale));
        Vector v = p.subtract(P0);
        //v = v.normalize();
        return new Ray(P0, p.subtract(P0));
    }

    /**
     * Casts a ray and returns the color calculated by the ray tracer.
     *
     * @param ray The ray to be cast.
     * @return The color calculated by the ray tracer.
     */
   private Color castRay(Ray ray) {
        return rayTracer.traceRay(ray);

    }

    /**
     * Casts a ray through the specified pixel coordinates and returns the color calculated by the ray tracer.
     *
     * @param nX The number of pixels in the X direction.
     * @param nY The number of pixels in the Y direction.
     * @param i  The X coordinate of the pixel.
     * @param j  The Y coordinate of the pixel.
     * @return The color calculated by the ray tracer.
     */
    private Color castRay(int nX, int nY, int i, int j)
    {
        return rayTracer.traceRay(constructRay(nX, nY, i, j));
    }

    /**
     * Calculates the center point of a pixel given its coordinates and dimensions.
     *
     * @param i           The X coordinate of the pixel.
     * @param j           The Y coordinate of the pixel.
     * @param nX          The number of pixels in the X direction.
     * @param nY          The number of pixels in the Y direction.
     * @param pixelHeight The height of a pixel.
     * @param pixelWidth  The width of a pixel.
     * @return The center point of the pixel.
     */
    public Point getCenterOfPixel(int i, int j, int nX,int nY,double pixelHeight,double pixelWidth)
    {
        Point center = this.P0.add(this.vTo.scale(dis));
        double yi = -(i - ((double)nY - 1) / 2) * pixelHeight;
        if (yi !=0 ) center = center.add(this.vUp.scale(yi));
        double xj = (j - ((double)nX - 1) / 2) * pixelWidth;
        if (xj !=0 ) center = center.add(this.vRight.scale(xj));
        return center;
    }
    /**
     * Constructs a beam of rays for a pixel at the specified coordinates, with a grid pattern.
     *
     * @param i            The X coordinate of the pixel.
     * @param j            The Y coordinate of the pixel.
     * @param nX           The number of pixels in the X direction.
     * @param nY           The number of pixels in the Y direction.
     * @param gridWidth    The width of the ray grid.
     * @param pixelWidth   The width of a pixel.
     * @return A list of rays forming a beam for the pixel.
     */
    private List<Ray> constructRayBeam(int i, int j, int nX, int nY, int gridWidth, int gridHighet, double pixelHighet, double pixelWidth)
    {
        List<Ray> beam = new ArrayList<>();
        Point center = getCenterOfPixel(i,j,nX,nY, pixelHighet, pixelWidth);
        for (int i1=0;i1<gridHighet;i1++)
        {
            for (int j1=0;j1<gridWidth;j1++)
            {
                beam.add(constructRayInPixel(nX, nY,j1,i1,center,gridWidth,gridHighet));
            }
        }
        return beam;
    }
    /**
     * Constructs a ray within a pixel at the specified coordinates, relative to a grid.
     *
     * @param nX          The number of pixels in the X direction.
     * @param nY          The number of pixels in the Y direction.
     * @param j           The X coordinate of the ray within the grid.
     * @param i           The Y coordinate of the ray within the grid.
     * @param center      The center point of the pixel.
     * @param gridWidth   The width of the ray grid.
     * @param gridHeight  The height of the ray grid.
     * @return The constructed ray.
     */
    private Ray constructRayInPixel(int nX, int nY, int j, int i, Point center, int gridWidth, int gridHeight) {
        Point pij = center;
        double yi = -(i - ((double) gridHeight - 1) / 2) * (height / nY) / gridHeight;
        if (yi != 0) pij = pij.add(vUp.scale(yi));
        double xj = (j - ((double) gridWidth - 1) / 2) * (width / nX) / gridWidth;
        if (xj != 0) pij = pij.add(vRight.scale(xj));
        return new Ray(P0, pij.subtract(P0));
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
    /**
     * Sets the number of rays for each pixel during rendering.
     *
     * @param numberOfRays The number of rays to be cast for each pixel.
     * @return The Camera instance with the updated number of rays.
     */
    public Camera setNumberOfRays(int numberOfRays) {
        this.numberOfRays = numberOfRays;
        return this;
    }

    /**
     * Sets the number of threads to be used for rendering.
     *
     * @param threadsCount The number of threads to be used for rendering.
     * @return The Camera instance with the updated number of threads.
     */
    public Camera setThreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
        return this;
    }
    /**
     * Sets whether adaptive super-sampling should be used during rendering.
     *
     * @param adaptive Boolean value indicating whether adaptive super-sampling should be used.
     * @return The Camera instance with the updated adaptive super-sampling setting.
     */
    public Camera setadaptive(boolean adaptive) {
        this.adaptive = adaptive;
        return this;
    }
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
    /**
     * Sets the image writer for the camera.
     *
     * @param imageWriter The image writer to be set.
     * @return The Camera object for method chaining.
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Sets the ray tracer for the camera.
     *
     * @param rayTracerBase The ray tracer to be set.
     * @return The Camera object for method chaining.
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracer = rayTracerBase;
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
}


