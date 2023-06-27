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
import java.util.ArrayList;
import primitives.*;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static java.lang.Math.*;
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
    private int numberOfRays =1;

    public Camera renderImage() {
        if (this.rayTracer == null || this.imageWriter == null || this.width == 0 || this.height == 0 || this.dis == 0)
            throw new UnsupportedOperationException("MissingResourcesException");
        if (numberOfRays == 0) {
            throw new IllegalArgumentException("num Of Rays can not be 0");
        }

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        PixelManager pixelManager = new PixelManager(nY, nX, 100l);
        if(threadsCount==0) {
            if (numberOfRays == 1) {
                for (int i = 0; i < nY; i++) {
                    for (int j = 0; j < nX; j++) {
                        castRay(nX, nY, j, i);
                    }
                }
            } else if (!adaptive) {//Anti-aliasing* improve is on
                for (int i = 0; i < nY; i++) {
                    for (int j = 0; j < nX; j++) {
                        Color color = rayTracer.average_color_calculator(constructRayBeam(j,i, imageWriter.getNx(),  imageWriter.getNy(), 17,17, height / imageWriter.getNy(), width / imageWriter.getNx()));
                        imageWriter.writePixel(i, j, color);
                    }
                }
            } else {//Adaptive super sampling improve is on
                for (int i = 0; i < nY; i++) {
                    for (int j = 0; j < nX; j++) {
                        imageWriter.writePixel(j, i, AdaptiveSuperSampling(imageWriter.getNx(), imageWriter.getNy(), j, i, numberOfRays));
                    }
                }
            }
            return this;
        }
        else { // see further... option 2
            var threads = new LinkedList<Thread>(); // list of threads
            while (threadsCount-- > 0) // add appropriate number of threads
                threads.add(new Thread(() -> { // add a thread with its code
                    PixelManager.Pixel pixel; // current pixel(row,col)
                    // allocate pixel(row,col) in loop until there are no more pixels
                    while ((pixel = pixelManager.nextPixel()) != null) {
                        // cast ray through pixel (and color it – inside castRay)
                        if (numberOfRays == 1) castRay(nX, nY, pixel.col(), pixel.row());
                        else if (!adaptive) {
                            Color color = rayTracer.traceBeamRay(constructRayBeam(pixel.col(),pixel.row(), imageWriter.getNx(),  imageWriter.getNy(), 17,17, height / imageWriter.getNy(), width / imageWriter.getNx()));
                            imageWriter.writePixel(pixel.col(),pixel.row(), color);
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
        this.P0 = _P0;
        if (!isZero(_vTo.dotProduct(_vUp)))
            throw new IllegalArgumentException("direction vectors of camera must be orthogonal");
        this.vTo = _vTo.normalize();
        this.vUp = _vUp.normalize();
        this.vRight = _vTo.crossProduct(_vUp).normalize();
    }
    public Ray constructRay(int nX, int nY, double j, double i) {
        Point Pc = this.P0.add(vTo.scale(this.dis));
        double Ry = this.height / nY;
        double Rx = this.width / nX;
        double Yi = -(i - (double) (nY - 1) / 2) * Ry;
        double Xj = (j - (double) (nX - 1) / 2) * Rx;
        Point Pij = Pc;
        if (Xj != 0) Pij = Pij.add(vRight.scale(Xj));
        if (Yi != 0) Pij = Pij.add(vUp.scale(Yi));
        //Point Pij=Pc.add((vRight.scale(Xj)).add(vUp.scale(Yi)));
        Vector Vij = Pij.subtract(this.P0);
        return new Ray(this.P0, Vij);
    }

    private Color castRay(Ray ray) {
        return rayTracer.traceRay(ray);

    }
    private Color castRay(int nX, int nY, int i, int j) {
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
    public Camera antiAliasingOn(int multiRaysNum, boolean adaptive) {
        if (multiRaysNum < 1) {
            throw new IllegalArgumentException("number of rays through pixels must be positive");
        }
        this.numberOfRays = multiRaysNum;
        //this.adaptiveSuperSampling=adaptive;
        return this;
    }

    public Camera antiAliasingOff() {
        this.numberOfRays = 1;
        return this;
    }

    /*public List<Ray> constructRaySuperSampling(int nX, int nY, int j, int i) {
        List<Ray> result = new LinkedList<Ray>();
        //view plane center
        Point Pc = P0.add(vTo.scale(dis));
        double pixelHeight = (double) height / nY;
        double pixelWidth = (double) width / nX;
        double cellHeight = (double) pixelHeight / antiAliasingNumRays;
        double cellWidth = (double) pixelWidth / antiAliasingNumRays;

        Point Pij = Pc;
        Point point;
        //how to move from the center of the view plane.
        double Yi = -(0.5 + i - (nY - 1) / 2d) * pixelHeight + 0.5 * cellHeight;
        double Xj = (-0.5 + j - (nX - 1) / 2d) * pixelWidth + 0.5 * cellWidth;
        if (Xj != 0)
            Pij = Pij.add(vRight.scale(Xj));
        if (Yi != 0)
            Pij = Pij.add(vUp.scale(Yi));
        for (int k = 0; k < antiAliasingNumRays; k++) {
            for (int l = 0; l < antiAliasingNumRays; l++) {
                point = Pij;
                if (k != 0) point = point.add(vRight.scale(cellWidth * k));
                if (l != 0) point = point.add(vUp.scale(cellHeight * l));
                result.add(new Ray(P0, point.subtract(P0)));
            }
        }
        return result;
    }*/

   /* public Color pixelColorASS(int nX, int nY, int j, int i) {
        //view plane center
        Point Pc = P0.add(vTo.scale(dis));
        double pixelHeight = (double) height / nY;
        double pixelWidth = (double) width / nX;
        double cellHeight = (double) pixelHeight / antiAliasingNumRays;
        double cellWidth = (double) pixelWidth / antiAliasingNumRays;

        //pixel[i,j] center
        Point Pij = Pc;
        Point point;
        double Yi = -(0.5 + i - (nY - 1) / 2d) * pixelHeight + 0.5 * cellHeight; //how to move from the center of the view plane.
        double Xj = (-0.5 + j - (nX - 1) / 2d) * pixelWidth + 0.5 * cellWidth;
        if (Xj != 0)
            Pij = Pij.add(vRight.scale(Xj));
        if (Yi != 0)
            Pij = Pij.add(vUp.scale(Yi));
        List<Color> colors = List.of(  //find the colors of the corners of the pixel
                castRay(new Ray(this.P0, Pij.subtract(this.P0))),
                castRay(new Ray(this.P0, Pij.add(vUp.scale(pixelHeight)).subtract(this.P0))),
                castRay(new Ray(this.P0, Pij.add(vUp.scale(pixelHeight)).add(vRight.scale(pixelWidth)).subtract(this.P0))),
                castRay(new Ray(this.P0, Pij.add(vRight.scale(pixelWidth)).subtract(this.P0))));
        int recursionLevel = (int) (Math.log(antiAliasingNumRays - 1) / Math.log(2)); // = log2(antiAliasingNumRays-1)
        return recursiveConstructRay(Pij, colors, pixelHeight, pixelWidth, vUp, vRight, recursionLevel);//***
    }
*/
    /*public Color recursiveConstructRay(Point corner, List<Color> c, double height, double width, Vector vUp, Vector vRight, int level) {
        //if the colors are very similar return this color.
        if (c.get(0).equals(c.get(1)) && c.get(0).equals(c.get(2)) && c.get(0).equals(c.get(3)))
            return c.get(0);
        //stop the recursion.
        if (level <= 0) {
            return c.get(0).add(c.get(1)).add(c.get(2)).add(c.get(3)).reduce(4);
        }
        // 01 if the point (and the color) between the point p0 and p1 (for example).
        //Finds 3 internal points to be sent to the recursive calls.
        Point p01 = corner.add(vUp.scale(height / 2));
        Point p31 = corner.add(vRight.scale(width / 2));
        Point pCenter = corner.add(vUp.scale(height / 2)).add(vRight.scale(width / 2));
        //find the colors of all the internal points.
        Color c01 = castRay(new Ray(this.P0, p01.subtract(this.P0)));
        Color c12 = castRay(new Ray(this.P0, corner.add(vUp.scale(height)).add(vRight.scale(width / 2)).subtract(this.P0)));
        Color c23 = castRay(new Ray(this.P0, corner.add(vUp.scale(height / 2)).add(vRight.scale(width)).subtract(this.P0)));
        Color c31 = castRay(new Ray(this.P0, p31.subtract(this.P0)));
        Color cCenter = castRay(new Ray(this.P0, pCenter.subtract(this.P0)));
        //call the recursions and calculate the average.
        return recursiveConstructRay(corner, List.of(c.get(0), c01, cCenter, c31), height / 2, width / 2, vUp, vRight, level - 1)
                .add(recursiveConstructRay(p01, List.of(c01, c.get(1), c12, cCenter), height / 2, width / 2, vUp, vRight, level - 1))
                .add(recursiveConstructRay(pCenter, List.of(cCenter, c12, c.get(2), c23), height / 2, width / 2, vUp, vRight, level - 1))
                .add(recursiveConstructRay(p31, List.of(c31, cCenter, c23, c.get(3)), height / 2, width / 2, vUp, vRight, level - 1))
                .reduce(4);
    }

*/

    /*public void renderImage() {
        //check that all the fields hava a value.
        if (P0 == null || vTo == null || vUp == null || vRight == null ||
                height == 0 || width == 0 || dis == 0 ||
                imageWriter == null || rayTracer == null) {
            throw new MissingResourceException
                    ("no img. can't render", "", "");
        }
        int printInterval=60;
        int Nx=imageWriter.getNx(), Ny=imageWriter.getNy();
        Pixel.initialize(Ny, Nx, printInterval);
        while (threadsCount-- > 0) {
            new Thread(() -> {
                for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone()) {
                    Color color;
                    if (antiAliasingNumRays == 1) { //if the improvement "anti aliasing" is off- call the appropriate function.
                        Ray ray = constructRay(Nx, Ny, pixel.col, pixel.row);
                        color = castRay(ray);
                    } else if (adaptiveSuperSampling) { //if the improvement "adaptive super sampling" is on- call the appropriate function
                        color = pixelColorASS(Nx, Ny, pixel.col, pixel.row);
                    } else { //if the improvement "anti aliasing" is on (and not adaptive)- call the appropriate function for getting the rays and calculate the average of the colors.
                        List<Ray> rays = constructRaySuperSampling(Nx, Ny, pixel.col, pixel.row);
                        color = Color.BLACK;
                        for (Ray ray :
                                rays) {
                            color = color.add(castRay(ray));
                        }
                        color = color.reduce(rays.size());
                    }
                    imageWriter.writePixel(pixel.col, pixel.row, color);
                    Pixel.pixelDone();
                    Pixel.printPixel();
                }
                //castRay(imageWriter.getNx(), imageWriter.getNy(), pixel.col, pixel.row);
            }).start();
        }
        Pixel.waitToFinish();
    }*/



/*
    public Camera renderImage() {
        if (this.rayTracer == null || this.imageWriter == null || this.width == 0 || this.height == 0 || this.dis == 0)
            throw new UnsupportedOperationException("MissingResourcesException");
        if (antiAliasingNumRays == 0) {
            throw new IllegalArgumentException("no img. cant render");
        }

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        PixelManager pixelManager = new PixelManager(nY, nX, 100l);
        if(threadsCount==0) {
            if (antiAliasingNumRays == 1) {
                for (int i = 0; i < nY; i++) {
                    for (int j = 0; j < nX; j++) {
                        castRay(nX, nY, j, i);
                    }
                }
            } else if (!adaptiveSuperSampling) {//Anti-aliasing* improve is on
                for (int i = 0; i < nY; i++) {
                    for (int j = 0; j < nX; j++) {
                        Color color = rayTracer.traceBeamRay(constructRayBeam(j,i, imageWriter.getNx(),  imageWriter.getNy(), 17,17, height / imageWriter.getNy(), width / imageWriter.getNx()));
                        imageWriter.writePixel(i, j, color);
                    }
                }
            } else {//Adaptive super sampling improve is on
                for (int i = 0; i < nY; i++) {
                    for (int j = 0; j < nX; j++) {
                        imageWriter.writePixel(j, i, AdaptiveSuperSampling(imageWriter.getNx(), imageWriter.getNy(), j, i, antiAliasingNumRays));
                    }
                }
            }
            return this;
        }
        else { // see further... option 2
            var threads = new LinkedList<Thread>(); // list of threads
            while (threadsCount-- > 0) // add appropriate number of threads
                threads.add(new Thread(() -> { // add a thread with its code
                    PixelManager.Pixel pixel; // current pixel(row,col)
                    // allocate pixel(row,col) in loop until there are no more pixels
                    while ((pixel = pixelManager.nextPixel()) != null) {
                        // cast ray through pixel (and color it – inside castRay)
                        if (antiAliasingNumRays == 1) castRay(nX, nY, pixel.col(), pixel.row());
                        else if (!adaptiveSuperSampling) {
                            Color color = rayTracer.traceBeamRay(constructRayBeam(pixel.col(),pixel.row(), imageWriter.getNx(),  imageWriter.getNy(), 17,17, height / imageWriter.getNy(), width / imageWriter.getNx()));
                            imageWriter.writePixel(pixel.col(),pixel.row(), color);
                        }
                        else imageWriter.writePixel(pixel.col(), pixel.row(), AdaptiveSuperSampling(imageWriter.getNx(), imageWriter.getNy(), pixel.col(), pixel.row(), antiAliasingNumRays));
                    }
                }));
            // start all the threads
            for (var thread : threads) thread.start();
            // wait until all the threads have finished
            try { for (var thread : threads) thread.join(); } catch (InterruptedException ignore) {}
        }
        return this;
    }*/
















  /*  public Point getCenterOfPixel(int i, int j, int nX,int nY,double pixelHeight,double pixelWidth)
    {
        Point center = this.P0.add(this.vTo.scale(dis));
        double yi = -(i - ((double)nY - 1) / 2) * pixelHeight;
        if (yi !=0 ) center = center.add(this.vUp.scale(yi));
        double xj = (j - ((double)nX - 1) / 2) * pixelWidth;
        if (xj !=0 ) center = center.add(this.vRight.scale(xj));
        return center;
    }*/

   /* private Ray constructRayInPixel(int nX, int nY, int j, int i, Point center, int gridWidth, int gridHeight) {
        Point pij = center;
        double yi = -(i - ((double) gridHeight - 1) / 2) * (height / nY) / gridHeight;
        if (yi != 0) pij = pij.add(vUp.scale(yi));
        double xj = (j - ((double) gridWidth - 1) / 2) * (width / nX) / gridWidth;
        if (xj != 0) pij = pij.add(vRight.scale(xj));
        return new Ray(P0, pij.subtract(P0));
    }*/

    /*private List<Ray> constructRayBeam(int i, int j, int nX, int nY, int gridWidth, int gridHighet, double pixelHighet, double pixelWidth)
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
    }*/
/*    private void castRay(int nX, int nY, int i, int j){
        Ray ray = constructRay(nX, nY, j, i);
        Color pixelColor = rayTracer.traceRay(ray);
        imageWriter.writePixel(j, i, pixelColor);
    }*/








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



    /*private Color AdaptiveSuperSampling(int nX, int nY, int j, int i,  int numOfRays)  {
        Vector Vright = vRight;
        Vector Vup = vUp;
        Point cameraLoc = this.P0;
        int numOfRaysInRowCol = (int)Math.floor(Math.sqrt(numOfRays));
        if(numOfRaysInRowCol == 1)
            return castRay(nX,nY,j,i);
        double rY = alignZero(height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);
        Point pIJ = getCenterOfPixel(i,j,nX,nY,rY,rX);
        double PRy = rY/numOfRaysInRowCol;
        double PRx = rX/numOfRaysInRowCol;
        return rayTracer.AdaptiveSuperSamplingRec(pIJ, rX, rY, PRx, PRy,cameraLoc,Vright, Vup,null);
    }
*/
/*        public Ray constructRay(int nX, int nY, int j, int i)
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
    }*/




/*    public Camera renderImage(){
        try {
            // Check if all required resources are available
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

            // Rendering the image
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    castRay(nX, nY, i, j);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        return this;
    }*/

    /**
     * Prints a grid pattern on the image using the specified interval and color.
     *
     * @ interval The interval between grid lines.
     * @ color    The color of the grid lines.
     * @ MissingResourceException If the image writer is missing.
     */
    /*public void printGrid(int interval, Color color) throws MissingResourceException {
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
    }*/







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


