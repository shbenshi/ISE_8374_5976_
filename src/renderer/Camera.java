package renderer;
import geometries.Plane;
import primitives.*;

import static primitives.Util.isZero;
public class Camera {
    // fileds
    private Point place;
    private Vector to;
    private Vector up;
    private Vector right;

    private double dis;
    private double height;
    private double width;

    /**
     * Get the position of the camera.
     * @return The position of the camera as a Point.
     */
    public Point getPlace() {
        return place;
    }
    /**
     * Get the direction vector of the camera.
     * @return The direction vector of the camera as a Vector.
     */
    public Vector getToVec() {
        return to;
    }
    /**
     * Get the up vector of the camera.
     * @return The up vector of the camera as a Vector.
     */
    public Vector getUp() {
        return up;
    }
    /**
     * Get the right vector of the camera.
     * @return The right vector of the camera as a Vector.
     */
    public Vector getRight() {
        return right;
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
     * @param _place The position of the camera as a Point.
     * @param _to The direction vector of the camera as a Vector.
     * @param _up The up vector of the camera as a Vector.
     * @throws IllegalArgumentException if the direction vector and up vector are not perpendicular.
     */
    public Camera(Point _place, Vector _to, Vector _up) {
     this.place = _place;
        if (!(isZero(_up.dotProduct(_to))))
        {
            throw new IllegalArgumentException("the vector are not verticals");
        }
        this.to = _to;
        this.up = _up;
        this.right =  _to.crossProduct(_up);
        this.to.normalize();
        this.up.normalize();
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
        Point PC = place.add(to.scale(dis));

        double Rx = width / nX;
        double Ry = height / nY;

        Point PIJ = PC;

        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yj = -(i - (nY - 1) / 2d) * Ry;

        if (!isZero(Yj)) {
            PIJ = PIJ.add(getUp().scale(Yj));
        }

        if (!isZero(Xj)) {
            PIJ = PIJ.add(getRight().scale(Xj));//לא עובר את השורה הזאת בכלל
        }
        //vector from camera's eye in the direction of point(i,j) in the view-plane
        Vector Vij = PIJ.subtract(place);
        return new Ray(place, Vij);
    }

}


