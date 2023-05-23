package renderer;
import geometries.Plane;
import primitives.*;

import static java.lang.System.out;
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
        if (!(isZero(_up.dotProduct(_to))))
        {
            throw new IllegalArgumentException("the vector are not verticals");
        }
        this.to = _to;
        this.up = _up;
        this.right =  _to.crossProduct(_up);
        this.to.normalize();
        this.up.normalize();
        //this.right.normalize();
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
        Point Pc = place.add(to.scale(dis));

        double Rx = width / nX;
        double Ry = height / nY;

        Point Pij = Pc;

        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        if (!isZero(Yi)) {
            Pij = Pij.add(getUp().scale(Yi));
        }

        if (!isZero(Xj)) {
            Pij = Pij.add(getRight().scale(Xj));//לא עובר את השורה הזאת בכלל
        }


        //vector from camera's eye in the direction of point(i,j) in the view-plane
        Vector Vij = Pij.subtract(place);

        return new Ray(place, Vij);
    }

}


