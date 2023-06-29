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


    private Color AdaptiveSuperSampling(int nX, int nY, int j, int i,  int numOfRays)  {
        Vector Vright = vRight;
        Vector Vup = vUp;
        Point cameraLoc = this.P0;
        int numOfRaysInRowCol = (int)Math.floor(Math.sqrt(numOfRays));
        if(numOfRaysInRowCol == 1)  return castRay(nX,nY,j,i);
        double rY = alignZero(height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);
        Point pIJ = getCenterOfPixel(i,j,nX,nY,rY,rX);
        double PRy = rY/numOfRaysInRowCol;
        double PRx = rX/numOfRaysInRowCol;
        return rayTracer.AdaptiveSuperSamplingRec(pIJ, rX, rY, PRx, PRy,cameraLoc,Vright, Vup,null);
    }


    public Camera printGrid(int interval, Color color)
    {
        if (imageWriter == null) throw new UnsupportedOperationException("MissingResourcesException");

        for (int i = 0; i < imageWriter.getNx(); i+=interval) {
            for (int j = 0; j < imageWriter.getNy(); j+=1) {
                imageWriter.writePixel(i, j, new Color(color.getColor()));
            }
        }
        for (int i = 0; i < imageWriter.getNy(); i+=interval) {
            for (int j = 0; j < imageWriter.getNx(); j+=1) {
                imageWriter.writePixel(j, i, new Color(color.getColor()));
            }
        }
        return this;
    }



    public void writeToImage() {
        if (this.imageWriter == null) // the image writer is uninitialized
            throw new MissingResourceException("missing Camera", ImageWriter.class.getName(), "");
        imageWriter.writeToImage();
    }

    public Camera(Point _P0, Vector _vTo, Vector _vUp) throws IllegalArgumentException {
        if (!isZero(_vTo.dotProduct(_vUp)))
            throw new IllegalArgumentException("direction vectors of camera must be orthogonal");
        this.P0 = _P0;
        this.vTo = _vTo.normalize();
        this.vUp = _vUp.normalize();
        this.vRight = _vTo.crossProduct(_vUp).normalize();
    }
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

   private Color castRay(Ray ray) {
        return rayTracer.traceRay(ray);

    }
    private Color castRay(int nX, int nY, int i, int j)
    {
        return rayTracer.traceRay(constructRay(nX, nY, i, j));
    }
    public Point getCenterOfPixel(int i, int j, int nX,int nY,double pixelHeight,double pixelWidth)
    {
        Point center = this.P0.add(this.vTo.scale(dis));
        double yi = -(i - ((double)nY - 1) / 2) * pixelHeight;
        if (yi !=0 ) center = center.add(this.vUp.scale(yi));
        double xj = (j - ((double)nX - 1) / 2) * pixelWidth;
        if (xj !=0 ) center = center.add(this.vRight.scale(xj));
        return center;
    }
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
    public Camera setNumberOfRays(int numberOfRays) {
        this.numberOfRays = numberOfRays;
        return this;
    }

    public Camera setThreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
        return this;
    }
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


