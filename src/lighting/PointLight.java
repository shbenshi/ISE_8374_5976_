package lighting;

import primitives.Point;
import primitives.Vector;
import primitives.Color;



public class PointLight extends Light{

    private Point position;
    private double Kc = 1;
    private double Kl = 0;
    private double Kq = 0;



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
