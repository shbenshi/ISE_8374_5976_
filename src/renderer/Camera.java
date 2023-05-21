package renderer;
import geometries.Plane;
import primitives.*;
import static primitives.Util.isZero;
public class Camera {
    private Point place;
    private Vector to;
    private Vector up;
    private Vector right;

    private double dis;
    private double height;
    private double width;

    public Point getPlace() {
        return place;
    }

    public Vector getToVec() {
        return to;
    }

    public Vector getUp() {
        return up;
    }

    public Vector getRight() {
        return right;
    }

    public double getDis() {
        return dis;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Camera(Point _place, Vector _to, Vector _up) {
        this.place = _place;
        this.to = _to;
        this.up = _up;
        Vector _right =  _to.crossProduct(_up);
        if (!(isZero(_up.dotProduct(_to))))
        {
            throw new IllegalArgumentException("the vector are not verticals");
        }
        _to.normalize();
        _up.normalize();
        _right.normalize();
    }

    public Camera setVPSize(double _width, double _height)
    {
        width = _width;
        height = _height;
        return this;
    }
    public Camera setVPDistance(double distance)
    {
        dis = distance;
        return this;
    }
    // Nx represent the number of width and lines
    public Ray constructRay(int nX, int nY, int j, int i)
    {
        return null;
    }

}


