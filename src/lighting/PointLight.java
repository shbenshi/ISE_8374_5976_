package lighting;

import primitives.Point;
import primitives.Vector;
import primitives.Color;



public class PointLight extends Light implements LightSource{

    private Point position;
    private double Kc = 1;
    private double Kl = 0;
    private double Kq = 0;
    @Override
    public double getDistance(Point point) {
        return point.distance(this.position);
    }
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();

    }
    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        double denominator = Kc + Kl * d + Kq * d * d;
        return getIntensity().reduce(denominator);

    }
    public PointLight setKq(double kq) {
        this.Kq = kq;
        return this;
    }

    public PointLight setKl(double kl) {
        this.Kl = kl;
        return this;
    }

    public PointLight setKc(double kc) {
        this.Kc = kc;
        return this;
    }

    public PointLight(Color intensity,Point position)
    {
        super(intensity);
        this.position = position;
    }


}
